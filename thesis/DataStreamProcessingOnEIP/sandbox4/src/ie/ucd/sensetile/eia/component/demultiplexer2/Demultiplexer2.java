package ie.ucd.sensetile.eia.component.demultiplexer2;

import ie.ucd.sensetile.eia.component.BufferedAggregator;
import ie.ucd.sensetile.eia.component.DataStreamAggregator;
import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class Demultiplexer2 implements Processor{
	
	Integer packetCounter = 0;
	
	Map<Integer, DataStreamAggregator> syncOutput = new HashMap<Integer,DataStreamAggregator>();
	
	OutputSynchronizer syncher = null;
	
	public Demultiplexer2(BufferedAggregator primaryAggregator, BufferedAggregator secondaryAggregator) {
		syncher = new OutputSynchronizer(primaryAggregator, secondaryAggregator);
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		CompositeDataPacket cdp = (CompositeDataPacket)exchange.getIn().getBody();
		syncher.setCurrentPacket(cdp);
	}

	
}
