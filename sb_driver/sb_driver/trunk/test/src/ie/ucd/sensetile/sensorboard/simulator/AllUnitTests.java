package ie.ucd.sensetile.sensorboard.simulator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  ClonePacketBuilderTest.class, 
  SimulatorPacketInputStreamTest.class})

public class AllUnitTests {
  
  public static void main(final String[] args) {
    org.junit.runner.JUnitCore.main(
        "ie.ucd.sensetile.sensorboard.simulator.AllTests");
  }
}