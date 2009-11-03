/*
 * ByteArrayPacket.java
 *
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */
package ie.ucd.sensetile.sensorboard.driver;

import ie.ucd.sensetile.sensorboard.Frame;
import ie.ucd.sensetile.sensorboard.SenseTileException;
import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.util.BytePattern;
import ie.ucd.sensetile.util.UnsignedByteArray;

/**
 * Packet from byte array.
 * 
 * @author Vieri del Bianco (vieri.delbianco@gmail.com)
 */
public final class ByteArrayPacket implements Packet {
  
  /*@
    @ requires raw.length() == LENGTH;
    @*/
  ByteArrayPacket(final UnsignedByteArray raw) {
    this.raw = raw;
  }
  
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
  public static final int COUNTER_POSITION = 30;
  
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
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getTemperature()
   */
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
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getPressure()
   */
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
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getLightLevel()
   */
  public short getLightLevel() {
    return (short) raw.getShortUnsigned(LIGHT_LEVEL_POSITION);
  }
  
  void setLightLevel(final int value) {
    raw.setShortUnsigned(LIGHT_LEVEL_POSITION, value);
  }
  
  /**
   * field position: accelerometer x.
   */
  public static final int ACCELEROMETER_X_POSITION = 10;
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getAccelerometerX()
   */
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
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getAccelerometerY()
   */
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
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getAccelerometerZ()
   */
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

  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getSupplyVoltage()
   */
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
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getSupplyCurrent()
   */
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
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getTime()
   */
  public Time getTime() {
    return new Time() {
      /* (non-Javadoc)
       * @see ie.ucd.sensetile.sensorboard.Packet.Time#getHours()
       */
      public byte getHours() {
        return getRaw().getByte(HOURS_POSITION);
      }
      
      /* (non-Javadoc)
       * @see ie.ucd.sensetile.sensorboard.Packet.Time#getMinutes()
       */
      public byte getMinutes() {
        return getRaw().getByte(MINUTES_POSITION);
      }
      
      /* (non-Javadoc)
       * @see ie.ucd.sensetile.sensorboard.Packet.Time#getSeconds()
       */
      public byte getSeconds() {
        return getRaw().getByte(SECONDS_POSITION);
      }
      
      /* (non-Javadoc)
       * @see ie.ucd.sensetile.sensorboard.Packet.Time#getCentiSeconds()
       */
      public byte getCentiSeconds() {
        return getRaw().getByte(CENTISECONDS_POSITION);
      }
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
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getFrame(int)
   */
  public Frame getFrame(final int index) {
    return new ByteArrayFrame(this.getRaw(), index);
  }
  
  private final UnsignedByteArray raw;
  
  /**
   * Create packet from byte array.
   * 
   * @param rawPacket byte array
   * @param previousPacket previous packet in stream
   * @return packet
   * @throws SenseTileException byte array not valid
   */
  public static Packet createPacket(
      final byte[] rawPacket, 
      final Packet previousPacket) 
      throws SenseTileException {
    final Packet packet = createPacket(rawPacket);
    checkIndex(previousPacket, packet);
    return packet;
  }
  
  /**
   * Create packet from byte array.
   * 
   * @param rawPacket byte array
   * @return packet
   * @throws SenseTileException byte array not valid   */
  public static ByteArrayPacket createPacket(
      final byte[] rawPacket) 
      throws SenseTileException {
    final UnsignedByteArray raw = UnsignedByteArray.create(rawPacket);
    return createPacket(raw);
  }
  
  /**
   * Create packet from byte array.
   * 
   * @param raw byte array
   * @param previousPacket previous packet in stream
   * @return packet
   * @throws SenseTileException byte array not valid
   */
  public static Packet createPacket(
      final UnsignedByteArray raw, 
      final Packet previousPacket) 
      throws SenseTileException {
    final Packet packet = createPacket(raw);
    checkIndex(previousPacket, packet);
    return packet;
  }
  
  /**
   * Create packet from byte array.
   * 
   * @param raw byte array
   * @return packet
   * @throws SenseTileException byte array not valid
   */
  public static ByteArrayPacket createPacket(
      final UnsignedByteArray raw) 
      throws SenseTileException {
    checkLength(raw);
    final UnsignedByteArray newRaw = UnsignedByteArray.create(raw, 0, LENGTH);
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
    final BytePattern bp = BytePattern.createPattern(PATTERN);
    final int relativeOffset = 
      bp.match(UnsignedByteArray.create(raw, PATTERN_OFFSET, raw.length()));
    if (relativeOffset == -1) {
      throw new SenseTileException("Packet pattern not found.");
    }
    if (relativeOffset != 0) {
      throw new SenseTileException("Packet pattern misplaced.");
    }
  }
  
  static void checkIndex(
      final Packet previous, final Packet current) 
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
