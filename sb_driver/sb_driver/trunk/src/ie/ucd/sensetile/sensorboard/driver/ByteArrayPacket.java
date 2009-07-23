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
public class ByteArrayPacket implements SensorBoardPacket {
  
  /**
   * packet bytes length
   */
  public final static int LENGTH = 1024;
  
  /**
   * packet pattern
   */
  final static byte[] PATTERN = {
      (byte) 0xff, (byte) 0xee, 
      (byte) 0xff, (byte) 0xee, 
      (byte) 0xff, (byte) 0xee, 
      (byte) 0xff, (byte) 0xff, 
      (byte) 0xff, (byte) 0xff };
  public final static int PATTERN_OFFSET = 1018;
  
  /**
   * field position: date
   */
  public final static int DATE_POSITION = -1;
  
  /**
   * field position: index
   */
  public final static int COUNTER_POSITION = 20;
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketI#getIndex()
   */
  public char getCounter() {
    return (char) raw.getShortUnsigned(COUNTER_POSITION);
  }
  
  void setCounter(int value) {
    raw.setShortUnsigned(COUNTER_POSITION, value);
  }
  
  /**
   * field position: temperature
   */
  public final static int TEMPERATURE_POSITION = 4;
  
  public short getTemperature() {
    return (short) raw.get12BitsSigned(TEMPERATURE_POSITION);
  }
  
  void setTemperature(int value) {
    raw.set12BitsSigned(TEMPERATURE_POSITION, value);
  }
  
  /**
   * field position: pressure
   */
  public final static int PRESSURE_POSITION = 6;
  
  public short getPressure() {
    return (short) raw.getShortUnsigned(PRESSURE_POSITION);
  }
  
  void setPressure(int value) {
    raw.setShortUnsigned(PRESSURE_POSITION, value);
  }
  
  /**
   * field position: light level
   */
  public final static int LIGHT_LEVEL_POSITION = 8;
  
  public short getLighLevel() {
    return (short) raw.getShortUnsigned(LIGHT_LEVEL_POSITION);
  }
  
  void setLighLevel(int value) {
    raw.setShortUnsigned(LIGHT_LEVEL_POSITION, value);
  }
  
  /**
   * field position: accelerometer x
   */
  public final static int ACCELEROMETER_X_POSITION = 10;
  
  public short getAccelerometerX() {
    return (short) raw.getShortUnsigned(ACCELEROMETER_X_POSITION);
  }
  
  void setAccelerometerX(int value) {
    raw.setShortUnsigned(ACCELEROMETER_X_POSITION, value);
  }
  
  /**
   * field position: accelerometer y
   */
  public final static int ACCELEROMETER_Y_POSITION = 12;
  
  public short getAccelerometerY() {
    return (short) raw.getShortUnsigned(ACCELEROMETER_Y_POSITION);
  }

  void setAccelerometerY(int value) {
    raw.setShortUnsigned(ACCELEROMETER_Y_POSITION, value);
  }
  
  /**
   * field position: accelerometer z
   */
  public final static int ACCELEROMETER_Z_POSITION = 14;
  
  public short getAccelerometerZ() {
    return (short) raw.getShortUnsigned(ACCELEROMETER_Z_POSITION);
  }

  void setAccelerometerZ(int value) {
    raw.setShortUnsigned(ACCELEROMETER_Z_POSITION, value);
  }
  
  /**
   * field position: supply voltage
   */
  public final static int SUPPLY_VOLTAGE_POSITION = 16;

  public int getSupplyVoltage() {
    return raw.getShortUnsigned(SUPPLY_VOLTAGE_POSITION);
  }
  
  void setSupplyVoltage(int value) {
    raw.setShortUnsigned(SUPPLY_VOLTAGE_POSITION, value);
  }
  
  /**
   * field position: supply current
   */
  public final static int SUPPLY_CURRENT_POSITION = 18;
  
  public int getSupplyCurrent() {
    return raw.getShortUnsigned(SUPPLY_CURRENT_POSITION);
  }
  
  void setSupplyCurrent(int value) {
    raw.setShortUnsigned(SUPPLY_CURRENT_POSITION, value);
  }
  
  /**
   * field position: hours
   */
  public final static int HOURS_POSITION = 20;
  
  /**
   * field position: minutes
   */
  public final static int MINUTES_POSITION = 21;
  
  /**
   * field position: seconds
   */
  public final static int SECONDS_POSITION = 22;
  
  /**
   * field position: centiseconds
   */
  public final static int CENTISECONDS_POSITION = 23;
  
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
   * frame position: 
   */
  public final static int ADC_DATA_OFFSET = 34;
  
  /**
  * frame length
   */
  public final static int FRAME_LENGTH = 12;
  
  public ByteArrayFrame getFrame(int index) {
    return new ByteArrayFrame(index);
  }
  
  
  
  private final UnsignedByteArray raw;
  
  ByteArrayPacket(UnsignedByteArray raw) {
    this.raw = raw;
  }
  
  public static SensorBoardPacket createPacket(
      final byte[] rawPacket, 
      SensorBoardPacket previousPacket) 
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
      UnsignedByteArray raw, 
      SensorBoardPacket previousPacket) 
      throws SenseTileException {
    SensorBoardPacket packet = createPacket(raw);
    checkIndex(previousPacket, packet);
    return packet;
  }
  
  public static ByteArrayPacket createPacket(
      UnsignedByteArray raw) 
      throws SenseTileException {
    checkLength(raw);
    UnsignedByteArray newRaw = UnsignedByteArray.create(raw, 0, LENGTH);
    checkPattern(newRaw);
    return new ByteArrayPacket(newRaw);
  }
  
  private static void checkLength(UnsignedByteArray raw) 
      throws SenseTileException {
    if (raw.length() < LENGTH) {
      throw new SenseTileException("Wrong packet length.");
    }
  }

  private static void checkPattern(UnsignedByteArray raw) 
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
  
  static void checkIndex(SensorBoardPacket previous, SensorBoardPacket current) 
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
  
  public class ByteArrayFrame implements Frame{
    
    private final UnsignedByteArray rawFrame; 
    
    public ByteArrayFrame(int index) {
      rawFrame = UnsignedByteArray.create(
          getRaw(), ADC_DATA_OFFSET + FRAME_LENGTH * index, FRAME_LENGTH);
    }
    
    /**
     * IRD synchronisation bit position: 
     */
    public final static int IRD_BIT_POSITION = 2;
    
    public boolean isIRDSynachronizationActive() {
      return getBit(IRD_BIT_POSITION);
    }
    
    void setIRDSynachronizationActive(boolean value) {
      setBit(IRD_BIT_POSITION, value);
    }
    
    /**
     * audio enable bit position: 
     */
    public final static int AUDIO_ENABLE_BIT_POSITION = 0;
    
    /**
     * audio valid bit position: 
     */
    public final static int AUDIO_VALID_BIT_POSITION = 1;
    
    public boolean isAudioActive() {
      return 
          getBit(AUDIO_ENABLE_BIT_POSITION) && 
          getBit(AUDIO_VALID_BIT_POSITION);
    }
    
    void setAudioActive(boolean value) {
      setBit(AUDIO_ENABLE_BIT_POSITION, value);
      setBit(AUDIO_VALID_BIT_POSITION, value);
    }
    
    /**
     * audio frequency bit position and length:
     */
    public final static int AUDIO_FREQUENCY_BIT_POSITION = 14;
    public final static int AUDIO_FREQUENCY_BIT_LENGTH = 15;
    
    public int getAudioFrequency() {
      return getBits(
          AUDIO_FREQUENCY_BIT_POSITION, 
          AUDIO_FREQUENCY_BIT_LENGTH);
    }
    
    void setAudioFrequency(int frequency) {
      setBits(
          AUDIO_FREQUENCY_BIT_POSITION, 
          AUDIO_FREQUENCY_BIT_LENGTH, 
          frequency);
    }
    
    /**
     * audio position: 
     */
    public final static int AUDIO_POSITION = 4;
    
    public char getAudio(int channel) {
      if (channel < 0 || channel >= 4) {
        throw new IndexOutOfBoundsException();
      }
      return (char) rawFrame.getShortUnsigned(ADC_POSITION + channel * 2);
    }
    
    public void setAudio(int channel, int value) {
      if (channel < 0 || channel >= 4) {
        throw new IndexOutOfBoundsException();
      }
      rawFrame.setShortUnsigned(ADC_POSITION + channel * 2, value);
    }
    
    /**
     * ADC enable bit position: 
     */
    public final static int ADC_ENABLE_BIT_POSITION = 5;
    
    /**
     * ADC valid bit position: 
     */
    public final static int ADC_VALID_BIT_POSITION = 7;
    
    /**
     * ADC used bit position: 
     */
    public final static int ADC_USED_BIT_POSITION = 6;
    
    public boolean isADCActive() {
      return 
          getBit(ADC_ENABLE_BIT_POSITION) && 
          getBit(ADC_VALID_BIT_POSITION) &&
          getBit(ADC_USED_BIT_POSITION);
    }
    
    void setADCActive(boolean value) {
      setBit(ADC_ENABLE_BIT_POSITION, value);
      setBit(ADC_VALID_BIT_POSITION, value);
      setBit(ADC_USED_BIT_POSITION, value);
    }
    
    /**
     * ADC channel bit position and length: 
     */
    public final static int ADC_CHANNEL_BIT_POSITION = 8;
    public final static int ADC_CHANNEL_BIT_LENGTH = 3;
    
    public int getADCChannel() {
      return getBits(
          ADC_CHANNEL_BIT_POSITION, 
          ADC_CHANNEL_BIT_LENGTH);
    }
    
    void setADCChannel(int channel) {
      setBits(
          ADC_CHANNEL_BIT_POSITION, 
          ADC_CHANNEL_BIT_LENGTH, 
          channel);
    }
    
    /**
     * ADC position: 
     */
    public final static int ADC_POSITION = 2;
    
    public char getADC() {
      return (char) rawFrame.getShortUnsigned(ADC_POSITION);
    }
    
    void setADC(int value) {
      rawFrame.setShortUnsigned(ADC_POSITION, value);
    }
    
    boolean getBit(int index) {
      return rawFrame.getBit(1 - index / 8, index % 8);
    }
    
    void setBit(int index, boolean value) {
      rawFrame.setBit(1 - index / 8, index % 8, value);
    }
    
    int getBits(int index, int length) {
      int mask = (int) (Math.pow(2, length) - 1) << index;
      int value = ((mask & rawFrame.getShortUnsigned(0)) >>> index) & 0xFFFF;
      return value;
    }
    
    void setBits(int index, int length, int value) {
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
