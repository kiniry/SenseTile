package ie.ucd.sensetile;


public class UnsignedByteArray {
  
  private byte[] ba;
  int offset;
  private int length;
  private boolean folding;
  
  public byte get(int index){
    index = checkAndNormalizeIndex(index, 1);
    return ba[(index + offset) % ba.length];
  }
  
  public void set(int index, byte value){
    index = checkAndNormalizeIndex(index, 1);
    ba[(index + offset) % ba.length] = value;
  }
  
  public int getInt(int index) {
    index = checkAndNormalizeIndex(index, 2);
    return 
      ((0xff & get(index)) << 8) | 
      (0xff & get(index+1));
  }
  
  public void setInt(int index, int value) {
    index = checkAndNormalizeIndex(index, 2);
    set(index, (byte) (0xff & value >>> 8));
    set(index + 1, (byte) (0xff & value));
  }
  
  public int length() {
    return length;
  }
  
  public static UnsignedByteArray create(byte[] ba) {
    return create(ba, 0, ba.length, false);
  }
  
  public static UnsignedByteArray create(UnsignedByteArray uba, int offset, int length) {
    byte[] ba = uba.ba;
    int realOffset = (offset + uba.offset) % ba.length;
    return create(ba, realOffset, length, false);
  }
  
  public static UnsignedByteArray create(byte[] ba, int offset, int length) {
    return create(ba, offset, length, false);
  }
  
  public static UnsignedByteArray createFolding(byte[] ba) {
    return create(ba, 0, ba.length, true);
  }
  
  public static UnsignedByteArray createFolding
      (UnsignedByteArray uba, int offset, int length) {
    byte[] ba = uba.ba;
    int realOffset = (offset + uba.offset) % ba.length;
    return create(ba, realOffset, length, true);
  }
  
  public static UnsignedByteArray createFolding
      (byte[] ba, int offset, int length) {
    return create(ba, offset, length, true);
  }
  
  public static UnsignedByteArray create
      (byte[] ba, int offset, int length, boolean folding) {
    checkParameters(ba, offset, length);
    return new UnsignedByteArray(ba, offset, length, folding);
  }

  private static void checkParameters(byte[] ba, int offset, int length) {
    if (ba == null){
      throw new NullPointerException();
    }
    if ((length < 0) || (length > ba.length)) {
      throw new IllegalArgumentException("length: " + length);
    }
  }
  
  private UnsignedByteArray(
      byte[] ba, int offset, int length, boolean folding) {
    this.ba = ba;
    this.length = length;
    this.offset = (length == 0) ? 0 : normalizeIndex(offset, ba.length);
    this.folding = folding;
  }
  
  private int checkAndNormalizeIndex(int index, int operationLength){
    if (folding) {
      index = normalizeIndex(index, length);
    } else {
      if ((index + operationLength - 1) >= length) {
        throw new IndexOutOfBoundsException();
      }
    }
    return index;
  }
  
  private static int normalizeIndex(int index, int length){
    if (index >= length) {
      index = index % length;
    }
    if (index < 0) {
      index = index % length + length;
    }
    return index;
  }
  
}