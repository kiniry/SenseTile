package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.SensorBoardPacket;

public interface PacketBuilder {
  
  SensorBoardPacket getPacket();
  
}
