package ie.ucd.sensetile.sensorboard;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  DriverUnitTest.class})

public class AllUnitTests {
  
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("ie.ucd.sensetile.sensorboard.AllTests");
  }
}
