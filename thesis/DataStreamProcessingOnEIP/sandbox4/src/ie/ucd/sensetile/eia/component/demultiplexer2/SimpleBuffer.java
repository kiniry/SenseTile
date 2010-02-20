package ie.ucd.sensetile.eia.component.demultiplexer2;

public class SimpleBuffer {
	
	protected int currentPacketId = 0;
	
	protected int size;
	protected int [] data = null;
	protected int index = 0;
	
	protected BufferDataProcessor dataProcessor;
	
	public SimpleBuffer(int size) {
		this.size = size;
		this.data = new int [size];
		this.index = size;
		
		setDataProcessor(new BufferDataProcessor());
	}
	
	public void setDataProcessor(BufferDataProcessor processor) {
		this.dataProcessor = processor;
	}
	
	public int writeData(int sample) {
	
		if (index-1 < 0 || index > data.length) {
			System.out.println("BUFFER OVERFLOW");
			return Integer.MIN_VALUE;
		}
		
		index--;
		this.data[index] = sample;
		
		checkBuffer();
		return index;
	}
	
	protected void checkBuffer() {
		if (index == 0) {
			dataProcessor.processBufferData(this);
		}
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int [] getData() {
		return this.data;
	}
	
	public void reset() {
		this.index = size;
	}
	
	public Integer getSampleIndex() {
		return index;
	}
	
	public void setCurrentPacketId(int id) {
		this.currentPacketId = id;
	}
	
	public int getCurrentPacketId() {
		return this.currentPacketId;
	}
	
	public int [] getBufferData() {
		return data;
	}
	
}
