package ie.ucd.sensetile.eia.component.synchronizer;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;


public class StreamSynchronizerConfig implements CamelContextAware {

	private CamelContext ctx;
	private int outputBufferSize;
	private int inputBufferSize;
	private String [] channelIds = new String[0];
	private String outputEndpoint;
	
	public String getOutputEndpoint() {
		return outputEndpoint;
	}
	public void setOutputEndpoint(String outputEndpoint) {
		this.outputEndpoint = outputEndpoint;
	}
	public int getInputBufferSize() {
		return inputBufferSize;
	}
	public void setInputBufferSize(int inputBufferSize) {
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
	@Override
	public CamelContext getCamelContext() {
		return ctx;
	}
	@Override
	public void setCamelContext(CamelContext ctx) {
		this.ctx = ctx;
	}
} 
