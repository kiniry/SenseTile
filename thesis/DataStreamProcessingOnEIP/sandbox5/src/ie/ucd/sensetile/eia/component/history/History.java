package ie.ucd.sensetile.eia.component.history;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;

import java.util.List;

import org.apache.camel.Processor;

public interface History extends Processor {
	public List<CompositeDataPacket> getHistory(long time);
	public List<CompositeDataPacket> getHistory(long from, long to);	
}
