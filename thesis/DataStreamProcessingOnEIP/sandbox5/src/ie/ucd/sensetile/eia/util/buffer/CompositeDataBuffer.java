package ie.ucd.sensetile.eia.util.buffer;

public class CompositeDataBuffer extends BasicBuffer {

	int [][] secondaryChannels = null;
	
	public CompositeDataBuffer(int size, int channelCount) {
		super(size);
		this.secondaryChannels = new int[channelCount-1][size];
	}
	
	public int writeData(int [] values) {
		
		// If values.length won't fit all channels then we ignore it
		if (values.length != (secondaryChannels.length + 1)) {
			System.out.println("CompositeBuffer: values is not the right length: " + values.length);
			return Integer.MIN_VALUE;
		}

		if (index-1 < 0 || index > secondaryChannels[0].length) {
			System.out.println("Buffer overflow");
			return Integer.MIN_VALUE;
		}
		
		index--;
		super.data[index] = values[0];
		for (int i=0; i<secondaryChannels.length;i++) {
			this.secondaryChannels[i][index] = values[i+1];
		}
		
		checkBuffer();
		return index;
	}

	public int writeData(int value) {
		index--;
		super.data[index] = value;
		for (int i=0; i<secondaryChannels.length;i++) {
			this.secondaryChannels[i][index] = Integer.MIN_VALUE;
		}
		checkBuffer();
		return index;
	}

	public int [][] getBufferCompositeData() {
		return this.secondaryChannels;
	}

}
