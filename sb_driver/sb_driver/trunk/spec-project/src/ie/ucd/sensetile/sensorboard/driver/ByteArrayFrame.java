/*
 * ByteArrayFrame.java
 *
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */
package ie.ucd.sensetile.sensorboard.driver;

//@ model import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.Frame;
import ie.ucd.sensetile.util.UnsignedByteArray;

public final class ByteArrayFrame implements Frame{
  
  private final UnsignedByteArray rawFrame; 
  
  /*@ 
    @ requires index >= 0;
    @ requires index < Packet.FRAMES;
    @*/
  public ByteArrayFrame(ByteArrayPacket byteArrayPacket, final int index) {
    UnsignedByteArray raw = byteArrayPacket.getRaw();
    rawFrame = UnsignedByteArray.create(
        raw, 
        ByteArrayPacket.ADC_DATA_OFFSET + ByteArrayPacket.FRAME_LENGTH * index, 
        ByteArrayPacket.FRAME_LENGTH);
  }
  
  /**
   * IRD synchronisation bit position. 
   */
  public static final int IRD_BIT_POSITION = 2;
  
  public boolean isIRDSynachronizationActive() {
    return getBit(IRD_BIT_POSITION);
  }
  
  void setIRDSynachronizationActive(final boolean value) {
    setBit(IRD_BIT_POSITION, value);
  }
  
  /**
   * audio enable bit position. 
   */
  public static final int AUDIO_ENABLE_BIT_POSITION = 0;
  
  /**
   * audio valid bit position. 
   */
  public static final int AUDIO_VALID_BIT_POSITION = 1;
  
  public boolean isAudioActive() {
    return 
        getBit(AUDIO_ENABLE_BIT_POSITION) && 
        getBit(AUDIO_VALID_BIT_POSITION);
  }
  
  void setAudioActive(final boolean value) {
    setBit(AUDIO_ENABLE_BIT_POSITION, value);
    setBit(AUDIO_VALID_BIT_POSITION, value);
  }
  
  /**
   * audio frequency bit position and length.
   */
  public static final int AUDIO_FREQUENCY_BIT_POSITION = 14;
  public static final int AUDIO_FREQUENCY_BIT_LENGTH = 15;
  
  public int getAudioFrequency() {
    return getBits(
        AUDIO_FREQUENCY_BIT_POSITION, 
        AUDIO_FREQUENCY_BIT_LENGTH);
  }
  
  void setAudioFrequency(final int frequency) {
    setBits(
        AUDIO_FREQUENCY_BIT_POSITION, 
        AUDIO_FREQUENCY_BIT_LENGTH, 
        frequency);
  }
  
  /**
   * audio position.
   */
  public static final int AUDIO_POSITION = 4;
  
  public char getAudio(final int channel) {
    if (channel < 0 || channel >= AUDIO_CHANNELS) {
      throw new IndexOutOfBoundsException();
    }
    return (char) rawFrame.getShortUnsigned(ADC_POSITION + channel * 2);
  }
  
  void setAudio(final int channel, final int value) {
    if (channel < 0 || channel >= AUDIO_CHANNELS) {
      throw new IndexOutOfBoundsException();
    }
    rawFrame.setShortUnsigned(ADC_POSITION + channel * 2, value);
  }
  
  /**
   * ADC enable bit position.
   */
  public static final int ADC_ENABLE_BIT_POSITION = 5;
  
  /**
   * ADC valid bit position.
   */
  public static final int ADC_VALID_BIT_POSITION = 7;
  
  /**
   * ADC used bit position.
   */
  public static final int ADC_USED_BIT_POSITION = 6;
  
  public boolean isADCActive() {
    return 
        getBit(ADC_ENABLE_BIT_POSITION) && 
        getBit(ADC_VALID_BIT_POSITION) &&
        getBit(ADC_USED_BIT_POSITION);
  }
  
  void setADCActive(final boolean value) {
    setBit(ADC_ENABLE_BIT_POSITION, value);
    setBit(ADC_VALID_BIT_POSITION, value);
    setBit(ADC_USED_BIT_POSITION, value);
  }
  
  /**
   * ADC channel bit position and length.
   */
  public static final int ADC_CHANNEL_BIT_POSITION = 8;
  public static final int ADC_CHANNEL_BIT_LENGTH = 3;
  
  public int getADCChannel() {
    return getBits(
        ADC_CHANNEL_BIT_POSITION, 
        ADC_CHANNEL_BIT_LENGTH);
  }
  
  void setADCChannel(final int channel) {
    setBits(
        ADC_CHANNEL_BIT_POSITION, 
        ADC_CHANNEL_BIT_LENGTH, 
        channel);
  }
  
  /**
   * ADC position.
   */
  public static final int ADC_POSITION = 2;
  
  public char getADC() {
    return (char) rawFrame.getShortUnsigned(ADC_POSITION);
  }
  
  void setADC(final int value) {
    rawFrame.setShortUnsigned(ADC_POSITION, value);
  }
  
  boolean getBit(final int index) {
    return rawFrame.getBit(1 - index / 8, index % 8);
  }
  
  void setBit(final int index, final boolean value) {
    rawFrame.setBit(1 - index / 8, index % 8, value);
  }
  
  int getBits(final int index, final int length) {
    int mask = (int) (Math.pow(2, length) - 1) << index;
    int value = ((mask & rawFrame.getShortUnsigned(0)) >>> index) & 0xFFFF;
    return value;
  }
  
  void setBits(final int index, final int length, final int value) {
    if (value < 0 || value >= (int) (Math.pow(2, length))) {
      throw new IllegalArgumentException();
    }
    int mask = ~ (
          ((int) (Math.pow(2, length) - 1)) 
          << index);
    int oldValueWithHole = rawFrame.getShortUnsigned(0) & mask;
    int newValue = oldValueWithHole | (value<<index);
    rawFrame.setShortUnsigned(0, newValue);
  }
}