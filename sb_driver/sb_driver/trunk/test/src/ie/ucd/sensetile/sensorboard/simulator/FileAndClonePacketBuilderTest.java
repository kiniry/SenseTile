package ie.ucd.sensetile.sensorboard.simulator;

import static org.junit.Assert.*;
import ie.ucd.sensetile.sensorboard.Packet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileAndClonePacketBuilderTest {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }
  
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }
  
  private InstancePacket template;
  private FileAndClonePacketBuilder builder;
  
  @Before
  public void setUp() throws Exception {
    template = new InstancePacket();
    builder = new FileAndClonePacketBuilder(template);
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testConstructor() {
    FileAndClonePacketBuilder builder = new FileAndClonePacketBuilder(template);
    assertNotNull(builder);
  }
  
  @Test
  public void testConstructorCloneTemplate() {
    Packet packet = builder.getTemplate();
    assertFalse(template == packet);
  }
  
  @Test
  public void testGetPacket() {
    Packet packet = builder.getPacket();
    assertNotNull(packet);  
    assertFalse(template == packet);
  }
  
  @Test
  public void testGetTemplate() {
    Packet packet = builder.getTemplate();
    assertNotNull(packet);  
    assertFalse(template == packet);
  }
  
  @Test
  public void testSetTemplate() {
    builder.setTemplate(template);
    Packet packet = builder.getTemplate();
    assertTrue(template == packet);
  }
}
