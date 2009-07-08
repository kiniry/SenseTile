package ie.ucd.sensetile.sensorboard;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  BoardCommunicationAcceptanceTest.class,
  InputStreamAcceptanceTest.class})

public class AllAcceptanceTests {
  
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("ie.ucd.sensetile.sensorboard.BoardCommunicationAcceptanceTest");
  }
}
