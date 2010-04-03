package ie.ucd.sensetile.eia.util.buffer;

public class SyncChannelProcessor extends ChannelProcessor {

	BasicBuffer primaryBuffer = null;
	
	public SyncChannelProcessor(int dataBufferSize, BasicBuffer primaryBuffer) {
		super(dataBufferSize);
		this.primaryBuffer = primaryBuffer;
	}
	
	public SyncChannelProcessor(int channelId, int dataBufferSize, BasicBuffer primaryBuffer) {
		super(channelId, dataBufferSize);
		this.primaryBuffer = primaryBuffer;
	}
	
	public boolean writeSample() {
		boolean result = false;
		if (dataIndex > 0 && dataIndex <= data.length) {
			dataIndex--;
			
			int val = primaryBuffer.getSampleIndex();
			
			buffer.writeData(val);
			result = true;
		} 
		return result;
	}
}
