package ie.ucd.sensetile.sensorboard;

import static org.junit.Assert.assertEquals;

import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.SenseTileException;
import ie.ucd.sensetile.util.UnsignedByteArray;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;


public class PacketUnitTest {
  
  private byte[] rawPacket;
  private Packet current;
  private Packet previous;
  
  @Before
  public void setUp() throws SenseTileException {
    rawPacket = new byte[1024];
    for (int index = 0; index < Packet.PATTERN.length; index++) {
      rawPacket[(index + Packet.PATTERN_OFFSET) % Packet.LENGTH] = 
        Packet.PATTERN[index];
    }
    current = Packet.createPacket(Arrays.copyOf(rawPacket, rawPacket.length));
    previous = Packet.createPacket(Arrays.copyOf(rawPacket, rawPacket.length));
    int packetIndex = 100;
    current.setIndex(packetIndex);
    previous.setIndex(packetIndex - 1);
  }
  
  @Test
  public void testConstructorNullArrayNotChecked() {
    try {
      new Packet(null);
    } catch (Exception e) {
    }
  }
  
  @Test
  public void testConstructor() {
    new Packet(UnsignedByteArray.create(new byte[1024]));
  }
  
  @Test (expected = SenseTileException.class)
  public void testCreatePacketWrongLength() throws Exception {
    Packet.createPacket(new byte[1023]);
  }
  
  @Test (expected = SenseTileException.class)
  public void testCreatePacketPatternNotFound() throws Exception {
    Packet.createPacket(new byte[1024]);
  }
  
  @Test (expected = SenseTileException.class)
  public void testCreatePacketPatternMisplaced() throws Exception {
    byte[] rawPacket = new byte[1024];
    for (int index = 0; index < Packet.PATTERN.length; index++) {
      rawPacket[index] = Packet.PATTERN[index];
    }
    Packet.createPacket(rawPacket);
  }
  
  @Test
  public void testCreatePacketPatternOk() throws Exception {
    byte[] rawPacket = new byte[1024];
    for (int index = 0; index < Packet.PATTERN.length; index++) {
      rawPacket[(index + Packet.PATTERN_OFFSET) % Packet.LENGTH] = 
        Packet.PATTERN[index];
    }
    Packet.createPacket(rawPacket);
  }
  
  @Test
  public void testCheckIndexSequential() throws Exception {
    Packet.checkIndex(previous, current);
  }
  
  @Test
  public void testGetIndex(){
    int value = 256*200 + 201;
    current.setIndex(value);
    assertEquals(value, current.getIndex());
  }
}
