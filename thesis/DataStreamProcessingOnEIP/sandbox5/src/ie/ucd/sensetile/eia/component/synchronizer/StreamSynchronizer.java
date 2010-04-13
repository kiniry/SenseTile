package ie.ucd.sensetile.eia.component.synchronizer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;
import ie.ucd.sensetile.eia.util.buffer.ChannelProcessor;
import ie.ucd.sensetile.eia.util.buffer.CompositeDataBuffer;
import ie.ucd.sensetile.eia.util.buffer.SyncChannelProcessor;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class StreamSynchronizer implements Processor, CamelContextAware {

	CompositeDataBuffer output = null;
	
	CamelContext ctx = null;
	
	Map<String, ChannelProcessor> buffers = new TreeMap<String, ChannelProcessor>();
	Map<String, SyncChannelProcessor> syncBuffers = new TreeMap<String, SyncChannelProcessor>();
	
	private Exchange currentExchange = null;
	
	public StreamSynchronizer(StreamSynchronizerConfig cfg) {
		int size = cfg.getInputBufferSize();
		
		for (String id : cfg.getChannelIds()) {
			ChannelProcessor cp = new ChannelProcessor(size, null);
			buffers.put(id, cp);
			
			SyncChannelProcessor scp = new SyncChannelProcessor(size, cp.getBuffer());
			syncBuffers.put(id, scp);
		}
		
		output = new CompositeDataBuffer(cfg.getOutputBufferSize(), cfg.getChannelIds().length);
	//	output.setDataProcessor(new EndpointProducerBufferListener(cfg.getOutputEndpoint(), ctx.createProducerTemplate() ));
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		currentExchange = exchange;
		CompositeDataPacket cdp = (CompositeDataPacket)exchange.getIn().getBody();
		String id = (String)exchange.getIn().getHeader("streamid");
		processPacket(cdp, id);
	}
	
	public void processPacket(CompositeDataPacket cdp, String streamID) {
		ChannelProcessor dataBuffer = buffers.get(streamID);
		ChannelProcessor syncBuffer = syncBuffers.get(streamID);
		
		if (dataBuffer == null || syncBuffer == null) {
			//
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
		for (int i=shortestSubsequence-1; i>=0; i--) {
			int [] samples = new int[segments.length];
			for (int j=0; j<segments.length; j++) {
				samples[j] = segments[j][i];
			}
			output.writeData(samples);
		}
	}

	@Override
	public CamelContext getCamelContext() {
		// TODO Auto-generated method stub
		return this.ctx;
	}

	@Override
	public void setCamelContext(CamelContext ctx) {
		this.ctx = ctx;
	}
}
