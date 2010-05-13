package ie.ucd.sensetile.tinyos.telos;

import ie.ucd.sensetile.tinyos.telos.service.TelosService;
import junit.framework.TestCase;

public class TelosWrapperIntegrationTest extends TestCase {

  public void testInitialize() throws Exception {
    TelosService service = new TelosService();
    service.setCycleTime(500);
    service.initialize();
    Thread.sleep(2 * 1000);
    service.setActive(false);
  }

}
