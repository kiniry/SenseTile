package ie.ucd.sensetile.eia.util.buffer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

public class SimpleConsumingBufferDataListener implements BufferDataListener {
	
	int count = 0;
	
	public void processBufferData(Buffer buffer){
		
		CompositeDataPacket cdp = new CompositeDataPacket();
		cdp.setPrimaryStream(buffer.getData());
				
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
