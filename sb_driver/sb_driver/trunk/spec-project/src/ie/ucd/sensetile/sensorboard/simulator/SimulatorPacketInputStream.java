package ie.ucd.sensetile.sensorboard.simulator;

import java.io.IOException;

import ie.ucd.sensetile.sensorboard.PacketInputStream;
import ie.ucd.sensetile.sensorboard.Packet;

public class SimulatorPacketInputStream implements PacketInputStream {
  
  private static final int AVAILABLE_PACKETS = 100;
  
  private PacketBuilder builder;
  private boolean isClose;
  
  public SimulatorPacketInputStream(final PacketBuilder builder) {
    this.builder = builder;
    isClose = false;
  }
  
  public int availablePackets() throws IOException {
    checkClose();
    return AVAILABLE_PACKETS;
  }
  
  public void close() {
    isClose = true;
  }
  
  public Packet read() 
      throws IOException {
    checkClose();
    return internalRead();
  }

  public int read(final Packet[] array) 
      throws IOException {
    return read(array, 0, array.length);
  }
  
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
  
  public void readFully(final Packet[] array) 
      throws IOException {
    read(array);
  }
  
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
