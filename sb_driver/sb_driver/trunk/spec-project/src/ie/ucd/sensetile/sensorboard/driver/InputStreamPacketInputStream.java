/*
 * InputStreamPacketInputStream.java
 *
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */
package ie.ucd.sensetile.sensorboard.driver;

import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.PacketInputStream;
import ie.ucd.sensetile.sensorboard.SenseTileException;
import ie.ucd.sensetile.util.BytePattern;
import ie.ucd.sensetile.util.UnsignedByteArray;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PacketInputStream built on real senor board InputStream.
 * 
 * @author Vieri del Bianco (vieri.delbianco@gmail.com)
 *
 */
public final class InputStreamPacketInputStream implements PacketInputStream {
  
  /**
   * Property name of trim packets: the number of packets to be trimmed when 
   * pattern is not found (in packets).
   */
  public static final String TRIM_PACKETS_PROPERTY = 
    "trimPackets";
  
  /**
   * Property name of validate minimum packets: the minimum valid packets to 
   * consider the stream valid (in packets).
   */
  public static final String VALIDATE_MINIMUM_PACKETS_PROPERTY = 
    "validateMinimumPackets";
  
  /**
   * Property name of buffer packets: the buffer length (in packets).
   */
  public static final String BUFFER_PACKETS_PROPERTY = 
    "bufferPackets";
  
  /**
   * Property name of not valid maximum data: the maximum data to read to 
   * identify a packet, if not identified an exception is raised.
   */
  public static final String NOT_VALID_MAXIMUM_DATA_PROPERTY = 
    "notValidMaximumData";
  
  /*
   * default properties
   */
  private static final Properties DEFAULT_PROPERTIES = new Properties();
  
  static {
    DEFAULT_PROPERTIES.setProperty(BUFFER_PACKETS_PROPERTY, "6");
    DEFAULT_PROPERTIES.setProperty(VALIDATE_MINIMUM_PACKETS_PROPERTY, "3");
    DEFAULT_PROPERTIES.setProperty(TRIM_PACKETS_PROPERTY, "2");
    DEFAULT_PROPERTIES.setProperty(NOT_VALID_MAXIMUM_DATA_PROPERTY, "32768");
  }
  
  /*
   * bufferRaw input stream
   */
  private final DataInputStream input;
  
  /*
   * properties
   */
  private final Properties properties;
  
  /*
   * buffer
   */
  private byte[] bufferRaw = new byte[0];
  private UnsignedByteArray buffer = 
    UnsignedByteArray.create(bufferRaw);
  
  /*
   * not valid data buffer
   */
  private byte[] trimmedRaw = new byte[0];
  private UnsignedByteArray trimmed = 
    UnsignedByteArray.create(trimmedRaw);
  
  /*
   * byte pattern matcher
   */
  private BytePattern pattern = 
    BytePattern.createPattern(getPacketPattern(), getPacketLength());
  
  /*
   * flag: input stream closed
   */
  private boolean isClose;
  
  /*
   * flag: input stream EOF
   */
  private boolean isEOF;
  
  /*
   * flag: input stream has been validated
   */
  private boolean isValid;
  
  /**
   * Creates a PacketInputStream from an InputStreeam.
   * FactoryMethod.
   * 
   * @param is InputStream
   * @return created PacketInputStream
   */
  public static InputStreamPacketInputStream createInputStreamPacketInputStream(
      final InputStream is) {
    return createInputStreamPacketInputStream(is, new Properties());
  }
  
  /**
   * Creates a PacketInputStream from an InputStreeam.
   * FactoryMethod.
   * 
   * @param is InputStream
   * @param properties initialization properties
   * @return created PacketInputStream
   */
  public static InputStreamPacketInputStream createInputStreamPacketInputStream(
      final InputStream is, final Properties properties) {
    final InputStreamPacketInputStream pis = 
      new InputStreamPacketInputStream(is, properties);
    // buffer
    pis.bufferRaw = new byte[getPacketLength() * pis.getBufferPackets()];
    pis.buffer = UnsignedByteArray.create(pis.bufferRaw, 0, 0);
    // not valid data buffer
    pis.trimmedRaw = new byte[
      getPacketLength() * pis.getTrimPackets() + pis.getNotValidMaximumData()];
    pis.trimmed = UnsignedByteArray.create(pis.trimmedRaw, 0, 0);
    // pattern
    pis.pattern = 
      BytePattern.createPattern(getPacketPattern(), getPacketLength());
    // flags
    pis.isValid = false;
    pis.isEOF = false;
    return pis;
  }
  
  private InputStreamPacketInputStream(
      final InputStream is, final Properties properties) {
    // input stream
    this.input = new DataInputStream(is);
    // properties
    this.properties = 
      new Properties(InputStreamPacketInputStream.DEFAULT_PROPERTIES);
    this.properties.putAll(properties);
  }
  
  private int getBufferPackets() {
    return Integer.parseInt(properties.getProperty(BUFFER_PACKETS_PROPERTY));
  }
  
  private int getValidateMinimumPackets() {
    return Integer.parseInt(
        properties.getProperty(VALIDATE_MINIMUM_PACKETS_PROPERTY));
  }
  
  private int getTrimPackets() {
    return Integer.parseInt(properties.getProperty(TRIM_PACKETS_PROPERTY));
  }
  
  private int getNotValidMaximumData() {
    return Integer.parseInt(
      properties.getProperty(NOT_VALID_MAXIMUM_DATA_PROPERTY));
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStream#availablePackets()
   */
  public int availablePackets() throws IOException {
   return (buffer.length() + input.available()) / getPacketLength();
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStream#read(
   *   ie.ucd.sensetile.sensorboard.Packet[])
   */
  public int read(final Packet[] array) 
      throws IOException, SenseTileException {
    return read(array, 0, array.length);
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStream#read(
   *   ie.ucd.sensetile.sensorboard.Packet[], int, int)
   */
  public int read(
      final Packet[] array, final int offset, final int length) 
      throws IOException, SenseTileException {
    if (isClose()) {
      throw new IOException();
    }
    if (isEOF()) {
      return -1;
    }
    if (!isValid()) {
      if (isEOF()) {
        return -1;
      }
      return 0;
    }
    final ReturnPacketArray returnArray = 
      new ReturnPacketArray(array, offset, length);
    readToReturn(returnArray);
    if (returnArray.read() == 0 && isEOF()) {
      return -1;
    }
    return returnArray.read();
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStream#read()
   */
  public Packet read() throws IOException, SenseTileException {
    final Packet[] array = new Packet[1];
    readFully(array);
    return array[0];
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStream#readFully(
   *   ie.ucd.sensetile.sensorboard.Packet[])
   */
  public void readFully(final Packet[] array) 
      throws IOException, SenseTileException {
    readFully(array, 0, array.length);
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStream#readFully(
   *   ie.ucd.sensetile.sensorboard.Packet[], int, int)
   */
  public void readFully(
      final Packet[] array, 
      final int offset, 
      final int length) 
      throws IOException, SenseTileException {
    if (isClose()) {
      throw new IOException();
    }
    if (isEOF()) {
      throw new EOFException();
    }
    if (!isValid()) {
      waitReadToValidate();
    }
    final ReturnPacketArray returnArray = 
      new ReturnPacketArray(array, offset, length);
    waitReadToReturn(returnArray);
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStream#close()
   */
  public void close() throws IOException {
    isClose = true;
    input.close();
  }
  
  private void readToValidate() throws IOException, SenseTileException {
    while (!isValid) {
      readIntoBuffer();
      if (buffer.length() < getValidateMinimumPackets() * getPacketLength()) {
        return;
      }
      if (validateAndTrimBuffer()) {
        this.isValid = true;
      } else {
        if (
            getTrimPackets() * getPacketLength() + trimmed.length() > 
            getNotValidMaximumData()) {
          throw new SenseTileException("not valid maximum data exceeded");
        }
        trimBuffer(getTrimPackets() * getPacketLength());
      }
    }
  }
  
  private void readToReturn(final ReturnPacketArray array) 
      throws IOException, SenseTileException {
    do {
      readIntoBuffer();
      if (buffer.length() < getPacketLength()) {
        return;
      }
      bufferToReturn(array);
    } while (!array.isFull());
  }
  
  private int readIntoBuffer() throws IOException {
    if (bufferRaw.length - buffer.length() == 0) {
      return 0;
    }
    final int begin = buffer.getBeginOffset();
    final int end = buffer.getEndOffset();
    int read = 0;
    if (end < begin) {
      read = input.read(
          bufferRaw, end, bufferRaw.length - buffer.length());
    } else {
      read = input.read(bufferRaw, end, bufferRaw.length - end);
      if (read == (bufferRaw.length - end)) {
        read = read + input.read(bufferRaw, 0, begin);
      }
    }
    if (read == -1) {
      isEOF = true;
      read = 0;
    }
    buffer = UnsignedByteArray.create(
        buffer, 0, buffer.length() + read);
    return read;
  }
  
  private void waitReadToValidate() throws IOException, SenseTileException {
    while (!isValid) {
      waitReadIntoBuffer(getPacketLength() * getValidateMinimumPackets());
      if (validateAndTrimBuffer()) {
        isValid = true;
      } else {
        if (
            getTrimPackets() * getPacketLength() + trimmed.length() > 
            getNotValidMaximumData()) {
          throw new SenseTileException("not valid maximum data exceeded");
        }
        trimBuffer(getTrimPackets() * getPacketLength());
      }
    }
  }
  
  private void waitReadToReturn(final ReturnPacketArray array) 
      throws IOException, SenseTileException {
    do {
      int toBeRead = array.toBeRead();
      toBeRead = toBeRead > getBufferPackets() ? getBufferPackets() : toBeRead;
      waitReadIntoBuffer(toBeRead * getPacketLength());
      bufferToReturn(array);
    } while (!array.isFull());
  }
  
  private void waitReadIntoBuffer(final int length) throws IOException {
    if (length > bufferRaw.length) {
      throw new IndexOutOfBoundsException();
    }
    if (length - buffer.length() <= 0) {
      return;
    }
    final int begin = buffer.getBeginOffset();
    final int end = buffer.getEndOffset();
    final int readEnd = (begin + length) % bufferRaw.length;
    if (readEnd > end) {
      input.readFully(bufferRaw, end, readEnd - end);
    } else {
      input.readFully(bufferRaw, end, bufferRaw.length - end);
      input.readFully(bufferRaw, 0, readEnd);
    }
    buffer = UnsignedByteArray.create(
        buffer, 0, length);
  }
  
  private void bufferToReturn(final ReturnPacketArray array) 
      throws SenseTileException {
    do {
      if (buffer.length() < getPacketLength()) {
        return;
      }
      Packet packet;
      packet = readPacketFromBuffer();
      array.add(packet);
    } while (!array.isFull());
  }
  
  private boolean validateAndTrimBuffer() {
    final int index = pattern.match(buffer);
    if (index == -1) {
      return false;
    }
    // calculate packet start position
    int absoluteIndex = 0;
    absoluteIndex = (
          index + 
          (getPacketLength() - ByteArrayPacket.PATTERN_OFFSET)
        ) % getPacketLength();
    trimBuffer(absoluteIndex);
    return true;
  }
  
  private Packet readPacketFromBuffer() throws SenseTileException {
    final UnsignedByteArray raw = extractBuffer(getPacketLength());
    return ByteArrayPacket.createPacket(raw);
  }
  
  private UnsignedByteArray extractBuffer(final int length) {
    final byte[] array = new byte[length];
    for (int i = 0; i < array.length; i++) {
      array[i] = buffer.getByte(i);
    }
    trimBuffer(length);
    return UnsignedByteArray.create(array);
  }
  
  private void trimBuffer(final int length) {
    if (!isValid) {
      final UnsignedByteArray toBeTrimmed = UnsignedByteArray.create(
        buffer, 0, length);
      final byte[] toBeTrimmedRaw = toBeTrimmed.toArray();
      System.arraycopy(
          toBeTrimmedRaw, 0, 
          trimmedRaw, trimmed.length(), 
          toBeTrimmed.length());
      trimmed = UnsignedByteArray.create(
          trimmedRaw, 0, trimmed.length() + toBeTrimmed.length());
    }
    buffer = UnsignedByteArray.create(
        buffer, length, buffer.length() - length);
  }
  
  private boolean isClose() {
    return isClose;
  }
  
  private boolean isEOF() {
    return isEOF;
  }
  
  private boolean isValid() throws IOException, SenseTileException {
    if (!isValid) {
      readToValidate();
    }
    return isValid;
  }
  
  private static int getPacketLength() {
    return ByteArrayPacket.LENGTH;
  }
  
  private static byte[] getPacketPattern() {
    return ByteArrayPacket.PATTERN;
  }

  private static final class ReturnPacketArray {
    
    private Packet[] array; 
    private int offset; 
    private int length;
    private int internalOffset;
    
    private ReturnPacketArray(
        final Packet[] array, 
        final int offset, 
        final int length) {
      if (
          (offset < 0) ||
          (length < 0) ||
          (offset + length > array.length)) {
        throw new IndexOutOfBoundsException();
      }
      this.array = array;
      this.offset = offset;
      this.length = length;
      this.internalOffset = offset;
    }
    
    private int toBeRead() {
      return offset + length - internalOffset;
    }
    
    private int read() {
      return internalOffset - offset;
    }
    
    private void add(final Packet packet) {
      if (isFull()) {
        throw new IndexOutOfBoundsException();
      }
      array[internalOffset] = packet;
      internalOffset++;
    }
    
    private boolean isFull() {
      if (internalOffset - offset == length) {
        return true;
      }
      return false;
    }
    
  }
}
