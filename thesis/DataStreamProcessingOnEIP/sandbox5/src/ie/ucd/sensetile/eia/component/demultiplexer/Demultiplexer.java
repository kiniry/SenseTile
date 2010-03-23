package ie.ucd.sensetile.eia.component.demultiplexer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;
import ie.ucd.sensetile.eia.util.buffer.BufferDataProcessor;
import ie.ucd.sensetile.eia.util.buffer.CompositeDataBuffer;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Service;
import org.apache.camel.spi.ManagementAware;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(description = "Managed Processor" )
public class Demultiplexer implements Processor, ManagementAware<Demultiplexer>, Service {

	@Override
	public Object getManagedObject(Demultiplexer arg0) {
		return this;
	}
	
	private ChannelProcessor primaryProcessor = null;
	private List<ChannelProcessor> secondaryProcessors = null;
	private List<CompositeDataBuffer> syncBuffers = null;
	
	public Demultiplexer(DemultiplexerConfig config) {
		
		int [] secondaryChannels = config.getSecondaryChannels();
		int [] secondaryBufferSizes = config.getSecondaryBufferSizes();
		
		this.primaryProcessor = new ChannelProcessor(0, config.getPrimaryBufferSize());
		this.secondaryProcessors = new ArrayList<ChannelProcessor>(secondaryChannels.length);
		this.syncBuffers = new ArrayList<CompositeDataBuffer>(secondaryChannels.length);
		
		for (int i=0; i<secondaryBufferSizes.length; i++) {
			Integer channelId = secondaryChannels[i];
			ChannelProcessor cp = new ChannelProcessor(channelId, secondaryBufferSizes[i]);
			secondaryProcessors.add(cp);
			
			CompositeDataBuffer cb = new CompositeDataBuffer(config.getSyncBufferSize(), 4);
			cb.setDataProcessor(new BufferDataProcessor());
			syncBuffers.add(cb);
		}
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		CompositeDataPacket cdp = (CompositeDataPacket)exchange.getIn().getBody();
		processPacket(cdp);
	}
	
	public void processPacket(CompositeDataPacket cdp) {
		
		int [] pData = cdp.getPrimaryChannelData();
		
		primaryProcessor.init(cdp.getPrimaryChannelData(), null);
		
		for (ChannelProcessor processor : secondaryProcessors) {
			Integer channel = processor.getCurrentPacketChannelId();
			processor.init(cdp.getSecondaryChannel(channel), cdp.getSyncDataForChannel(channel)); 
		}
		
		for (int i=pData.length - 1; i >= 0; i--) {
			primaryProcessor.handlePrimarySample(i);
			for (int j=0; j<secondaryProcessors.size(); j++) {
				ChannelProcessor processor = (ChannelProcessor)secondaryProcessors.get(j);
				if (processor.handlePrimarySample(i)) {
					CompositeDataBuffer syncBuffer = (CompositeDataBuffer)syncBuffers.get(j);
					
					int [] newSyncData = new int [4];
					newSyncData[0] = primaryProcessor.getCurrentBufferPacketId();
					newSyncData[1] = primaryProcessor.getCurrentBufferIndex();
					newSyncData[2] = processor.getCurrentBufferPacketId();
					newSyncData[3] = processor.getCurrentBufferIndex();
					
					syncBuffer.writeData(newSyncData);
				}
			}
		}
	}

	@Override
	public void start() throws Exception {
		System.out.println("Called start() on demultiplexer");
	}

	@Override
	public void stop() throws Exception {
		System.out.println("Called stop() on demultiplexer");
	}

}
