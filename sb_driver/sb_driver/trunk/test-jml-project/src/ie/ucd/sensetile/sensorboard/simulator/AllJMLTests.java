package ie.ucd.sensetile.sensorboard.simulator;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllJMLTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for ie.ucd.sensetile.sensorboard");
    //$JUnit-BEGIN$
    suite.addTest(CloneablePacket_JML_Test.suite());
    suite.addTest(InstanceFrame_JML_Test.suite());
    suite.addTest(InstancePacket_JML_Test.suite());
    suite.addTest(PacketBuilder_JML_Test.suite());
    suite.addTest(SimulatorPacketInputStream_JML_Test.suite());
    //$JUnit-END$
    return suite;
  }

}
