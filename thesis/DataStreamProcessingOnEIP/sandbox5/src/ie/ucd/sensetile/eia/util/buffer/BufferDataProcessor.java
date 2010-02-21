package ie.ucd.sensetile.eia.util.buffer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

public class BufferDataProcessor {
	public void processBufferData(BasicBuffer buffer){
		
		CompositeDataPacket cdp = new CompositeDataPacket();
		cdp.setPrimaryStream(buffer.getBufferData());
				
		if (buffer instanceof CompositeDataBuffer) {
			CompositeDataBuffer cb = (CompositeDataBuffer)buffer;
			cdp.setSecondaryStreams(cb.getBufferCompositeData());
		}
		buffer.reset();
		//System.out.println("Sending: " + cdp);
	}
}
