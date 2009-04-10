
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
    if (data.length == 0) {
      return -1;
    }
    int repetitionStep = (isPatternRepeated() ? this.repetitionStep
        : data.length);
    int index = matchSingle(data, repetitionStep);
    if (index != -1 && isPatternRepeated()) {
      index = checkNextRepetitions(index, data);
    }
    return index;
  }

  private boolean isPatternRepeated() {
    return (repetitionStep != -1);
  }

  private int matchSingle(byte[] data, int length) {
    int match_count = 0;
    int iRecursive;
    for (int i = 0; i < (length + pattern.length); i++) {
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
