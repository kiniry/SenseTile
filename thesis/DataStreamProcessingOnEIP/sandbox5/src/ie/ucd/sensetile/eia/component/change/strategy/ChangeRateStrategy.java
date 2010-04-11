package ie.ucd.sensetile.eia.component.change.strategy;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

public interface ChangeRateStrategy {
	public int getValue(CompositeDataPacket [] cdp, int channel);
}
