package ie.ucd.sensetile.eia.component.demultiplexer2;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class Demultiplexer2 implements Processor{
	
	OutputSynchronizer syncher = null;
	
	public Demultiplexer2(int bufSize, int syncSize, int [] secChannels, int [] secSizes) {
		syncher = new OutputSynchronizer(bufSize, syncSize, secChannels, secSizes);
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		CompositeDataPacket cdp = (CompositeDataPacket)exchange.getIn().getBody();
		syncher.processPacket(cdp);
	}
}
