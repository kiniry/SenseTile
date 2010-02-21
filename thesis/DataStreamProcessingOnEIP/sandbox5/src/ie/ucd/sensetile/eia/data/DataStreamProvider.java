package ie.ucd.sensetile.eia.data;

public class DataStreamProvider {

	public CompositeDataPacket [] getSampleSensorDataStream(int packets, int packetLength, short [] secondaryRates) {
		CompositeDataPacket [] data = new CompositeDataPacket[packets];
		
		for (int i=0; i<data.length; i++) {
			data[i] = buildSamplePacket(packetLength, secondaryRates);
		}
		
		return data;
	}
	
	public CompositeDataPacket buildSamplePacket(int packetLength, short [] secondaryRates) {
		CompositeDataPacket packet = new CompositeDataPacket();
		
		packet.setPrimaryStream(generateSampleAudioSamples(packetLength));
		
		int [][] syncData = new int [secondaryRates.length][];
		
		packet.setSecondaryStreams(generateSampleSecondaryStreams(packetLength, secondaryRates, syncData));
		packet.setSyncData(syncData);
		
		return packet;
	}

	private int[] generateSampleAudioSamples(int length) {
		int [] data = new int[length];
		
		for (int i=0; i<data.length; i++) {
			data[i] = '\u00B5';
		}
		
		return data;
	}
	
	private int [][] generateSampleSecondaryStreams(int primaryLength, short [] rates, int [][] syncData) {
		int [][] data = new int[rates.length][];
		
		for (int i=0; i<data.length; i++) {
			
			int syncCount = primaryLength/rates[i];
			if (primaryLength % rates[i] != 0) { 
				syncCount++;
			}

			syncData[i] = new int[syncCount];
			data[i] = new int[syncCount];
			fillRandomChannelData(data[i], rates[i], i, syncData[i]);
		}
		
		return data;
	}
	
	private void fillRandomChannelData(int [] data, int rate, int channel, int [] syncData) {
		int index = 0;
		
		for (int i=0; i<data.length; i++) {
			data[i] = '\u00B6';
			data[i] += channel;
			
			syncData[i] = index;
			
			index += rate;
			
		}
	}

}
