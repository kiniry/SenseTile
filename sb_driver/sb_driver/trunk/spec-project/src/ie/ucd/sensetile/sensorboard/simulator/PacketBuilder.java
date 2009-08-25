package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.Packet;

/**
 * Packet builder class to be used to build simulators.
 * 
 * @author delbianc
 */
public interface PacketBuilder {
  
  /**
   * Return a packet.
   * 
   * @return next packet
   */
  Packet getPacket();
  
}
