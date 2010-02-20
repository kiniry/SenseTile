package ie.ucd.sensetile.eia.component.demultiplexer2;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import java.util.ArrayList;
import java.util.List;


public class OutputSynchronizer {
	
	private ChannelProcessor primaryProcessor = null;
	private List<ChannelProcessor> secondaryProcessors = null;
	private List<CompositeBuffer> syncBuffers = null;
	
	public OutputSynchronizer(int primaryBufferSize, int syncBufferSize, int[] secondaryChannels, int [] secondaryBufferSizes) {
		
		this.primaryProcessor = new ChannelProcessor(0, primaryBufferSize);
		this.secondaryProcessors = new ArrayList<ChannelProcessor>(secondaryChannels.length);
		this.syncBuffers = new ArrayList<CompositeBuffer>(secondaryChannels.length);
		
		for (int i=0; i<secondaryBufferSizes.length; i++) {
			Integer channelId = secondaryChannels[i];
			ChannelProcessor cp = new ChannelProcessor(channelId, secondaryBufferSizes[i]);
			secondaryProcessors.add(cp);
			
			CompositeBuffer cb = new CompositeBuffer(syncBufferSize, 4);
			syncBuffers.add(cb);
		}
	}
	
	public void processPacket(CompositeDataPacket cdp) {

		long l1 = System.currentTimeMillis();
		
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
					CompositeBuffer syncBuffer = (CompositeBuffer)syncBuffers.get(j);
					
					int [] newSyncData = new int [4];
					newSyncData[0] = primaryProcessor.getCurrentBufferPacketId();
					newSyncData[1] = primaryProcessor.getCurrentBufferIndex();
					newSyncData[2] = processor.getCurrentBufferPacketId();
					newSyncData[3] = processor.getCurrentBufferIndex();
					
					syncBuffer.writeData(newSyncData);
				}
			}
		}
		
	//	System.out.println(System.currentTimeMillis()-l1);
	}
	
	class ChannelProcessor {
		
		int channelId = 0;
		int [] data = null;
		int [] syncData = null;
		int dataIndex = 0;
		
		SimpleBuffer buffer = null;

		public ChannelProcessor(int channelId, int dataBufferSize) {
			this.channelId = channelId;
			this.buffer = new SimpleBuffer(dataBufferSize);
		}
		
		public void init(int [] data, int [] syncData) {
			this.data = data;
			this.syncData = syncData;
			this.dataIndex = this.data.length;
		}
		
		public boolean handlePrimarySample(int sampleIndex) {
			boolean result = false;
			if (syncData == null) {
				result = writeSample();
			}
			else {
				for (int i=dataIndex-1; syncData[i] >= sampleIndex && i>=0; i--) {
					if (syncData[i] == sampleIndex) {
						result = writeSample();
						break;
					}
				}
			}
			return result;
		}
		
		public boolean writeSample() {
			boolean result = false;
			if (dataIndex > 0 && dataIndex <= data.length) {
				dataIndex--;
				buffer.writeData(data[dataIndex]);
				result = true;
			} 
			return result;
		}
		
		public int getCurrentPacketIndex() {
			return dataIndex;
		}
		
		public int getCurrentBufferIndex() {
			return buffer.getSampleIndex();
		}
		
		public int getCurrentBufferPacketId() {
			return buffer.getCurrentPacketId();
		}
		
		public int getCurrentPacketChannelId() {
			return channelId;
		}
	}
}

