package ie.ucd.sensetile.sensorboard.simulator.formal;

import ie.ucd.sensetile.sensorboard.Packet;

/**
 * Packet builder class to be used to build simulators.
 * @title         "PacketBuilder"
 * @date          "2009/10/10"
 * @author        "delbianc && Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
//@ model import ie.ucd.sensetile.sensorboard.simulator.sensor.ISensor;
public interface PacketBuilder {
  /**
   * Return a packet.
   * @return next packet
   */
	//@ ghost InstancePacket g_packet;
	//@ ghost Object g_o;
	//@ ghost ISensor g_sensor; 
	
	/*@ public behavior
	  @ ensures (g_o instanceof InstancePacket  && 
	  @ g_sensor.isEnabled()) ==> (\result == g_packet ) && (g_packet != null);
	  @ signals_only UnsupportedOperationException;
	  @*/
	Packet getPacket(); 
}
