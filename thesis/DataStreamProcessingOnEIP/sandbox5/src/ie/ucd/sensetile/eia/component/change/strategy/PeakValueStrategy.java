package ie.ucd.sensetile.eia.component.change.strategy;

import java.util.List;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

public class PeakValueStrategy implements ChangeRateStrategy {

	@Override
	public int getValue(List<CompositeDataPacket> cdp, int channel) {
		int peakValue = Integer.MIN_VALUE;
		if (cdp == null || cdp.size() == 0) {
			return peakValue;
		}
		
		for (CompositeDataPacket p : cdp) {
			int [] data = p.getStreamData(channel);
			for (int i=0; i<data.length; i++) {
				if (data[i] > peakValue) {
					peakValue = data[i];
				}
			}
		}
		
		return peakValue;
	}
}
