package ie.ucd.sensetile.eia.component.history;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MemoryBackedHistory implements History, Processor {
	
	private long windowSize = 0;
	
	Map<Long, List<CompositeDataPacket>> data = null;
	
	Timer purgeTimer = null;
	long purgeInterval = 1000;
	
	Lock dataLock = new ReentrantLock();
	
	public MemoryBackedHistory(HistoryConfig cfg) {
		this.windowSize = cfg.getWindowSize();
		
		SortedMap<Long, List<CompositeDataPacket>> map = new TreeMap<Long, List<CompositeDataPacket>>();
		data = Collections.synchronizedMap(map);
		
		setupPurger();
	}
	
	protected void setupPurger() {
		purgeTimer = new Timer();
		purgeTimer.schedule(new PurgerTask(), new Date(), purgeInterval);
	}
	
	@Override
	public void process(Exchange e) {
		CompositeDataPacket p = (CompositeDataPacket)e.getIn().getBody();
		process(p);
	}
	
	protected void process(CompositeDataPacket p) {
		long ts = System.currentTimeMillis();
		dataLock.lock();
		try {
			List<CompositeDataPacket> list = data.get(ts);
			if (list == null) {
				list = new ArrayList<CompositeDataPacket>();
				data.put(ts, list);
			}
			list.add(p);
		} finally {
			dataLock.unlock();
		}
	}
	
	@Override
	public List<CompositeDataPacket> getHistory(long from, long to) {
		List<CompositeDataPacket> result = new ArrayList<CompositeDataPacket>();
		dataLock.lock();
		try {
			Collection<Long> keys = data.keySet();
			for (Long t : keys) {
				if (t >= from && t <= to) {
					List<CompositeDataPacket> list = data.get(t);
					result.addAll(list);
				}
			}
		} finally {
			dataLock.unlock();
		}
		return result;
	}
	
	@Override
	public List<CompositeDataPacket> getHistory(long time) {
		long ts = System.currentTimeMillis() - time;
		List<CompositeDataPacket> result = new ArrayList<CompositeDataPacket>();
		dataLock.lock();
		try {
			Collection<Long> keys = data.keySet();
			for (Long t : keys) {
				if (t > ts) {
					List<CompositeDataPacket> list = data.get(t);
					result.addAll(list);
				}
			}
		} finally {
			dataLock.unlock();
		}
		return result;
	}
	
	class PurgerTask extends TimerTask {
		
		public void run() {
			long ts = System.currentTimeMillis() - windowSize;
			dataLock.lock();
			try {
				
				List<Long> toBeDeleted = new ArrayList<Long>();
				
				// Depends on the natural sorting of the keys, from lowest to highest
				Collection<Long> keys = data.keySet();
				for (Long t : keys) {
					if (t < ts) {
						toBeDeleted.add(t);
					} else {
						break;
					}
				}
				for (Long l : toBeDeleted) {
					data.remove(l);
				}
			} finally {
				dataLock.unlock();
				System.gc();
			}
		}
	}
}
