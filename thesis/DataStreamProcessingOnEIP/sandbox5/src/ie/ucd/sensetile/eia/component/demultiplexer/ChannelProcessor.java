package ie.ucd.sensetile.eia.component.demultiplexer;

import ie.ucd.sensetile.eia.util.buffer.BasicBuffer;

public class ChannelProcessor {
	
	int channelId = 0;
	int [] data = null;
	int [] syncData = null;
	int dataIndex = 0;
	
	BasicBuffer buffer = null;

	public ChannelProcessor(int channelId, int dataBufferSize) {
		this.channelId = channelId;
		this.buffer = new BasicBuffer(dataBufferSize);
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
