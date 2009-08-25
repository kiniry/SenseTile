package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.Packet;

public interface CloneablePacket extends Packet, Cloneable {

  Object clone() throws CloneNotSupportedException;

}
