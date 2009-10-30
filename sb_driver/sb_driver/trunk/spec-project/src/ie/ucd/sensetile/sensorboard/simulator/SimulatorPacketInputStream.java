package ie.ucd.sensetile.sensorboard.simulator;

import java.io.IOException;

import ie.ucd.sensetile.sensorboard.PacketInputStream;
import ie.ucd.sensetile.sensorboard.Packet;

/**
 * PacketInputStream from PacketBuilder. 
 * 
 * @author delbianc
 */
public class SimulatorPacketInputStream implements PacketInputStream {
  
  private static final int AVAILABLE_PACKETS = 100;
  
  private PacketBuilder builder;
  private boolean isClose;
  
  /**
   * PacketInputStream from PacketBuilder.
   * 
   * @param builder packet builder
   */
  public SimulatorPacketInputStream(final PacketBuilder builder) {
    this.builder = builder;
    isClose = false;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStream#availablePackets()
   */
  public int availablePackets() throws IOException {
    checkClose();
    return AVAILABLE_PACKETS;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStream#close()
   */
  public void close() {
    isClose = true;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStream#read()
   */
  public Packet read() 
      throws IOException {
    checkClose();
    return internalRead();
  }

  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStream#read(
   *   ie.ucd.sensetile.sensorboard.Packet[])
   */
  public int read(final Packet[] array) 
      throws IOException {
    return read(array, 0, array.length);
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStream#read(
   *   ie.ucd.sensetile.sensorboard.Packet[], int, int)
   */
  public int read(
      final Packet[] array, final int offset, final int length) 
      throws IOException {
    checkClose();
    if (length < 0) {
      throw new IndexOutOfBoundsException();
    }
    for (int index = offset; index < offset + length; index++) {
      array[index] = internalRead();
    }
    return length;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStream#readFully(
   *   ie.ucd.sensetile.sensorboard.Packet[])
   */
  public void readFully(final Packet[] array) 
      throws IOException {
    read(array);
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.PacketInputStream#readFully(
   *   ie.ucd.sensetile.sensorboard.Packet[], int, int)
   */
  public void readFully(
      final Packet[] array, final int offset, final int length)
      throws IOException {
    read(array, offset, length);
  }
  
  private boolean isClose() {
    return isClose;
  }
  
  private void checkClose() throws IOException {
    if (isClose()) {
      throw new IOException();
    }
  }
  
  private Packet internalRead() {
    return builder.getPacket();
  }
  
}
