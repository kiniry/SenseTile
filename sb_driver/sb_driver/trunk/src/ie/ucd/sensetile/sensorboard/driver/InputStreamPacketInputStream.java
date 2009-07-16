/*
 * PacketInputStream.java
 *
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */
package ie.ucd.sensetile.sensorboard.driver;

import ie.ucd.sensetile.sensorboard.SensorBoardPacket;
import ie.ucd.sensetile.sensorboard.PacketInputStream;
import ie.ucd.sensetile.sensorboard.SenseTileException;
import ie.ucd.sensetile.util.BytePattern;
import ie.ucd.sensetile.util.UnsignedByteArray;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

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
  
  /**
   * constants
   */
  static int bufferPackets = 6;
  static int validateMinimumPackets = 3;
  static int trimPackets = 2;
  /*@
    @ public constraint bufferPackets == 6;
    @ public invariant 
    @   (validateMinimumPackets > 0) && 
    @   (validateMinimumPackets <= bufferPackets);
    @ public invariant (trimPackets > 0) && (trimPackets <= bufferPackets);
    @*/
  
  /**
   * raw input stream
   */
  final private DataInputStream input;
  /*@
    @ public invariant input != null;
  @*/
  
  /**
   * buffer
   */
  private byte[] raw;
  private UnsignedByteArray byteArray;
  
  /**
   * byte pattern matcher
   */
  final private BytePattern pattern;
  
  /**
   * flag: input stream EOF
   */
  private boolean isEOF;
  
  /**
   * flag: input stream has been validates
   */
  private boolean isValid;
  
  /*@
    @ requires bufferPackets > 0;
    @*/
  public InputStreamPacketInputStream(/*@ non_null */ InputStream is) {
    // input stream
    this.input = new DataInputStream(is);
    // buffer
    raw = new byte[getPacketLength() * bufferPackets];
    byteArray = UnsignedByteArray.create(raw, 0, 0);
    // pattern
    pattern = BytePattern.createPattern(getPacketPattern(), getPacketLength());
    // flags
    isValid = false;
    isEOF = false;
  }

  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStreamI#availablePackets()
   */
  public int availablePackets() throws IOException {
   return (byteArray.length() + input.available()) / getPacketLength();
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStreamI#read(ie.ucd.sensetile.sensorboard.Packet[])
   */
  public int read(SensorBoardPacket[] array) 
      throws IOException, SenseTileException{
    return read(array, 0, array.length);
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStreamI#read(ie.ucd.sensetile.sensorboard.Packet[], int, int)
   */
  public int read(SensorBoardPacket[] array, int offset, int length) 
      throws IOException, SenseTileException{
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
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStreamI#read()
   */
  public SensorBoardPacket read() throws IOException, SenseTileException{
    SensorBoardPacket[] array = new SensorBoardPacket[1];
    readFully(array);
    return array[0];
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStreamI#readFully(ie.ucd.sensetile.sensorboard.Packet[])
   */
  public void readFully(SensorBoardPacket[] array) 
      throws IOException, SenseTileException{
    readFully(array, 0, array.length);
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStreamI#readFully(ie.ucd.sensetile.sensorboard.Packet[], int, int)
   */
  public void readFully(SensorBoardPacket[] array, int offset, int length) 
      throws IOException, SenseTileException{
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
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStreamI#close()
   */
  public void close() throws IOException {
    input.close();
  }
  
  private void readToValidate() throws IOException {
    readIntoBuffer();
    if (byteArray.length() < validateMinimumPackets * getPacketLength()) {
      return;
    }
    if (validateAndTrimBuffer()) {
      this.isValid = true;
    } else {
      trimBuffer(trimPackets * getPacketLength());
    }
  }
  
  private void readToReturn(ReturnPacketArray array) throws IOException, SenseTileException {
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
    while(this.isValid == false) {
      waitReadIntoBuffer(ByteArrayPacket.LENGTH * validateMinimumPackets);
      if (validateAndTrimBuffer()) {
        this.isValid = true;
      } else {
        trimBuffer(trimPackets * getPacketLength());
      }
    }
  }
  
  private void waitReadToReturn(ReturnPacketArray array) throws IOException, SenseTileException {
    do {
      int toBeRead = array.toBeRead();
      toBeRead = toBeRead > bufferPackets ? bufferPackets : toBeRead;
      waitReadIntoBuffer(toBeRead * ByteArrayPacket.LENGTH);
      bufferToReturn(array);
    } while (! array.isFull());
  }
  
  private void waitReadIntoBuffer(int length) throws IOException {
    if (length > raw.length) {
      throw new IndexOutOfBoundsException();
    }
    if (byteArray.length() >= length) {
      return;
    }
    int begin = byteArray.getBeginOffset();
    int end = byteArray.getEndOffset();
    int readEnd = begin + length % ByteArrayPacket.LENGTH;
    if(readEnd > end) {
      input.readFully(raw, end, length);
    } else {
      input.readFully(raw, end, raw.length - end);
      input.readFully(raw, 0, readEnd);
    }
    byteArray = UnsignedByteArray.create(
        byteArray, 0, byteArray.length() + length);
  }
  
  private void bufferToReturn(ReturnPacketArray array) throws SenseTileException {
    do {
      if (byteArray.length() < getPacketLength()) {
        return;
      }
      SensorBoardPacket packet;
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
    int absoluteIndex = 
      (index+(getPacketLength()-ByteArrayPacket.PATTERN_OFFSET))%getPacketLength();
    trimBuffer(absoluteIndex);
    return true;
  }
  
  private SensorBoardPacket readPacketFromBuffer() throws SenseTileException {
    UnsignedByteArray raw = extractBuffer(getPacketLength());
    return ByteArrayPacket.createPacket(raw);
  }  
  
  private UnsignedByteArray extractBuffer(int length) {
    byte[] array = new byte[length];
    for (int i = 0; i < array.length; i++) {
      array[i] = byteArray.getByte(i);
    }
    trimBuffer(length);
    return UnsignedByteArray.create(array);
  }
  
  private void trimBuffer(int length) {
    byteArray = UnsignedByteArray.create(
        byteArray, length, byteArray.length() - length);
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
  
  //@ ensures \result > 0;
  private /*@ pure */ int getPacketLength() {
    return ByteArrayPacket.LENGTH;
  }
  
  private byte[] getPacketPattern() {
    return ByteArrayPacket.PATTERN;
  }
  
  private class ReturnPacketArray {
    
    private SensorBoardPacket[] array; 
    private int offset; 
    private int length;
    private int internalOffset;
    
    private ReturnPacketArray(SensorBoardPacket[] array, int offset, int length) {
      this.array = array;
      this.offset = offset;
      this.length = length;
      this.internalOffset = offset;
    }
    
    public int toBeRead() {
      return offset + length - internalOffset;
    }
    
    public int read() {
      return internalOffset - offset;
    }
    
    public void add(SensorBoardPacket packet) {
      if (isFull()) {
        throw new IndexOutOfBoundsException();
      }
      array[internalOffset] = packet;
      internalOffset++;
    }
    
    public boolean isFull() {
      if (internalOffset - offset == length) {
        return true;
      }
      return false;
    }
    
  }
}
