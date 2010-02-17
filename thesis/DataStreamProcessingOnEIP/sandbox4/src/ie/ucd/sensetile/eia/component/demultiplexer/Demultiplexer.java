package ie.ucd.sensetile.eia.component.demultiplexer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class Demultiplexer implements Processor {
	
	List<String> mainChannelEndpoint = new ArrayList<String>();
	Map<Integer, HashSet<String>> secondaryEndpoints = new HashMap<Integer, HashSet<String>>();
	String syncEndpoint = null;
	
	ProducerTemplate producer = null;
	DefaultCamelContext ctx = null;
	
	Integer packetCounter = 0;
	
	public Demultiplexer(DefaultCamelContext ctx) {
		this.ctx = ctx;
		producer = ctx.createProducerTemplate();
	}
	
	public void addMainEndpoint(String endpoint) {
		if (!mainChannelEndpoint.contains(endpoint)) {
			mainChannelEndpoint.add(endpoint);
		}
	}
	
	public void addSecondaryEndpoint(Integer channel, String endpoint) {	
		HashSet<String> endpoints = secondaryEndpoints.get(channel);
		if (endpoints == null) {
			endpoints = new HashSet<String>();
			secondaryEndpoints.put(channel, endpoints);
		}
		endpoints.add(endpoint);
	}
	

	public void process (Exchange exchange) {
		packetCounter++;
		CompositeDataPacket packet = (CompositeDataPacket)exchange.getIn().getBody();
		processMainChannel(packet, packetCounter);
		processSecondaryChannels(packet, packetCounter);
	}
	
	protected void processMainChannel(CompositeDataPacket packet, int packetCount) {
		CompositeDataPacket mainPacket = new CompositeDataPacket();
		mainPacket.setPrimaryStream(packet.getPrimaryChannelData());
		mainPacket.setPacketCountFromSource(packetCount);
		
		for (String e : mainChannelEndpoint) {
			sendBody(e, mainPacket);
		}
	}
	
	protected void processSecondaryChannels(CompositeDataPacket packet, int packetCount) {
		
		int channelCount = packet.getSecondaryChannelCount();
		if ( channelCount > 0) {
			 
			for (int i=0; i<channelCount; i++) {
				HashSet<String> endpoints = secondaryEndpoints.get(i);
				
				if (endpoints == null || endpoints.size() == 0) {
					continue;
				} else {
					CompositeDataPacket secondaryPacket = new CompositeDataPacket();
					secondaryPacket.setPrimaryStream(packet.getSecondaryChannel(i));
					secondaryPacket.setPacketCountFromSource(packetCount);
					
					for (String e : endpoints) {
						sendBody(e, secondaryPacket);
					}
					
					processSyncData(i, packet.getSyncDataForChannel(i));
				}		
			}
		}
	}
	
	protected void processSyncData(int channel, int [] data) {
		if (syncEndpoint != null) {
			int [] newData = new int[data.length];
			for (int i=0; i<newData.length; i++) {
				newData[i] = (char) data[i];
			}
			CompositeDataPacket packet = new CompositeDataPacket();
			packet.setPacketCountFromSource(packetCounter);
			packet.setPrimaryStream(newData);
			
			sendBody(syncEndpoint, packet);
		}
	}
	
	protected void sendBody(String endpoint, Object body) {
		producer.sendBody(endpoint, body);
	}
}
