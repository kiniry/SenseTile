package ie.ucd.sensetile.eia.component.synchronizer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;
import ie.ucd.sensetile.eia.util.buffer.BasicBuffer;
import ie.ucd.sensetile.eia.util.buffer.CompositeDataBuffer;
import junit.framework.TestCase;


public class StreamSynchronizerTest extends TestCase {

	public void testConstructor() {
		
		StreamSynchronizer ss = new StreamSynchronizer(getSyncConfig());
		
		assertEquals(2, ss.buffers.size());
		assertEquals(2, ss.syncBuffers.size());
		
	}
	
	public void testProcessPacket1() {
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
		
		StreamSynchronizer ss = new StreamSynchronizer(getSyncConfig());
		ss.processPacket(cdp, "1");
		
		assertEquals(100, ss.buffers.get("1").getBuffer().getCount());
		assertEquals(1, ss.syncBuffers.get("1").getBuffer().getCount());
		
		ss.processPacket(cdp, "1");
		
		assertEquals(200, ss.buffers.get("1").getBuffer().getCount());
		assertEquals(2, ss.syncBuffers.get("1").getBuffer().getCount());
		
		
	}
	
	public void testProcessPacket3() {
		CompositeDataPacket cdp = new CompositeDataPacket();
		
		int [] stream1Packet1 = new int[1000];
		fillIntArray(stream1Packet1);
		
		int [][] stream1SyncDataPacket1 = new int[1][10];
		stream1SyncDataPacket1[0][0] = 1;
		
		int [][] stream1SyncSyncPacket1 = new int [1][10];
		
		for (int i=0; i<10; i++) {
			stream1SyncSyncPacket1[0][i] = 50*i;
		}
		
		cdp.setPrimaryStream(stream1Packet1);
		cdp.setSecondaryStreams(stream1SyncDataPacket1);
		cdp.setSyncData(stream1SyncSyncPacket1);
		
		
		StreamSynchronizer ss = new StreamSynchronizer(getSyncConfig());
		ss.processPacket(cdp, "1");
		
		assertEquals(1000, ss.buffers.get("1").getBuffer().getCount());
		assertEquals(10, ss.syncBuffers.get("1").getBuffer().getCount());
		
		ss.processPacket(cdp, "1");
		ss.processPacket(cdp, "1");
		ss.processPacket(cdp, "1");
		ss.processPacket(cdp, "1");
		ss.processPacket(cdp, "1");
		
		assertEquals(6000, ss.buffers.get("1").getBuffer().getCount());
		assertEquals(60, ss.syncBuffers.get("1").getBuffer().getCount());
		
		ss.processPacket(cdp, "2");
		
		BasicBuffer dataBuffer1 = ss.buffers.get("1").getBuffer();
		BasicBuffer syncBuffer1 = ss.syncBuffers.get("1").getBuffer();
		CompositeDataBuffer output = ss.output;
		
		assertTrue(true);
	}
	
	public void testProcessPacket2() {
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
		
		
		StreamSynchronizer ss = new StreamSynchronizer(getSyncConfig());
		ss.processPacket(cdp, "1");
		ss.processPacket(cdp, "2");
		
		assertEquals(50, ss.buffers.get("1").getBuffer().getCount());
		assertEquals(0, ss.syncBuffers.get("1").getBuffer().getCount());
		
		assertEquals(50, ss.buffers.get("2").getBuffer().getCount());
		assertEquals(0, ss.syncBuffers.get("2").getBuffer().getCount());
		
		assertEquals(50, ss.output.getCount());
		
		int [][] output = ss.output.getBufferCompositeData();
		assertEquals(1, output.length);
		
		int [] primary = ss.output.getData();
		assertEquals(50, primary.length);
		
	}
	
	private StreamSynchronizerConfig getSyncConfig() {
		StreamSynchronizerConfig cfg = new StreamSynchronizerConfig();
		cfg.setOutputBufferSize(8000);
		cfg.setInputBufferSizes(8000);
		cfg.setChannelIds(new String [] {"1", "2"});
		return cfg;
	}
	
	private void fillIntArray(int [] array) {
		for (int i=0;i<array.length; i++) {
			array[i] = i;
		}
	}
}
