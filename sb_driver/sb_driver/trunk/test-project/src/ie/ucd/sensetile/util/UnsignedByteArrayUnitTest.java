package ie.ucd.sensetile.util;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnsignedByteArrayUnitTest {
  
  byte[] ba = new byte[0];
  
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
    //@SuppressWarnings
    UnsignedByteArray.create(null);
  }
  
  @Test (expected=IndexOutOfBoundsException.class)
  public void testCreate0WindowLength(){
    UnsignedByteArray uba = UnsignedByteArray.create(ba, 5, 0);
    uba.getByte(0);
  }
  
  @Test (expected=IndexOutOfBoundsException.class)
  public void testCreate0Length(){
    byte[] ba = new byte[0];
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    uba.getByte(0);
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
  public void testGetByte(){
    int offset = 3;
    int length = 3;
    ba[4] = 1;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    assertEquals(1, uba.getByte(1));
  }
  
  @Test
  public void testSetByte(){
    int offset = 3;
    int length = 3;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    uba.setByte(1, (byte) 1);
    assertEquals(1, ba[4]);
  }
  
  @Test
  public void testGet12BitsSigned(){
    ba[0] = (byte)0x07;
    ba[1] = (byte)0xff;
    ba[2] = (byte)0x0f;
    ba[3] = (byte)0xff;
    ba[4] = (byte)0x08;
    ba[5] = (byte)0x00;
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    assertEquals(2047, uba.get12BitsSigned(0));
    assertEquals(-1, uba.get12BitsSigned(2));
    assertEquals(-2048, uba.get12BitsSigned(4));
  }
  
  @Test
  public void testSet12BitsSigned(){
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    uba.set12BitsSigned(0, 2047);
    assertEquals(2047, uba.get12BitsSigned(0));
    uba.set12BitsSigned(2, -1);
    assertEquals(-1, uba.get12BitsSigned(2));
    uba.set12BitsSigned(4, 2048);
    assertEquals(-2048, uba.get12BitsSigned(4));
  }
  
  @Test
  public void testGetBit(){
    ba[0] = (byte)0x01;
    ba[1] = (byte)0x80;
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    assertEquals(true, uba.getBit(0, 0));
    assertEquals(true, uba.getBit(1, 7));
  }
  
  @Test
  public void testSetBit(){
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    uba.setBit(0, 0, true);
    assertEquals(true, uba.getBit(0, 0));
    uba.setBit(1, 7, true);
    assertEquals(true, uba.getBit(1, 7));
  }
  
  @Test
  public void testGetOffsetFold(){
    int offset = 8;
    int length = 4;
    ba[0] = 1;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    assertEquals(1, uba.getByte(2));
  }
  
  @Test
  public void testSetOffsetFold(){
    int offset = 8;
    int length = 4;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    uba.setByte(2, (byte) 1);
    assertEquals(1, ba[0]);
  }
  
  @Test
  public void testCreateUnsignedByteArrayFolding(){
    int offset = 3;
    int length = 3;
    ba[4] = 1;
    UnsignedByteArray ubaTemp = UnsignedByteArray.create(ba);
    UnsignedByteArray uba = 
      UnsignedByteArray.createFolding(ubaTemp, offset, length);
    assertEquals(1, uba.getByte(4));
    assertEquals(1, uba.getByte(-2));
  }
  
  @Test
  public void testGetFolding(){
    int offset = 3;
    int length = 3;
    ba[4] = 1;
    UnsignedByteArray uba = 
      UnsignedByteArray.createFolding(ba, offset, length);
    assertEquals(1, uba.getByte(4));
    assertEquals(1, uba.getByte(-2));
  }
  
  @Test
  public void testSetFolding(){
    int offset = 3;
    int length = 3;
    UnsignedByteArray uba = 
      UnsignedByteArray.createFolding(ba, offset, length);
    uba.setByte(4, (byte) 1);
    assertEquals(1, ba[4]);
    uba.setByte(-2, (byte) 2);
    assertEquals(2, ba[4]);
  }
  
  @Test (expected=IndexOutOfBoundsException.class)
  public void testGetOutOfBond(){
    int offset = 3;
    int length = 3;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    uba.getByte(3);
  }
  
  @Test (expected=IndexOutOfBoundsException.class)
  public void testSetOutOfBound(){
    int offset = 3;
    int length = 3;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    uba.setByte(3, (byte) 1);
  }
  
  @Test
  public void testGetInt() {
    byte[] ba = new byte[4];
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    ba[1] = (byte) 0xfd;
    assertEquals(253, uba.getShortUnsigned(0));
    ba[2] = (byte) 0xfd;
    ba[3] = (byte) 0xfc;
    assertEquals(253*256 + 252, uba.getShortUnsigned(2));
  }
  
  @Test
  public void testSetInt() {
    byte[] ba = new byte[4];
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    uba.setShortUnsigned(0, 255);
    assertEquals((byte) 0, ba[0]);
    assertEquals((byte) 255, ba[1]);
    uba.setShortUnsigned(2, 253*256 + 252);
    assertEquals((byte) 0xfd, ba[2]);
    assertEquals((byte) 0xfc, ba[3]);
  }
  
  @Test
  public void testSetOutOfRangeInt() {
    byte[] ba = new byte[2];
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    uba.setShortUnsigned(0, 256*256 + 256 + 1);
    assertEquals((byte) 1, ba[0]);
    assertEquals((byte) 1, ba[1]);
  }
  
  @Test
  public void testSetNegativeInt() {
    byte[] ba = new byte[2];
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    uba.setShortUnsigned(0, -1);
    assertEquals((byte) 255, ba[0]);
    assertEquals((byte) 255, ba[1]);
  }
  
  @Test (expected=IndexOutOfBoundsException.class)
  public void testGetIntOutOfBond(){
    int offset = 3;
    int length = 3;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    uba.getShortUnsigned(2);
  }
  
  @Test (expected=IndexOutOfBoundsException.class)
  public void testSetIntOutOfBound(){
    int offset = 3;
    int length = 3;
    UnsignedByteArray uba = UnsignedByteArray.create(ba, offset, length);
    uba.setShortUnsigned(2, (byte) 1);
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
  
  @Test
  public void testGetArray() {
    UnsignedByteArray uba = UnsignedByteArray.create(ba);
    assertEquals(ba, uba.getArray());
  }
}