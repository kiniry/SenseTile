package ie.ucd.sensetile;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  ie.ucd.sensetile.util.AllUnitTests.class,
  ie.ucd.sensetile.sensorboard.AllUnitTests.class})

public class AllUnitTests {
  
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("ie.ucd.sensetile.AllTests");
  }
}
