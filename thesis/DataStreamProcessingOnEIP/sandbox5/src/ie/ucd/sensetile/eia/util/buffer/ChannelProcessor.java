package ie.ucd.sensetile.eia.util.buffer;

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

	public ChannelProcessor(int dataBufferSize, BufferDataListener bufferProcessor) {
		this.buffer = new BasicBuffer(dataBufferSize);
		this.buffer.setDataProcessor(bufferProcessor);
	}
	
	public ChannelProcessor(int channelId, int dataBufferSize, BufferDataListener bufferProcessor) {
		this(dataBufferSize, bufferProcessor);
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
			result = handleSyncData(sampleIndex);
		}
		return result;
	}
	
	protected boolean handleSyncData(int sampleIndex) {
		boolean result = false;
		// Find the equivalent sample in the sync data and write it to the buffer
		if (dataIndex > 0) {
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
