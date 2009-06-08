package ie.ucd.sensetile;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class PacketInputStream implements Closeable {
  
  /**
   * constants
   */
  static int BUFFER_PACKETS = 6;
  static int VALIDATE_MINIMUM_PACKETS = 3;
  static int TRIM_PACKETS = 2;
  
  /**
   * raw input stream
   */
  final private DataInputStream is;
  
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
  
  PacketInputStream(InputStream is) {
    // input stream
    this.is = new DataInputStream(is);
    // buffer
    raw = new byte[getPacketLength() * BUFFER_PACKETS];
    byteArray = UnsignedByteArray.create(raw, 0, 0);
    // pattern
    pattern = BytePattern.createPattern(getPacketPattern(), getPacketLength());
    // flags
    isValid = false;
    isEOF = false;
  }

  /**
   * input stream contains a valid packet
   * @throws IOException 
   */
  public boolean isValid() throws IOException {
    if (! isValid) {
      readToValidate();
    }
    return isValid;
  }
  
  /**
   * Returns an estimate of the number of packets that can be read from this 
   * input stream without blocking.
   *
   * @return an estimate of the number of bytes that can be read from this 
   * input stream without blocking.
   * @exception IOException if an I/O error occurs.
   */
  public int availablePackets() throws IOException {
   return (byteArray.length() + is.available()) / getPacketLength();
  }
  
  /**
   * Reads the next packet from the input stream. 
   * 
   * <p>This method blocks until input data is available, or an exception is 
   * thrown.</p>
   *
   * @return the next packet of data.
   * @throws EOFException if EOF reached.
   * @throws IOException if an I/O error occurs.
   */
  public Packet read() throws IOException, SenseTileException{
    Packet[] array = new Packet[1];
    readFully(array);
    return array[0];
  }
  
  /**
   * Reads some number of packets from the contained input stream and stores 
   * them into the array.
   * 
   * The number of bytes actually read is returned as an integer. 
   * If b is null, a NullPointerException is thrown. If the length of b is 
   * zero, then no bytes are read and 0 is returned; otherwise, there is an 
   * attempt to read at least one packet. 
   * 
   * @param array the buffer into which the data is read.
   * @return the total number of packets read into the buffer.
   * @throws IOException
   * @throws SenseTileException data is malformed
   */
  public int read(Packet[] array) 
      throws IOException, SenseTileException{
    return read(array, 0, array.length);
  }
  
  /**
   * Reads some number of packets from the contained input stream and stores 
   * them into the array.
   * 
   * The number of bytes actually read is returned as an integer. 
   * If b is null, a NullPointerException is thrown. If the length of b is 
   * zero, then no bytes are read and 0 is returned; otherwise, there is an 
   * attempt to read at least one packet.
   * 
   * The first packet read is stored into element array[offset], the next one 
   * into array[offset+1], and so on. 
   * The number of packets read is, at most, equal to length. Let k be the number 
   * of packets actually read; these packets will be stored in elements 
   * array[offset] through array[offset+k-1].
  
   * 
   * @param array the buffer into which the data is read.
   * @param offset the start offset in the destination array.
   * @param length the maximum number of packets read. 
   * @return the total number of packets read into the buffer.
   * @throws IOException
   * @throws SenseTileException data is malformed
   */
  public int read(Packet[] array, int offset, int length) 
      throws IOException, SenseTileException{
    if(! isValid()) {
      return 0;
    }
    ReturnPacketArray returnArray = 
      new ReturnPacketArray(array, offset, length);
    readToReturn(returnArray);
    return returnArray.read();
  }
  
  public void readFully(Packet[] array) 
      throws IOException, SenseTileException{
    readFully(array, 0, array.length);
  }
  
  public void readFully(Packet[] array, int offset, int length) 
      throws IOException, SenseTileException{
    if(! isValid()){
      waitReadToValidate();
    }
    ReturnPacketArray returnArray = 
      new ReturnPacketArray(array, offset, length);
    waitReadToReturn(returnArray);
  }
  
  public void close() throws IOException {
    is.close();
  }
  
  private void readToValidate() throws IOException {
    readIntoBuffer();
    if (byteArray.length() < VALIDATE_MINIMUM_PACKETS * getPacketLength()) {
      return;
    }
    if (validateAndTrimBuffer()) {
      this.isValid = true;
    } else {
      trimBuffer(TRIM_PACKETS * getPacketLength());
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
    int begin = byteArray.getOffset();
    int end = byteArray.getEndOffset();
    int read = 0;
    if (end < begin) {
      read = is.read(raw, end, raw.length - byteArray.length());
    } else {
      read = is.read(raw, end, raw.length - end);
      if (read == (raw.length - end)) {
        read = read + is.read(raw, 0, begin);
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
      waitReadIntoBuffer(Packet.LENGTH * VALIDATE_MINIMUM_PACKETS);
      if (validateAndTrimBuffer()) {
        this.isValid = true;
      } else {
        trimBuffer(TRIM_PACKETS * getPacketLength());
      }
    }
  }
  
  private void waitReadToReturn(ReturnPacketArray array) throws IOException, SenseTileException {
    do {
      int toBeRead = array.toBeRead();
      toBeRead = toBeRead > BUFFER_PACKETS ? BUFFER_PACKETS : toBeRead;
      waitReadIntoBuffer(toBeRead * Packet.LENGTH);
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
    int begin = byteArray.getOffset();
    int end = byteArray.getEndOffset();
    int readEnd = begin + length % Packet.LENGTH;
    if(readEnd > end) {
      is.readFully(raw, end, length);
    } else {
      is.readFully(raw, end, raw.length - end);
      is.readFully(raw, 0, readEnd);
    }
    byteArray = UnsignedByteArray.create(
        byteArray, 0, byteArray.length() + length);
  }
  
  private void bufferToReturn(ReturnPacketArray array) throws SenseTileException {
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
    int absoluteIndex = 
      (index+(getPacketLength()-Packet.PATTERN_OFFSET))%getPacketLength();
    trimBuffer(absoluteIndex);
    return true;
  }
  
  private Packet readPacketFromBuffer() throws SenseTileException {
    UnsignedByteArray raw = extractBuffer(getPacketLength());
    return Packet.createPacket(raw);
  }  
  
  private UnsignedByteArray extractBuffer(int length) {
    byte[] array = new byte[length];
    for (int i = 0; i < array.length; i++) {
      array[i] = byteArray.get(i);
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
  
  private int getPacketLength() {
    return Packet.LENGTH;
  }
  
  private byte[] getPacketPattern() {
    return Packet.PATTERN;
  }
  
  private class ReturnPacketArray {
    
    private Packet[] array; 
    private int offset; 
    private int length;
    private int internalOffset;
    
    private ReturnPacketArray(Packet[] array, int offset, int length) {
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
    
    public void add(Packet packet) {
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
