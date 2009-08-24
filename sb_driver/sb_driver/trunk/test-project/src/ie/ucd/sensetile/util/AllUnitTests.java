package ie.ucd.sensetile.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  BytePatternUnitTest.class,
  UnsignedByteArrayUnitTest.class})

public class AllUnitTests {
  
  public static void main(final String[] args) {
    org.junit.runner.JUnitCore.main("ie.ucd.sensetile.util.AllTests");
  }
}