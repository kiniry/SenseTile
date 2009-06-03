package ie.ucd.sensetile;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class PacketInputStream implements Closeable {
  
  /**
   * raw input stream
   */
  final private DataInputStream is;
  
  private byte[] raw;
  private int buffered;
  private UnsignedByteArray uba_raw;
  
  static int VALIDATE_PACKETS = 3;
  
  /**
   * byte pattern matcher
   */
  final private BytePattern pattern;
  
  /**
   * input stream EOF
   */
  private boolean isEOF;
  
  /**
   * input stream contains a valid packet
   */
  private boolean isValid;
  
  /**
   * input stream contains a valid packet
   * @throws IOException 
   */
  public boolean isValid() throws IOException {
    if (! isValid) {
      validate();
    }
    return isValid;
  }
  
  PacketInputStream(InputStream is) {
    this.is = new DataInputStream(is);
    raw = new byte[Packet.LENGTH * VALIDATE_PACKETS];
    buffered = 0;
    uba_raw = UnsignedByteArray.create(raw, 0, 0);
    pattern = BytePattern.createPattern(Packet.PATTERN, Packet.LENGTH);
    isValid = false;
    isEOF = false;
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
   return (uba_raw.length() + is.available()) / Packet.LENGTH;
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
    if(! isValid()){
      waitUntilValid();
      return null;
    }
    waitUntilBuffer(Packet.LENGTH);
    return readPacketFromBuffer();
  }
  
  private void waitUntilBuffer(int length) 
      throws IOException, SenseTileException {
    if (length > raw.length){
      throw new IllegalArgumentException();
    }
    int missing = length - buffered;
    if (missing > 0) {
      is.readFully(raw, buffered, missing);
      buffered = length;
    }
  }
  
  private int readIntoBuffer() throws IOException {
    int read = is.read(raw, buffered, raw.length - buffered);
    if (read == -1) {
      throw new EOFException();
    }
    buffered = buffered + read;
    return read;
  }
  
  private Packet readPacketFromBuffer() throws SenseTileException {
    Packet packet= Packet.createPacket(raw);
    trimBuffer(Packet.LENGTH);
    return packet;
  }
  
  private void validate() throws IOException {
    int read = readIntoBuffer();
    if (read < 0) {
      isEOF = true;
      return;
    }
    if (read == 0) {
      return;
    }
    byte[] arraySlice = Arrays.copyOfRange(raw, 0, buffered);
    int match = pattern.match(arraySlice);
    if (match == -1) {
      this.isValid = false;
      if (raw.length == buffered) {
        trimBuffer(Packet.LENGTH);
      };
    } else {
      int packetStart = calculatePacketStart(match);
      trimBuffer(packetStart);
      this.isValid = true;
    }
  }
  
  private void trimBuffer(int length) {
    raw = Arrays.copyOfRange(raw, length, raw.length + length);
    buffered = buffered - length;
  }
  
  private int calculatePacketStart(int match) {
    return ((Packet.LENGTH - Packet.PATTERN_OFFSET) + match) % Packet.LENGTH;
  }

  public void close() throws IOException {
    is.close();
  }
  
}
