package ie.ucd.sensetile.eia.component;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class BufferedAggregator implements Processor, DataStreamAggregator {
	private HashSet<String> endpoints = new HashSet<String>();
	
	private int bufferSize  = 0;
	private int bufferIndex = 0;
	private int [] buffer = new int[0];
	
	private ProducerTemplate producer = null;
	
	private List<AggregatorListener> listeners = new ArrayList<AggregatorListener>();
	
	public BufferedAggregator(DefaultCamelContext ctx) {
		producer = ctx.createProducerTemplate();
	}
	
	public void setPacketSize(int size) {
		bufferSize = size;
		buffer = new int[size];
		resetBuffer();
	}
	
	public void addEndpoint(String endpoint) {
		endpoints.add(endpoint);
	}
	
	public void process(Exchange exchange) throws Exception {
		try {
			CompositeDataPacket packet = (CompositeDataPacket)exchange.getIn().getBody();
			int [] data = packet.getPrimaryChannelData();
			handleData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int handleData(int [] data) {
		int packetsSent = 0;
		if (data.length == 0) {
			return 0;
		}
		
		int dataIndex = 0;
		while (dataIndex < (data.length-1)) {
			while (bufferIndex < (bufferSize-1) && dataIndex < (data.length-1)) {
				buffer[bufferIndex++] = data[dataIndex++];
			}
			if (bufferIndex == (bufferSize-1)) {
				sendBuffer();
				packetsSent++;
			}
		}
		
		return packetsSent;
	}
	

	public void addListener(AggregatorListener listener) {
		this.listeners.add(listener);
	}
	
	protected void resetBuffer() {
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
	
	public int getBufferSize() {
		return bufferSize;
	}
	
	protected void notifyListeners(CompositeDataPacket packet) {
		for (AggregatorListener l : listeners) {
			l.packetSent(packet);
		}
	}
}
