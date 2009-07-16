/*
 * Packet.java
 *
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */

package ie.ucd.sensetile.sensorboard.driver;

import java.util.Date;

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
  public final static byte[] PATTERN = {
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
  
  public Date getDate() {
    // TODO te be implemented
    return null;
  }
  
  public int getDateRaw() {
    // TODO te be implemented
    return -1;
  }
  
  /**
   * field position: index
   */
  public final static int INDEX_POSITION = 20;
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketI#getIndex()
   */
  public int getIndex() {
    return raw.getUnsignedShort(INDEX_POSITION);
  }
  
  void setIndex(int value) {
    raw.setUnsignedShort(INDEX_POSITION, value);
  }
  
  /**
   * field position: temperature
   */
  public final static int TEMPERATURE_POSITION = 4;
  
  public int getTemperature() {
    // TODO te be implemented
    return getTemperatureRaw();
  }

  public int getTemperatureRaw() {
    return raw.getUnsignedShort(TEMPERATURE_POSITION);
  }
  
  /**
   * field position: pressure
   */
  public final static int PRESSURE_POSITION = 6;
  
  public int getPressure() {
    // TODO te be implemented
    return getPressureRaw();
  }
  
  public int getPressureRaw() {
    return raw.getUnsignedShort(PRESSURE_POSITION);
  }
  
  /**
   * field position: light level
   */
  public final static int LIGHT_LEVEL_POSITION = 8;
  
  public int getLightLevel() {
    // TODO te be implemented
    return getLighLevelRaw();
  }

  public int getLighLevelRaw() {
    return raw.getUnsignedShort(LIGHT_LEVEL_POSITION);
  }
  
  /**
   * field position: accelerometer x
   */
  public final static int ACCELEROMETER_X_POSITION = 10;
  
  public int getAccelerometerX() {
    // TODO te be implemented
    return getAccelerometerXRaw();
  }

  public int getAccelerometerXRaw() {
    return raw.getUnsignedShort(ACCELEROMETER_X_POSITION);
  }

  /**
   * field position: accelerometer y
   */
  public final static int ACCELEROMETER_Y_POSITION = 12;
  
  public int getAccelerometerY() {
    // TODO te be implemented
    return getAccelerometerYRaw();
  }

  public int getAccelerometerYRaw() {
    return raw.getUnsignedShort(ACCELEROMETER_Y_POSITION);
  }

  /**
   * field position: accelerometer z
   */
  public final static int ACCELEROMETER_Z_POSITION = 14;
  
  public int getAccelerometerZ() {
    // TODO te be implemented
    return getAccelerometerZRaw();
  }

  public int getAccelerometerZRaw() {
    return raw.getUnsignedShort(ACCELEROMETER_Z_POSITION);
  }

  /**
   * field position: supply voltage
   */
  public final static int SUPPLY_VOLTAGE_POSITION = 20;

  public int getSupplyVoltage() {
    // TODO te be implemented
    return getSupplyVoltageRaw();
  }
  
  public int getSupplyVoltageRaw() {
    return raw.getUnsignedShort(SUPPLY_VOLTAGE_POSITION);
  }
  
  /**
   * field position: supply current
   */
  public final static int SUPPLY_CURRENT_POSITION = 20;
  
  public int getSupplyCurrent() {
    // TODO te be implemented
    return getSupplyCurrentRaw();
  }
  
  public int getSupplyCurrentRaw() {
    return raw.getUnsignedShort(PRESSURE_POSITION);
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
    if (!((previous.getIndex() + 1) == current.getIndex())) {
      throw new SenseTileException(
          "Packet indexes are not sequential: " + 
          "previous is " + previous.getIndex() + ", " + 
          "current is" + current.getIndex() + ".");
    }
  }
}
