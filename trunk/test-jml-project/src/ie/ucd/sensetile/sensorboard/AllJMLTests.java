package ie.ucd.sensetile.sensorboard;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllJMLTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for ie.ucd.sensetile.sensorboard");
    //$JUnit-BEGIN$
    suite.addTest(PacketInputStream_JML_Test.suite());
    suite.addTest(Packet_JML_Test.suite());
    suite.addTest(Frame_JML_Test.suite());
    //$JUnit-END$
    return suite;
  }

}
