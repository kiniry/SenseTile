package ie.ucd.sensetile;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  BytePatternUnitTest.class,
  DriverUnitTest.class,
  PacketUnitTest.class,
  UnsignedByteArrayUnitTest.class})

public class AllUnitTests {
  
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("ie.ucd.sensetile.AllTests");
  }
}