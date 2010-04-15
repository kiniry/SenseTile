package ie.ucd.sensetile.eia.data;

import java.io.Serializable;
import java.util.Arrays;

public class CompositeDataPacket implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int [] primaryChannel = new int [0];
	private int [][] secondaryChannels = new int [0][0];
	
	private int syncData [][] = new int [0][0];;

	private int packetCountFromSource = 0;
	private long timestamp = 0;
	private boolean useSyncBuffers = true;

	public CompositeDataPacket() {
		timestamp = System.currentTimeMillis();
	}
	
	public int [] getStreamData(int streamNumber) {
		if (streamNumber == 0) {
			return getPrimaryChannelData();
		} else {
			return getSecondaryChannel(streamNumber - 1);
		}
	}
	
	public void setSecondaryStreams (int[][] secondaryStreams) {
		this.secondaryChannels = secondaryStreams;
	}

	public void setPrimaryStream(int [] data) {
		this.primaryChannel = data;
	}
	
	public void setSyncData(int [][] syncData) {
		this.syncData = syncData;
	}
	
	public int[] getPrimaryChannelData() {
		return primaryChannel;
	}
	
	public int[] getSecondaryChannel(int index) {
		if (index < secondaryChannels.length) {
			return secondaryChannels[index];
		} else {
			return new int[0];
		}
	}
	
	public int getSecondaryChannelCount() {
		if (secondaryChannels != null) {
			return secondaryChannels.length;
		}
		else {
			return 0;
		}
	}
	
	public int[] getSyncDataForChannel(int index) {
		if (index < syncData.length) {
			return syncData[index];
		} else {
			return new int[0];
		}
	}
	
	public int getByteCount() {
		int byteCount = primaryChannel.length * 4;
		
		for (int i=0; i<secondaryChannels.length; i++) {
			byteCount += secondaryChannels[i].length * 4;
		}
		
		for (int i=0; i<syncData.length; i++) {
			byteCount += syncData[i].length * 4;
		}
		
		return byteCount;
	}
	
	public int getPayloadByteCount() {
		int byteCount = primaryChannel.length * 4;
		
		for (int i=0; i<secondaryChannels.length; i++) {
			byteCount += secondaryChannels[i].length * 4;
		}
		
		return byteCount;
	}
	
	public int findSecondaryIndexForPrimaryIndex (int channel, int i) {

		if (syncData.length -1 < channel) {
			if (secondaryChannels.length -1 < channel) {
				return -1;
			} else {
				if (secondaryChannels[channel].length == primaryChannel.length) {
					return i;
				}
			}
		}
		
		int [] sync = syncData[channel];
		int [] sec  = secondaryChannels[channel];
		
		// Perfect alignment
		if (sync.length == 0 && sec.length == primaryChannel.length) {
			return i;
		} else {
			return Arrays.binarySearch(sync, i);
		}
	}
	
	public void setPacketCountFromSource(int value) {
		this.packetCountFromSource = value;
	}
	
	public int getPacketCountFromSource() {
		return this.packetCountFromSource;
	}
	
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("PACKET " + getByteCount() + " bytes, payload: " + getPayloadByteCount() + " bytes");
		sb.append(" SAMPLES: ");
		sb.append(primaryChannel.length);
		
		sb.append("\nPRIMARY: " + primaryChannel.length + " samples");
		
		for (int i=0; i<secondaryChannels.length; i++) {
			sb.append("\nCH" + i + ": " + secondaryChannels[i].length + " samples" );
			
		}
		sb.append("\nSYNC DATA:");
		for (int i=0; i<syncData.length; i++) {
			sb.append("\nCH" + i + ": " + syncData[i].length + " items" );
		}
		
		return sb.toString();
	}
}
