package ie.ucd.sensetile.sensorboard.driver;

import ie.ucd.sensetile.sensorboard.Frame;
import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.SenseTileException;
import ie.ucd.sensetile.util.UnsignedByteArray;

import java.util.Arrays;

/**
 * Byte array builder, based on PacketByteArray structure.
 * 
 * @author Vieri del Bianco (vieri.delbianco@gmail.com)
 *
 */
public final class PacketRawByteArrayBuilder {
  
  private PacketRawByteArrayBuilder() {
  }
  
  private static byte[] array = new byte[0];
  
  private static byte[] prepareArray(final int length) 
      throws SenseTileException {
    final byte[] array = new byte[ByteArrayPacket.LENGTH * length];
    final byte[] rawPacket = new byte[ByteArrayPacket.LENGTH];
    intializePacket(rawPacket);
    ByteArrayPacket packet = ByteArrayPacket.createPacket(rawPacket);
    for (int packetIndex = 0; packetIndex < length; packetIndex++) {
      // packet initialization
      packet.setCounter(packet.getCounter() + 1);
      // copy
      System.arraycopy(
          rawPacket, 0, 
          array, packetIndex * ByteArrayPacket.LENGTH, 
          ByteArrayPacket.LENGTH);
    }
    return array;
  }
  
  private static void intializePacket(final byte[] rawPacket) 
      throws SenseTileException {
    UnsignedByteArray bytePacket = UnsignedByteArray.create(rawPacket);
    // copy pattern structure into packet
    for (int index = 0; index < ByteArrayPacket.PATTERN.length; index++) {
      bytePacket.setByte(
          (index + ByteArrayPacket.PATTERN_OFFSET) % ByteArrayPacket.LENGTH, 
          ByteArrayPacket.PATTERN[index]);
    }
    // initialize frames
    ByteArrayFrame frame;
    for (int frameIndex = 0; frameIndex < Packet.FRAMES; frameIndex++) {
      frame = new ByteArrayFrame(bytePacket, frameIndex);
      frame.setADCChannel(frameIndex % Frame.ADC_CHANNELS);
    }
    // initialize packet
    final int pressureDefault = 310;
    final int accelerometerDefault = 1860;
    ByteArrayPacket packet = ByteArrayPacket.createPacket(bytePacket);
    packet.setCounter(0);
    packet.setPressure(pressureDefault);
    packet.setAccelerometerX(accelerometerDefault);
    packet.setAccelerometerY(accelerometerDefault);
    packet.setAccelerometerZ(accelerometerDefault);
  }
  
  private static byte[] getArray(final int length) throws SenseTileException {
    if (length >= array.length / ByteArrayPacket.LENGTH) {
      setArray(prepareArray(length));
    }
    return array;
  }
  
  private static void setArray(final byte[] rawPacketArray) {
    PacketRawByteArrayBuilder.array = rawPacketArray;
  }
  
  /**
   * Builds a raw bytes packet array. 
   * 
   * @param length bytes length of array
   * @return byte array of packets
   * @throws SenseTileException wrong packet structure
   */
  public static byte[] prepare(final int length) throws SenseTileException {
    return prepare(length, 0);
  }
  
  /**
   * Builds a raw bytes packet array with an offset. 
   * 
   * @param length bytes length of array
   * @param offset packet offset in array
   * @return byte array of packets
   * @throws SenseTileException wrong packet structure
   */
  public static byte[] prepare(final int length, final int offset) 
      throws SenseTileException {
    int copyOffset = 
      (ByteArrayPacket.LENGTH  - offset) % ByteArrayPacket.LENGTH;
    if (copyOffset < 0) {
      copyOffset = copyOffset + ByteArrayPacket.LENGTH;
    }
    return Arrays.copyOfRange(
        getArray(length / ByteArrayPacket.LENGTH + 2), 
        copyOffset, copyOffset + length);
  }
}
