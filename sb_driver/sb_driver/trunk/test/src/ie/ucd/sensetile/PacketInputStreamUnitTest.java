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
  
  @Test (expected = EOFException.class)
  public void testAvailableEmpty() throws IOException{
    InputStream is = new ByteArrayInputStream(new byte[0]);
    PacketInputStream pis = new PacketInputStream( is );
    pis.available();
  }
  
  @Test
  public void testAvailableNotEnough() throws IOException{
    InputStream is = new ByteArrayInputStream(new byte[100]);
    PacketInputStream pis = new PacketInputStream( is );
    assertEquals(0, pis.available());
  }
  
  @Test
  public void testAvailableJustOne() throws IOException{
    byte[] rawPacket = new byte[1024];
    for (int index = 0; index < Packet.PATTERN.length; index++) {
      rawPacket[(index + Packet.PATTERN_OFFSET) % Packet.LENGTH] = 
        Packet.PATTERN[index];
    }
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = new PacketInputStream( is );
    assertEquals(1, pis.available());
  }
  
}
