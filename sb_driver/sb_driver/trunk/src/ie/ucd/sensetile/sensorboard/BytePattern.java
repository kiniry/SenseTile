/*
 * BytePattern.java
 *
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */

package ie.ucd.sensetile.sensorboard;

/**
 * Byte pattern matcher.
 * 
 * Find a pattern of bytes into a ByteArray.
 * The pattern can be repeated.
 * 
 * @author Vieri del Bianco
 *
 */
public class BytePattern {
  
  private UnsignedByteArray pattern;
  private int repetitionStep;
  
  BytePattern(UnsignedByteArray pattern, int repetitionStep) {
    this.pattern = pattern;
    this.repetitionStep = repetitionStep;
  }
  
  /**
   * Creation factory method.
   * 
   * Creates a pattern matcher based on pattern.
   * 
   * @param pattern to be matched. pattern cannot be null or empty.
   * @return created pattern matcher.
   */
  static public BytePattern createPattern(byte[] ba_pattern) {
    return createPattern(ba_pattern, 0);
  }
  
  /**
   * Creation factory method.
   * 
   * Creates a repetitive pattern matcher based on pattern, with a repetition
   * of repetitionStep.
   * 
   * @param ba_pattern to be matched. pattern cannot be null or empty.
   * @param repetitionStep 0 means no repetition step.
   * @return created pattern matcher.
   */
  public static BytePattern createPattern(
      byte[] ba_pattern, 
      int repetitionStep) {
    UnsignedByteArray pattern = UnsignedByteArray.create(ba_pattern);
    checkPattern(pattern);
    checkRepetitionStep(repetitionStep);
    BytePattern creation = new BytePattern(pattern, repetitionStep);
    return creation;
  }
  
  /**
   * Find a match.
   * 
   * <p>Finds whether data matches the pattern.
   * 
   * @param raw to be searched for pattern.
   * @return index of begin of pattern found in data, or -1 if pattern is not 
   * found.
   */
  public int match(final byte[] raw) {
    if (raw == null) {
      return -1;
    }
    return match(UnsignedByteArray.createFolding(raw));
  }
  
  /**
   * Find a match.
   * 
   * <p>Finds whether data matches the pattern.
   * 
   * @param raw to be searched for pattern.
   * @return index of begin of pattern found in data, or -1 if pattern is not 
   * found.
   */
  public int match(UnsignedByteArray data) {
    int index = -1;
    if (data == null || data.length() == 0) {
      return index;
    }
    if (isPatternRepeated()) {
      if (data.length() < getRepetitionStep()) {
        return index;
      }
      index = matchRepeated(data); 
    } else {
      index = matchSingle(data, 0);
    }
    return index;
  }
  
  private int matchRepeated(UnsignedByteArray data) {
    int index = 0;
    boolean check = false;
    index = matchSingle(UnsignedByteArray.createFolding(data, 0, getRepetitionStep()), 0);
    check = checkRepetitions(UnsignedByteArray.create(data, index, data.length() - index));
    while ((index != -1) && (! check)) {
      index = matchSingle(UnsignedByteArray.createFolding(data, 0, getRepetitionStep()), index + 1);
      if (index != -1) {
        check = checkRepetitions(UnsignedByteArray.create(data, index, data.length() - index));
      }
    }
    return index;
  }
  
  private boolean isPatternRepeated() {
    return (getRepetitionStep() != 0);
  }
  
  private boolean checkRepetitions(UnsignedByteArray data) {
    int dataIndex = getRepetitionStep();
    int patternIndex = 0;
    while (dataIndex < data.length()) {
      if (pattern.getByte(patternIndex) != data.getByte(dataIndex)) {
        return false; // NOPMD by delbianc on 4/30/09 12:15 PM
      }
      dataIndex++;
      patternIndex++;
      if (patternIndex >= pattern.length()) {
        dataIndex = (dataIndex - patternIndex) + getRepetitionStep();
        patternIndex = 0;
      }
    }
    return true;
  }
  
  private int matchSingle(UnsignedByteArray data, int startPosition) {
    for (int index = startPosition; index < data.length(); index++) {
      if (checkPatternMatch(UnsignedByteArray.create(data, index, data.length()))) {
        return index;
      }
    }
    return -1;
  }
  
  private boolean checkPatternMatch(UnsignedByteArray data) {
    for (int index = 0; index < pattern.length() && index < data.length() ; index++) {
      if (data.getByte(index) != pattern.getByte(index)) {
        return false;
      }
    }
    return true;
  }
  
  private static void checkPattern(UnsignedByteArray pattern) {
    if (pattern.length() == 0) {
      throw new IllegalArgumentException();
    }
  }

  private static void checkRepetitionStep(final int repetition) {
    if (repetition < 0) {
      throw new IllegalArgumentException();
    }
  }
  
  private int getRepetitionStep() {
    return repetitionStep;
  }
  
}
