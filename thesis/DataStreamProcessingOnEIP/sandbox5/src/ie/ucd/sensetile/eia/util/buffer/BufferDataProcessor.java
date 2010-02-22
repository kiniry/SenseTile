package ie.ucd.sensetile.eia.util.buffer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

public class BufferDataProcessor {
	
	int count = 0;
	
	public void processBufferData(BasicBuffer buffer){
		
		CompositeDataPacket cdp = new CompositeDataPacket();
		cdp.setPrimaryStream(buffer.getBufferData());
				
		if (buffer instanceof CompositeDataBuffer) {
			CompositeDataBuffer cb = (CompositeDataBuffer)buffer;
			cdp.setSecondaryStreams(cb.getBufferCompositeData());
		}
		buffer.reset();
		
		count++;
		
		if (count % 100 == 0) {
			System.out.println(this + " processed " + count + " buffers");
		}
	}
}
