package ie.ucd.sensetile.eia.component.synchronizer;

import ie.ucd.sensetile.eia.component.demultiplexer.ChannelProcessor;
import ie.ucd.sensetile.eia.data.CompositeDataPacket;
import ie.ucd.sensetile.eia.util.buffer.CompositeDataBuffer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class StreamSynchronizer implements Processor {

	CompositeDataBuffer output = new CompositeDataBuffer(8000, 2);
	
	Map<String, ChannelProcessor> buffers = new HashMap<String, ChannelProcessor>();
	Map<String, ChannelProcessor> syncBuffers = new HashMap<String, ChannelProcessor>();
	
	private Exchange currentExchange = null;
	
	public StreamSynchronizer(StreamSynchronizerConfig cfg) {
		int size = 10000;
		buffers.put("1", new ChannelProcessor(size));
		buffers.put("2", new ChannelProcessor(size));
		
		syncBuffers.put("1", new ChannelProcessor(size/2));
		syncBuffers.put("2", new ChannelProcessor(size/2));
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		currentExchange = exchange;
		CompositeDataPacket cdp = (CompositeDataPacket)exchange.getIn().getBody();
		String id = (String)exchange.getIn().getHeader("streamID");
		processPacket(cdp, id);
	}
	
	public void processPacket(CompositeDataPacket cdp, String streamID) {
		ChannelProcessor dataBuffer = buffers.get(streamID);
		ChannelProcessor syncBuffer = syncBuffers.get(streamID);
		
		if (dataBuffer == null || syncBuffer == null) {
			// Stop the message
		}
		
		int[] pData = cdp.getPrimaryChannelData(); 
		dataBuffer.init(pData, null);
		syncBuffer.init(cdp.getSecondaryChannel(0), cdp.getSyncDataForChannel(0));
		
		for (int i=pData.length - 1; i >= 0; i--) {
			dataBuffer.handlePrimarySample(i);
			if(syncBuffer.handlePrimarySample(i)) {
				
				// We just wrote a sync value to our buffer.
				handleSynchronizationPoint(streamID);
				
			}
		}
	}
	
	protected boolean handleSynchronizationPoint(String currentStreamID) {
		for (ChannelProcessor cp : syncBuffers.values()) {
			if (!cp.hasData()) {
				// At least on of the sync buffers doesn't have a value so 
				return false;
			}
		}
		
		writeSynchronizedData();
		
		return true;
	}
	
	protected void writeSynchronizedData() {
		
	}
	
}
