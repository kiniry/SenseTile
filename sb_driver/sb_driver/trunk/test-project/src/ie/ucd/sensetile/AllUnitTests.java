package ie.ucd.sensetile;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  ie.ucd.sensetile.sensorboard.AllUnitTests.class,
  ie.ucd.sensetile.sensorboard.driver.AllUnitTests.class,
  ie.ucd.sensetile.sensorboard.simulator.AllUnitTests.class,
  ie.ucd.sensetile.util.AllUnitTests.class })
public class AllUnitTests {
  
  public static Test suite() {
    return new JUnit4TestAdapter(AllUnitTests.class);
  }
  
  public static void main(final String[] args) {
    org.junit.runner.JUnitCore.main("ie.ucd.sensetile.AllUnitTests");
  }
}
