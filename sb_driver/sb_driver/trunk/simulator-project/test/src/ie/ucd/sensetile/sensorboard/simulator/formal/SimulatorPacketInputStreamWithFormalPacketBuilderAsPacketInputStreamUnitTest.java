package ie.ucd.sensetile.sensorboard.simulator.formal;

import ie.ucd.sensetile.sensorboard.PacketInputStream;
import ie.ucd.sensetile.sensorboard.PacketInputStreamUnitTestCase;
import ie.ucd.sensetile.sensorboard.simulator.SimulatorPacketInputStream;
import ie.ucd.sensetile.sensorboard.simulator.formal.channel.FilePathProvider;
import ie.ucd.sensetile.sensorboard.simulator.formal.sensor.type.SensorIndexer;

public class SimulatorPacketInputStreamWithFormalPacketBuilderAsPacketInputStreamUnitTest 
    extends PacketInputStreamUnitTestCase {
  
  @Override
  protected PacketInputStream createPacketInputStream(final int availablePackets) {
    String[] thePaths = new String[]{
        "./audios/Temperature",
        "./audios/Light",
        "./audios/Pressure",
        "./audios/AxesX",
        "./audios/AxesY",
        "./audios/AxesZ"};
      String[] theSoundPaths = new String[]{
          "./audios/Sound0",
          "./audios/Sound1",
          "./audios/Sound2",
          "./audios/Sound3"};
      String[] theUltraSoundPaths = new String[]{
          "",
          "",
          "",
          "",
          ""};
      FilePathProvider fpp = new FilePathProvider(thePaths,theSoundPaths,theUltraSoundPaths);
      FormalInstancePacket instancePacket = new FormalInstancePacket();
      FormalPacketBuilder builder = new FormalSensorPacketBuilder(instancePacket, fpp, SensorIndexer.AUDIO_FREQUENCY_48KHZ);
      SimulatorPacketInputStream simulator = new SimulatorPacketInputStream(builder);
      return simulator;
    }
  
  @Override
  protected void disposePacketInputStream(final PacketInputStream packetInputStream) {
  }
  
}
