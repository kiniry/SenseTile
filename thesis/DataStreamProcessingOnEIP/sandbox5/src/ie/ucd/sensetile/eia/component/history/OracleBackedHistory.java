package ie.ucd.sensetile.eia.component.history;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.camel.Exchange;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.bind.tuple.LongBinding;
import com.sleepycat.je.Cursor;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

public class OracleBackedHistory implements History {

	private long windowSize = 0;
	private Timer purgeTimer = null;
	private long purgeInterval = 5000;
	
	private String historyTableName = null;
	
	private Database myDatabase = null;
	private Environment myDbEnvironment = null;
	private StoredClassCatalog classCatalog = null;
	private EntryBinding dataBinding = null;
	
	private List<CompositeDataPacket> msBuffer = new ArrayList<CompositeDataPacket>();
	private long currentMillisecond = System.currentTimeMillis();
	
	public OracleBackedHistory(OracleHistoryConfig cfg) {
		this.windowSize = cfg.getWindowSize();
		this.historyTableName = "History" + System.currentTimeMillis();
		this.windowSize = cfg.getWindowSize();
		setupDatabase(cfg);
		setupPurgeTimer();
	}
	
	
	protected void setupDatabase(OracleHistoryConfig cfg) {
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setAllowCreate(true);
		
		System.out.println("Cache percent: " + envConfig.getCachePercent());
		System.out.println("Cache size: " + envConfig.getCacheSize());
		
		myDbEnvironment = new Environment(new File(cfg.getDbDirectory()), envConfig);

		DatabaseConfig dbConfig = new DatabaseConfig();
		dbConfig.setAllowCreate(true);
		myDatabase = myDbEnvironment.openDatabase(null, cfg.getTableName(), dbConfig);
		
		Database myClassDb = myDbEnvironment.openDatabase(null, "classDb", dbConfig);
		classCatalog = new StoredClassCatalog(myClassDb);
		
		dataBinding = new SerialBinding(classCatalog, CompositeDataPacket[].class);
	}
	
	protected void setupPurgeTimer() {
		purgeTimer = new Timer();
		purgeTimer.schedule(new PurgerTask(), new Date(), purgeInterval);
		
//		TestResults tr = new TestResults();
//		purgeTimer.schedule(tr, new Date(), 5000);
	}
	
	public void process(Exchange e) {
		CompositeDataPacket p = (CompositeDataPacket)e.getIn().getBody();
		process(p);
	}
	
	protected void process(CompositeDataPacket p) {
		long ts = System.currentTimeMillis();
		
		msBuffer.add(p);
		
		if (msBuffer.size() == 100){
		///if (ts != currentMillisecond) {
			currentMillisecond = ts;
			CompositeDataPacket[] dataToSave = new CompositeDataPacket[msBuffer.size()];
			for (int i=0; i<dataToSave.length; i++) {
				dataToSave[i] = msBuffer.get(i);
			}
			
			msBuffer.clear();
			
			DatabaseEntry theKey = new DatabaseEntry();
			DatabaseEntry theData = new DatabaseEntry();
			
			LongBinding.longToEntry(ts, theKey);
		    dataBinding.objectToEntry(dataToSave, theData);
			myDatabase.put(null, theKey, theData);
			System.out.println("Stored " + dataToSave.length + " packets in " + (System.currentTimeMillis() - currentMillisecond) + " msec");
		}
	}
	
	public List<CompositeDataPacket> getHistory(long time) {
	
		List<CompositeDataPacket> result = new ArrayList<CompositeDataPacket>();
		long limit = System.currentTimeMillis() - time;
		
		DatabaseEntry theKey = new DatabaseEntry();
		DatabaseEntry theData = new DatabaseEntry();
	    
		Cursor cursor = myDatabase.openCursor(null, null);
	    try {
			while (cursor.getNext(theKey, theData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
				long ts = LongBinding.entryToLong(theKey);
				if (ts > limit) {
					CompositeDataPacket[] loadedPackets = (CompositeDataPacket[])dataBinding.entryToObject(theData);
					for (CompositeDataPacket p : loadedPackets) {
						result.add(p);
					}
				}
			}
	    } finally {
	    	if (cursor != null) {
	    		cursor.close();
	    	}
	    }
	    
	    return result;
	}
	
	class TestResults extends TimerTask {
		@Override
		public void run() {			
			List<CompositeDataPacket> result = getHistory(30000);
			System.out.println("TestResults run(): " + result.size());	

		}
	}
	
	class PurgerTask extends TimerTask {
		
		public void run() {
			long limit = System.currentTimeMillis() - windowSize;
			
			DatabaseEntry theKey = new DatabaseEntry();
			DatabaseEntry theData = new DatabaseEntry();
		    
			Cursor cursor = myDatabase.openCursor(null, null);
		    try {
		    	
		    	int i=0;
		    	long ts = LongBinding.entryToLong(theKey);
				while (cursor.getNext(theKey, theData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
					if (ts < limit) {
						cursor.delete();
						i++;
					}
				}
				System.out.println("Cleared : " + i + " rows");
		    } finally {
		    	if (cursor != null) {
		    		cursor.close();
		    	}
		    }
		}
	}
}
