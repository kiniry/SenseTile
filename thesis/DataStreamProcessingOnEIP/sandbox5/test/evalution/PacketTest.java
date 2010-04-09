package evalution;

public class PacketTest {
	
	static TestArrayPacketStructure arrayTest;
	static TestObjectPacketStructure objectTest;
	
	public static void main(String[] args) {
	
		arrayTest = new TestArrayPacketStructure();
		objectTest = new TestObjectPacketStructure();


		PacketTest test = new PacketTest();
		test.test1KBPacketsToDisk();
		test.test234KBPacketsToDisk();
		test.test2343KBPacketsToDisk();
		
//		NetworkTest net = new NetworkTest();
//		//
//		objectTest.runNetworkTest(80000,5, net);
	//    arrayTest.runNetworkTest(80000, 5, net);
		
	}

	
	public void test1KBPacketsToDisk() {
		objectTest.runTest(50, 5);
		arrayTest.runTest(50, 5);
	}
	
	public void test234KBPacketsToDisk() {
		objectTest.runTest(8000, 5);
		arrayTest.runTest(8000, 5);
	}
	
	public void test2343KBPacketsToDisk() {
		objectTest.runTest(80000, 5);
		arrayTest.runTest(80000, 5);
	}
}