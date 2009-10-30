package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.Packet;

/**
 * Packet builder class to be used to build simulators.
 * 
 * @author Vieri del Bianco (vieri.delbianco@gmail.com)
 */
public interface PacketBuilder {
  
  /**
   * Return a packet.
   * 
   * @return next packet
   */
  Packet getPacket();
  
}
