package ie.ucd.sensetile.eia.component.history;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.camel.Exchange;

public class FileBackedHistory implements History {
	
	private File historyStore;
	private long windowSize;

	private List<CompositeDataPacket> buffer = new ArrayList<CompositeDataPacket>();
	
	ReentrantLock fileLock = new ReentrantLock(); 
	ReentrantLock bufferLock = new ReentrantLock();
	
	public FileBackedHistory(HistoryConfig cfg) {
		this.historyStore = new File("./history");
		this.windowSize = cfg.getWindowSize();
		
		if (!historyStore.exists()) {
			historyStore.mkdir();
		}
		
		Timer t = new Timer();
		t.schedule(new Purger() , new Date(System.currentTimeMillis()+2000), 2000);
	}
	
	public void process(Exchange e) {
		CompositeDataPacket p = (CompositeDataPacket)e.getIn().getBody();
		process(p);
	}
	
	protected void process(CompositeDataPacket p) {
		p.setTimestamp(System.currentTimeMillis());
		
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
		try {
			FileOutputStream fout = new FileOutputStream(historyStore + "/" + (ts));
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oos = new ObjectOutputStream(bout);
			oos.writeObject(p);
			oos.flush();
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Wrote: " + size + " packets in one record "+ (System.currentTimeMillis() - ts) + " msec");
	}
	
	public List<CompositeDataPacket> getHistory(long from, long to) {
		fileLock.lock();

		List<CompositeDataPacket> cdpList = new ArrayList<CompositeDataPacket>();
		List<CompositeDataPacket> bufferRemnant = new ArrayList<CompositeDataPacket>();
		
		bufferLock.lock();
		try {
			for (CompositeDataPacket p : buffer) {
				long ts = p.getTimestamp(); 
				if ( ts >= from && ts <= to) {
					bufferRemnant.add(p);
				}
			}
		} finally {
			bufferLock.unlock();
		}
		
		QueryFileFilter filter = new QueryFileFilter();
		filter.from = Long.toString(from);
		filter.to = Long.toString(to);
			
		File [] files = historyStore.listFiles(filter);
		for (File f : files) {
			try {
				while (!f.canRead()) {
					Thread.sleep(50);
				}
				FileInputStream in = new FileInputStream(f);
				ObjectInputStream oin = new ObjectInputStream(in);
				Object o = oin.readObject();
				
				cdpList.addAll((List<CompositeDataPacket>)o);
				
				oin.close();
				in.close();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		fileLock.unlock();
		cdpList.addAll(bufferRemnant);
		
		return cdpList;
		
	}
	
	public List<CompositeDataPacket> getHistory(long time) {
		long ts = System.currentTimeMillis();
		return getHistory(ts-time, ts);
	}
	
	class Purger extends TimerTask {
		FilenameFilter filter = null;
		String currentCutoff = "";
		
		public Purger() {
			filter = new FilenameFilter() {
				public boolean accept(File arg0, String name) {
					if (currentCutoff.compareTo(name) > 0) {
						return true;
					} else {
						return false;
					}
				}
			};
		}
		
		public void run() {
			currentCutoff = Long.toString(System.currentTimeMillis() - windowSize);
			File [] files = historyStore.listFiles(filter);
			fileLock.lock();
			for (File f : files) {
				f.delete();
			}
			fileLock.unlock();			
		}
	}
	
	class QueryFileFilter implements FilenameFilter {
		String from = "";
		String to = "";
		
		public boolean accept(File arg0, String name) {
			if (from.compareTo(name) <= 0 && to.compareTo(name) >= 0) {
				return true;
			} else {
				return false;
			}
		}
	}
}
