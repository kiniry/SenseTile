package ie.ucd.sensetile;


import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ftdichip.ftd2xx.Device;

public class DriverUnitTest {
  
  private Mockery context;
  private Device device;
  private Driver driver;
  
  @Before
  public void setUp() throws Exception {
    setUpMockDevice();
    driver = new Driver(device);
  }
  
  private void setUpMockDevice() {
    context = new JUnit4Mockery() { {
      setImposteriser(ClassImposteriser.INSTANCE);
    } };
    device = context.mock(Device.class);
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCreate() {
    new Driver(device);
  }
  
//  @Test
//  public void testOpen() throws IOException {
//    context.checking(new Expectations() {{
//      oneOf(device).open();
//    }});
//    driver.open();
//  }
  

}
