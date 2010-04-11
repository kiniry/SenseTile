package ie.ucd.sensetile.eia.component.change.strategy;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import java.util.List;

public interface ChangeRateStrategy {
	public int getValue(List<CompositeDataPacket> cdp, int channel);
}
