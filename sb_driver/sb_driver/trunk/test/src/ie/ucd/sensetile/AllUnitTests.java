package ie.ucd.sensetile;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  BytePatternTest.class,
  DriverTest.class,
  PacketTest.class,
  UnsignedByteArrayTest.class})

public class AllTests {
  
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("ie.ucd.sensetile.AllTests");
  }
}