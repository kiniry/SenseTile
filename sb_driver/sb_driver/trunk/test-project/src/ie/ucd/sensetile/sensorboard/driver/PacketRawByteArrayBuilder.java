package ie.ucd.sensetile.sensorboard.driver;

import ie.ucd.sensetile.sensorboard.SenseTileException;
import ie.ucd.sensetile.util.UnsignedByteArray;

import java.util.Arrays;

public final class PacketRawByteArrayBuilder {
  
  private PacketRawByteArrayBuilder() {
  }
  
  private static byte[] array = new byte[0];
  
  private static byte[] prepareArray(final int length) throws SenseTileException {
    final byte[] array = new byte[ByteArrayPacket.LENGTH * length];
    final byte[] rawArray = new byte[ByteArrayPacket.LENGTH];
    UnsignedByteArray byteArray = UnsignedByteArray.create(rawArray);
    for (
        int patternIndex = 0; 
        patternIndex < ByteArrayPacket.PATTERN.length; 
        patternIndex++) {
      int index = 
        (patternIndex + ByteArrayPacket.PATTERN_OFFSET) % 
        ByteArrayPacket.LENGTH;
      byteArray.setByte(index, ByteArrayPacket.PATTERN[patternIndex]);
    }
    ByteArrayPacket packetArray = ByteArrayPacket.createPacket(byteArray);
    for (int packetIndex = 0; packetIndex < length; packetIndex ++) {
      packetArray.setCounter(packetArray.getCounter() + 1);
      System.arraycopy(
          rawArray, 0, 
          array, packetIndex * ByteArrayPacket.LENGTH, 
          ByteArrayPacket.LENGTH);
    }
    return array;
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
  public static byte[] prepare(final int length, final int offset) throws SenseTileException {
    int copyOffset = (ByteArrayPacket.LENGTH  - offset) % ByteArrayPacket.LENGTH;
    if (copyOffset < 0) {
      copyOffset = copyOffset + ByteArrayPacket.LENGTH;
    }
    return Arrays.copyOfRange(
        getArray(length / ByteArrayPacket.LENGTH + 2), 
        copyOffset, copyOffset + length);
  }
}
