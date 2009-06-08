package ie.ucd.sensetile;

public class Packet {
  
  final static int LENGTH = 1024;
  final static byte[] PATTERN = {
      (byte) 0xff, (byte) 0xee, 
      (byte) 0xff, (byte) 0xee, 
      (byte) 0xff, (byte) 0xee, 
      (byte) 0xff, (byte) 0xff, 
      (byte) 0xff, (byte) 0xff };
  final static int PATTERN_OFFSET = 1018;
  
  final static int INDEX_POSITION = 20;
  
  final UnsignedByteArray raw;
  
  Packet(UnsignedByteArray raw) {
    this.raw = raw;
  }
  
  public static Packet createPacket(
      final byte[] rawPacket, 
      Packet previousPacket) 
      throws SenseTileException {
    Packet packet = createPacket(rawPacket);
    checkIndex(previousPacket, packet);
    return packet;
  }
  public static Packet createPacket(
      final byte[] rawPacket) 
      throws SenseTileException {
    UnsignedByteArray raw = UnsignedByteArray.create(rawPacket);
    return createPacket(raw);
  }
  
  public static Packet createPacket(
      UnsignedByteArray raw, 
      Packet previousPacket) 
      throws SenseTileException {
    Packet packet = createPacket(raw);
    checkIndex(previousPacket, packet);
    return packet;
  }
  
  public static Packet createPacket(
      UnsignedByteArray raw) 
      throws SenseTileException {
    checkLength(raw);
    raw = UnsignedByteArray.create(raw, 0, LENGTH);
    checkPattern(raw);
    return new Packet(raw);
  }
  
  public int getIndex() {
    return raw.getInt(INDEX_POSITION);
  }
  
  void setIndex(int value) {
    raw.setInt(INDEX_POSITION, value);
  }
  
  private static void checkLength(UnsignedByteArray raw) 
      throws SenseTileException {
    if (raw.length() < LENGTH) {
      throw new SenseTileException("Wrong packet length.");
    }
  }

  private static void checkPattern(UnsignedByteArray raw) 
      throws SenseTileException {
    BytePattern bp = BytePattern.createPattern(PATTERN);
    int relativeOffset = bp.match(UnsignedByteArray.create(raw, PATTERN_OFFSET, raw.length()));
    if (relativeOffset == -1) {
      throw new SenseTileException("Packet pattern not found.");
    }
    if (relativeOffset != 0) {
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
