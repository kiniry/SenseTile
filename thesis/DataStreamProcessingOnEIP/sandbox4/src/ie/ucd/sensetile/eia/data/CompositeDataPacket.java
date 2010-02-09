package ie.ucd.sensetile.eia.data;

import java.io.Serializable;

public class CompositeDataPacket implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private char [] primaryChannel = new char [0];
	private char [][] secondaryChannels = new char [0][0];
	
	private short syncData [][] = new short [0][0];;

	private int packetCountFromSource = 0;
	
	public CompositeDataPacket() {
	}
	
	public void setSecondaryStreams (char[][] secondaryStreams) {
		this.secondaryChannels = secondaryStreams;
	}

	public void setPrimaryStream(char [] data) {
		this.primaryChannel = data;
	}
	
	public void setSyncData(short [][] syncData) {
		this.syncData = syncData;
	}
	
	public char[] getPrimaryChannelData() {
		return primaryChannel;
	}
	
	public char[] getSecondaryChannel(int index) {
		if (index < secondaryChannels.length) {
			return secondaryChannels[index];
		} else {
			return new char[0];
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
	
	public short[] getSyncDataForChannel(int index) {
		if (index < syncData.length) {
			return syncData[index];
		} else {
			return new short[0];
		}
	}
	
	public int getByteCount() {
		int byteCount = primaryChannel.length * 2;
		
		for (int i=0; i<secondaryChannels.length; i++) {
			byteCount += secondaryChannels[i].length * 2;
		}
		
		for (int i=0; i<syncData.length; i++) {
			byteCount += syncData[i].length * 2;
		}
		
		return byteCount;
	}
	
	public int getPayloadByteCount() {
		int byteCount = primaryChannel.length * 2;
		
		for (int i=0; i<secondaryChannels.length; i++) {
			byteCount += secondaryChannels[i].length * 2;
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
