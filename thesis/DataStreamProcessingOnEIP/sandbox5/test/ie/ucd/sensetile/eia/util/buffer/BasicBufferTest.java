package ie.ucd.sensetile.eia.util.buffer;

import junit.framework.TestCase;

public class BasicBufferTest extends TestCase {

	public void testSimpleWriteData() {
		BasicBuffer buffer = new BasicBuffer(1000);
		buffer.writeData(1);
		assertEquals(999, (int)buffer.getSampleIndex());
		
		buffer.writeData(1);
		assertEquals(998, (int)buffer.getSampleIndex());
	}
	
	
	public void testMaxWriteData() {
		BasicBuffer buffer = new BasicBuffer(1000);
		for (int i=0; i<1000; i++) {
			buffer.writeData(i);
		}
		assertEquals(0, (int)buffer.getSampleIndex());
	}
	
	public void testErrorWriteData() {
		BasicBuffer buffer = new BasicBuffer(10);
		for (int i=0; i<10; i++) {
			buffer.writeData(i);
		}
		assertEquals(buffer.writeData(1), Integer.MIN_VALUE);
	}
	
	public void testBufferProcessor() {
		BasicBuffer buffer = new BasicBuffer(10);
		
		buffer.setDataProcessor(new SimpleConsumingBufferDataListener() {
			public void processBufferData(Buffer buffer) {
				assertEquals((int)buffer.getSampleIndex(), 0);
			}
		});
		
		for (int i=0; i<10; i++) {
			buffer.writeData(i);
		}
	}
	
	public void testGetData() {
		BasicBuffer buffer = new BasicBuffer(20);
		int [] data = buffer.getData();
		
		assertEquals(0, data.length);
		for (int i=0; i<10; i++) {
			buffer.writeData(i);
		}
		
		data = buffer.getData();
		assertEquals(10, data.length);
		
		for (int i=0; i<10; i++) {
			buffer.writeData(i);
		}
		
		data = buffer.getData();
		assertEquals(20, data.length);		
	}
	
	public void testSubsequence() {
		BasicBuffer buffer = new BasicBuffer(20);
		for (int i=1; i<11; i++) {
			buffer.writeData(i);
		}
		assertEquals(10,(int)buffer.getSampleIndex());
		
		int [] result = buffer.subSequence(3);
		assertEquals(10, result.length);
		
		result = buffer.subSequence(10);
		assertEquals(0, result.length);
		
		buffer = new BasicBuffer(20);
		for (int i=1; i<11; i++) {
			buffer.writeData(i);
		}
		
		result = buffer.subSequence(15);
		assertEquals(5, result.length);
		assertEquals(5, buffer.getData().length);
		assertEquals(15, (int)buffer.getSampleIndex());
		
		result = buffer.subSequence(20);
		assertEquals(0, result.length);
		assertEquals(15, (int)buffer.getSampleIndex());
		
		result = buffer.subSequence(buffer.getSize()-1);
		assertEquals(1, result.length);
		
		result = buffer.getData();
		assertEquals(4, result.length);
		
		result = buffer.subSequence(19);
		assertEquals(1, result.length);
		
		result = buffer.getData();
		assertEquals(3, result.length);
	}
	
	public void testGetCount() {
		BasicBuffer buffer = new BasicBuffer(20);		
		for (int i=1; i<21; i++) {
			buffer.writeData(i);
		}
		
		int [] result = buffer.subSequence(0);
		assertEquals(20, result.length);
		assertEquals(0, buffer.getCount());
		
		for (int i=1; i<21; i++) {
			buffer.writeData(i);
		}
		
		result = buffer.subSequence(10);
		assertEquals(10, result.length);
		assertEquals(10, buffer.getCount());
	}
	
	public void testShiftValue() {
		BasicBuffer buffer = new BasicBuffer(20);		
		for (int i=1; i<21; i++) {
			buffer.writeData(i);
		}
		
		buffer.shiftValues(10);
		
		int [] data = buffer.getData();
		
		assertEquals(20, data.length);
		
		for (int i=0, j=30;i<20; i++, j--) {
			assertEquals(j, data[i]);
		}
	}
}
