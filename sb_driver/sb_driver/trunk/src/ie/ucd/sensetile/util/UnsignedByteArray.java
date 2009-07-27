/*
 * UnsignedByteArray.java
 *
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */

package ie.ucd.sensetile.util;

/**
 * Byte array manipulation utility.
 * 
 * <p>Build a window over a raw byte array and work inside the window.
 * 
 * @author Vieri del Bianco
 */
public final class UnsignedByteArray {
  
  private byte[] array;
  private int offset;
  private int length;
  private boolean folding;
  
  private UnsignedByteArray(
      final byte[] array, 
      final int offset, 
      final int length, 
      final boolean folding) {
    this.array = array;
    this.length = length;
    this.offset = 
      (array.length == 0) ? 0 : normalizeIndex(offset, array.length);
    this.folding = folding;
  }
  
  /**
   * Get byte.
   * 
   * @param index byte index
   * @return value
   */
  public byte getByte(final int index){
    int newIndex = checkAndNormalizeIndex(index, 1);
    return array[(newIndex + offset) % array.length];
  }
  
  /**
   * Set byte.
   * 
   * @param index byte index
   * @param value byte value
   */
  public void setByte(final int index, final byte value){
    int newIndex = checkAndNormalizeIndex(index, 1);
    array[(newIndex + offset) % array.length] = value;
  }
  
  /**
   * Get bit.
   * 
   * @param index byte index
   * @param bitIndex bit index
   * @return bit value
   */
  public boolean getBit(final int index, final int bitIndex) {
    if (bitIndex < 0 || bitIndex >= 8) {
      throw new IndexOutOfBoundsException();
    }
    int newIndex = checkAndNormalizeIndex(index, 1);
    return (array[newIndex] & (1<<bitIndex)) > 0;
  }
  
  /**
   * Set bit.
   * 
   * @param index byte index
   * @param bitIndex bit index
   * @param value bit value
   */
  public void setBit(
      final int index, final int bitIndex, final boolean value) {
    if (bitIndex < 0 || bitIndex >= 8) {
      throw new IndexOutOfBoundsException();
    }
    int newIndex = checkAndNormalizeIndex(index, 1);
    byte oldByteWithHole = (byte) (array[newIndex] & (~ (1<<bitIndex)));
    byte newValue = (byte) ((value ? 1 : 0)<<bitIndex);
    array[newIndex] = (byte) (oldByteWithHole | newValue);
  }
  
  /**
   * Get signed 12 bits.
   * 
   * @param index byte index
   * @return signed 12 bits
   */
  public int get12BitsSigned(final int index) {
    return (getShortUnsigned(index) << 20) >> 20;
  }
  
  /**
   * Set signed 12 bits.
   * 
   * @param index byte index
   * @param value signed 12 bits value
   */
  public void set12BitsSigned(final int index, final int value) {
    setShortUnsigned(index, (value << 20) >>> 20);
  }
  
  /**
   * Get unsigned short.
   * 
   * @param index byte index
   * @return unsigned short
   */
  public int getShortUnsigned(final int index) {
    int newIndex = checkAndNormalizeIndex(index, 2);
    return 
      ((0xff & getByte(newIndex)) << 8) | 
      (0xff & getByte(newIndex+1));
  }
  
  /**
   * Set unsigned short.
   * 
   * @param index byte index
   * @param value unsigned short value
   */
  public void setShortUnsigned(final int index, final int value) {
    int newIndex = checkAndNormalizeIndex(index, 2);
    setByte(newIndex, (byte) (0xff & value >>> 8));
    setByte(newIndex + 1, (byte) (0xff & value));
  }
  
  /**
   * Byte array length.
   * 
   * @return length
   */
  public int length() {
    return length;
  }
  
  /**
   * Begin offset.
   * 
   * @return offset
   */
  public int getBeginOffset() {
    return offset;
  }
  
  /**
   * End offset.
   * 
   * @return offset
   */
  public int getEndOffset() {
    return UnsignedByteArray.normalizeIndex(
        getBeginOffset() + length(), array.length);
  }
  
  /**
   * Internal raw array.
   * 
   * @return array
   */
  public byte[] getArray() {
    //@SuppressWarnings
    return array;
  }
  
  private int checkAndNormalizeIndex(final int index, final int operationLength){
    int newIndex = index;
    if (folding) {
      newIndex = normalizeIndex(index, length);
    } else {
      if ((index + operationLength - 1) >= length) {
        throw new IndexOutOfBoundsException();
      }
    }
    return newIndex;
  }
  
  /**
   * Create UnsignedByteArray from raw array.
   * 
   * @param array raw byte array
   * @return unsigned byte array
   */
  public static UnsignedByteArray create(final byte[] array) {
    return create(array, 0, array.length, false);
  }
  
  /**
   * Create folding UnsignedByteArray from raw array.
   * 
   * @param array byte array
   * @return unsigned byte array
   */
  public static UnsignedByteArray createFolding(final byte[] array) {
    return create(array, 0, array.length, true);
  }
  
  /**
   * Create UnsignedByteArray from UnsignedByteArray.
   * 
   * @param uba byte array
   * @param offset array offset
   * @param length array length
   * @return unsigned byte array
   */
  public static UnsignedByteArray create
      (final UnsignedByteArray uba, final int offset, final int length) {
    byte[] ba = uba.array;
    int realOffset = (offset + uba.offset) % ba.length;
    return create(ba, realOffset, length, false);
  }
  
  /**
   * Create folding UnsignedByteArray from UnsignedByteArray.
   * 
   * @param uba byte array
   * @param offset array offset
   * @param length array length
   * @return unsigned byte array
   */
  public static UnsignedByteArray createFolding
      (final UnsignedByteArray uba, final int offset, final int length) {
    byte[] ba = uba.array;
    int realOffset = (offset + uba.offset) % ba.length;
    return create(ba, realOffset, length, true);
  }
  
  /**
   * Create UnsignedByteArray from raw array.
   * 
   * @param array raw byte array
   * @param offset array offset
   * @param length array length
   * @return unsigned byte array
   */
  public static UnsignedByteArray create
      (final byte[] array, final int offset, final int length) {
    return create(array, offset, length, false);
  }
  
  /**
   * Create folding UnsignedByteArray from raw array.
   * 
   * @param array raw byte array
   * @param offset array offset
   * @param length array length
   * @return unsigned byte array
   */
  public static UnsignedByteArray createFolding(
      final byte[] array, final int offset, final int length) {
    return create(array, offset, length, true);
  }
  
  private static UnsignedByteArray create(
      final byte[] array, 
      final int offset, 
      final int length, 
      final boolean folding) {
    checkParameters(array, offset, length);
    return new UnsignedByteArray(array, offset, length, folding);
  }
  
  private static void checkParameters(
      final byte[] array, final int offset, final int length) {
    if ((length < 0) || (length > array.length)) {
      throw new IllegalArgumentException("length: " + length);
    }
  }
  
  private static int normalizeIndex(final int index, final int length){
    int newIndex = index;
    if (index >= length) {
      newIndex = index % length;
    }
    if (index < 0) {
      newIndex = index % length + length;
    }
    return newIndex;
  }
}
