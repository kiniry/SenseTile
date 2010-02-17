package ie.ucd.sensetile.eia.component.demultiplexer2;

import ie.ucd.sensetile.eia.component.AggregatorListener;
import ie.ucd.sensetile.eia.component.DataStreamAggregator;
import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class Demultiplexer2 implements Processor{
	
	DataStreamAggregator primaryOutput = null;
	Map<Integer, DataStreamAggregator> secondaryOutput = new HashMap<Integer,DataStreamAggregator>();
	Integer packetCounter = 0;
	
	Map<Integer, DataStreamAggregator> syncOutput = new HashMap<Integer,DataStreamAggregator>();
	
	public Demultiplexer2() {
	}

	public void setPrimaryOutput(DataStreamAggregator output){ 
		this.primaryOutput = output;
	}
	
	public void setSecondaryOutput(Integer channel, DataStreamAggregator output) {
		secondaryOutput.put(channel, output);
	}
	
	public void process (Exchange exchange) {
		packetCounter++;
		CompositeDataPacket packet = (CompositeDataPacket)exchange.getIn().getBody();
		
		processMainChannel(packet, packetCounter);
		processSecondaryChannels(packet, packetCounter);
	}
	
	protected void processMainChannel(CompositeDataPacket packet, int packetCount) {
		primaryOutput.handleData(packet.getPrimaryChannelData());
	}
	
	protected void processSecondaryChannels(CompositeDataPacket packet, int packetCount) {
		
		int channelCount = packet.getSecondaryChannelCount();
		if ( channelCount > 0) {
			for (int i=0; i<channelCount; i++) {
				DataStreamAggregator output = secondaryOutput.get(i);
				if (output != null) {
					output.handleData(packet.getSecondaryChannel(i));
					// Handle syncher
				}
			}
		}
	}
}
