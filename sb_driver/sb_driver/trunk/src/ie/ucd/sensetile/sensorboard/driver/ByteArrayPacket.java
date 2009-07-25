/*
 * Packet.java
 *
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */

package ie.ucd.sensetile.sensorboard.driver;

import ie.ucd.sensetile.sensorboard.SenseTileException;
import ie.ucd.sensetile.sensorboard.SensorBoardPacket;
import ie.ucd.sensetile.util.BytePattern;
import ie.ucd.sensetile.util.UnsignedByteArray;

/**
 * @author delbianc
 *
 */
public final class ByteArrayPacket implements SensorBoardPacket {
  
  /**
   * packet bytes length.
   */
  public static final int LENGTH = 1024;
  
  /**
   * packet pattern.
   */
  static final byte[] PATTERN = {
      (byte) 0xff, (byte) 0xee, 
      (byte) 0xff, (byte) 0xee, 
      (byte) 0xff, (byte) 0xee, 
      (byte) 0xff, (byte) 0xff, 
      (byte) 0xff, (byte) 0xff };
  
  /**
   * packet pattern offset.
   */
  public static final int PATTERN_OFFSET = 1018;
  
  /**
   * field position: date.
   */
  public static final int DATE_POSITION = -1;
  
  /**
   * field position: index.
   */
  public static final int COUNTER_POSITION = 20;
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketI#getIndex()
   */
  public char getCounter() {
    return (char) raw.getShortUnsigned(COUNTER_POSITION);
  }
  
  void setCounter(final int value) {
    raw.setShortUnsigned(COUNTER_POSITION, value);
  }
  
  /**
   * field position: temperature.
   */
  public static final int TEMPERATURE_POSITION = 4;
  
  public short getTemperature() {
    return (short) raw.get12BitsSigned(TEMPERATURE_POSITION);
  }
  
  void setTemperature(final int value) {
    raw.set12BitsSigned(TEMPERATURE_POSITION, value);
  }
  
  /**
   * field position: pressure.
   */
  public static final int PRESSURE_POSITION = 6;
  
  public short getPressure() {
    return (short) raw.getShortUnsigned(PRESSURE_POSITION);
  }
  
  void setPressure(final int value) {
    raw.setShortUnsigned(PRESSURE_POSITION, value);
  }
  
  /**
   * field position: light level.
   */
  public static final int LIGHT_LEVEL_POSITION = 8;
  
  public short getLighLevel() {
    return (short) raw.getShortUnsigned(LIGHT_LEVEL_POSITION);
  }
  
  void setLighLevel(final int value) {
    raw.setShortUnsigned(LIGHT_LEVEL_POSITION, value);
  }
  
  /**
   * field position: accelerometer x.
   */
  public static final int ACCELEROMETER_X_POSITION = 10;
  
  public short getAccelerometerX() {
    return (short) raw.getShortUnsigned(ACCELEROMETER_X_POSITION);
  }
  
  void setAccelerometerX(final int value) {
    raw.setShortUnsigned(ACCELEROMETER_X_POSITION, value);
  }
  
  /**
   * field position: accelerometer y.
   */
  public static final int ACCELEROMETER_Y_POSITION = 12;
  
  public short getAccelerometerY() {
    return (short) raw.getShortUnsigned(ACCELEROMETER_Y_POSITION);
  }

  void setAccelerometerY(final int value) {
    raw.setShortUnsigned(ACCELEROMETER_Y_POSITION, value);
  }
  
  /**
   * field position: accelerometer z.
   */
  public static final int ACCELEROMETER_Z_POSITION = 14;
  
  public short getAccelerometerZ() {
    return (short) raw.getShortUnsigned(ACCELEROMETER_Z_POSITION);
  }

  void setAccelerometerZ(final int value) {
    raw.setShortUnsigned(ACCELEROMETER_Z_POSITION, value);
  }
  
  /**
   * field position: supply voltage.
   */
  public static final int SUPPLY_VOLTAGE_POSITION = 16;

  public int getSupplyVoltage() {
    return raw.getShortUnsigned(SUPPLY_VOLTAGE_POSITION);
  }
  
  void setSupplyVoltage(final int value) {
    raw.setShortUnsigned(SUPPLY_VOLTAGE_POSITION, value);
  }
  
  /**
   * field position: supply current.
   */
  public static final int SUPPLY_CURRENT_POSITION = 18;
  
  public int getSupplyCurrent() {
    return raw.getShortUnsigned(SUPPLY_CURRENT_POSITION);
  }
  
  void setSupplyCurrent(final int value) {
    raw.setShortUnsigned(SUPPLY_CURRENT_POSITION, value);
  }
  
  /**
   * field position: hours.
   */
  public static final int HOURS_POSITION = 20;
  
  /**
   * field position: minutes.
   */
  public static final int MINUTES_POSITION = 21;
  
  /**
   * field position: seconds.
   */
  public static final int SECONDS_POSITION = 22;
  
  /**
   * field position: centiseconds.
   */
  public static final int CENTISECONDS_POSITION = 23;
  
  public Time getTime() {
    return new Time(){
      public byte getHours() {
        return getRaw().getByte(HOURS_POSITION);
      };
      
      public byte getMinutes() {
        return getRaw().getByte(MINUTES_POSITION);
      };
      
      public byte getSeconds() {
        return getRaw().getByte(SECONDS_POSITION);
      };
      
      public byte getCentiSeconds() {
        return getRaw().getByte(CENTISECONDS_POSITION);
      };
    };
  }
  
  /**
   * frame position. 
   */
  public static final int ADC_DATA_OFFSET = 34;
  
  /**
  * frame length.
   */
  public static final int FRAME_LENGTH = 12;
  
  public ByteArrayFrame getFrame(final int index) {
    return new ByteArrayFrame(index);
  }
  
  
  
  private final UnsignedByteArray raw;
  
  ByteArrayPacket(final UnsignedByteArray raw) {
    this.raw = raw;
  }
  
  public static SensorBoardPacket createPacket(
      final byte[] rawPacket, 
      final SensorBoardPacket previousPacket) 
      throws SenseTileException {
    SensorBoardPacket packet = createPacket(rawPacket);
    checkIndex(previousPacket, packet);
    return packet;
  }
  public static ByteArrayPacket createPacket(
      final byte[] rawPacket) 
      throws SenseTileException {
    UnsignedByteArray raw = UnsignedByteArray.create(rawPacket);
    return createPacket(raw);
  }
  
  public static SensorBoardPacket createPacket(
      final UnsignedByteArray raw, 
      final SensorBoardPacket previousPacket) 
      throws SenseTileException {
    SensorBoardPacket packet = createPacket(raw);
    checkIndex(previousPacket, packet);
    return packet;
  }
  
  public static ByteArrayPacket createPacket(
      final UnsignedByteArray raw) 
      throws SenseTileException {
    checkLength(raw);
    UnsignedByteArray newRaw = UnsignedByteArray.create(raw, 0, LENGTH);
    checkPattern(newRaw);
    return new ByteArrayPacket(newRaw);
  }
  
  private static void checkLength(final UnsignedByteArray raw) 
      throws SenseTileException {
    if (raw.length() < LENGTH) {
      throw new SenseTileException("Wrong packet length.");
    }
  }

  private static void checkPattern(final UnsignedByteArray raw) 
      throws SenseTileException {
    BytePattern bp = BytePattern.createPattern(PATTERN);
    int relativeOffset = bp.match(UnsignedByteArray.create(raw, PATTERN_OFFSET, raw.length()));
    if (relativeOffset == -1) {
      throw new SenseTileException("Packet pattern not found.");
    }
    if (relativeOffset != 0) {
      throw new SenseTileException("Packet pattern misplaced.");
    }
  }
  
  static void checkIndex(
      final SensorBoardPacket previous, final SensorBoardPacket current) 
  throws SenseTileException {
    if (!((previous.getCounter() + 1) == current.getCounter())) {
      throw new SenseTileException(
          "Packet indexes are not sequential: " + 
          "previous is " + previous.getCounter() + ", " + 
          "current is" + current.getCounter() + ".");
    }
  }

  UnsignedByteArray getRaw() {
    return raw;
  }
  
  public final class ByteArrayFrame implements Frame{
    
    private final UnsignedByteArray rawFrame; 
    
    public ByteArrayFrame(final int index) {
      rawFrame = UnsignedByteArray.create(
          getRaw(), ADC_DATA_OFFSET + FRAME_LENGTH * index, FRAME_LENGTH);
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
      if (channel < 0 || channel >= 4) {
        throw new IndexOutOfBoundsException();
      }
      return (char) rawFrame.getShortUnsigned(ADC_POSITION + channel * 2);
    }
    
    public void setAudio(final int channel, final int value) {
      if (channel < 0 || channel >= 4) {
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
}
