package ie.ucd.sensetile.eia.component.demultiplexer2;

import java.util.Arrays;

import ie.ucd.sensetile.eia.component.AggregatorListener;
import ie.ucd.sensetile.eia.component.BufferedAggregator;
import ie.ucd.sensetile.eia.data.CompositeDataPacket;

public class OutputSynchronizer {
	private int primaryPacketCount    = 0;
	private int secondaryPacketCount  = 0;
	private int currentPrimaryIndex   = 0;
	private int currentSecondaryIndex = 0;
	
	int [][] syncData = null;
	
	CompositeDataPacket currentPacket = null;
	
	private BufferedAggregator primaryAggregator = null;
	private BufferedAggregator secondaryAggregator = null;
	
	public OutputSynchronizer(BufferedAggregator primaryAggregator, BufferedAggregator secondaryAggregator) {
		
		this.syncData = new int [4][secondaryAggregator.getBufferSize()];
		this.primaryAggregator = primaryAggregator;
		this.secondaryAggregator = secondaryAggregator;
		
		primaryAggregator.addListener(new AggregatorListener() {
			public void packetSent(CompositeDataPacket packet) {
				incrementPrimary();
			}
			public void samplesWritten(Integer start, Integer end) {
				primaryWritten(start, end);
			}
		});
	
		secondaryAggregator.addListener(new AggregatorListener() {
			public void packetSent(CompositeDataPacket packet) {
				incrementSecondary();
			}
			public void samplesWritten(Integer start, Integer end) {
				secondaryWritten(start, end);
			}
		});
	}
	
	public void setCurrentPacket(CompositeDataPacket currentPacket) {
		this.currentPacket = currentPacket;
		int [] data = currentPacket.getPrimaryChannelData();
		primaryAggregator.handleData(data);
	}
	
	protected void processSecondary(int startIndex, int endIndex) {
		// At this point, get secondary data and get sync data.
		// Get sync data up to endIndex
		// Recrod sync data and channel data 
		// write channel data using handleData with sub-array of secondary channel in the range 
		
		secondaryAggregator.handleData(currentPacket.getSecondaryChannel(0));
	}
	
	protected void primaryWritten(int startIndex, int endIndex) {
		currentPrimaryIndex += endIndex;
		processSecondary(startIndex, endIndex);
	}
	
	
	protected void secondaryWritten(int startIndex, int endIndex) {
		currentSecondaryIndex += endIndex;
	}

	protected void incrementPrimary() {
		primaryPacketCount++;
		currentPrimaryIndex = 0;
	}
	
	protected void incrementSecondary() {
		secondaryPacketCount++;
		currentSecondaryIndex = 0;
	}
}

