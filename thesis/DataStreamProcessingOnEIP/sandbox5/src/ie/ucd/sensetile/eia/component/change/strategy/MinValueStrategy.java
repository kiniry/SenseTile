package ie.ucd.sensetile.eia.component.change.strategy;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import java.util.List;

public class MinValueStrategy implements ChangeRateStrategy {
	
	@Override
	public int getValue(List<CompositeDataPacket> cdp, int channel) {
		int minValue = Integer.MAX_VALUE;
		if (cdp == null || cdp.size() == 0) {
			return minValue;
		}
		
		for (CompositeDataPacket p : cdp) {
			int [] data = p.getStreamData(channel);
			for (int i=0; i<data.length; i++) {
				if (data[i] < minValue) {
					minValue = data[i];
				}
			}
		}
		
		return minValue;
	}
}
