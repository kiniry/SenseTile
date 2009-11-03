package ie.ucd.sensetile.sensorboard.driver;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  ByteArrayPacketUnitTest.class,
  ByteArrayFrameUnitTest.class,
  InputStreamPacketInputStreamAsPacketInputStreamUnitTest.class,
  InputStreamPacketInputStreamUnitTest.class })

public class AllUnitTests {
  
  public static void main(final String[] args) {
    org.junit.runner.JUnitCore.main("ie.ucd.sensetile.sensorboard.AllTests");
  }
}
