package ie.ucd.sensetile.eia.component.demultiplexer2;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

public class BufferDataProcessor {
	public void processBufferData(SimpleBuffer buffer){
		
		CompositeDataPacket cdp = new CompositeDataPacket();
		cdp.setPrimaryStream(buffer.getBufferData());
				
		if (buffer instanceof CompositeBuffer) {
			CompositeBuffer cb = (CompositeBuffer)buffer;
			cdp.setSecondaryStreams(cb.getBufferCompositeData());
		}
		buffer.reset();
		//System.out.println("Sending: " + cdp);
	}
}
