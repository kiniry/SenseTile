package ie.ucd.jdk.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class ByteArrayInputStreamUnitTest {

  @Test
  public void testReadOverEOF() {
    final byte[] raw = {1,2};
    final ByteArrayInputStream input = new ByteArrayInputStream(raw);
    input.read();
    input.read();
    assertEquals("wrong value read", 0, input.read());
  }

//  @Test
//  public void testReadByteArrayIntInt() {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public void testClose() {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public void testByteArrayInputStreamByteArray() {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public void testReadByteArray() {
//    fail("Not yet implemented");
//  }

}
