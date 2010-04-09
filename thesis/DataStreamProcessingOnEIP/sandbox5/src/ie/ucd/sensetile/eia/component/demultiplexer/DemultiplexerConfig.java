package ie.ucd.sensetile.eia.component.demultiplexer;

public class DemultiplexerConfig {
	private int primaryBufferSize; 
	private int syncBufferSize; 
	private int[] secondaryChannels; 
	private int [] secondaryBufferSizes;
	private String primaryEndpoint;
	private String [] secondaryEndpoints;
	private String syncEndpoint;
	
	
	public String getPrimaryEndpoint() {
		return primaryEndpoint;
	}
	public void setPrimaryEndpoint(String primaryEndpoint) {
		this.primaryEndpoint = primaryEndpoint;
	}
	
	
	public int getPrimaryBufferSize() {
		return primaryBufferSize;
	}
	public void setPrimaryBufferSize(int primaryBufferSize) {
		this.primaryBufferSize = primaryBufferSize;
	}
	public int getSyncBufferSize() {
		return syncBufferSize;
	}
	public void setSyncBufferSize(int syncBufferSize) {
		this.syncBufferSize = syncBufferSize;
	}
	public int[] getSecondaryChannels() {
		return secondaryChannels;
	}
	public void setSecondaryChannels(int[] secondaryChannels) {
		this.secondaryChannels = secondaryChannels;
	}
	public int[] getSecondaryBufferSizes() {
		return secondaryBufferSizes;
	}
	public void setSecondaryBufferSizes(int[] secondaryBufferSizes) {
		this.secondaryBufferSizes = secondaryBufferSizes;
	}
	public String[] getEndpoints() {
		return secondaryEndpoints;
	}
	public void setEndpoints(String[] secondaryEndpoints) {
		this.secondaryEndpoints = secondaryEndpoints;
	}
	public String getSyncEndpoint() {
		return syncEndpoint;
	}
	public void setSyncEndpoint(String syncEndpoint) {
		this.syncEndpoint = syncEndpoint;
	}
}
