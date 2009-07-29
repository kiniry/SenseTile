package ie.ucd.sensetile.sensorboard.simulator;

import static org.junit.Assert.*;
import ie.ucd.sensetile.sensorboard.SensorBoardPacket;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ClonePacketBuilderTest {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }
  
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }
  
  private PacketInstance template;
  private ClonePacketBuilder builder;
  
  @Before
  public void setUp() throws Exception {
    template = new PacketInstance();
    builder = new ClonePacketBuilder(template);
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testConstructor() {
    ClonePacketBuilder builder = new ClonePacketBuilder(template);
    assertNotNull(builder);
  }
  
  @Test
  public void testConstructorCloneTemplate() {
    SensorBoardPacket packet = builder.getTemplate();
    assertFalse(template == packet);
  }
  
  @Test
  public void testGetPacket() {
    SensorBoardPacket packet = builder.getPacket();
    assertNotNull(packet);  
    assertFalse(template == packet);
  }
  
  @Test
  public void testGetTemplate() {
    SensorBoardPacket packet = builder.getTemplate();
    assertNotNull(packet);  
    assertFalse(template == packet);
  }
  
  @Test
  public void testSetTemplate() {
    builder.setTemplate(template);
    SensorBoardPacket packet = builder.getTemplate();
    assertTrue(template == packet);
  }
}
