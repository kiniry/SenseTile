package ie.ucd.sensetile.eia.data;

import junit.framework.TestCase;

public class TestDataStreamProvider extends TestCase {

	
	public void testBuildSamplePacket() {
		DataStreamProvider dsp = new DataStreamProvider();
		CompositeDataPacket cdp = dsp.buildSamplePacket(1000, new short[] {});
		
		assertEquals(1000, cdp.getPrimaryChannelData().length);
		assertEquals(0, cdp.getSecondaryChannelCount());
		assertEquals(0, cdp.getSecondaryChannel(0).length);
		
		cdp = dsp.buildSamplePacket(1000, new short[] {2,4,6,8,2});
		
		assertEquals(1000, cdp.getPrimaryChannelData().length);
		
		assertEquals(5, cdp.getSecondaryChannelCount());
		
		assertEquals(500, cdp.getSecondaryChannel(0).length);
		assertEquals(500, cdp.getSyncDataForChannel(0).length);
	
		
		assertEquals(250, cdp.getSecondaryChannel(1).length);
		assertEquals(250, cdp.getSyncDataForChannel(1).length);
		
		assertEquals(167, cdp.getSecondaryChannel(2).length);
		assertEquals(167, cdp.getSyncDataForChannel(2).length);
		
		assertEquals(125, cdp.getSecondaryChannel(3).length);
		assertEquals(125, cdp.getSyncDataForChannel(3).length);
		
		assertEquals(500, cdp.getSecondaryChannel(4).length);
		assertEquals(500, cdp.getSyncDataForChannel(4).length);
	}
	
	public void testGetSampleSensorDataStream() {
		DataStreamProvider dsp = new DataStreamProvider();
		CompositeDataPacket [] cdps = dsp.getSampleSensorDataStream(1000, 1000, new short[] {2,4,6,8,2});
		
		for (CompositeDataPacket cdp : cdps) {
			assertEquals(1000, cdp.getPrimaryChannelData().length);
			
			assertEquals(5, cdp.getSecondaryChannelCount());
			
			assertEquals(500, cdp.getSecondaryChannel(0).length);
			assertEquals(500, cdp.getSyncDataForChannel(0).length);
		
			
			assertEquals(250, cdp.getSecondaryChannel(1).length);
			assertEquals(250, cdp.getSyncDataForChannel(1).length);
			
			assertEquals(167, cdp.getSecondaryChannel(2).length);
			assertEquals(167, cdp.getSyncDataForChannel(2).length);
			
			assertEquals(125, cdp.getSecondaryChannel(3).length);
			assertEquals(125, cdp.getSyncDataForChannel(3).length);
			
			assertEquals(500, cdp.getSecondaryChannel(4).length);
			assertEquals(500, cdp.getSyncDataForChannel(4).length);
		}
	}
	
	
}
