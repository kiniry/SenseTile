package ie.ucd.sensetile.sensorboard.simulator.formal;

import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.simulator.PacketBuilder;

/**
 * Packet builder class to be used to build simulators.
 * @title         "FormalPacketBuilder"
 * @date          "2009/10/10"
 * @author        "delbianc && Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
//@ model import ie.ucd.sensetile.sensorboard.simulator.formal.sensor.ISensor;
public interface FormalPacketBuilder extends PacketBuilder {
  /**
   * Return a packet.
   * @return next packet
   */
	//@ ghost FormalInstancePacket g_packet;
	//@ ghost Object g_o;
	//@ ghost ISensor g_sensor; 
	
	/*@ also
	  @ public behavior
	  @ ensures (g_o instanceof FormalInstancePacket  && 
	  @ g_sensor.isEnabled()) ==> (\result == g_packet ) && (g_packet != null);
	  @ signals_only UnsupportedOperationException;
	  @*/
	Packet getPacket(); 
}
