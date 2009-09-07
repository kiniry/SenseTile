package ie.ucd.sensetile.sensorboard.driver;


import ie.ucd.sensetile.sensorboard.PacketInputStream;
import ie.ucd.sensetile.sensorboard.PacketInputStreamUnitTestCase;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InputStreamPacketInputStreamAsPacketInputStreamUnitTest 
    extends PacketInputStreamUnitTestCase {
  
  @Override
  protected PacketInputStream createPacketInputStream(final int availablePackets) {
    byte[] rawPacket = PacketRawByteArrayBuilder.prepare(
        ByteArrayPacket.LENGTH * availablePackets);
    InputStream is = new ByteArrayInputStream(rawPacket);
    PacketInputStream pis = InputStreamPacketInputStream.createInputStreamPacketInputStream(is);
    return pis;
  }
  
  @Override
  protected void disposePacketInputStream(final PacketInputStream packetInputStream) {
  }

}
