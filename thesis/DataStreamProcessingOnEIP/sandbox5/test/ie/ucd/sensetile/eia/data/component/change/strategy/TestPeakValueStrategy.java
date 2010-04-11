package ie.ucd.sensetile.eia.data.component.change.strategy;

import ie.ucd.sensetile.eia.component.change.strategy.ChangeRateStrategy;
import ie.ucd.sensetile.eia.component.change.strategy.MinValueStrategy;
import ie.ucd.sensetile.eia.component.change.strategy.PeakValueStrategy;
import ie.ucd.sensetile.eia.data.CompositeDataPacket;
import junit.framework.TestCase;


public class TestPeakValueStrategy  extends TestCase {
	
	public void testGetPeakValue() {
		CompositeDataPacket [] cdp = new CompositeDataPacket[2];
		cdp[0] = new CompositeDataPacket();
		cdp[1] = new CompositeDataPacket();
		
		int [] primaryData = new int[1000];
		int highestValue = 0;
		for (int i=0; i<primaryData.length; i++) {
			primaryData[i] = (int)(Math.random()* 999);
			if (primaryData[i] > highestValue) {
				highestValue = primaryData[i];
			}
		}
		
		cdp[0].setPrimaryStream(primaryData);
		cdp[1].setPrimaryStream(new int[1000]);
		
		PeakValueStrategy strategy = new PeakValueStrategy();
		
		int result = strategy.getValue(cdp, 0);
		assertEquals(highestValue, result);
		
		cdp[1].setPrimaryStream(new int [] {1000});
		result = strategy.getValue(cdp,0);
		
		assertEquals(1000, result);
	}
	
	public void testGetMinValue() {
		CompositeDataPacket [] cdp = new CompositeDataPacket[2];
		cdp[0] = new CompositeDataPacket();
		cdp[1] = new CompositeDataPacket();
		
		int [] primaryData = new int[1000];
		int minValue = Integer.MAX_VALUE;
		for (int i=0; i<primaryData.length; i++) {
			primaryData[i] = (int)(Math.random()* 999);
			if (primaryData[i] < minValue) {
				minValue = primaryData[i];
			}
		}
		
		cdp[0].setPrimaryStream(primaryData);
		cdp[1].setPrimaryStream(new int [] {minValue+1});
		
		ChangeRateStrategy strategy = new MinValueStrategy();
		
		int result = strategy.getValue(cdp, 0);
		assertEquals(minValue, result);
		
		cdp[1].setPrimaryStream(new int [] {Integer.MIN_VALUE + 1});
		result = strategy.getValue(cdp,0);
		
		assertEquals(Integer.MIN_VALUE + 1, result);
	}

}
