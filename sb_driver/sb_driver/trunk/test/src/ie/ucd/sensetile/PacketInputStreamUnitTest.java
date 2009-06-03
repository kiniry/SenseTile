package ie.ucd.sensetile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class PacketInputStreamUnitTest {
  
  @Test
  public void testConstructor(){
    InputStream is = new ByteArrayInputStream(new byte[0]);
    PacketInputStream pis = new PacketInputStream( is );
    assertNotNull(pis);
    assertTrue(pis instanceof Closeable);
  }
  
//  @Test
//  public void testAvailable0() throws IOException{
//    InputStream is = new ByteArrayInputStream(new byte[0]);
//    PacketInputStream pis = new PacketInputStream( is );
//    assertEquals(0, pis.availablePackets());
//  }
  
  @Test
  public void testAvailable0NotEnough() throws IOException{
    InputStream is = new ByteArrayInputStream(new byte[100]);
    PacketInputStream pis = new PacketInputStream( is );
    assertEquals(0, pis.availablePackets());
  }
  
  @Test
  public void testAvailable1() throws IOException{
    byte[] rawPacket = new byte[Packet.LENGTH];
    for (int index = 0; index < Packet.PATTERN.length; index++) {
      rawPacket[(index + Packet.PATTERN_OFFSET) % Packet.LENGTH] = 
        Packet.PATTERN[index];
    }
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new PacketInputStream( is );
    assertEquals(1, pis.availablePackets());
  }
  
  @Test
  public void testAvailable2More() throws IOException{
    byte[] rawPacket = new byte[Packet.LENGTH * 2 + 100];
    int startOffset = 50;
    int rawIndex;
    for (int packet = 0; packet < 3; packet++) {
      for (int index = 0; index < Packet.PATTERN.length; index++) {
        rawIndex = index + startOffset + Packet.LENGTH * packet;
        if (rawIndex > rawPacket.length) {
          break;
        }
        rawPacket[rawIndex] = Packet.PATTERN[index];
      }
    }
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new PacketInputStream( is );
    assertEquals(2, pis.availablePackets());
  }
  
//  @Test
//  public void testRead1Available1() throws IOException, SenseTileException {
//    byte[] rawPacket = new byte[Packet.LENGTH];
//    for (int index = 0; index < Packet.PATTERN.length; index++) {
//      rawPacket[(index + Packet.PATTERN_OFFSET) % Packet.LENGTH] = 
//        Packet.PATTERN[index];
//    }
//    InputStream is = new ByteArrayInputStream(rawPacket);
//    PacketInputStream pis = new PacketInputStream( is );
//    assertNotNull(pis.read());
//  }
  
//  @Test (expected=EOFException.class)
//  public void testRead1Available0() throws IOException, SenseTileException {
//    InputStream is = new ByteArrayInputStream(new byte[100]);
//    PacketInputStream pis = new PacketInputStream( is );
//    pis.read();
//  }
  
  @Test
  public void testRead1Available2More() throws IOException, SenseTileException {
    byte[] rawPacket = new byte[Packet.LENGTH * 2 + 100];
    int startOffset = 50;
    int rawIndex;
    for (int packet = 0; packet < 3; packet++) {
      for (int index = 0; index < Packet.PATTERN.length; index++) {
        rawIndex = index + startOffset + Packet.LENGTH * packet;
        if (rawIndex > rawPacket.length) {
          break;
        }
        rawPacket[rawIndex] = Packet.PATTERN[index];
      }
    }
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new PacketInputStream( is );
    assertNotNull(pis.read());
    assertNotNull(pis.read());
  }
}
