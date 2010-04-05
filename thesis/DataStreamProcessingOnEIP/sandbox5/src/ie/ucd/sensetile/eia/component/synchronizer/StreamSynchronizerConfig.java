package ie.ucd.sensetile.eia.component.synchronizer;


public class StreamSynchronizerConfig {

	private int outputBufferSize;
	private int inputBufferSize;
	private String [] channelIds;
	
	public int getInputBufferSize() {
		return inputBufferSize;
	}
	public void setInputBufferSizes(int inputBufferSize) {
		this.inputBufferSize = inputBufferSize;
	}
	public String[] getChannelIds() {
		return channelIds;
	}
	public void setChannelIds(String[] channelIds) {
		this.channelIds = channelIds;
	}
	
	public int getOutputBufferSize() {
		return outputBufferSize;
	}
	public void setOutputBufferSize(int outputBufferSize) {
		this.outputBufferSize = outputBufferSize;
	}
} 