package ie.ucd.sensetile;

import java.util.Arrays;

public class Packet {
  
  final static int LENGTH = 1024;
  final static byte[] PATTERN = {
      (byte) 0xff, 
      (byte) 0xff, 
      (byte) 0xff, 
      (byte) 0xff, 
      (byte) 0xff, 
      (byte) 0xff, 
      (byte) 0xff, 
      (byte) 0xff, 
      (byte) 0xff, 
      (byte) 0xff };
  final static int PATTERN_OFFSET = 1018;
  
  final static int INDEX_POSITION = 10;
  
  final byte[] rawPacket;
  
  Packet(final byte[] rawPacket) {
    byte[] rawPacketCopy = Arrays.copyOf(rawPacket, rawPacket.length);
    this.rawPacket = rawPacketCopy;
  }
  
  public static Packet createPacket(final byte[] rawPacket) throws SenseTileException {
    checkLength(rawPacket);
    checkPattern(rawPacket);
    return new Packet(rawPacket);
  }
  
  public int getIndex() {
    return UnsignedByteArray.bytes2ToInt(rawPacket, INDEX_POSITION);
  }
  
  void setIndex(int value) {
    UnsignedByteArray.intToBytes2(rawPacket, INDEX_POSITION, value);
  }
  
  static public Packet createPacket(
      final byte[] rawPacket, 
      Packet previousPacket) 
  throws SenseTileException {
    Packet packet = createPacket(rawPacket);
    checkIndex(previousPacket, packet);
    return packet;
  }
  
  private static void checkLength(final byte[] rawPacket) 
  throws SenseTileException {
    if (rawPacket.length != LENGTH) {
      throw new SenseTileException("Wrong packet length.");
    }
  }

  private static void checkPattern(final byte[] rawPacket) throws SenseTileException {
    BytePattern bp = BytePattern.createPattern(PATTERN);
    int offset = bp.match(rawPacket);
    if (offset == -1) {
      throw new SenseTileException("Packet pattern not found.");
    }
    if (offset != PATTERN_OFFSET) {
      throw new SenseTileException("Packet pattern misplaced.");
    }
  }
  
  static void checkIndex(Packet previous, Packet current) 
  throws SenseTileException {
    if (!((previous.getIndex() + 1) == current.getIndex())) {
      throw new SenseTileException(
          "Packet indexes are not sequential: " + 
          "previous is " + previous.getIndex() + ", " + 
          "current is" + current.getIndex() + ".");
    }
  }
}
