package ie.ucd.sensetile.eia.data.component.change.strategy;

import ie.ucd.sensetile.eia.component.change.strategy.ChangeRateStrategy;
import ie.ucd.sensetile.eia.component.change.strategy.MinValueStrategy;
import ie.ucd.sensetile.eia.component.change.strategy.PeakValueStrategy;
import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;


public class TestPeakValueStrategy  extends TestCase {
	
	public void testGetPeakValue() {
		List<CompositeDataPacket>cdp = new ArrayList<CompositeDataPacket>();
		cdp.add(new CompositeDataPacket());
		cdp.add(new CompositeDataPacket());
		
		int [] primaryData = new int[1000];
		int highestValue = 0;
		for (int i=0; i<primaryData.length; i++) {
			primaryData[i] = (int)(Math.random()* 999);
			if (primaryData[i] > highestValue) {
				highestValue = primaryData[i];
			}
		}
		
		cdp.get(0).setPrimaryStream(primaryData);
		cdp.get(1).setPrimaryStream(new int[1000]);
		
		PeakValueStrategy strategy = new PeakValueStrategy();
		
		int result = strategy.getValue(cdp, 0);
		assertEquals(highestValue, result);
		
		cdp.get(1).setPrimaryStream(new int [] {1000});
		result = strategy.getValue(cdp,0);
		
		assertEquals(1000, result);
	}
	
	public void testGetMinValue() {
		List<CompositeDataPacket>cdp = new ArrayList<CompositeDataPacket>();
		cdp.add(new CompositeDataPacket());
		cdp.add(new CompositeDataPacket());
		
		int [] primaryData = new int[1000];
		int minValue = Integer.MAX_VALUE;
		for (int i=0; i<primaryData.length; i++) {
			primaryData[i] = (int)(Math.random()* 999);
			if (primaryData[i] < minValue) {
				minValue = primaryData[i];
			}
		}
		
		cdp.get(0).setPrimaryStream(primaryData);
		cdp.get(1).setPrimaryStream(new int [] {minValue+1});
		
		ChangeRateStrategy strategy = new MinValueStrategy();
		
		int result = strategy.getValue(cdp, 0);
		assertEquals(minValue, result);
		
		cdp.get(1).setPrimaryStream(new int [] {Integer.MIN_VALUE + 1});
		result = strategy.getValue(cdp,0);
		
		assertEquals(Integer.MIN_VALUE + 1, result);
	}

}
