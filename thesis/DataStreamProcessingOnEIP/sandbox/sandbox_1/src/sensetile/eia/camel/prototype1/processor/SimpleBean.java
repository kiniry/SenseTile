package sensetile.eia.camel.prototype1.processor;

import org.apache.camel.Exchange;

import sensetile.eia.camel.prototype1.data.SimpleDataPacket;

public class SimpleBean {

	public int processCount = 0;
	
	public int kbSize = 0;
	
	public void process(Exchange e) {
		processCount++;
		
		if (processCount%1001 == 0) {
			
			SimpleDataPacket sdp = (SimpleDataPacket)e.getIn().getBody();
			int size = sdp.getSize();
			kbSize += size;
			
			System.out.println("Processed : " + processCount + " packet size: " + size + " total size processed: " + (kbSize) + "kb");
			
			
		}
	}
	
}
