package ie.ucd.sensetile.eia.component.synchronizer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;
import ie.ucd.sensetile.eia.util.buffer.ChannelProcessor;
import ie.ucd.sensetile.eia.util.buffer.CompositeDataBuffer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
		
		syncBuffers.put("1", new ChannelProcessor(size));
		syncBuffers.put("2", new ChannelProcessor(size));
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
	
	protected boolean syncAvailableOnOtherBuffers(String currentStreamID) { 
		return true;
	}
	
	protected boolean handleSynchronizationPoint(String currentStreamID) {
		for (ChannelProcessor syncBuffer : syncBuffers.values()) {
			if (!syncBuffer.getBuffer().bufferDirty()) {
				// At least on of the sync buffers doesn't have a value so 
				return false;
			}
		}
		writeSynchronizedData();
		return true;
	}
	
	protected void writeSynchronizedData() {

		int [][] segments = new int[buffers.size()][];
	
		int shortestSubsequence = -1;
		
		Iterator<String> keys = buffers.keySet().iterator();
		
		for (int i=0; i<segments.length; i++){
			String id = keys.next();
			
			ChannelProcessor dataProcessor = buffers.get(id);
			ChannelProcessor syncProcessor = syncBuffers.get(id);
			
			int [] syncIndex = syncProcessor.getBuffer().popLast();
			int [] dataSubsequence = dataProcessor.getBuffer().subSequence(syncIndex[0]); 
		
			if (dataSubsequence.length < shortestSubsequence || shortestSubsequence == -1) {
				shortestSubsequence = dataSubsequence.length;
			}
			
			// Move all existing sync points up by the size of the 
			syncProcessor.getBuffer().shiftValues(dataSubsequence.length);
			
			segments[i] = dataSubsequence;
		}
		
		// Discarding the trailing samples due to mismatch
		for (int i=0; i<shortestSubsequence; i++) {
			int [] samples = new int[segments.length];
			output.writeData(samples);
		}
	}
}
