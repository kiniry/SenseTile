package ie.ucd.sensetile.sensorboard.driver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import ie.ucd.sensetile.sensorboard.PacketInputStream;
import ie.ucd.sensetile.sensorboard.SenseTileException;
import ie.ucd.sensetile.sensorboard.SensorBoardPacket;

import java.io.ByteArrayInputStream;
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
    InputStreamPacketInputStreamUnitTest.rawPacketArray = new byte[ByteArrayPacket.LENGTH * 200];
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
  }
  
  @Test
  public void testNotValidLongRead() throws IOException, SenseTileException{
    InputStream is = new ByteArrayInputStream(new byte[ByteArrayPacket.LENGTH * 100]);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    SensorBoardPacket[] packets = new SensorBoardPacket[1];
    assertEquals(0, pis.read(packets));
  }
  
  @Test (expected = EOFException.class)
  public void testNotValidLongFullRead() throws IOException, SenseTileException{
    InputStream is = new ByteArrayInputStream(new byte[ByteArrayPacket.LENGTH * 100]);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    SensorBoardPacket[] packets = new SensorBoardPacket[1];
    pis.readFully(packets);
  }
  
  @Test
  public void testNotValidLongThanValidFullRead() throws IOException, SenseTileException{
    byte[] invalid = new byte[ByteArrayPacket.LENGTH * 100];
    byte[] valid = prepareRawPacketArray(ByteArrayPacket.LENGTH * 100 + 100, 50);
    byte[] total = new byte[invalid.length + valid.length];
    System.arraycopy(invalid, 0, total, 0, invalid.length);
    System.arraycopy(valid, 0, total, invalid.length, valid.length);
    InputStream is = new ByteArrayInputStream(total);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    SensorBoardPacket[] packets = new SensorBoardPacket[1];
    pis.readFully(packets);
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
    SensorBoardPacket[] array = new SensorBoardPacket[10];
    assertEquals(1, pis.read(array));
    assertNotNull(array[0]);
  }
  
  @Test
  public void testReadArray3() throws IOException, SenseTileException {
    byte[] rawPacket = prepareRawPacketArray(ByteArrayPacket.LENGTH * 3 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    SensorBoardPacket[] array = new SensorBoardPacket[10];
    assertEquals(3, pis.read(array));
    assertNotNull(array[2]);
  }
  
  @Test
  public void testReadArray20ToEmpty() throws IOException, SenseTileException {
    final int limit = 20;
    byte[] rawPacket = prepareRawPacketArray(ByteArrayPacket.LENGTH * limit + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    SensorBoardPacket[] array = new SensorBoardPacket[30];
    final int offset = 3;
    assertEquals(limit, pis.read(array, 3, 25));
    assertNotNull(array[offset]);
    assertNotNull(array[limit + offset - 1]);
  }
  
  @Test
  public void testReadArray25ToPartial() throws IOException, SenseTileException {
    byte[] rawPacket = prepareRawPacketArray(ByteArrayPacket.LENGTH * 40 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    SensorBoardPacket[] array = new SensorBoardPacket[30];
    final int offset = 3;
    final int length = 25;
    assertEquals(length, pis.read(array, 3, 25));
    assertNotNull(array[offset]);
    assertNotNull(array[offset + length - 1]);
  }
  
  @Test
  public void testReadFullyArray20ToEmpty() throws IOException, SenseTileException {
    final int limit = 20;
    byte[] rawPacket = prepareRawPacketArray(ByteArrayPacket.LENGTH * limit + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    SensorBoardPacket[] array = new SensorBoardPacket[30];
    final int offset = 3;
    assertEquals(limit, pis.read(array, 3, limit));
    assertNotNull(array[offset]);
    assertNotNull(array[limit + offset - 1]);
  }
  
  @Test
  public void testReadFullyArray25ToPartial() throws IOException, SenseTileException {
    byte[] rawPacket = prepareRawPacketArray(ByteArrayPacket.LENGTH * 40 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    SensorBoardPacket[] array = new SensorBoardPacket[30];
    final int offset = 3;
    final int length = 25;
    pis.readFully(array, offset, length);
    assertNotNull(array[offset]);
    assertNotNull(array[offset + length - 1]);
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
  
  @Test (expected = IOException.class)
  public void testClose() throws IOException, SenseTileException {
    byte[] rawPacket = prepareRawPacketArray(ByteArrayPacket.LENGTH * 5, 0);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new InputStreamPacketInputStream( is );
    pis.close();
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
