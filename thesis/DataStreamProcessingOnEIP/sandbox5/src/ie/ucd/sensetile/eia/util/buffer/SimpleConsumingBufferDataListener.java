package ie.ucd.sensetile.eia.util.buffer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

public class SimpleConsumingBufferDataListener implements BufferDataListener {
	
	private String name = "Default";
	
	int count = 0;
	
	public SimpleConsumingBufferDataListener() {
		
	}
	
	public SimpleConsumingBufferDataListener(String name) {
		this.name = name;
	}
	
	public void processBufferData(Buffer buffer){
		
		CompositeDataPacket cdp = new CompositeDataPacket();
		cdp.setPrimaryStream(buffer.getData());
				
		if (buffer instanceof CompositeDataBuffer) {
			CompositeDataBuffer cb = (CompositeDataBuffer)buffer;
			cdp.setSecondaryStreams(cb.getBufferCompositeData());
		}
		buffer.reset();
		
		count++;
		
		System.out.println(name + ": processed " + count + " buffers");
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
