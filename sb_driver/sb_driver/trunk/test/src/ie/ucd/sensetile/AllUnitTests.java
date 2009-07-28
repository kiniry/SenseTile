package ie.ucd.sensetile;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  ie.ucd.sensetile.sensorboard.AllUnitTests.class,
  ie.ucd.sensetile.sensorboard.driver.AllUnitTests.class,
  ie.ucd.sensetile.sensorboard.simulator.AllUnitTests.class,
  ie.ucd.sensetile.util.AllUnitTests.class})

public class AllUnitTests {
  
  public static void main(final String[] args) {
    org.junit.runner.JUnitCore.main("ie.ucd.sensetile.AllTests");
  }
}
