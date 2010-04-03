package ie.ucd.sensetile.eia.util.buffer;

import java.util.Arrays;

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
	
		if (index-1 < 0 || index > size) {
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
	
	public int[] popLast() {
		return subSequence(size - 1);
	}
	
	public int [] subSequence(int startIndex) {

		if (!bufferDirty() || startIndex > size-1) {
			return new int[0];
		}
		
		if (startIndex < index) {
			startIndex = index;
		}
		
		int length = Math.abs(startIndex-(size-1)) + 1; 
		
		int [] subsequence = new int[length];
		for (int i=0, j=startIndex; i<length; i++, j++) {
			subsequence[i] = data[j];
		}
		
		if (index == startIndex) {
			index = size;
		} else {
			int gap = size - index - length;
			for (int i=index+gap-1, j=size-1; i >= index; i--, j--) {
				data[j] = data[i];
			}
			index +=length;
		}
		
		return subsequence;
	}
	
	public void shiftValues(int shiftValue) {
		for (int i=index; i<size; i++) {
			data[i] += shiftValue;
		}
	}
	
	public boolean bufferDirty() {
		return (index < size && index >= 0);
	}
	
	public int getCount() {
		if (index > size) {
			return 0;
		} else if (index <= 0) {
			return size;
		} else {
			return size - index;
		}
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
		if (index < size) {
			return Arrays.copyOfRange(data, index, size);
		} else {
			return new int[0];
		}
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
