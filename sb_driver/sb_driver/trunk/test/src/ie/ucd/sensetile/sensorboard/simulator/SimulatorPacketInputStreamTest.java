package ie.ucd.sensetile.sensorboard.simulator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimulatorPacketInputStreamTest {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }
  
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testSimulatorPacketInputStream() {
    PacketBuilder builder = null;
    SimulatorPacketInputStream simulator = 
      new SimulatorPacketInputStream(builder);
    assertNotNull(simulator);
  }
  
//  @Test
//  public void testAvailablePackets() {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public void testClose() {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public void testRead() {
//    fail("Not yet implemented");
//  }

}
