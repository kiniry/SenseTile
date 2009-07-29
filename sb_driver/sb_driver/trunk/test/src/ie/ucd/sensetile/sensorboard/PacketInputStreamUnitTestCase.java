package ie.ucd.sensetile.sensorboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class PacketInputStreamUnitTestCase {
  
  protected abstract PacketInputStream createPacketInputStream(
      final int availablePackets);
  
  protected abstract void disposePacketInputStream(
      final PacketInputStream packetInputStream2);
  
  protected void setPacketInputStream(
      final PacketInputStream packetInputStream) {
    this.packetInputStream = packetInputStream;
  }
  
  protected PacketInputStream getPacketInputStream() {
    return packetInputStream;
  }
  
  private PacketInputStream packetInputStream;
  
  @Before
  public void setUp() throws Exception {
    int availablePackets = 100;
    setPacketInputStream(createPacketInputStream(availablePackets));
  }
  
  @After
  public void tearDown() throws Exception {
    disposePacketInputStream(getPacketInputStream());
  }
  
  @Test
  public void testAvailablePackets() throws IOException {
    assertTrue(getPacketInputStream().availablePackets() > 0);
  }
  
  @Test
  public void testRead() throws IOException, SenseTileException {
    assertNotNull(getPacketInputStream().read());
  }
  
  @Test
  public void testReadArray() throws IOException, SenseTileException {
    int arrayLength = 10;
    Packet[] array = new Packet[arrayLength];
    assertEquals(arrayLength, getPacketInputStream().read(array));
    for (int index = 0; index < array.length; index++) {
      assertNotNull(array[index]);
    }
  }
  
  @Test (expected = NullPointerException.class)
  public void testReadArrayNull() throws IOException, SenseTileException {
    getPacketInputStream().read(null);
  }
  
  @Test
  public void testReadArrayOffset() throws IOException, SenseTileException {
    int arrayLength = 20;
    int offset = 5;
    int readLength = 10;
    Packet[] array = new Packet[arrayLength];
    int actualRead = getPacketInputStream().read(array, offset, readLength);
    assertTrue(actualRead > 0);
    for (int index = 0; index < array.length; index++) {
      if ((index >= offset) && (index < offset + actualRead)) {
        assertNotNull(array[index]);
      } else {
        assertNull(array[index]);
      }
    }
  }
  
  @Test (expected = NullPointerException.class)
  public void testReadArrayOffsetNull() 
      throws IOException, SenseTileException {
    getPacketInputStream().read(null, 1, 1);
  }
  
  @Test
  public void testReadArrayOffsetIndexOutOfBound() 
      throws IOException, SenseTileException {
    final int arrayLength = 10;
    Packet[] array = new Packet[arrayLength];
    IndexOutOfBoundsException caught;
    caught = null;
    try {
      getPacketInputStream().read(array, -1, 1);
      fail("IndexOutOfBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
      caught = e;
    }
    assertNotNull(caught);
    caught = null;
    try {
      getPacketInputStream().read(array, 1, -1);
      fail("IndexOutOfBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
      caught = e;
    }
    assertNotNull(caught);
    caught = null;
    try {
      getPacketInputStream().read(array, 1, 10);
      fail("IndexOutOfBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
      caught = e;
    }
    assertNotNull(caught);
    caught = null;
    try {
      getPacketInputStream().read(array, 0, 11);
      fail("IndexOutOfBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
      caught = e;
    }
    assertNotNull(caught);
  }
  
  @Test
  public void testReadFullyArray() throws IOException, SenseTileException {
    int arrayLength = 10;
    Packet[] array = new Packet[arrayLength];
    getPacketInputStream().read(array);
    for (int index = 0; index < array.length; index++) {
      assertNotNull(array[index]);
    }
  }
  
  @Test (expected = NullPointerException.class)
  public void testReadFullyArrayNull() 
      throws IOException, SenseTileException {
    getPacketInputStream().readFully(null);
  }
  
  @Test
  public void testReadFullyArrayOffset() throws IOException, SenseTileException {
    int arrayLength = 20;
    int offset = 5;
    int readLength = 10;
    Packet[] array = new Packet[arrayLength];
    getPacketInputStream().read(array, offset, readLength);
    for (int index = 0; index < array.length; index++) {
      if ((index >= offset) && (index < offset + readLength)) {
        assertNotNull(array[index]);
      } else {
        assertNull(array[index]);
      }
    }
  }
  
  @Test (expected = NullPointerException.class)
  public void testReadFullyArrayOffsetNull() 
      throws IOException, SenseTileException {
    getPacketInputStream().readFully(null, 1, 1);
  }
  
  @Test
  public void testReadFullyArrayOffsetIndexOutOfBound() 
      throws IOException, SenseTileException {
    final int arrayLength = 10;
    Packet[] array = new Packet[arrayLength];
    IndexOutOfBoundsException caught;
    caught = null;
    try {
      getPacketInputStream().readFully(array, -1, 1);
      fail("IndexOutOfBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
      caught = e;
    }
    assertNotNull(caught);
    caught = null;
    try {
      getPacketInputStream().readFully(array, 1, -1);
      fail("IndexOutOfBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
      caught = e;
    }
    assertNotNull(caught);
    caught = null;
    try {
      getPacketInputStream().readFully(array, 1, 10);
      fail("IndexOutOfBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
      caught = e;
    }
    assertNotNull(caught);
    caught = null;
    try {
      getPacketInputStream().readFully(array, 0, 11);
      fail("IndexOutOfBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
      caught = e;
    }
    assertNotNull(caught);
  }
  
  @Test (expected=IOException.class)
  public void testClose() throws IOException, SenseTileException {
    try {
      getPacketInputStream().close();
    } catch (IOException e) {
      fail("IOException not expected");
    }
    getPacketInputStream().read();
  }
  
}
