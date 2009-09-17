package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.Generator;

public class SimulatorGenerator extends Generator{

  public static CloneablePacket[] getCloneablePacketArray() {
    return new CloneablePacket[] { new InstancePacket() };
  }

  public static InstancePacket[] getInstancePacketArray() {
    return new InstancePacket[] { new InstancePacket() };
  }

  public static InstanceFrame[] getInstanceFrameArray() {
    return new InstanceFrame[] { new InstanceFrame() };
  }
  
  public static PacketBuilder getPacketBuilder(int n) {
    switch (n) {
    case 0:
      return new FileAndClonePacketBuilder(new InstancePacket());
    default:
      break;
    }
    throw new java.util.NoSuchElementException();
  }
  
  public static SimulatorPacketInputStream getSimulatorPacketInputStream(int n) {
    return new SimulatorPacketInputStream(getPacketBuilder(n));
  }

}
