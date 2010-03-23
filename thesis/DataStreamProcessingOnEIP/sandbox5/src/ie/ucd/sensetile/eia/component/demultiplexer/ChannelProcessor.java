package ie.ucd.sensetile.eia.component.demultiplexer;

import ie.ucd.sensetile.eia.util.buffer.BasicBuffer;
import ie.ucd.sensetile.eia.util.buffer.BufferDataProcessor;

/**
 * ChannelProcessor.
 * @author damian
 *
 * This class is used to control the writing of data to a BasicBuffer.
 * 
 */
public class ChannelProcessor {
	
	int channelId = 0;
	int [] data = null;
	int [] syncData = null;
	int dataIndex = 0;
	
	BasicBuffer buffer = null;

	public ChannelProcessor(int dataBufferSize) {
		this.buffer = new BasicBuffer(dataBufferSize);
		this.buffer.setDataProcessor(new BufferDataProcessor());
	}
	
	public ChannelProcessor(int channelId, int dataBufferSize) {
		this(dataBufferSize);
		this.channelId = channelId;
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
			// Find the equivalent sample in the sync data and write it to the buffer
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
	
	public BasicBuffer getBuffer() {
		return buffer;
	}
	
	public boolean hasData() {
		return (dataIndex < data.length);
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
