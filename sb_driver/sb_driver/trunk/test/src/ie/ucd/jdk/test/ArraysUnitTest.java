package ie.ucd.jdk.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ArraysUnitTest {

  @Test
  public void testCopyOfRangeByteArrayIntInt() {
    final byte[] original = {0,1,2,3,4,5,6,7};
    final byte[] copy = Arrays.copyOfRange(original, 6, 10);
    assertEquals("length mismatch", 4, copy.length);
    assertEquals("value mismatch", 7, copy[1]);
    // no folding
    assertEquals("value mismatch", 0, copy[3]);
  }

}
