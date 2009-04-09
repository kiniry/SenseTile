package ie.ucd.sensetile;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BytePatternTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreatePattern() {
        byte[] dataPattern = {3,4,5,6};
        BytePattern pattern = BytePattern.createPattern(dataPattern);
        assertNotNull(pattern);
    }
    
    @Test(expected=IllegalArgumentException.class) 
    public void testCreatePatternEmptyPattern() {
        byte[] dataPattern = {};
        BytePattern.createPattern(dataPattern);
    }
    
    @Test(expected=IllegalArgumentException.class) 
    public void testCreatePatternNullPattern() {
        byte[] dataPattern = null;
        BytePattern.createPattern(dataPattern);
    }
    
    @Test
    public void testIdentifyPattern(){
        byte[] dataPattern = {3,4,5,6};
        BytePattern pattern = BytePattern.createPattern(dataPattern);
        byte[] data = {0,1,2,0,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0};
        assertEquals(13, pattern.match(data));
    }
    
    @Test
    public void testMatchSingleBytePattern(){
        byte[] dataPattern = {6};
        BytePattern pattern = BytePattern.createPattern(dataPattern);
        byte[] data = {0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0};
        assertEquals(6, pattern.match(data));
    }
    
    @Test
    public void testMatchEmptyPattern(){
        byte[] dataPattern = {6};
        BytePattern pattern = BytePattern.createPattern(dataPattern);
        byte[] data = {0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0};
        assertEquals(6, pattern.match(data));
    }
    
    @Test
    public void testMatchNotFoundPattern(){
        byte[] dataPattern = {10};
        BytePattern pattern = BytePattern.createPattern(dataPattern);
        byte[] data = {0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0};
        assertEquals(-1, pattern.match(data));
    }

    @Test
    public void testMatchSmallData(){
        byte[] dataPattern = {1,2,3};
        BytePattern pattern = BytePattern.createPattern(dataPattern);
        byte[] data = {0};
        assertEquals(-1, pattern.match(data));
    }
    
    @Test
    public void testMatchEmptyData(){
        byte[] dataPattern = {1,2,3};
        BytePattern pattern = BytePattern.createPattern(dataPattern);
        byte[] data = {};
        assertEquals(-1, pattern.match(data));
    }
    
    @Test
    public void testCreatePatternRepeated(){
        byte[] dataPattern = {3,4};
        int repetitionStep = 5;
        BytePattern pattern = 
            BytePattern.createPattern(dataPattern, repetitionStep);
        assertNotNull(pattern);
    }
    
    @Test
    public void testMatchPatternRepeated(){
        byte[] dataPattern = {3,4};
        int repetitionStep = 5;
        BytePattern pattern = 
            BytePattern.createPattern(dataPattern, repetitionStep);
        byte[] data = {0,1,2,3,4,0,1,2,3,4,0,1,2,3,4, 0};
        assertEquals(3, pattern.match(data));
    }
    
    @Test
    public void testMatchPatternRepeatedTruncated(){
        byte[] dataPattern = {3,4};
        int repetitionStep = 5;
        BytePattern pattern = 
            BytePattern.createPattern(dataPattern, repetitionStep);
        byte[] data = {0,1,2,3,4,0,1,2,3,4,0,1,2,3};
        assertEquals(3, pattern.match(data));
    }
    
    @Test
    public void testMatchPatternRepeatedNotFound(){
        byte[] dataPattern = {3,4};
        int repetitionStep = 5;
        BytePattern pattern = 
            BytePattern.createPattern(dataPattern, repetitionStep);
        byte[] data = {0,1,2,3,4,0,1,2,3,4,0,1,2,3,3};
        assertEquals(-1, pattern.match(data));
    }
    
}
