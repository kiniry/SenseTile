package ie.ucd.sensetile.sensorboard.driver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import ie.ucd.sensetile.sensorboard.SenseTileException;
import ie.ucd.sensetile.util.UnsignedByteArray;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;


public class ByteArrayPacketUnitTest {
  
  private UnsignedByteArray rawPacket;
  private ByteArrayPacket current;
  private ByteArrayPacket previous;
  
  @Before
  public void setUp() throws SenseTileException {
    rawPacket = UnsignedByteArray.create(
        PacketRawByteArrayBuilder.prepare(ByteArrayPacket.LENGTH));
    // initialize packets
    current = ByteArrayPacket.createPacket(
        Arrays.copyOf(rawPacket.getInternalArray(), rawPacket.length()));
    previous = ByteArrayPacket.createPacket(
        Arrays.copyOf(rawPacket.getInternalArray(), rawPacket.length()));
    int packetIndex = 100;
    current.setCounter(packetIndex);
    previous.setCounter(packetIndex - 1);
  }
  
  @Test
  public void testConstructor() {
    new ByteArrayPacket(UnsignedByteArray.create(rawPacket.getInternalArray()));
  }
  
  @Test (expected = SenseTileException.class)
  public void testCreatePacketWrongLength() throws Exception {
    ByteArrayPacket.createPacket(new byte[1023]);
  }
  
  @Test (expected = SenseTileException.class)
  public void testCreatePacketPatternNotFound() throws Exception {
    ByteArrayPacket.createPacket(new byte[1024]);
  }
  
  @Test (expected = SenseTileException.class)
  public void testCreatePacketPatternMisplaced() throws Exception {
    byte[] rawPacket = new byte[1024];
    for (int index = 0; index < ByteArrayPacket.PATTERN.length; index++) {
      rawPacket[index] = ByteArrayPacket.PATTERN[index];
    }
    ByteArrayPacket.createPacket(rawPacket);
  }
  
  @Test
  public void testCreatePacketPatternOk() throws Exception {
    ByteArrayPacket.createPacket(rawPacket);
  }
  
  @Test
  public void testCheckIndexSequential() throws Exception {
    ByteArrayPacket.checkIndex(previous, current);
  }
  
  @Test
  public void testGetCounter() {
    int value = 256 * 200 + 201;
    current.setCounter(value);
    assertEquals(value, current.getCounter());
  }
  
  @Test 
  public void testGetFrame() {
    assertNotNull(current.getFrame(0));
  }
  
}
