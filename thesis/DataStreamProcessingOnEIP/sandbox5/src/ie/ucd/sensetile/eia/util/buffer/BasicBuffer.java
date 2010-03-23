package ie.ucd.sensetile.eia.util.buffer;

public class BasicBuffer implements Buffer {
	
	protected int currentPacketId = 0;
	
	protected int size;
	protected int [] data = null;
	protected int index = 0;
	
	protected BufferDataProcessor dataProcessor = null;
	
	public BasicBuffer(int size) {
		this.size = size;
		this.data = new int [size];
		this.index = size;
	}
	
	public void setDataProcessor(BufferDataProcessor processor) {
		this.dataProcessor = processor;
	}
	
	/* (non-Javadoc)
	 * @see ie.ucd.sensetile.eia.util.buffer.Buffer#writeData(int)
	 */
	public int writeData(int sample) {
	
		if (index-1 < 0 || index > data.length) {
			return Integer.MIN_VALUE;
		}
		
		index--;
		this.data[index] = sample;
		
		checkBuffer();
		return index;
	}
	
	protected void checkBuffer() {
		if (dataProcessor != null && index == 0) {
			dataProcessor.processBufferData(this);
		}
	}
	
	public int [] subSequence(int startIndex) {
		
		// This is invalid.
		if (startIndex < index) {
			return null;
		}
		
		int length = Math.abs(startIndex-(size-1)) + 1; 
		
		int [] subsequence = new int[length];
		for (int i=0, j=startIndex; i<length; i++, j++) {
			subsequence[i] = data[j];
		}
		
		for (int i=index, j=size-1; i <= index; i--, j--) {
			data[j] = data[i];
		}
		
		index +=length;
		
		return subsequence;
	}
	
	/* (non-Javadoc)
	 * @see ie.ucd.sensetile.eia.util.buffer.Buffer#getSize()
	 */
	public int getSize() {
		return this.size;
	}
	
	/* (non-Javadoc)
	 * @see ie.ucd.sensetile.eia.util.buffer.Buffer#getData()
	 */
	public int [] getData() {
		return this.data;
	}
	
	/* (non-Javadoc)
	 * @see ie.ucd.sensetile.eia.util.buffer.Buffer#reset()
	 */
	public void reset() {
		this.index = size;
	}
	
	/* (non-Javadoc)
	 * @see ie.ucd.sensetile.eia.util.buffer.Buffer#getSampleIndex()
	 */
	public Integer getSampleIndex() {
		return index;
	}
	
	public void setCurrentPacketId(int id) {
		this.currentPacketId = id;
	}
	
	public int getCurrentPacketId() {
		return this.currentPacketId;
	}
}
