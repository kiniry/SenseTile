package ie.ucd.sensetile;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class PacketInputStream implements Closeable {
  
  final private InputStream is;
  
  /**
   * byte buffer
   */
  private byte[] buffer;
  
  /**
   * used byte buffer index
   */
  private int buffered;
  
  /**
   * byte pattern
   */
  final private BytePattern pattern = 
    BytePattern.createPattern(Packet.PATTERN, Packet.LENGTH);
  
  private boolean isInitialized = false;
  
  public boolean isInitialized() {
    return isInitialized;
  }
  
  private void setInitialized(boolean isInitialized) {
    this.isInitialized = isInitialized;
  }
  
  PacketInputStream(InputStream is) {
    this.is = is;
    buffer = new byte[Packet.LENGTH * 3];
  }
  
  public void close() throws IOException {
    is.close();
  }
  
  public int available() throws IOException {
    if (! isInitialized()) {
      initialize();
    }
    if (! isInitialized()) {
      return 0;
    }
    return (buffered + is.available()) / Packet.LENGTH;
  }
  
  private void initialize() throws IOException {
    int read = readIntoBuffer();
    if (read == 0) {
      return;
    }
    byte[] arraySlice = Arrays.copyOfRange(buffer, 0, buffered);
    int match = pattern.match(arraySlice);
    if (match == -1) {
      if (buffer.length == buffered) {
        trimBuffer(Packet.LENGTH);
      };
    } else {
      int packetStart = calculatePacketStart(match);
      trimBuffer(packetStart);
      setInitialized(true);
    }
  }
  
  private int readIntoBuffer() throws IOException {
    int read = is.read(buffer, buffered, buffer.length - buffered);
    if (read == -1) {
      throw new EOFException();
    }
    buffered = buffered + read;
    return read;
  }
  
  private void trimBuffer(int length) {
    buffer = Arrays.copyOfRange(buffer, length, buffer.length + length);
    buffered = buffered - length;
  }
  
  private int calculatePacketStart(int match) {
    return ((Packet.LENGTH - Packet.PATTERN_OFFSET) + match) % Packet.LENGTH;
  }
  
}
