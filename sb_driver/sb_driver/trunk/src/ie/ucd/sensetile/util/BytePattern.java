/*
 * BytePattern.java
 *
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */

package ie.ucd.sensetile.util;


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
  
  BytePattern(final UnsignedByteArray pattern, final int repetitionStep) {
    this.pattern = pattern;
    this.repetitionStep = repetitionStep;
  }
  
  /**
   * Creation factory method.
   * 
   * Creates a pattern matcher based on pattern.
   * 
   * @param pattern pattern to be matched. pattern cannot be null or empty.
   * @return created pattern matcher.
   */
  public static BytePattern createPattern(final byte[] pattern) {
    return createPattern(pattern, 0);
  }
  
  /**
   * Creation factory method.
   * 
   * Creates a repetitive pattern matcher based on pattern, with a repetition
   * of repetitionStep.
   * 
   * @param rawPattern pattern to be matched. pattern cannot be null or empty.
   * @param repetitionStep 0 means no repetition step.
   * @return created pattern matcher.
   */
  public static BytePattern createPattern(
      final byte[] rawPattern, 
      final int repetitionStep) {
    UnsignedByteArray pattern = UnsignedByteArray.create(rawPattern);
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
    return match(UnsignedByteArray.createFolding(raw));
  }
  
  /**
   * Find a match.
   * 
   * <p>Finds whether data matches the pattern.
   * 
   * @param data array to be searched for pattern.
   * @return index of begin of pattern found in data, or -1 if pattern is not 
   * found.
   */
  public int match(final UnsignedByteArray data) {
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
  
  private int matchRepeated(final UnsignedByteArray data) {
    int index = 0;
    boolean check = false;
    index = matchSingle(UnsignedByteArray.createFolding(data, 0, getRepetitionStep()), 0);
    if (index == -1) {
      return index;
    }
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
  
  private boolean checkRepetitions(final UnsignedByteArray data) {
    int dataIndex = getRepetitionStep();
    int patternIndex = 0;
    while (dataIndex < data.length()) {
      if (pattern.getByte(patternIndex) != data.getByte(dataIndex)) {
        return false; 
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
  
  private int matchSingle(final UnsignedByteArray data, final int startPosition) {
    for (int index = startPosition; index < data.length(); index++) {
      if (checkPatternMatch(UnsignedByteArray.create(data, index, data.length()))) {
        return index;
      }
    }
    return -1;
  }
  
  private boolean checkPatternMatch(final UnsignedByteArray data) {
    for (int index = 0; index < pattern.length() && index < data.length() ; index++) {
      if (data.getByte(index) != pattern.getByte(index)) {
        return false;
      }
    }
    return true;
  }
  
  private static void checkPattern(final UnsignedByteArray pattern) {
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
