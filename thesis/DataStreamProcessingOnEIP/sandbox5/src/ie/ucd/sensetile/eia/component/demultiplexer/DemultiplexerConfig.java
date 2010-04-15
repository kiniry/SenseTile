package ie.ucd.sensetile.eia.component.demultiplexer;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;

public class DemultiplexerConfig implements CamelContextAware {
	private int primaryBufferSize; 
	private int syncBufferSize; 
	private int[] secondaryChannels; 
	private int [] secondaryBufferSizes;
	private String primaryEndpoint;
	private String [] secondaryEndpoints;
	private String syncEndpoint;
	private CamelContext ctx = null;
	
	
	public String[] getSecondaryEndpoints() {
		return secondaryEndpoints;
	}
	public void setSecondaryEndpoints(String[] secondaryEndpoints) {
		this.secondaryEndpoints = secondaryEndpoints;
	}
	public CamelContext getCamelContext() {
		return ctx;
	}
	public void setCamelContext(CamelContext ctx) {
		this.ctx = ctx;
	}
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
	public String getSyncEndpoint() {
		return syncEndpoint;
	}
	public void setSyncEndpoint(String syncEndpoint) {
		this.syncEndpoint = syncEndpoint;
	}
}
