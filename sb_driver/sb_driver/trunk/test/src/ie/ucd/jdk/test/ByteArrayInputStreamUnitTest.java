package ie.ucd.jdk.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ByteArrayInputStreamUnitTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testReadOverEOF() {
    byte[] raw = {1,2};
    ByteArrayInputStream is = new ByteArrayInputStream(raw);
    is.read();
    is.read();
    assertEquals(0, is.read());
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
