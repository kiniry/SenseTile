package ie.ucd.sensetile.eia.component;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import java.util.HashSet;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class BufferedAggregator implements Processor {
	HashSet<String> endpoints = new HashSet<String>();
	
	int bufferSize  = 0;
	int bufferIndex = 0;
	char [] buffer = new char[0];
	
	ProducerTemplate producer = null;
	DefaultCamelContext ctx = null;
	
	
	public BufferedAggregator(DefaultCamelContext ctx) {
		this.ctx = ctx;
		producer = ctx.createProducerTemplate();
	}
	
	public void setPacketSize(int size) {
		bufferSize = size;
		resetBuffer();
	}
	
	public void addEndpoint(String endpoint) {
		endpoints.add(endpoint);
	}
	
	public void process(Exchange exchange) throws Exception {
		CompositeDataPacket packet = (CompositeDataPacket)exchange.getIn().getBody();
		char [] data = packet.getPrimaryChannelData();
		handleData(data);	
	}
	
	protected void handleData(char [] data) {
		int dataIndex = 0;
		while (dataIndex < (data.length-1)) {
			while (bufferIndex < (bufferSize-1) && dataIndex < (data.length-1)) {
				buffer[bufferIndex++] = data[dataIndex++];
			}
			if (bufferIndex == (bufferSize-1)) {
				sendBuffer();
			}
		}
	}
	
	protected void resetBuffer() {
		buffer = new char [bufferSize];
		bufferIndex = 0;
	}
	
	protected void sendBuffer() {
		
		CompositeDataPacket packet = new CompositeDataPacket();
		packet.setPrimaryStream(buffer);
		
		for (String endpoint : endpoints) {
			producer.sendBody(endpoint, packet);
		}
		
		resetBuffer();
	}
}
