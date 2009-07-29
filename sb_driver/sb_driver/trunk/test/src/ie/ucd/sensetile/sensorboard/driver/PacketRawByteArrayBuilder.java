package ie.ucd.sensetile.sensorboard.driver;

import java.util.Arrays;

public final class PacketRawByteArrayBuilder {
  
  private PacketRawByteArrayBuilder() {
  }

  private static byte[] array = new byte[0];
  
  private static byte[] prepareArray(final int length) {
    final byte[] rawPacketArray = new byte[ByteArrayPacket.LENGTH * length];
    final byte[] array = rawPacketArray;
    for (
        int packetIndex = 0; 
        packetIndex <= array.length / ByteArrayPacket.LENGTH; 
        packetIndex ++) {
      for (
          int patternIndex = 0; 
          patternIndex < ByteArrayPacket.PATTERN.length; 
          patternIndex++) {
        int index = 
          (patternIndex + ByteArrayPacket.PATTERN_OFFSET) % 
          ByteArrayPacket.LENGTH + packetIndex * ByteArrayPacket.LENGTH;
        if (index < array.length) {
          array[index] = ByteArrayPacket.PATTERN[patternIndex];
        }
      }
    }
    return rawPacketArray;
  }
  
  private static byte[] getArray(final int length) {
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
   */
  public static byte[] prepare(final int length) {
    return prepare(length, 0);
  }
  
  /**
   * Builds a raw bytes packet array with an offset. 
   * 
   * @param length bytes length of array
   * @param offset packet offset in array
   * @return byte array of packets
   */
  public static byte[] prepare(final int length, final int offset) {
    int copyOffset = (ByteArrayPacket.LENGTH  - offset) % ByteArrayPacket.LENGTH;
    if (copyOffset < 0) {
      copyOffset = copyOffset + ByteArrayPacket.LENGTH;
    }
    return Arrays.copyOfRange(
        getArray(length / ByteArrayPacket.LENGTH + 2), 
        copyOffset, copyOffset + length);
  }
}
