package ie.ucd.sensetile.sensorboard.driver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import ie.ucd.sensetile.sensorboard.PacketInputStream;
import ie.ucd.sensetile.sensorboard.SenseTileException;
import ie.ucd.sensetile.sensorboard.Packet;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

public class InputStreamPacketInputStreamUnitTest {
  
  private static Properties defaultProperties;
  
  @BeforeClass 
  public static void initDefaultProperties() {
    defaultProperties = new Properties();
    defaultProperties.setProperty(
        InputStreamPacketInputStream.BUFFER_PACKETS_PROPERTY, "6");
    defaultProperties.setProperty(
        InputStreamPacketInputStream.VALIDATE_MINIMUM_PACKETS_PROPERTY, "3");
    defaultProperties.setProperty(
        InputStreamPacketInputStream.TRIM_PACKETS_PROPERTY, "2");
  }
  
  @Test
  public void testConstructor() {
    InputStream is = new ByteArrayInputStream(new byte[0]);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    assertNotNull(pis);
  }
  
  @Test
  public void testNotValidLongRead() throws IOException, SenseTileException {
    InputStream is = new ByteArrayInputStream(
        new byte[ByteArrayPacket.LENGTH * 100]);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    Packet[] packets = new Packet[1];
    assertEquals(0, pis.read(packets));
  }

  @Test (expected = EOFException.class)
  public void testNotValidLongFullRead() 
      throws IOException, SenseTileException {
    InputStream is = new ByteArrayInputStream(
        new byte[ByteArrayPacket.LENGTH * 100]);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    Packet[] packets = new Packet[1];
    pis.readFully(packets);
  }
  
  @Test
  public void testNotValidLongThanValidFullRead() 
      throws IOException, SenseTileException {
    byte[] invalid = new byte[ByteArrayPacket.LENGTH * 100];
    byte[] valid = 
      PacketRawByteArrayBuilder.prepare(
          ByteArrayPacket.LENGTH * 100 + 100, 50);
    byte[] total = new byte[invalid.length + valid.length];
    System.arraycopy(invalid, 0, total, 0, invalid.length);
    System.arraycopy(valid, 0, total, invalid.length, valid.length);
    InputStream is = new ByteArrayInputStream(total);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    Packet[] packets = new Packet[1];
    pis.readFully(packets);
  }
  
  @Test
  public void testAvailable0() throws IOException {
    InputStream is = new ByteArrayInputStream(new byte[0]);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    assertEquals(0, pis.availablePackets());
  }
  
  @Test
  public void testAvailable0NotEnough() throws IOException {
    InputStream is = new ByteArrayInputStream(new byte[100]);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    assertEquals(0, pis.availablePackets());
  }
  
  @Test
  public void testAvailable1() throws IOException, SenseTileException {
    byte[] rawPacket = 
      PacketRawByteArrayBuilder.prepare(ByteArrayPacket.LENGTH);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    assertEquals(1, pis.availablePackets());
  }
  
  @Test
  public void testAvailable2More() throws IOException, SenseTileException {
    byte[] rawPacket = 
      PacketRawByteArrayBuilder.prepare(ByteArrayPacket.LENGTH * 2 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    assertEquals(2, pis.availablePackets());
  }
  
  @Test
  public void testReadArray1() throws IOException, SenseTileException {
    byte[] rawPacket = 
      PacketRawByteArrayBuilder.prepare(ByteArrayPacket.LENGTH + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    Properties properties = new Properties();
    properties.setProperty(
        InputStreamPacketInputStream.VALIDATE_MINIMUM_PACKETS_PROPERTY, "1");
    properties.setProperty(
        InputStreamPacketInputStream.TRIM_PACKETS_PROPERTY, "1");
    PacketInputStream pis = 
      InputStreamPacketInputStream.createInputStreamPacketInputStream(
          is, properties);
    Packet[] array = new Packet[10];
    assertEquals(1, pis.read(array));
    assertNotNull(array[0]);
  }
  
  @Test
  public void testReadArray3() throws IOException, SenseTileException {
    byte[] rawPacket = 
      PacketRawByteArrayBuilder.prepare(ByteArrayPacket.LENGTH * 3 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    Packet[] array = new Packet[10];
    assertEquals(3, pis.read(array));
    assertNotNull(array[2]);
  }
  
  @Test
  public void testReadArray20ToEmpty() throws IOException, SenseTileException {
    final int limit = 20;
    byte[] rawPacket = 
      PacketRawByteArrayBuilder.prepare(
          ByteArrayPacket.LENGTH * limit + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    Packet[] array = new Packet[30];
    final int offset = 3;
    assertEquals(limit, pis.read(array, 3, 25));
    assertNotNull(array[offset]);
    assertNotNull(array[limit + offset - 1]);
  }
  
  @Test
  public void testReadArray25ToPartial() 
      throws IOException, SenseTileException {
    byte[] rawPacket = 
      PacketRawByteArrayBuilder.prepare(ByteArrayPacket.LENGTH * 40 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    Packet[] array = new Packet[30];
    final int offset = 3;
    final int length = 25;
    assertEquals(length, pis.read(array, 3, 25));
    assertNotNull(array[offset]);
    assertNotNull(array[offset + length - 1]);
  }
  
  @Test
  public void testReadFullyArray20ToEmpty() 
      throws IOException, SenseTileException {
    final int limit = 20;
    byte[] rawPacket = 
      PacketRawByteArrayBuilder.prepare(
          ByteArrayPacket.LENGTH * limit + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    Packet[] array = new Packet[30];
    final int offset = 3;
    assertEquals(limit, pis.read(array, 3, limit));
    assertNotNull(array[offset]);
    assertNotNull(array[limit + offset - 1]);
  }
  
  @Test
  public void testReadFullyArray25ToPartial() 
      throws IOException, SenseTileException {
    byte[] rawPacket = 
      PacketRawByteArrayBuilder.prepare(ByteArrayPacket.LENGTH * 40 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    Packet[] array = new Packet[30];
    final int offset = 3;
    final int length = 25;
    pis.readFully(array, offset, length);
    assertNotNull(array[offset]);
    assertNotNull(array[offset + length - 1]);
  }
  
  @Test
  public void testRead4() throws IOException, SenseTileException {
    byte[] rawPacket = 
      PacketRawByteArrayBuilder.prepare(ByteArrayPacket.LENGTH * 4 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    assertNotNull(pis.read());
    assertNotNull(pis.read());
    assertNotNull(pis.read());
    assertNotNull(pis.read());
  }
  
  @Test (expected = EOFException.class)
  public void testRead1Available0() throws IOException, SenseTileException {
    byte[] rawPacket = 
      PacketRawByteArrayBuilder.prepare(500, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    pis.read();
  }
  
  @Test (expected = EOFException.class)
  public void testRead4Available3() throws IOException, SenseTileException {
    byte[] rawPacket = 
      PacketRawByteArrayBuilder.prepare(ByteArrayPacket.LENGTH * 3 + 100, 50);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    pis.read();
    pis.read();
    pis.read();
    pis.read();
  }
  
  @Test (expected = IOException.class)
  public void testClose() throws IOException, SenseTileException {
    byte[] rawPacket = 
      PacketRawByteArrayBuilder.prepare(ByteArrayPacket.LENGTH * 5, 0);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = getDefaultPacketInputStream(is);
    pis.close();
    pis.read();
  }
  
  private InputStreamPacketInputStream getDefaultPacketInputStream(
      final InputStream is) {
    return InputStreamPacketInputStream.createInputStreamPacketInputStream(is,
        defaultProperties);
  }
  
}
