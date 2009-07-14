package ie.ucd.sensetile.sensorboard.driver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.PacketInputStream;
import ie.ucd.sensetile.sensorboard.SenseTileException;
import ie.ucd.sensetile.sensorboard.driver.ByteArrayPacket;
import ie.ucd.sensetile.sensorboard.driver.InputStreamPacketInputStream;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class InputStreamPacketInputStreamUnitTest {
  
  static private byte[] rawPacketArray;
  
  @BeforeClass 
  static public void initRawPacketArray() {
    InputStreamPacketInputStreamUnitTest.rawPacketArray = new byte[ByteArrayPacket.LENGTH * 50];
    byte[] array = rawPacketArray;
    for (
        int packetIndex = 0; 
        packetIndex <= array.length / ByteArrayPacket.LENGTH; 
        packetIndex ++) {
      for (
          int patternIndex = 0; 
          patternIndex < ByteArrayPacket.PATTERN.length; 
          patternIndex++) {
        int index = 
          (patternIndex + ByteArrayPacket.PATTERN_OFFSET) % 
          ByteArrayPacket.LENGTH + packetIndex * ByteArrayPacket.LENGTH;
        if (index < array.length) {
          array[index] = ByteArrayPacket.PATTERN[patternIndex];
        }
      }
    }
  }
  
  @Before
  public void setUp() {
    InputStreamPacketInputStream.bufferPackets = 6;
    InputStreamPacketInputStream.validateMinimumPackets = 3;
    InputStreamPacketInputStream.trimPackets = 2;
  }
  
  @Test
  public void testConstructor(){
    InputStream is = new ByteArrayInputStream(new byte[0]);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    assertNotNull(pis);
    assertTrue(pis instanceof Closeable);
  }
  
  @Test
  public void testAvailable0() throws IOException{
    InputStream is = new ByteArrayInputStream(new byte[0]);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    assertEquals(0, pis.availablePackets());
  }
  
  @Test
  public void testAvailable0NotEnough() throws IOException{
    InputStream is = new ByteArrayInputStream(new byte[100]);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    assertEquals(0, pis.availablePackets());
  }
  
  @Test
  public void testAvailable1() throws IOException{
    byte[] rawPacket = prepareRawPacketArray(ByteArrayPacket.LENGTH);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    assertEquals(1, pis.availablePackets());
  }
  
  @Test
  public void testAvailable2More() throws IOException{
    byte[] rawPacket = prepareRawPacketArray(ByteArrayPacket.LENGTH * 2 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    assertEquals(2, pis.availablePackets());
  }
  
  @Test
  public void testReadArray1() throws IOException, SenseTileException {
    InputStreamPacketInputStream.validateMinimumPackets = 1;
    InputStreamPacketInputStream.trimPackets = 1;
    byte[] rawPacket = prepareRawPacketArray(ByteArrayPacket.LENGTH + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    Packet[] array = new Packet[10];
    assertEquals(1, pis.read(array));
    assertNotNull(array[0]);
  }
  
  @Test
  public void testReadArray3() throws IOException, SenseTileException {
    byte[] rawPacket = prepareRawPacketArray(ByteArrayPacket.LENGTH * 3 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    Packet[] array = new Packet[10];
    assertEquals(3, pis.read(array));
    assertNotNull(array[2]);
  }
  
  @Test
  public void testReadArray20() throws IOException, SenseTileException {
    byte[] rawPacket = prepareRawPacketArray(ByteArrayPacket.LENGTH * 20 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    Packet[] array = new Packet[30];
    assertEquals(20, pis.read(array, 3, 25));
    assertNotNull(array[22]);
  }
  
  @Test
  public void testRead4() throws IOException, SenseTileException {
    byte[] rawPacket = prepareRawPacketArray(ByteArrayPacket.LENGTH * 4 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    assertNotNull(pis.read());
    assertNotNull(pis.read());
    assertNotNull(pis.read());
    assertNotNull(pis.read());
  }
  
  @Test (expected = EOFException.class)
  public void testRead1Available0() throws IOException, SenseTileException {
    byte[] rawPacket = prepareRawPacketArray(500, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    pis.read();
  }
  
  @Test (expected = EOFException.class)
  public void testRead4Available3() throws IOException, SenseTileException {
    byte[] rawPacket = prepareRawPacketArray(ByteArrayPacket.LENGTH * 3 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    pis.read();
    pis.read();
    pis.read();
    pis.read();
  }
  
  private byte[] prepareRawPacketArray(int length) {
    return prepareRawPacketArray(length, 0);
  }
  
  private byte[] prepareRawPacketArray(int length, int offset) {
    int realOffset = (ByteArrayPacket.LENGTH  - offset) % ByteArrayPacket.LENGTH;
    if (realOffset < 0) {
      realOffset = realOffset + ByteArrayPacket.LENGTH;
    }
    return Arrays.copyOfRange(
        InputStreamPacketInputStreamUnitTest.rawPacketArray, 
        realOffset, realOffset + length);
  }
}
