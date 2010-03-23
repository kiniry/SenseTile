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
		
		buffer.setDataProcessor(new BufferDataProcessor() {
			public void processBufferData(Buffer buffer) {
				assertEquals((int)buffer.getSampleIndex(), 0);
			}
		});
		
		for (int i=0; i<10; i++) {
			buffer.writeData(i);
		}
	}
	
	public void testSubsequence() {
		BasicBuffer buffer = new BasicBuffer(100);
		assertEquals(100, (int)buffer.getSampleIndex());
		
		buffer.writeData(1);
		assertEquals(99, (int)buffer.getSampleIndex());
		
		for (int i=0; i<50; i++) {
			buffer.writeData(i);
		}
		assertEquals(49,(int)buffer.getSampleIndex());
	}
	
}
