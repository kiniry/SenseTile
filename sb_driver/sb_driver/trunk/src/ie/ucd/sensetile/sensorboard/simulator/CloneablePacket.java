package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.SensorBoardPacket;

public interface CloneablePacket extends SensorBoardPacket, Cloneable {

  CloneablePacket clone() throws CloneNotSupportedException;

}
