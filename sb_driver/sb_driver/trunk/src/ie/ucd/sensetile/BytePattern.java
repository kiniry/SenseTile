package ie.ucd.sensetile;

import java.util.Arrays;

public class BytePattern {
    
    private byte[] pattern;
    private int repetitionStep;
    
    private BytePattern() {
    }
    
    static public BytePattern createPattern(byte[] pattern) {
	checkPattern(pattern);
	BytePattern creation = new BytePattern();
	creation.pattern = Arrays.copyOf(pattern, pattern.length);
	creation.repetitionStep = -1;
	return creation;
    }
    
    public static BytePattern createPattern(
	    byte[] pattern,
	    int repetitionStep) {
	checkPattern(pattern);
	BytePattern creation = new BytePattern();
	creation.pattern = Arrays.copyOf(pattern, pattern.length);
	creation.repetitionStep = repetitionStep;
	return creation;
    }
    
    public int match(byte[] data) {
	int repetitionStep = 
	    ( isPatternRepeated() ? this.repetitionStep : data.length );
	int index = matchSingle(data, repetitionStep);
	if ( index != -1 && isPatternRepeated() ){
	    index = checkNextRepetitions(index, data);
	}
	return index;
    }
    
    private boolean isPatternRepeated(){
	return ( repetitionStep != -1 );
    }
    
    private int matchSingle(byte[] data, int length) {
	int match_count = 0;
	for (int i = 0; i < length; i++) {
	    byte b = data[i];
	    if (b == pattern[match_count]) {
		match_count++;
		if (match_count == pattern.length){
		    return (i - pattern.length + 1);
		}
	    } else {
		if ( match_count > 0 ){
		    i =- match_count;
		    match_count = 0;
		}
	    }
	}
	return -1;
    }
    
    private int checkNextRepetitions(int index, byte[] data) {
	byte[] pattern = this.pattern;
	int repetitionStep = this.repetitionStep;
	int dataIndex = index + repetitionStep;
	int patternIndex = 0;
	while (dataIndex < data.length){
	    if (pattern[patternIndex] != data[dataIndex]){
		    return -1;
	    }
	    dataIndex++;
	    patternIndex++;
	    if(patternIndex >= pattern.length){
		dataIndex = (dataIndex - patternIndex) + repetitionStep; 
		patternIndex = 0;
	    }
	}
	return index;
    }
    
    private static void checkPattern(byte[] pattern) {
	if (pattern == null || pattern.length == 0){
	    throw new IllegalArgumentException();
	}
    }
    
}
