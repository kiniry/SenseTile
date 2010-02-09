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
		
		short [][] syncData = new short [secondaryRates.length][];
		
		packet.setSecondaryStreams(generateSampleSecondaryStreams(packetLength, secondaryRates, syncData));
		packet.setSyncData(syncData);
		
		return packet;
	}

	private char[] generateSampleAudioSamples(int length) {
		char [] data = new char[length];
		
		for (int i=0; i<data.length; i++) {
			data[i] = '\u00B5';
		}
		
		return data;
	}
	
	private char [][] generateSampleSecondaryStreams(int primaryLength, short [] rates, short [][] syncData) {
		char [][] data = new char[rates.length][];
		
		for (int i=0; i<data.length; i++) {
			
			int syncCount = primaryLength/rates[i];
			if (primaryLength % rates[i] != 0) { 
				syncCount++;
			}

			syncData[i] = new short[syncCount];
			data[i] = new char[syncCount];
			fillRandomChannelData(data[i], rates[i], i, syncData[i]);
		}
		
		return data;
	}
	
	private void fillRandomChannelData(char [] data, int rate, int channel, short [] syncData) {
		short index = 0;
		
		for (int i=0; i<data.length; i++) {
			data[i] = '\u00B6';
			data[i] += channel;
			
			syncData[i] = index;
			
			index += rate;
			
		}
	}

}
