package ie.ucd.sensetile;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BytePatternUnitTest {

  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testCreate() {
    byte[] dataPattern = {3, 4, 5, 6};
    BytePattern pattern = BytePattern.createPattern(dataPattern);
    assertNotNull(pattern);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateEmpty() {
    byte[] dataPattern = {};
    BytePattern.createPattern(dataPattern);
  }

  @Test(expected = NullPointerException.class)
  public void testCreateNull() {
    byte[] dataPattern = null;
    BytePattern.createPattern(dataPattern);
  }
  
  @Test
  public void testMatch() {
    byte[] dataPattern = {3, 4, 5, 6};
    BytePattern pattern = BytePattern.createPattern(dataPattern);
    byte[] data = {0, 1, 2, 0, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8,
        9, 0};
    assertEquals(13, pattern.match(data));
  }
  
  @Test
  public void testMatchOnEdge() {
    byte[] dataPattern = {3, 4};
    BytePattern pattern = BytePattern.createPattern(dataPattern);
    byte[] leftEdge = {3, 4, 2, 0, 4, 5, 6, 7, 8, 9, 0, 1, 2, 0, 4, 5, 6, 7,
        8, 9, 0};
    assertEquals(0, pattern.match(leftEdge));
    byte[] rightEdge = {0, 1, 2, 0, 4, 5, 6, 7, 8, 3, 4};
    assertEquals(9, pattern.match(rightEdge));
  }
  
  @Test
  public void testMatchRecursive() {
    byte[] dataPattern = {3, 4, 5, 6};
    BytePattern pattern = BytePattern.createPattern(dataPattern);
    byte[] data = {5, 6, 0, 0, 3, 4};
    assertEquals(4, pattern.match(data));
  }
  
  @Test
  public void testMatchSingleByte() {
    byte[] dataPattern = {6};
    BytePattern pattern = BytePattern.createPattern(dataPattern);
    byte[] data = {0, 1, 2, 3, 4, 5, 6, 7, 6, 9};
    assertEquals(6, pattern.match(data));
  }
  
  @Test
  public void testMatchNotFound() {
    byte[] dataPattern = {8, 9, 10};
    BytePattern pattern = BytePattern.createPattern(dataPattern);
    byte[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2};
    assertEquals(-1, pattern.match(data));
  }
  
  @Test
  public void testMatchSingleData() {
    byte[] dataPattern = {1, 2, 3};
    BytePattern pattern = BytePattern.createPattern(dataPattern);
    byte[] data = {0};
    assertEquals(-1, pattern.match(data));
  }
  
  @Test
  public void testMatchSingleByteSingleData() {
    byte[] dataPattern = {6};
    BytePattern pattern = BytePattern.createPattern(dataPattern);
    byte[] data = {6};
    assertEquals(0, pattern.match(data));
  }
  
  @Test
  public void testMatchEmptyData() {
    byte[] dataPattern = {1, 2, 3};
    BytePattern pattern = BytePattern.createPattern(dataPattern);
    byte[] data = {};
    assertEquals(-1, pattern.match(data));
  }
  
  @Test
  public void testCreateRepeated() {
    byte[] dataPattern = {3, 4};
    int repetitionStep = 5;
    BytePattern pattern = BytePattern.createPattern(
        dataPattern, repetitionStep);
    assertNotNull(pattern);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testCreateRepeatedNegativeRepetition() {
    byte[] dataPattern = {3, 4};
    int repetitionStep = -4;
    BytePattern pattern = BytePattern.createPattern(
        dataPattern, repetitionStep);
    assertNotNull(pattern);
  }
  
  @Test
  public void testMatchRepeated() {
    byte[] dataPattern = {3, 4};
    int repetitionStep = 5;
    BytePattern pattern = BytePattern.createPattern(
        dataPattern, repetitionStep);
    byte[] data = {0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0};
    assertEquals(3, pattern.match(data));
  }
  
  @Test
  public void testMatchRepeatedRepetition1() {
    byte[] dataPattern = {3, 4};
    int repetitionStep = 5;
    BytePattern pattern = BytePattern.createPattern(
        dataPattern, repetitionStep);
    byte[] data = {0, 1, 2, 3, 4, 0, 1, 2};
    assertEquals(3, pattern.match(data));
  }
  
  public void testMatchRepeatedRepetition2() {
    byte[] dataPattern = {3, 4};
    int repetitionStep = 5;
    BytePattern pattern = BytePattern.createPattern(
        dataPattern, repetitionStep);
    byte[] data = {0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0, 1};
    assertEquals(3, pattern.match(data));
  }
  
  @Test
  public void testMatchRepeatedTruncated() {
    byte[] dataPattern = {3, 4};
    int repetitionStep = 5;
    BytePattern pattern = BytePattern.createPattern(
        dataPattern, repetitionStep);
    byte[] data = {0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0, 1, 2, 3};
    assertEquals(3, pattern.match(data));
  }
  
  @Test
  public void testMatchRepeatedRecursiveTruncated() {
    byte[] dataPattern = {4, 0};
    int repetitionStep = 5;
    BytePattern pattern = BytePattern.createPattern(
        dataPattern, repetitionStep);
    byte[] data = {0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0, 1, 2, 3};
    assertEquals(4, pattern.match(data));
  }
  
  @Test
  public void testMatchRepeatedNotFound() {
    byte[] dataPattern = {3, 4};
    int repetitionStep = 5;
    BytePattern pattern = BytePattern.createPattern(
        dataPattern, repetitionStep);
    byte[] data = {0, 1, 2, 3, 4, 
                   0, 1, 2, 3, 4, 
                   0, 1, 2, 3, 3 };
    assertEquals(-1, pattern.match(data));
  }
  
  @Test
  public void testMatchRepeatedFalsePositive() {
    byte[] dataPattern = {3, 4};
    int repetitionStep = 5;
    BytePattern pattern = BytePattern.createPattern(
        dataPattern, repetitionStep);
    byte[] data = {0, 3, 4, 3, 4, 
                   0, 3, 4, 3, 4, 
                   0, 1, 2, 3, 4 };
    assertEquals(3, pattern.match(data));
  }
  
  @Test
  public void testMatchRepeatedSingleData() {
    byte[] dataPattern = {3, 4};
    int repetitionStep = 5;
    BytePattern pattern = BytePattern.createPattern(
        dataPattern, repetitionStep);
    byte[] data = {0};
    assertEquals(-1, pattern.match(data));
  }

}
