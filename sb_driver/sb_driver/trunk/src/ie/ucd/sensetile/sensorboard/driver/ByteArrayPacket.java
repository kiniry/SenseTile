/*
 * Packet.java
 *
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */

package ie.ucd.sensetile.sensorboard.driver;

import ie.ucd.sensetile.sensorboard.SensorBoardPacket;
import ie.ucd.sensetile.sensorboard.SenseTileException;
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
  public int getCounter() {
    return raw.getShortUnsigned(COUNTER_POSITION);
  }
  
  void setCounter(int value) {
    raw.setShortUnsigned(COUNTER_POSITION, value);
  }
  
  /**
   * field position: temperature
   */
  public final static int TEMPERATURE_POSITION = 4;
  
  public int getTemperatureRaw() {
    return raw.get12BitsSigned(TEMPERATURE_POSITION);
  }
  
  void setTemperatureRaw(int value) {
    raw.set12BitsSigned(TEMPERATURE_POSITION, value);
  }
  
  /**
   * field position: pressure
   */
  public final static int PRESSURE_POSITION = 6;
  
  public int getPressureRaw() {
    return raw.getShortUnsigned(PRESSURE_POSITION);
  }
  
  void setPressureRaw(int value) {
    raw.setShortUnsigned(PRESSURE_POSITION, value);
  }
  
  /**
   * field position: light level
   */
  public final static int LIGHT_LEVEL_POSITION = 8;
  
  public int getLighLevelRaw() {
    return raw.getShortUnsigned(LIGHT_LEVEL_POSITION);
  }
  
  public void setLighLevelRaw(int value) {
    raw.setShortUnsigned(LIGHT_LEVEL_POSITION, value);
  }
  
  /**
   * field position: accelerometer x
   */
  public final static int ACCELEROMETER_X_POSITION = 10;
  
  public int getAccelerometerXRaw() {
    return raw.getShortUnsigned(ACCELEROMETER_X_POSITION);
  }
  
  public void setAccelerometerXRaw(int value) {
    raw.setShortUnsigned(ACCELEROMETER_X_POSITION, value);
  }
  
  /**
   * field position: accelerometer y
   */
  public final static int ACCELEROMETER_Y_POSITION = 12;
  
  public int getAccelerometerYRaw() {
    return raw.getShortUnsigned(ACCELEROMETER_Y_POSITION);
  }

  public void setAccelerometerYRaw(int value) {
    raw.setShortUnsigned(ACCELEROMETER_Y_POSITION, value);
  }
  
  /**
   * field position: accelerometer z
   */
  public final static int ACCELEROMETER_Z_POSITION = 14;
  
  public int getAccelerometerZRaw() {
    return raw.getShortUnsigned(ACCELEROMETER_Z_POSITION);
  }

  public void setAccelerometerZRaw(int value) {
    raw.setShortUnsigned(ACCELEROMETER_Z_POSITION, value);
  }
  
  /**
   * field position: supply voltage
   */
  public final static int SUPPLY_VOLTAGE_POSITION = 16;

  public int getSupplyVoltageRaw() {
    return raw.getShortUnsigned(SUPPLY_VOLTAGE_POSITION);
  }
  
  /**
   * field position: supply current
   */
  public final static int SUPPLY_CURRENT_POSITION = 18;
  
  public int getSupplyCurrentRaw() {
    return raw.getShortUnsigned(SUPPLY_CURRENT_POSITION);
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
      public int getHours() {
        return getRaw().getByte(HOURS_POSITION);
      };
      
      public int getMinutes() {
        return getRaw().getByte(MINUTES_POSITION);
      };
      
      public int getSeconds() {
        return getRaw().getByte(SECONDS_POSITION);
      };
      
      public int getCentiSeconds() {
        return getRaw().getByte(CENTISECONDS_POSITION);
      };
    };
  }
  
  /**
   * ADC data
   */
  public final static int ADC_DATA_OFFSET = -1;
  
  public int[] getFastADC(int channel) {
    // TODO Auto-generated method stub
    return null;
  }

  public int[] getSlowADC(int channel) {
    // TODO Auto-generated method stub
    return null;
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
}
