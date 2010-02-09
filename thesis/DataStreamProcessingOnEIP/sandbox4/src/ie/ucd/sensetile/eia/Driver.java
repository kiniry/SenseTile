package ie.ucd.sensetile.eia;

import ie.ucd.sensetile.eia.data.CompositeDataPacket;
import ie.ucd.sensetile.eia.data.DataStreamProvider;

public class Driver {

	public static void main (String [] args) {
		
		new Driver().test1();

	}
	
	public void test1() {

		DataStreamProvider provider = new DataStreamProvider();
		CompositeDataPacket [] packets = provider.getSampleSensorDataStream(1, 4000, new short [] {10,328,328,328});
		
		for (CompositeDataPacket p : packets) {
			System.out.println(p);
		}
	}
}