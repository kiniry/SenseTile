package ie.ucd.sensetile.eia.component.history;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;
import ie.ucd.sensetile.eia.util.db.DerbyDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.camel.Exchange;

public class DBBackedHistory implements History {

	private DerbyDatabase db = null;
	private long windowSize = 0;
	private Timer purgeTimer = null;
	private long purgeInterval = 5000;
	
	private String historyTableName = null;
	
	private Connection insertConn = null;
	private PreparedStatement insertStmt = null;
	private Connection queryConn = null;
	private PreparedStatement queryStmt = null;
	
	
	private List<CompositeDataPacket> buffer = new ArrayList<CompositeDataPacket>();
	
	private long lastTS = System.currentTimeMillis();
	private long lastSeq = 0;
	
	public DBBackedHistory(DBHistoryConfig cfg) {
		this.db = cfg.getDb();
		this.windowSize = cfg.getWindowSize();
		this.historyTableName = "History" + System.currentTimeMillis();
		this.windowSize = cfg.getWindowSize();
		setupTables();
		setupPurgeTimer();
	}
	
	
	protected void setupTables() {
		try {
			Connection conn = db.getNewConnection();
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			stmt.execute("create table " + historyTableName + "(ts bigint, seq bigint, data blob(10M) )");
			conn.commit();
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			String query = "insert into " + historyTableName + " (ts, seq, data) values (?, ?, ?)";
			insertConn = db.getNewConnection();
			insertStmt = insertConn.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String query = "select data from " + historyTableName + " where ts < ?";
			queryConn = db.getNewConnection();
			queryStmt = queryConn.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void setupPurgeTimer() {
		purgeTimer = new Timer();
		Date currentTimePlusInterval = new Date(System.currentTimeMillis() + purgeInterval);
		purgeTimer.schedule(new PurgerTask(), currentTimePlusInterval, purgeInterval);
		
//		TestResults tr = new TestResults();
//		purgeTimer.schedule(tr, currentTimePlusInterval, 10000);
	}
	
	public void process(Exchange e) {
		CompositeDataPacket p = (CompositeDataPacket)e.getIn().getBody();
		process(p);
	}
	
	protected void process(CompositeDataPacket p) {
		buffer.add(p);
		if (buffer.size() >= 100) {
			List<CompositeDataPacket> dump = new ArrayList<CompositeDataPacket>();
			dump.addAll(buffer);
			buffer.clear();
			process(dump);
		}
	}
	
	public void process(List<CompositeDataPacket> p) {
		int size = p.size();
		long ts = System.currentTimeMillis();
		if (lastTS == ts) {
			lastSeq++;
		}
		lastTS = ts;
		try {
			insertStmt.setLong(1, ts);
			insertStmt.setLong(2, lastSeq);
			
			Blob blob = insertConn.createBlob();
			ObjectOutputStream oos = new ObjectOutputStream(blob.setBinaryStream(1));
			oos.writeObject(p);
			
			insertStmt.setBlob(3, blob);
			insertStmt.execute();
			
			insertConn.commit();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Wrote: " + size + " packets in one record "+ (System.currentTimeMillis() - ts) + " msec");
	}
	
	public List<CompositeDataPacket> getHistory(long time) {
		try {
			
			List<CompositeDataPacket> result = new ArrayList<CompositeDataPacket>();
			
			long cutoff = System.currentTimeMillis() - time;
			queryStmt.setLong(1, cutoff);
			ResultSet rs = queryStmt.executeQuery();
			
			while (rs.next()){ 
				Blob b = rs.getBlob(1);
				ObjectInputStream ois = new ObjectInputStream(b.getBinaryStream());
				Object o = ois.readObject();
				if (o != null) {
					List<CompositeDataPacket> packets = (List<CompositeDataPacket>)o;
					result.addAll(packets);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}
	
	class TestResults extends TimerTask {
		@Override
		public void run() {			
			System.out.println("TestResults run()");	
			List<CompositeDataPacket> result = getHistory(15000);
			System.out.println(result.size());
		}
	}
	
	class PurgerTask extends TimerTask {
		Connection conn = null;
		Statement stmt = null;
		
		PurgerTask() {
			try {
				this.conn = db.getNewConnection();
				this.stmt = this.conn.createStatement();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void run() {
			try {
				long oldTSCutoff = System.currentTimeMillis() - windowSize;	
				System.out.println("PurgeTimer run() cutoff : " + oldTSCutoff);
//				ResultSet rs = stmt.executeQuery("select count(*) from " + historyTableName);
//				if (rs.next()){
//					System.out.println("Records: "  + rs.getInt(1));
//				}
				stmt.execute("delete from " + historyTableName + " where ts < " + oldTSCutoff);
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
