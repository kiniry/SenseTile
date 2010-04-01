package ie.ucd.sensetile.eia.component.synchronizer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;
import junit.framework.TestCase;


public class StreamSynchronizerTest extends TestCase {

	public void testConstructor() {
		
		StreamSynchronizer ss = new StreamSynchronizer(new StreamSynchronizerConfig());
		
		assertEquals(2, ss.buffers.size());
		assertEquals(2, ss.syncBuffers.size());
		
	}
	
	public void testProcessPacket() {
		CompositeDataPacket cdp = new CompositeDataPacket();
		
		int [] stream1Packet1 = new int[100];
		fillIntArray(stream1Packet1);
		
		int [][] stream1SyncDataPacket1 = new int[1][1];
		stream1SyncDataPacket1[0][0] = 1;
		
		int [][] stream1SyncSyncPacket1 = new int [1][1];
		stream1SyncSyncPacket1[0][0] = 50;
		
		cdp.setPrimaryStream(stream1Packet1);
		cdp.setSecondaryStreams(stream1SyncDataPacket1);
		cdp.setSyncData(stream1SyncSyncPacket1);
		
		StreamSynchronizer ss = new StreamSynchronizer(new StreamSynchronizerConfig());
		ss.processPacket(cdp, "1");
		
		assertEquals(100, ss.buffers.get("1").getBuffer().getCount());
		assertEquals(1, ss.syncBuffers.get("1").getBuffer().getCount());
		
		ss.processPacket(cdp, "1");
		
		assertEquals(200, ss.buffers.get("1").getBuffer().getCount());
		assertEquals(2, ss.syncBuffers.get("1").getBuffer().getCount());
		
		
	}
	
	private void fillIntArray(int [] array) {
		for (int i=0;i<array.length; i++) {
			array[i] = i;
		}
	}
}
