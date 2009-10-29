package sensetile.eia.camel.prototype1.processor;

import java.util.logging.Logger;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;

import sensetile.eia.camel.prototype1.data.SimpleDataPacket;
import sensetile.eia.camel.prototype1.data.StreamDataContainer;

public class PacketProcessor {
	
	public enum TYPE {LOW, HIGH};
	
	private int [][] buffer;
	private int index = 0;
	private TYPE type = TYPE.LOW;
	
	CamelContext ctx = null;
	
	private ProducerTemplate producer = null;
	
	private static final Logger LOG = Logger.getLogger(PacketProcessor.class.getPackage().getName());
    
	public PacketProcessor(int bufferSize, TYPE type, CamelContext ctx) {
		buffer = new int[bufferSize][];
		this.type = type;
		this.ctx = ctx;
		
		producer = ctx.createProducerTemplate();
		
	}
	    
    public void process(Exchange e) {
    	StreamDataContainer data = (StreamDataContainer)e.getIn().getBody();

    	if (index == buffer.length) {
    		emptyBuffer();
    	}
    	
		switch (type) {
		case HIGH:
			buffer[index++] = data.low; break;
		case LOW:
			buffer[index++] = data.high; break;
    	}     		
    }
    
    protected void emptyBuffer() {
    	SimpleDataPacket sdp = new SimpleDataPacket();
    	
    	sdp.setPayload(buffer);
    	
    	index = 0;

    	String endpoint = "";
    	if (type == TYPE.LOW) {
    		endpoint = "direct:sendLow";
    	} else if (type == TYPE.HIGH) {
    		endpoint = "direct:sendHigh";
    	}

		producer.sendBody(endpoint, sdp);
    }


    
    public void printState(String msg){
    	System.out.println("PacketProcessor: " + type + ":" + " index:" + index + ": msg:" + msg);
    }
}