package ie.ucd.sensetile.sensorboard.simulator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.SenseTileException;

import java.io.IOException;

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

  private SimulatorPacketInputStream simulator;
  
  @Before
  public void setUp() throws Exception {
    PacketBuilder builder = new ClonePacketBuilder(new InstancePacket());
    simulator = new SimulatorPacketInputStream(builder);
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testSimulatorPacketInputStream() {
    PacketBuilder builder = new ClonePacketBuilder(new InstancePacket());
    SimulatorPacketInputStream simulator = 
      new SimulatorPacketInputStream(builder);
    assertNotNull(simulator);
  }
  
  @Test
  public void testAvailablePackets() throws IOException {
    assertTrue(simulator.availablePackets() > 0);
  }
  
  @Test (expected=IOException.class)
  public void testClose() throws IOException, SenseTileException {
    try {
      simulator.close();
    } catch (IOException e) {
      fail("IOException not expected");
    }
    simulator.read();
  }
  
  @Test
  public void testRead() throws IOException, SenseTileException {
    assertNotNull(simulator.read());
  }
  
  @Test
  public void testReadArray() throws IOException, SenseTileException {
    final int length = 200;
    Packet[] array = new Packet[length];
    simulator.read(array);
    for (int index = 0; index < array.length; index++) {
      assertNotNull(array[index]);
    }
  }
  
  @Test
  public void testReadArrayOffset() throws IOException, SenseTileException {
    final int arrayLength = 200;
    final int offset = 10;
    final int readLength = 100;
    Packet[] array = new Packet[arrayLength];
    simulator.read(array, offset, readLength);
    for (int index = 0; index < array.length; index++) {
      if(index >= offset && index < offset + readLength ) {
        assertNotNull(array[index]);
      } else {
        assertNull(array[index]);
      }
    }
  }
}
