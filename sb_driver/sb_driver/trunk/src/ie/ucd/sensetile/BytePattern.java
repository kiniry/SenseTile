
package ie.ucd.sensetile;

import java.util.Arrays;

/**
 * Byte pattern matcher.
 * 
 * Find a pattern of bytes into a ByteArray.
 * The pattern can be repeated.
 * 
 * @author delbianc
 *
 */
public class BytePattern {

  private byte[] pattern;
  private int repetitionStep;

  private BytePattern() {
  }

  
  /**
   * Creation factory method.
   * 
   * Creates a pattern matcher based on pattern.
   * 
   * @param pattern to be matched. pattern cannot be null or empty.
   * @return created pattern matcher.
   */
  static public BytePattern createPattern(byte[] pattern) {
    checkPattern(pattern);
    BytePattern creation = new BytePattern();
    creation.pattern = Arrays.copyOf(pattern, pattern.length);
    creation.repetitionStep = -1;
    return creation;
  }
  
  /**
   * Creation factory method.
   * 
   * Creates a repetitive pattern matcher based on pattern, with a repetition
   * of repetitionStep.
   * 
   * @param pattern to be matched. pattern cannot be null or empty.
   * @param repetitionStep.
   * @return created pattern matcher.
   */
  public static BytePattern createPattern(byte[] pattern, int repetitionStep) {
    checkPattern(pattern);
    checkRepetitionStep(repetitionStep);
    BytePattern creation = new BytePattern();
    creation.pattern = Arrays.copyOf(pattern, pattern.length);
    creation.repetitionStep = repetitionStep;
    return creation;
  }
  
  /**
   * Find a match.
   * 
   * Finds whether data matches the pattern.
   * 
   * @param data to be searched for pattern.
   * @return index of begin of pattern found in data, or -1 if pattern is not 
   * found.
   */
  public int match(byte[] data) {
    int index = -1;
    if (data.length == 0) {
      return index;
    }
    if (isPatternRepeated()) {
      index = matchRepeated(data);
    }
    else {
      index = matchSingle(data, data.length, 0);
    }
    return index;
  }
  
  private int matchRepeated(byte[] data) {
    int index = matchSingle(data, this.repetitionStep, 0);
    int repetitionIndex = checkNextRepetitions(index, data);
    while ((index != -1) && (repetitionIndex == -1 )) {
      index = matchSingle(data, this.repetitionStep, index + 1);
      repetitionIndex = checkNextRepetitions(index, data);
    }
    return repetitionIndex;
  }
  
  private boolean isPatternRepeated() {
    return (repetitionStep != -1);
  }
  
  private int matchSingle(byte[] data, int length, int startPosition) {
    int match_count = 0;
    int iRecursive;
    for (int i = startPosition; i < (length + pattern.length); i++) {
      iRecursive = i % length;
      byte b = data[iRecursive];
      if (b == pattern[match_count]) {
        match_count++;
        if (match_count == pattern.length) {
          return (i - pattern.length + 1);
        }
      } else {
        if (match_count > 0) {
          match_count = 0;
        }
      }
    }
    return -1;
  }
  
  private int checkNextRepetitions(int start, byte[] data) {
    int dataIndex = start + repetitionStep;
    int patternIndex = 0;
    while (dataIndex < data.length) {
      if (pattern[patternIndex] != data[dataIndex]) {
        return -1;
      }
      dataIndex++;
      patternIndex++;
      if (patternIndex >= pattern.length) {
        dataIndex = (dataIndex - patternIndex) + repetitionStep;
        patternIndex = 0;
      }
    }
    return start;
  }
  
  private static void checkPattern(byte[] pattern) {
    if (pattern == null || pattern.length == 0) {
      throw new IllegalArgumentException();
    }
  }

  private static void checkRepetitionStep(int repetition) {
    if (repetition <= 0) {
      throw new IllegalArgumentException();
    }
  }

}
