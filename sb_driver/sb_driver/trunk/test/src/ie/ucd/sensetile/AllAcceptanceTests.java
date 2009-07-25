package ie.ucd.sensetile;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  ie.ucd.sensetile.sensorboard.AllAcceptanceTests.class})

public class AllAcceptanceTests {
  
  public static void main(final String[] args){
    org.junit.runner.JUnitCore.main("ie.ucd.sensetile.AllAcceptanceTests");
  }
}
