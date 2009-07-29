package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.PacketInputStream;
import ie.ucd.sensetile.sensorboard.PacketInputStreamUnitTestCase;

public class SimulatorPacketInputStreamAsPacketInputStreamUnitTest 
    extends PacketInputStreamUnitTestCase {
  
  @Override
  protected PacketInputStream createPacketInputStream(final int availablePackets) {
    PacketBuilder builder = new ClonePacketBuilder(new InstancePacket());
    SimulatorPacketInputStream simulator = new SimulatorPacketInputStream(builder);
    return simulator;
  }
  
  @Override
  protected void disposePacketInputStream(final PacketInputStream packetInputStream) {
  }
  
}
