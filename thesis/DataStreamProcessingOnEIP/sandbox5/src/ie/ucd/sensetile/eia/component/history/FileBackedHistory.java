package ie.ucd.sensetile.eia.component.history;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.camel.Exchange;

public class FileBackedHistory implements History {
	
	private File historyStore;
	private long windowSize;
	
	public FileBackedHistory(HistoryConfig cfg) {
		this.historyStore = new File("/history");
		this.windowSize = cfg.getWindowSize();
		
		if (!historyStore.exists()) {
			try {
				historyStore.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	@Override
	public List<CompositeDataPacket> getHistory(long time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void process(Exchange e) throws Exception {
		CompositeDataPacket cdp = (CompositeDataPacket)e.getIn().getBody();
		writeFile(cdp, Long.toString(System.currentTimeMillis()));
	}
	
	protected void writeFile(Object o, String key) {
		try {
			FileOutputStream fout = new FileOutputStream(historyStore.getPath() + key);
			
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(o);
			
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
