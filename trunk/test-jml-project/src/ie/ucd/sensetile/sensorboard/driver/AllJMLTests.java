package ie.ucd.sensetile.sensorboard.driver;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllJMLTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for ie.ucd.sensetile.sensorboard");
    //$JUnit-BEGIN$
    suite.addTest(ByteArrayFrame_JML_Test.suite());
    suite.addTest(ByteArrayPacket_JML_Test.suite());
    suite.addTest(InputStreamPacketInputStream_JML_Test.suite());
    //$JUnit-END$
    return suite;
  }

}
