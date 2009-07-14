package ie.ucd.sensetile.sensorboard;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import com.ftdichip.ftd2xx.Device;

public class DriverUnitTest {
  
  private Mockery context;
  private Device device;
  
  @Before
  public void setUp() throws Exception {
    setUpMockDevice();
    new Driver(device);
  }
  
  private void setUpMockDevice() {
    context = new JUnit4Mockery() { {
      setImposteriser(ClassImposteriser.INSTANCE);
    } };
    device = context.mock(Device.class);
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
