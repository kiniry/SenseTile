package ie.ucd.sensetile.eia.util.buffer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;
import junit.framework.TestCase;

public class TestCompositeDataBuffer extends TestCase {
	
	
	public void testSimpleWriteData() {
		CompositeDataBuffer buffer = new CompositeDataBuffer(10,4);

		int [] data = new int[4];

		data[0] = 1;
		data[1] = 2;
		data[2] = 3;
		data[3] = 4;
		
		for (int i=0;i<10;i++) {
			buffer.writeData(data);
			data[0]++;
			data[1]++;
			data[2]++;
			data[3]++;
		}
	
		CompositeDataPacket p = new CompositeDataPacket();
		p.setPrimaryStream(buffer.getData());
		p.setSecondaryStreams(buffer.getBufferCompositeData());
		
		for (int i=0; i<3; i++) {
			assertEquals(p.getPrimaryChannelData().length, p.getSecondaryChannel(i).length);
		}
	}
	
}
