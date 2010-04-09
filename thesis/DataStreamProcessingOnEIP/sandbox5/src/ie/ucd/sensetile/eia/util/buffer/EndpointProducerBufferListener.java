package ie.ucd.sensetile.eia.util.buffer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import org.apache.camel.ProducerTemplate;

public class EndpointProducerBufferListener implements BufferDataListener {
	
	private ProducerTemplate producer = null;
	private String endpoint = null;
	
	public EndpointProducerBufferListener(String endpoint, ProducerTemplate producer) {
		this.producer = producer;
		this.endpoint = endpoint;
	}
	
	@Override
	public void processBufferData(Buffer buffer) {
		CompositeDataPacket cdp = new CompositeDataPacket();
		cdp.setPrimaryStream(buffer.getData());
				
		if (buffer instanceof CompositeDataBuffer) {
			CompositeDataBuffer cb = (CompositeDataBuffer)buffer;
			cdp.setSecondaryStreams(cb.getBufferCompositeData());
		}
		sendMessage(cdp);
		buffer.reset();
	}
	
	protected void sendMessage(CompositeDataPacket cdp) {
		producer.sendBody(endpoint, cdp);
	}
}
