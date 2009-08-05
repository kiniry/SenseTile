package ie.ucd.sensetile.sensorboard.simulator;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.util.UnsignedByteArray;

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
  
  @Test
  public void testSetTemperatureInputStream() {
    byte[] raw = new byte[4];
    UnsignedByteArray uba = UnsignedByteArray.create(raw);
    final int low = -880;
    final int high = 2047;
    uba.setShortUnsigned(0, low);
    uba.setShortUnsigned(2, high);
    builder.setTemperatureInputStream(new ByteArrayInputStream(raw));
    assertEquals(
        low, 
        builder.getPacket().getTemperature());
    assertEquals(
        high, 
        builder.getPacket().getTemperature());
    assertEquals(
        template.getTemperature(), 
        builder.getPacket().getTemperature());
  }
  
  @Test
  public void testSetPressureInputStream() {
    byte[] raw = new byte[4];
    UnsignedByteArray uba = UnsignedByteArray.create(raw);
    final int low = 310;
    final int high = 5585;
    uba.setShortUnsigned(0, low);
    uba.setShortUnsigned(2, high);
    builder.setPressureInputStream(new ByteArrayInputStream(raw));
    assertEquals(
        low, 
        builder.getPacket().getPressure());
    assertEquals(
        high, 
        builder.getPacket().getPressure());
    assertEquals(
        template.getPressure(), 
        builder.getPacket().getPressure());
  }
  
  @Test
  public void testSetLightLevelInputStream() {
    byte[] raw = new byte[4];
    UnsignedByteArray uba = UnsignedByteArray.create(raw);
    final int low = 0;
    final int high = 1000;
    uba.setShortUnsigned(0, low);
    uba.setShortUnsigned(2, high);
    builder.setLightLevelInputStream(new ByteArrayInputStream(raw));
    assertEquals(
        low, 
        builder.getPacket().getLightLevel());
    assertEquals(
        high, 
        builder.getPacket().getLightLevel());
    assertEquals(
        template.getPressure(), 
        builder.getPacket().getLightLevel());
  }
  
  @Test
  public void testSetAccellerometerInputStream() {
    byte[] raw = new byte[4];
    UnsignedByteArray uba = UnsignedByteArray.create(raw);
    final int low = 1488;
    final int high = 2232;
    uba.setShortUnsigned(0, low);
    uba.setShortUnsigned(2, high);
    builder.setAccelerometerXInputStream(new ByteArrayInputStream(raw));
    builder.setAccelerometerYInputStream(new ByteArrayInputStream(raw));
    builder.setAccelerometerZInputStream(new ByteArrayInputStream(raw));
    Packet packet;
    packet = builder.getPacket();
    assertEquals(low, packet.getAccelerometerX());
    assertEquals(low, packet.getAccelerometerY());
    assertEquals(low, packet.getAccelerometerZ());
    packet = builder.getPacket();
    assertEquals(high, packet.getAccelerometerX());
    assertEquals(high, packet.getAccelerometerY());
    assertEquals(high, packet.getAccelerometerZ());
    packet = builder.getPacket();
    assertEquals(template.getAccelerometerX(), packet.getAccelerometerX());
    assertEquals(template.getAccelerometerY(), packet.getAccelerometerY());
    assertEquals(template.getAccelerometerZ(), packet.getAccelerometerZ());
  }
  
  @Test
  public void testSetAudioInputStream() {
    final int channel = 3;
    final int frames = Packet.FRAMES * 2;
    byte[] raw = new byte[frames * 2];
    UnsignedByteArray uba = UnsignedByteArray.create(raw);
    final int[] values = {0, 255 + 256 * 255};
    for (int frameIndex = 0; frameIndex < frames; frameIndex++) {
      uba.setShortUnsigned(frameIndex * 2, values[frameIndex%2]);
    }
    builder.setAudioInputStream(channel, new ByteArrayInputStream(raw));
    Packet packet;
    packet = builder.getPacket();
    assertEquals(values[0], packet.getFrame(0).getAudio(channel));
    assertEquals(values[1], packet.getFrame(1).getAudio(channel));
    assertEquals(values[1], packet.getFrame(81).getAudio(channel));
    packet = builder.getPacket();
    assertEquals(values[0], packet.getFrame(0).getAudio(channel));
    assertEquals(values[1], packet.getFrame(1).getAudio(channel));
    assertEquals(values[1], packet.getFrame(81).getAudio(channel));
    packet = builder.getPacket();
    assertEquals(
        template.getFrame(0).getAudio(channel), 
        packet.getFrame(0).getAudio(channel));
    assertEquals(
        template.getFrame(1).getAudio(channel), 
        packet.getFrame(1).getAudio(channel));
    assertEquals(
        template.getFrame(81).getAudio(channel), 
        packet.getFrame(81).getAudio(channel));
  }
  
  
}
