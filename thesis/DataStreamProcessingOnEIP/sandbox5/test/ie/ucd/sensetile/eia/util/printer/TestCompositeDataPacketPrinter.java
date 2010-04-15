package ie.ucd.sensetile.eia.util.printer;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;
import ie.ucd.sensetile.eia.data.DataStreamProvider;
import junit.framework.TestCase;

public class TestCompositeDataPacketPrinter extends TestCase {

	public void testToString() {
		
		DataStreamProvider dsp = new DataStreamProvider();
		
		CompositeDataPacket packet = dsp.buildSamplePacket(1000, new short[] {7,7,4,3,4,5,4,3,300,300});
		
		CompositeDataPacketPrinter printer = new CompositeDataPacketPrinter();
		
		System.out.println(printer.printPacket(packet, false));
	}
	
}
