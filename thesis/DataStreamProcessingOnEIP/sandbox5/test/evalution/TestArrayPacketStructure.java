package evalution;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import evalution.TestObjectPacketStructure.Packet;


public class TestArrayPacketStructure implements Serializable {

	
	public void runNetworkTest(int packetSize, int tupleSize, NetworkTest net) {
		long t1 = System.currentTimeMillis();
		Packet p = buildPacket(packetSize, tupleSize);
		long t2 = System.currentTimeMillis();
		
		try {
			net.writeObject(p);
			long t3 = System.currentTimeMillis();

			System.out.println("ARRAY:  \t" + (t2 - t1) + "\t" + (t3 - t2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void runTest (int packetSize, int tupleSize) {
		long t1 = System.currentTimeMillis();
		Packet p = buildPacket(packetSize, tupleSize);
		long t2 = System.currentTimeMillis();
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\array_tuple.data"));
			oos.writeObject(p);
			long t3 = System.currentTimeMillis();

			System.out.println("ARRAY:  \t" + (t2 - t1) + "\t" + (t3 - t2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected Packet buildPacket(int packetSize, int tupleSize) {
		Packet p = new Packet(packetSize);
		
		for (int i=0; i<packetSize; i++) {
			int [] tuple = new int[tupleSize];
			for (int j=0; j<tupleSize; j++) {
				tuple[j] = (int)Math.random()%100000;
			}
			p.data[i] = tuple;
		}
		return p;
	}
	
	class Packet implements Serializable {
		int [][] data;
		public Packet(int size) {
			data = new int[size][];
		}
	}
}
