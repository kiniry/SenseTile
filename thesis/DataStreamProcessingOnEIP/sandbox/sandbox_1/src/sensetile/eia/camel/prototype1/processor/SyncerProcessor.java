package sensetile.eia.camel.prototype1.processor;

import org.apache.camel.Exchange;

import sensetile.eia.camel.prototype1.data.StreamDataContainer;

public class SyncerProcessor {
	
	public long timestamp1 = -1;
	public long timestamp2 = -1;
	
	int i=0;
	
	public void timestamp(Exchange e) {
		e.getIn().setHeader("syncCount", i++);
		
		if (timestamp1 < 0) {
			timestamp1 = System.currentTimeMillis();
		} else {
			timestamp2 = System.currentTimeMillis();
		}
	}
	
	public void presplit(Exchange e) {
		timestamp(e);
	}
}
