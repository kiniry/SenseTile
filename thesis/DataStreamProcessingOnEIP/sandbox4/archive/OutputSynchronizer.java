package ie.ucd.sensetile.eia.component.demultiplexer2;

import ie.ucd.sensetile.eia.component.AggregatorListener;
import ie.ucd.sensetile.eia.component.BufferedAggregator;
import ie.ucd.sensetile.eia.data.CompositeDataPacket;

public class OutputSynchronizer {
	private int primaryPacketCount    = 0;
	private int secondaryPacketCount  = 0;
	
	private int currentSecondaryStartIndex = 0;
	private int currentSecondaryEndIndex = 0;
	
	private int currentPrimaryStartIndex = 0;
	private int currentPrimaryEndIndex = 0;
	
	private int globalPacketCount = 0;
	
	int [][] syncData = null;
	
	CompositeDataPacket currentPacket = null;
	
	private BufferedAggregator primaryAggregator = null;
	private BufferedAggregator secondaryAggregator = null;
	
	private int secondaryChannelId = 0;
	
	public OutputSynchronizer(BufferedAggregator primaryAggregator, BufferedAggregator secondaryAggregator) {
		
		this.syncData = new int [3][secondaryAggregator.getBufferSize()];
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
		globalPacketCount++;
		this.currentPacket = currentPacket;
		
		currentSecondaryStartIndex = 0;
		currentSecondaryEndIndex = 0;
		currentPrimaryStartIndex = 0;
		currentPrimaryEndIndex = 0;
		
		int [] data = currentPacket.getPrimaryChannelData();
		primaryAggregator.handleData(data);
	}
	
	protected void processSecondary() {
		int [] syncData = currentPacket.getSyncDataForChannel(secondaryChannelId);

		int startSyncIndex = 0;
		int endSyncIndex = 0;
		
		if (currentPrimaryStartIndex == currentPrimaryEndIndex) {
			for (int i=0; i<syncData.length; i++) {
				if (syncData[i] == currentPrimaryStartIndex) {
					startSyncIndex = i;
					endSyncIndex = i;
					break;
				}
			}
		} else {
			
			for (startSyncIndex=0; startSyncIndex < syncData.length && syncData[startSyncIndex] < currentPrimaryStartIndex; startSyncIndex++);
			for (endSyncIndex=startSyncIndex; endSyncIndex < syncData.length && syncData[endSyncIndex] < currentPrimaryEndIndex; endSyncIndex++);
	
			if (endSyncIndex > (syncData.length -1) || syncData[endSyncIndex] > currentPrimaryEndIndex) {
				endSyncIndex--;
			}
		}
		
		secondaryAggregator.handleData(currentPacket.getSecondaryChannel(this.secondaryChannelId), startSyncIndex, endSyncIndex);
	}
	
	protected void primaryWritten(int startIndex, int endIndex) {
		currentPrimaryStartIndex = startIndex;
		currentPrimaryEndIndex = endIndex;
		processSecondary();
	}
	
	
	protected void secondaryWritten(int startIndex, int endIndex) {
		currentSecondaryStartIndex = startIndex;
		currentSecondaryEndIndex = endIndex;
		writeSyncData();
	}

	protected void writeSyncData() {
		
		int [] data = currentPacket.getSyncDataForChannel(secondaryChannelId);
		
		System.out.println("PakcetCount: " + globalPacketCount);
		System.out.println("PrimaryCount: " + primaryPacketCount);
		System.out.println("SecondaryCount: " + secondaryPacketCount);
		System.out.println("PrimaryIndex: " + currentPrimaryStartIndex + ":" + currentPrimaryEndIndex);
		System.out.println("SecondaryIndex: " + currentSecondaryStartIndex + ":" + currentSecondaryEndIndex);
		System.out.println("--");
	}
	
	protected void incrementPrimary() {
		primaryPacketCount++;
		
		
	}
	
	protected void incrementSecondary() {
		secondaryPacketCount++;
	}
}

