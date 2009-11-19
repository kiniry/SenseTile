package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.SenseTileException;

public class TestRAC {

  public static void main(String[] args) throws SenseTileException {
    InstancePacket packet = new InstancePacket();
    InstanceFrame frame = (InstanceFrame) packet.getFrame(0);
    for (int frameIndex = 70; frameIndex < Packet.FRAMES; frameIndex++) {
      System.out.println(frameIndex);
      frame = (InstanceFrame) packet.getFrame(frameIndex);;
      frame.setADCChannel(0);
    }
  }
}
