package ie.ucd.jdk.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArraysUnitTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testCopyOfRangeByteArrayIntInt() {
    byte[] original = {0,1,2,3,4,5,6,7};
    byte[] copy = Arrays.copyOfRange(original, 6, 10);
    assertEquals(4, copy.length);
    assertEquals(7, copy[1]);
    // no folding
    assertEquals(0, copy[3]);
  }

}
