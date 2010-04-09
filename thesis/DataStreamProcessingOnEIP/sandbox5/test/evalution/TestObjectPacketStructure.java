package evalution;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestObjectPacketStructure implements Serializable {

	public void runNetworkTest(int packetSize, int tupleSize, NetworkTest net) {
		long t1 = System.currentTimeMillis();
		Packet p = buildPacket(packetSize, tupleSize);
		long t2 = System.currentTimeMillis();
		
		try {
			net.writeObject(p);
			long t3 = System.currentTimeMillis();

			System.out.println("OBJECT: \t" + (t2 - t1) + "\t" + (t3 - t2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void runTest (int packetSize, int tupleSize) {
		long t1 = System.currentTimeMillis();
		Packet p = buildPacket(packetSize, tupleSize);
		long t2 = System.currentTimeMillis();
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\object_tuple.data"));
			oos.writeObject(p);
			long t3 = System.currentTimeMillis();

			System.out.println("OBJECT: \t" + (t2 - t1) + "\t" + (t3 - t2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected Packet buildPacket(int packetSize, int tupleSize) {
		Packet p = new Packet();
		
		for (int i=0; i<packetSize; i++) {
			Tuple tuple = new Tuple();
			for (int j=0; j<tupleSize; j++) {
				tuple.data.add((int)Math.random()%100000);
			}
			p.tuples.add(tuple);
		}
		return p;
	}
	
	class Packet implements Serializable {
		List<Tuple> tuples = new ArrayList<Tuple>();
	}
	
	class Tuple implements Serializable {
		List<Integer> data;
		public Tuple() {
			data = new ArrayList<Integer>();
		}
	}
}
