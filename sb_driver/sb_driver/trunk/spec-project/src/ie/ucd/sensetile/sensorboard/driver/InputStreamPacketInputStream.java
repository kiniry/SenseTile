/*
 * PacketInputStream.java
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
 * @author Vieri del Bianco
 *
 */
/**
 * A packet input stream lets an application read Packet instance data
 * from an underlying input stream.
 * 
 * <p> PacketInputStream is not safe for multithreaded access.
 *
 * @author Vieri del Bianco
 * @version 0
 * @see 
 */
public class InputStreamPacketInputStream implements PacketInputStream {
  
  /*
   * property names
   */
  public static final String TRIM_PACKETS_PROPERTY = "trimPackets";
  public static final String VALIDATE_MINIMUM_PACKETS_PROPERTY = "validateMinimumPackets";
  public static final String BUFFER_PACKETS_PROPERTY = "bufferPackets";

  /*
   * default properties
   */
  private static final Properties DEFAULT_PROPERTIES = new Properties();
  
  static {
    DEFAULT_PROPERTIES.setProperty(BUFFER_PACKETS_PROPERTY, "6");
    DEFAULT_PROPERTIES.setProperty(VALIDATE_MINIMUM_PACKETS_PROPERTY, "3");
    DEFAULT_PROPERTIES.setProperty(TRIM_PACKETS_PROPERTY, "2");
  }
  
  /*
   * raw input stream
   */
  private final DataInputStream input;
  
  /*
   * properties
   */
  private final Properties properties;
  
  /*
   * buffer
   */
  private byte[] raw;
  private UnsignedByteArray byteArray;
  
  /*
   * byte pattern matcher
   */
  private final BytePattern pattern;
  
  /*
   * flag: input stream closed
   */
  private boolean isClose;
  
  /*
   * flag: input stream EOF
   */
  private boolean isEOF;
  
  /*
   * flag: input stream has been validates
   */
  private boolean isValid;
  
  public InputStreamPacketInputStream(
      final InputStream is) {
    this(is, new Properties());
  }
  
  public InputStreamPacketInputStream(
      final InputStream is, final Properties properties) {
    // input stream
    this.input = new DataInputStream(is);
    // properties
    this.properties = 
      new Properties(InputStreamPacketInputStream.DEFAULT_PROPERTIES);
    this.properties.putAll(properties);
    // buffer
    raw = new byte[getPacketLength() * getBufferPackets()];
    byteArray = UnsignedByteArray.create(raw, 0, 0);
    // pattern
    pattern = BytePattern.createPattern(getPacketPattern(), getPacketLength());
    // flags
    isValid = false;
    isEOF = false;
  }

  private int getBufferPackets() {
    return Integer.parseInt(properties.getProperty(BUFFER_PACKETS_PROPERTY));
  }
  
  private int getValidateMinimumPackets() {
    return Integer.parseInt(properties.getProperty(VALIDATE_MINIMUM_PACKETS_PROPERTY));
  }
  
  private int getTrimPackets() {
    return Integer.parseInt(properties.getProperty(TRIM_PACKETS_PROPERTY));
  }
  
  public final int availablePackets() throws IOException {
   return (byteArray.length() + input.available()) / getPacketLength();
  }
  
  public int read(final Packet[] array) 
      throws IOException, SenseTileException{
    return read(array, 0, array.length);
  }
  
  public int read(
      final Packet[] array, final int offset, final int length) 
      throws IOException, SenseTileException{
    if (isClose()) {
      throw new IOException();
    }
    if (isEOF()) {
      return -1;
    }
    if(! isValid()) {
      return 0;
    }
    ReturnPacketArray returnArray = 
      new ReturnPacketArray(array, offset, length);
    readToReturn(returnArray);
    return returnArray.read();
  }
  
  public Packet read() throws IOException, SenseTileException{
    Packet[] array = new Packet[1];
    readFully(array);
    return array[0];
  }
  
  public void readFully(final Packet[] array) 
      throws IOException, SenseTileException{
    readFully(array, 0, array.length);
  }
  
  public void readFully(
      final Packet[] array, 
      final int offset, 
      final int length) 
      throws IOException, SenseTileException{
    if (isClose()) {
      throw new IOException();
    }
    if (isEOF()) {
      throw new EOFException();
    }
    if(! isValid()){
      waitReadToValidate();
    }
    ReturnPacketArray returnArray = 
      new ReturnPacketArray(array, offset, length);
    waitReadToReturn(returnArray);
  }
  
  public final void close() throws IOException {
    isClose = true;
    input.close();
  }
  
  private void readToValidate() throws IOException {
    readIntoBuffer();
    if (byteArray.length() < getValidateMinimumPackets() * getPacketLength()) {
      return;
    }
    if (validateAndTrimBuffer()) {
      this.isValid = true;
    } else {
      trimBuffer(getTrimPackets() * getPacketLength());
    }
  }
  
  private void readToReturn(final ReturnPacketArray array) 
      throws IOException, SenseTileException {
    do {
      readIntoBuffer();
      if (byteArray.length() < getPacketLength()) {
        return;
      }
      bufferToReturn(array);
    } while (! array.isFull());
  }
  
  private int readIntoBuffer() throws IOException {
    if (raw.length - byteArray.length() == 0) {
      return 0;
    }
    int begin = byteArray.getBeginOffset();
    int end = byteArray.getEndOffset();
    int read = 0;
    if (end < begin) {
      read = input.read(raw, end, raw.length - byteArray.length());
    } else {
      read = input.read(raw, end, raw.length - end);
      if (read == (raw.length - end)) {
        read = read + input.read(raw, 0, begin);
      }
    }
    if (read == -1) {
      isEOF = true;
      read = 0;
    }
    byteArray = UnsignedByteArray.create(
        byteArray, 0, byteArray.length() + read);
    return read;
  }
  
  private void waitReadToValidate() throws IOException {
    while(! isValid) {
      waitReadIntoBuffer(ByteArrayPacket.LENGTH * getValidateMinimumPackets());
      if (validateAndTrimBuffer()) {
        isValid = true;
      } else {
        trimBuffer(getTrimPackets() * getPacketLength());
      }
    }
  }
  
  private void waitReadToReturn(final ReturnPacketArray array) 
      throws IOException, SenseTileException {
    do {
      int toBeRead = array.toBeRead();
      toBeRead = toBeRead > getBufferPackets() ? getBufferPackets() : toBeRead;
      waitReadIntoBuffer(toBeRead * ByteArrayPacket.LENGTH);
      bufferToReturn(array);
    } while (! array.isFull());
  }
  
  private void waitReadIntoBuffer(final int length) throws IOException {
    if (length > raw.length) {
      throw new IndexOutOfBoundsException();
    }
    if (length - byteArray.length() <= 0) {
      return;
    }
    int begin = byteArray.getBeginOffset();
    int end = byteArray.getEndOffset();
    int readEnd = begin + length % ByteArrayPacket.LENGTH;
    if(readEnd > end) {
      input.readFully(raw, end, readEnd - end);
    } else {
      input.readFully(raw, end, raw.length - end);
      input.readFully(raw, 0, readEnd);
    }
    byteArray = UnsignedByteArray.create(
        byteArray, 0, length);
  }
  
  private void bufferToReturn(final ReturnPacketArray array) 
      throws SenseTileException {
    do {
      if (byteArray.length() < getPacketLength()) {
        return;
      }
      Packet packet;
      packet = readPacketFromBuffer();
      array.add(packet);
    } while (! array.isFull());
  }
  
  private boolean validateAndTrimBuffer() {
    int index = pattern.match(byteArray);
    if (index == -1) {
      return false;
    }
    // calculate packet start position
    int absoluteIndex = (
          index + 
          (getPacketLength() - ByteArrayPacket.PATTERN_OFFSET)
        ) % getPacketLength();
    trimBuffer(absoluteIndex);
    return true;
  }
  
  private Packet readPacketFromBuffer() throws SenseTileException {
    UnsignedByteArray raw = extractBuffer(getPacketLength());
    return ByteArrayPacket.createPacket(raw);
  }  
  
  private UnsignedByteArray extractBuffer(final int length) {
    byte[] array = new byte[length];
    for (int i = 0; i < array.length; i++) {
      array[i] = byteArray.getByte(i);
    }
    trimBuffer(length);
    return UnsignedByteArray.create(array);
  }
  
  private void trimBuffer(final int length) {
    byteArray = UnsignedByteArray.create(
        byteArray, length, byteArray.length() - length);
  }
  
  private boolean isClose() {
    return isClose;
  }
  
  private boolean isEOF() {
    return isEOF;
  }
  
  private boolean isValid() throws IOException {
    if (! isValid) {
      readToValidate();
    }
    return isValid;
  }
  
  private int getPacketLength() {
    return ByteArrayPacket.LENGTH;
  }
  
  private byte[] getPacketPattern() {
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
