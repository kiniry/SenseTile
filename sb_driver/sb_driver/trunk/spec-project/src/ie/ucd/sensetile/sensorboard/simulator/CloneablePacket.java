package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.Packet;

/**
 * Packet that can be cloned.
 * 
 * @author Vieri del Bianco (vieri.delbianco@gmail.com)
 */
public interface CloneablePacket extends Packet, Cloneable {

  /**
   * Clone method.
   * 
   * @return cloned packet
   * @throws CloneNotSupportedException clone is not supported
   */
  Object clone() throws CloneNotSupportedException;

}
