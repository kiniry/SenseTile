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
 * @author Vieri del Bianco (vieri.delbianco@gmail.com)
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
  public byte getByte(final int index) {
    final int newIndex = checkAndNormalizeIndex(index, 1);
    return array[(newIndex + offset) % array.length];
  }
  
  /**
   * Set byte.
   * 
   * @param index byte index
   * @param value byte value
   */
  public void setByte(final int index, final byte value) {
    final int newIndex = checkAndNormalizeIndex(index, 1);
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
    if (bitIndex < 0 || bitIndex >= BYTE_BIT_LENGTH) {
      throw new IndexOutOfBoundsException();
    }
    final int newIndex = checkAndNormalizeIndex(index, 1);
    return (array[newIndex] & (1 << bitIndex)) > 0;
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
    if (bitIndex < 0 || bitIndex >= BYTE_BIT_LENGTH) {
      throw new IndexOutOfBoundsException();
    }
    final int newIndex = checkAndNormalizeIndex(index, 1);
    final byte oldByteWithHole = (byte) (array[newIndex] & (~(1 << bitIndex)));
    final byte newValue = (byte) ((value ? 1 : 0) << bitIndex);
    array[newIndex] = (byte) (oldByteWithHole | newValue);
  }
  
  /**
   * Get signed 12 bits.
   * 
   * @param index byte index
   * @return signed 12 bits
   */
  public int get12BitsSigned(final int index) {
    return (getShortUnsigned(index) << OFFSET_12_BITS) >> OFFSET_12_BITS;
  }
  
  /**
   * Set signed 12 bits.
   * 
   * @param index byte index
   * @param value signed 12 bits value
   */
  public void set12BitsSigned(final int index, final int value) {
    setShortUnsigned(index, (value << OFFSET_12_BITS) >>> OFFSET_12_BITS);
  }
  
  /**
   * Get unsigned short.
   * 
   * @param index byte index
   * @return unsigned short
   */
  public int getShortUnsigned(final int index) {
    final int newIndex = checkAndNormalizeIndex(index, 2);
    return 
      ((BYTE_MASK & getByte(newIndex)) << BYTE_BIT_LENGTH) | 
      (BYTE_MASK & getByte(newIndex + 1));
  }
  
  /**
   * Set unsigned short.
   * 
   * @param index byte index
   * @param value unsigned short value
   */
  public void setShortUnsigned(final int index, final int value) {
    final int newIndex = checkAndNormalizeIndex(index, 2);
    setByte(newIndex, (byte) (BYTE_MASK & value >>> BYTE_BIT_LENGTH));
    setByte(newIndex + 1, (byte) (BYTE_MASK & value));
  }
  
  /**
   * Byte array length.
   * 
   * @return length
   */
  public /*@ pure */ int length() {
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
  public byte[] getInternalArray() {
    // @edu.umd.cs.findbugs.annotations.SuppressWarnings("EI_EXPOSE_REP")
    return array;
  }
  
  /**
   * Copy array.
   * 
   * @return array
   */
  public /*@ pure */ byte[] toArray() {
    final byte[] copy = new byte[length()];
    if (getEndOffset() >= getBeginOffset()) {
      System.arraycopy(array, getBeginOffset(), copy, 0, length());
    } else {
      System.arraycopy(
          array, getBeginOffset(), copy, 0, array.length - getBeginOffset());
      System.arraycopy(
          array, 0, copy, array.length - getBeginOffset(), getEndOffset());
    }
    return copy;
  }
  
  private int checkAndNormalizeIndex(
      final int index, final int operationLength) {
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
  public static UnsignedByteArray create(
      final UnsignedByteArray uba, final int offset, final int length) {
    final byte[] ba = uba.array;
    final int realOffset = (offset + uba.offset) % ba.length;
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
  public static UnsignedByteArray createFolding(
      final UnsignedByteArray uba, final int offset, final int length) {
    final byte[] ba = uba.array;
    final int realOffset = (offset + uba.offset) % ba.length;
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
  public static UnsignedByteArray create(
      final byte[] array, final int offset, final int length) {
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
  
  private static int normalizeIndex(final int index, final int length) {
    int newIndex = index;
    if (index >= length) {
      newIndex = index % length;
    }
    if (index < 0) {
      newIndex = index % length + length;
    }
    return newIndex;
  }
  
  private static final int BYTE_BIT_LENGTH = 8; 
  private static final int BYTE_MASK = 0xff;
  private static final int OFFSET_12_BITS = 20;
  
}
