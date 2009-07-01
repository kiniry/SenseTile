package ie.ucd.sensetile.sensorboard.driver;

import ie.ucd.sensetile.sensorboard.driver.InputStreamPacketInputStreamUnitTest;
import ie.ucd.sensetile.sensorboard.driver.ByteArrayPacketUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  InputStreamPacketInputStreamUnitTest.class,
  ByteArrayPacketUnitTest.class})

public class AllUnitTests {
  
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("ie.ucd.sensetile.sensorboard.AllTests");
  }
}
