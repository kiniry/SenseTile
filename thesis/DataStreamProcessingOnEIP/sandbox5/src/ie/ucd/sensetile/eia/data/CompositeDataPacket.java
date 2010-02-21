package ie.ucd.sensetile.eia.data;

import java.io.Serializable;

public class CompositeDataPacket implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int [] primaryChannel = new int [0];
	private int [][] secondaryChannels = new int [0][0];
	
	private int syncData [][] = new int [0][0];;

	private int packetCountFromSource = 0;
	
	public CompositeDataPacket() {
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
	
	public void setPacketCountFromSource(int value) {
		this.packetCountFromSource = value;
	}
	
	public int getPacketCountFromSource() {
		return this.packetCountFromSource;
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
