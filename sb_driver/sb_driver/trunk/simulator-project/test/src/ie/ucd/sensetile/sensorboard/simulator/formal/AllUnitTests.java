package ie.ucd.sensetile.sensorboard.simulator.formal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  SimulatorPacketInputStreamWithFormalPacketBuilderAsPacketInputStreamUnitTest.class, 
  SimulatorPacketInputStreamWithFormalPacketBuilderUnitTest.class})

public class AllUnitTests {
  
  public static void main(final String[] args) {
    org.junit.runner.JUnitCore.main(
        "ie.ucd.sensetile.sensorboard.simulator.AllTests");
  }
}