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
final public class UnsignedByteArray {
  
  private byte[] array;
  private int offset;
  private int length;
  private boolean folding;
  
  private UnsignedByteArray(
      byte[] array, int offset, int length, boolean folding) {
    this.array = array;
    this.length = length;
    this.offset = 
      (array.length == 0) ? 0 : normalizeIndex(offset, array.length);
    this.folding = folding;
  }
  
  /**
   * Get byte.
   * 
   * @param index
   * @return byte
   */
  public byte getByte(int index){
    int newIndex = checkAndNormalizeIndex(index, 1);
    return array[(newIndex + offset) % array.length];
  }
  
  /**
   * Set byte.
   * 
   * @param index
   * @param value
   */
  public void setByte(int index, byte value){
    int newIndex = checkAndNormalizeIndex(index, 1);
    array[(newIndex + offset) % array.length] = value;
  }
  
  /**
   * Get bit.
   * 
   * @param index
   * @param bit index
   * @return
   */
  public boolean getBit(int index, int bitIndex) {
    if (bitIndex < 0 || bitIndex >= 8) {
      throw new IndexOutOfBoundsException();
    }
    int newIndex = checkAndNormalizeIndex(index, 1);
    return (array[newIndex] & (1<<bitIndex)) > 0;
  }
  
  /**
   * Set bit.
   * 
   * @param index
   * @param bit index
   * @param bit value
   */
  public void setBit(int index, int bitIndex, boolean value) {
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
   * @param index
   * @return signed 12 bits
   */
  public int get12BitsSigned(int index) {
    return (getShortUnsigned(index) << 20) >> 20;
  }
  
  /**
   * Set signed 12 bits.
   * 
   * @param index
   * @param value
   */
  public void set12BitsSigned(int index, int value) {
    setShortUnsigned(index, (value << 20) >>> 20);
  }
  
  /**
   * Get unsigned short.
   * 
   * @param index
   * @return unsigned short
   */
  public int getShortUnsigned(int index) {
    int newIndex = checkAndNormalizeIndex(index, 2);
    return 
      ((0xff & getByte(newIndex)) << 8) | 
      (0xff & getByte(newIndex+1));
  }
  
  /**
   * Set unsigned short.
   * 
   * @param index
   * @param value
   */
  public void setShortUnsigned(int index, int value) {
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
   * Begin offset
   * 
   * @return offset
   */
  public int getBeginOffset() {
    return offset;
  }
  
  /**
   * End offset
   * 
   * @return offset
   */
  public int getEndOffset() {
    return UnsignedByteArray.normalizeIndex(
        getBeginOffset() + length(), array.length);
  }
  
  /**
   * Internal raw array
   * 
   * @return array
   */
  public byte[] getArray() {
    return array;
  }
  
  private int checkAndNormalizeIndex(int index, int operationLength){
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
   * @param array
   * @return unsigned byte array
   */
  public static UnsignedByteArray create(byte[] array) {
    return create(array, 0, array.length, false);
  }
  
  /**
   * Create folding UnsignedByteArray from raw array.
   * 
   * @param array
   * @return unsigned byte array
   */
  public static UnsignedByteArray createFolding(byte[] array) {
    return create(array, 0, array.length, true);
  }
  
  /**
   * Create UnsignedByteArray from UnsignedByteArray.
   * 
   * @param array
   * @param offset
   * @param length
   * @return unsigned byte array
   */
  public static UnsignedByteArray create
      (UnsignedByteArray uba, int offset, int length) {
    byte[] ba = uba.array;
    int realOffset = (offset + uba.offset) % ba.length;
    return create(ba, realOffset, length, false);
  }
  
  /**
   * Create folding UnsignedByteArray from UnsignedByteArray.
   * 
   * @param array
   * @param offset
   * @param length
   * @return unsigned byte array
   */
  public static UnsignedByteArray createFolding
      (UnsignedByteArray uba, int offset, int length) {
    byte[] ba = uba.array;
    int realOffset = (offset + uba.offset) % ba.length;
    return create(ba, realOffset, length, true);
  }
  
  /**
   * Create UnsignedByteArray from raw array.
   * 
   * @param array
   * @param offset
   * @param length
   * @return unsigned byte array
   */
  public static UnsignedByteArray create
      (byte[] array, int offset, int length) {
    return create(array, offset, length, false);
  }
  
  /**
   * Create folding UnsignedByteArray from raw array.
   * 
   * @param array
   * @param offset
   * @param length
   * @return unsigned byte array
   */
  public static UnsignedByteArray createFolding
      (byte[] array, int offset, int length) {
    return create(array, offset, length, true);
  }
  
  private static UnsignedByteArray create
      (byte[] array, int offset, int length, boolean folding) {
    checkParameters(array, offset, length);
    return new UnsignedByteArray(array, offset, length, folding);
  }
  
  private static void checkParameters(byte[] array, int offset, int length) {
    if ((length < 0) || (length > array.length)) {
      throw new IllegalArgumentException("length: " + length);
    }
  }
  
  private static int normalizeIndex(int index, int length){
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
