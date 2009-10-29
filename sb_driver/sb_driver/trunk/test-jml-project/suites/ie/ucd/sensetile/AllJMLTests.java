package ie.ucd.sensetile;


import junit.framework.Test;
import junit.framework.TestSuite;

public class AllJMLTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for ie.ucd.sensetile.sensorboard");
    //$JUnit-BEGIN$
    suite.addTest(ie.ucd.sensetile.sensorboard.AllJMLTests.suite());
    suite.addTest(e.ucd.sensetile.sensorboard.driver.AllJMLTests.suite());
    suite.addTest(ie.ucd.sensetile.sensorboard.simulator.AllJMLTests.suite());
    //$JUnit-END$
    return suite;
  }

}
