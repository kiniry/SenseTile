package ie.ucd.sensetile;

public class UnsignedByteArray {
  
  public static int bytes2ToInt(byte[] rawPacket, int indexPosition) {
    return (
      (0xff & rawPacket[indexPosition]) << 8) | 
      (0xff & rawPacket[indexPosition+1]);

  }
  
  public static void intToBytes2(byte[] rawPacket, int indexPosition, int value) {
    rawPacket[indexPosition] = (byte) (0xff & value >>> 8);
    rawPacket[indexPosition+1] = (byte) (0xff & value);
  }
  
}