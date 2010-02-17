package ie.ucd.sensetile.eia.component;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

public interface AggregatorListener {
	public void packetSent(CompositeDataPacket packet);
	public void samplesWritten(Integer startIndex, Integer endIndex);
}
