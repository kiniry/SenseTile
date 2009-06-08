package ie.ucd.sensetile;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnsignedByteArrayUnitTest {
  
  byte[] ba;
  
  @Before
  public void setUp() throws Exception {
    ba = new byte[10];
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCreate(){
    UnsignedByteArray.create(ba);
  }
  
  @Test (expected=NullPointerException.class)
  public void testCreateNullArray(){
    UnsignedByteArray.create(null);
  }
  
  @Test (expected=IndexOutOfBoundsException.class)
  public void testCreate0WindowLength(){
    UnsignedByteArray uba = UnsignedByteArray.create(ba, 5, 0);
    uba.get(0);
  }
  
  @Test (expected=IndexOutOfBoundsException.class)
  public void testCreate0Length(){
    byte[] ba = new byte[0];
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    uba.get(0);
  }
  
  @Test (expected=IllegalArgumentException.class)
  public void testCreateOutOfBoundsLength(){
    UnsignedByteArray.create(ba, 0, ba.length + 1);
  }
  
  @Test
  public void testConstructorNested(){
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    int offset = 3;
    int length = 3;
    UnsignedByteArray.create(uba, offset, length);
  }
  
  @Test
  public void testConstructorWindow(){
    int offset = 3;
    int length = 3;
    UnsignedByteArray.create(ba, offset, length);
  }
  
  @Test
  public void testGet(){
    int offset = 3;
    int length = 3;
    ba[4] = 1;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    assertEquals(1, uba.get(1));
  }
  
  @Test
  public void testSet(){
    int offset = 3;
    int length = 3;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    uba.set(1, (byte) 1);
    assertEquals(1, ba[4]);
  }
  
  @Test
  public void testGetOffsetFold(){
    int offset = 8;
    int length = 4;
    ba[0] = 1;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    assertEquals(1, uba.get(2));
  }
  
  @Test
  public void testSetOffsetFold(){
    int offset = 8;
    int length = 4;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    uba.set(2, (byte) 1);
    assertEquals(1, ba[0]);
  }
  
  @Test
  public void testGetFolding(){
    int offset = 3;
    int length = 3;
    ba[4] = 1;
    UnsignedByteArray uba = 
      UnsignedByteArray.createFolding(ba, offset, length);
    assertEquals(1, uba.get(4));
    assertEquals(1, uba.get(-2));
  }
  
  @Test
  public void testSetFolding(){
    int offset = 3;
    int length = 3;
    UnsignedByteArray uba = 
      UnsignedByteArray.createFolding(ba, offset, length);
    uba.set(4, (byte) 1);
    assertEquals(1, ba[4]);
    uba.set(-2, (byte) 2);
    assertEquals(2, ba[4]);
  }
  
  @Test (expected=IndexOutOfBoundsException.class)
  public void testGetOutOfBond(){
    int offset = 3;
    int length = 3;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    uba.get(3);
  }
  
  @Test (expected=IndexOutOfBoundsException.class)
  public void testSetOutOfBound(){
    int offset = 3;
    int length = 3;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    uba.set(3, (byte) 1);
  }
  
  @Test
  public void testGetInt() {
    byte[] ba = new byte[4];
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    ba[1] = (byte) 0xfd;
    assertEquals(253, uba.getInt(0));
    ba[2] = (byte) 0xfd;
    ba[3] = (byte) 0xfc;
    assertEquals(253*256 + 252, uba.getInt(2));
  }
  
  @Test
  public void testSetInt() {
    byte[] ba = new byte[4];
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    uba.setInt(0, 255);
    assertEquals((byte) 0, ba[0]);
    assertEquals((byte) 255, ba[1]);
    uba.setInt(2, 253*256 + 252);
    assertEquals((byte) 0xfd, ba[2]);
    assertEquals((byte) 0xfc, ba[3]);
  }
  
  @Test
  public void testSetOutOfRangeInt() {
    byte[] ba = new byte[2];
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    uba.setInt(0, 256*256 + 256 + 1);
    assertEquals((byte) 1, ba[0]);
    assertEquals((byte) 1, ba[1]);
  }
  
  @Test
  public void testSetNegativeInt() {
    byte[] ba = new byte[2];
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    uba.setInt(0, -1);
    assertEquals((byte) 255, ba[0]);
    assertEquals((byte) 255, ba[1]);
  }
  
  @Test (expected=IndexOutOfBoundsException.class)
  public void testGetIntOutOfBond(){
    int offset = 3;
    int length = 3;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    uba.getInt(2);
  }
  
  @Test (expected=IndexOutOfBoundsException.class)
  public void testSetIntOutOfBound(){
    int offset = 3;
    int length = 3;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    uba.setInt(2, (byte) 1);
  }
  
  @Test
  public void testGetEndOffset() {
    byte[] ba = new byte[10];
    UnsignedByteArray uba; 
    uba = UnsignedByteArray.create(ba, 4, 4);
    assertEquals(8, uba.getEndOffset());
    uba = UnsignedByteArray.create(ba, 8, 4);
    assertEquals(2, uba.getEndOffset());
    uba = UnsignedByteArray.create(ba, 0, 2);
    assertEquals(2, uba.getEndOffset());
    uba = UnsignedByteArray.create(ba, 5, 0);
    assertEquals(5, uba.getEndOffset());
    uba = UnsignedByteArray.create(ba, 5, 5);
    assertEquals(0, uba.getEndOffset());
    uba = UnsignedByteArray.create(ba, 5, 10);
    assertEquals(5, uba.getEndOffset());
  }
  
}
