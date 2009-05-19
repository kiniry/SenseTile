
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

  BytePattern() {
  }

  
  /**
   * Creation factory method.
   * 
   * Creates a pattern matcher based on pattern.
   * 
   * @param pattern to be matched. pattern cannot be null or empty.
   * @return created pattern matcher.
   */
  static public BytePattern createPattern(final byte[] pattern) {
    checkPattern(pattern);
    final BytePattern creation = new BytePattern();
    creation.setPattern(pattern);
    creation.setRepetitionStep(-1);
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
  public static BytePattern createPattern(
      final byte[] pattern, 
      final int repetitionStep) {
    checkPattern(pattern);
    checkRepetitionStep(repetitionStep);
    final BytePattern creation = new BytePattern();
    creation.setPattern(pattern);
    creation.setRepetitionStep(repetitionStep);
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
  public int match(final byte[] data) {
    int index = -1;
    if (data.length == 0) {
      return index; // NOPMD by delbianc on 4/30/09 12:12 PM
    }
    if (isPatternRepeated()) {
      if (data.length < getRepetitionStep()) {
        return index;
      }
      index = matchRepeated(data); 
    } else {
      index = matchSingle(data, data.length, 0); 
    }
    return index;
  }
  
  private int matchRepeated(final byte[] data) {
    int index = matchSingle(data, getRepetitionStep(), 0);
    int repetitionIndex = checkNextRepetitions(index, data);
    while ((index != -1) && (repetitionIndex == -1)) {
      index = matchSingle(data, this.repetitionStep, index + 1);
      repetitionIndex = checkNextRepetitions(index, data);
    }
    return repetitionIndex;
  }
  
  private boolean isPatternRepeated() {
    return (getRepetitionStep() != -1);
  }
  
  private int matchSingle(
      final byte[] data, 
      final int length, 
      final int startPosition) {
    int match_count = 0;
    int iRecursive;
    byte b; // NOPMD by delbianc on 4/30/09 12:14 PM
    for (int i = startPosition; i < (length + getPattern().length); i++) {
      iRecursive = i % length;
      b = data[iRecursive];
      if (b == pattern[match_count]) {
        match_count++;
        if (match_count == pattern.length) {
          return (i - pattern.length + 1); // NOPMD by delbianc on 4/30/09 12:15 PM
        }
      } else {
        if (match_count > 0) {
          match_count = 0;
        }
      }
    }
    return -1;
  }
  
  private int checkNextRepetitions(final int start, final byte[] data) {
    int dataIndex = start + getRepetitionStep();
    int patternIndex = 0;
    while (dataIndex < data.length) {
      if (getPattern()[patternIndex] != data[dataIndex]) {
        return -1; // NOPMD by delbianc on 4/30/09 12:15 PM
      }
      dataIndex++;
      patternIndex++;
      if (patternIndex >= getPattern().length) {
        dataIndex = (dataIndex - patternIndex) + getRepetitionStep();
        patternIndex = 0;
      }
    }
    return start;
  }
  
  private static void checkPattern(final byte[] pattern) {
    if (pattern == null || pattern.length == 0) {
      throw new IllegalArgumentException();
    }
  }

  private static void checkRepetitionStep(final int repetition) {
    if (repetition <= 0) {
      throw new IllegalArgumentException();
    }
  }
  
  private byte[] getPattern() {
    return Arrays.copyOf(pattern, pattern.length);
  }

  private void setPattern(final byte[] pattern) {
    this.pattern = Arrays.copyOf(pattern, pattern.length);
  }

  private int getRepetitionStep() {
    return repetitionStep;
  }

  private void setRepetitionStep(final int repetitionStep) {
    this.repetitionStep = repetitionStep;
  }

}
