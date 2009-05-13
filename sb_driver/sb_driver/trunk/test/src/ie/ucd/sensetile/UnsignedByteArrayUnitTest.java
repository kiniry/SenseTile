package ie.ucd.sensetile;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnsignedByteArrayTest {

  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testBytes2ToInt() {
    byte[] ba = new byte[2];
    ba[1] = (byte) 0xfd;
    assertEquals(253, UnsignedByteArray.bytes2ToInt(ba, 0));
    ba[0] = (byte) 0xfd;
    ba[1] = (byte) 0xfc;
    assertEquals(253*256 + 252, UnsignedByteArray.bytes2ToInt(ba, 0));
  }
  
  @Test
  public void testIntToBytes2() {
    byte[] ba = new byte[2];
    UnsignedByteArray.intToBytes2(ba, 0, 255);
    assertEquals((byte) 0, ba[0]);
    assertEquals((byte) 255, ba[1]);
    UnsignedByteArray.intToBytes2(ba, 0, 253*256 + 252);
    assertEquals((byte) 0xfd, ba[0]);
    assertEquals((byte) 0xfc, ba[1]);
  }
  
  @Test
  public void testIntToBytes2OutOfRange() {
    byte[] ba = new byte[2];
    UnsignedByteArray.intToBytes2(ba, 0, 256*256 + 256 + 1);
    assertEquals((byte) 1, ba[0]);
    assertEquals((byte) 1, ba[1]);
  }
  
  @Test
  public void testIntToBytes2Negative() {
    byte[] ba = new byte[2];
    UnsignedByteArray.intToBytes2(ba, 0, -1);
    assertEquals((byte) 255, ba[0]);
    assertEquals((byte) 255, ba[1]);
  }
  
}
