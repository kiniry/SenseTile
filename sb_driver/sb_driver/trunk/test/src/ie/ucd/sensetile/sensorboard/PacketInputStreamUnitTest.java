package ie.ucd.sensetile.sensorboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.PacketInputStream;
import ie.ucd.sensetile.sensorboard.SenseTileException;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PacketInputStreamUnitTest {
  
  static private byte[] rawPacketArray;
  
  @BeforeClass 
  static public void initRawPacketArray() {
    PacketInputStreamUnitTest.rawPacketArray = new byte[Packet.LENGTH * 50];
    byte[] array = rawPacketArray;
    for (
        int packetIndex = 0; 
        packetIndex <= array.length / Packet.LENGTH; 
        packetIndex ++) {
      for (
          int patternIndex = 0; 
          patternIndex < Packet.PATTERN.length; 
          patternIndex++) {
        int index = 
          (patternIndex + Packet.PATTERN_OFFSET) % 
          Packet.LENGTH + packetIndex * Packet.LENGTH;
        if (index < array.length) {
          array[index] = Packet.PATTERN[patternIndex];
        }
      }
    }
  }
  
  @Before
  public void setUp() {
    PacketInputStream.BUFFER_PACKETS = 6;
    PacketInputStream.VALIDATE_MINIMUM_PACKETS = 3;
    PacketInputStream.TRIM_PACKETS = 2;
  }
  
  @Test
  public void testConstructor(){
    InputStream is = new ByteArrayInputStream(new byte[0]);
    PacketInputStream pis = new PacketInputStream( is );
    assertNotNull(pis);
    assertTrue(pis instanceof Closeable);
  }
  
  @Test
  public void testAvailable0() throws IOException{
    InputStream is = new ByteArrayInputStream(new byte[0]);
    PacketInputStream pis = new PacketInputStream( is );
    assertEquals(0, pis.availablePackets());
  }
  
  @Test
  public void testAvailable0NotEnough() throws IOException{
    InputStream is = new ByteArrayInputStream(new byte[100]);
    PacketInputStream pis = new PacketInputStream( is );
    assertEquals(0, pis.availablePackets());
  }
  
  @Test
  public void testAvailable1() throws IOException{
    byte[] rawPacket = prepareRawPacketArray(Packet.LENGTH);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new PacketInputStream( is );
    assertEquals(1, pis.availablePackets());
  }
  
  @Test
  public void testAvailable2More() throws IOException{
    byte[] rawPacket = prepareRawPacketArray(Packet.LENGTH * 2 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new PacketInputStream( is );
    assertEquals(2, pis.availablePackets());
  }
  
  @Test
  public void testReadArray1() throws IOException, SenseTileException {
    PacketInputStream.VALIDATE_MINIMUM_PACKETS = 1;
    PacketInputStream.TRIM_PACKETS = 1;
    byte[] rawPacket = prepareRawPacketArray(Packet.LENGTH + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new PacketInputStream( is );
    Packet[] array = new Packet[10];
    assertEquals(1, pis.read(array));
    assertNotNull(array[0]);
  }
  
  @Test
  public void testReadArray3() throws IOException, SenseTileException {
    byte[] rawPacket = prepareRawPacketArray(Packet.LENGTH * 3 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new PacketInputStream( is );
    Packet[] array = new Packet[10];
    assertEquals(3, pis.read(array));
    assertNotNull(array[2]);
  }
  
  @Test
  public void testReadArray20() throws IOException, SenseTileException {
    byte[] rawPacket = prepareRawPacketArray(Packet.LENGTH * 20 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new PacketInputStream( is );
    Packet[] array = new Packet[30];
    assertEquals(20, pis.read(array, 3, 25));
    assertNotNull(array[22]);
  }
  
  @Test
  public void testRead4() throws IOException, SenseTileException {
    byte[] rawPacket = prepareRawPacketArray(Packet.LENGTH * 4 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new PacketInputStream( is );
    assertNotNull(pis.read());
    assertNotNull(pis.read());
    assertNotNull(pis.read());
    assertNotNull(pis.read());
  }
  
  @Test (expected = EOFException.class)
  public void testRead1Available0() throws IOException, SenseTileException {
    byte[] rawPacket = prepareRawPacketArray(500, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new PacketInputStream( is );
    pis.read();
  }
  
  @Test (expected = EOFException.class)
  public void testRead4Available3() throws IOException, SenseTileException {
    byte[] rawPacket = prepareRawPacketArray(Packet.LENGTH * 3 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new PacketInputStream( is );
    pis.read();
    pis.read();
    pis.read();
    pis.read();
  }
  
  private byte[] prepareRawPacketArray(int length) {
    return prepareRawPacketArray(length, 0);
  }
  
  private byte[] prepareRawPacketArray(int length, int offset) {
    int realOffset = (Packet.LENGTH  - offset) % Packet.LENGTH;
    if (realOffset < 0) {
      realOffset = realOffset + Packet.LENGTH;
    }
    return Arrays.copyOfRange(
        PacketInputStreamUnitTest.rawPacketArray, 
        realOffset, realOffset + length);
  }
}
