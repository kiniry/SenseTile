package ie.ucd.sensetile;


public class UnsignedByteArray {
  
  private byte[] array;
  int offset;
  private int length;
  private boolean folding;
  
  private UnsignedByteArray(
      byte[] array, int offset, int length, boolean folding) {
    this.array = array;
    this.length = length;
    this.offset = 
      (array.length == 0) ? 0 : normalizeIndex(offset, array.length);
    this.folding = folding;
  }
  
  public byte get(int index){
    index = checkAndNormalizeIndex(index, 1);
    return array[(index + offset) % array.length];
  }
  
  public void set(int index, byte value){
    index = checkAndNormalizeIndex(index, 1);
    array[(index + offset) % array.length] = value;
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
  
  public int getOffset() {
    return offset;
  }
  
  public int getEndOffset() {
    return UnsignedByteArray.normalizeIndex(
        getOffset() + length(), array.length);
  }
  
  public byte[] getArray() {
    return array;
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
  
  public static UnsignedByteArray create(byte[] array) {
    return create(array, 0, array.length, false);
  }
  
  public static UnsignedByteArray createFolding(byte[] array) {
    return create(array, 0, array.length, true);
  }
  
  public static UnsignedByteArray create
      (UnsignedByteArray uba, int offset, int length) {
    byte[] ba = uba.array;
    int realOffset = (offset + uba.offset) % ba.length;
    return create(ba, realOffset, length, false);
  }
  
  public static UnsignedByteArray createFolding
      (UnsignedByteArray uba, int offset, int length) {
    byte[] ba = uba.array;
    int realOffset = (offset + uba.offset) % ba.length;
    return create(ba, realOffset, length, true);
  }
  
  public static UnsignedByteArray create
      (byte[] array, int offset, int length) {
    return create(array, offset, length, false);
  }
  
  public static UnsignedByteArray createFolding
      (byte[] array, int offset, int length) {
    return create(array, offset, length, true);
  }
  
  public static UnsignedByteArray create
      (byte[] array, int offset, int length, boolean folding) {
    checkParameters(array, offset, length);
    return new UnsignedByteArray(array, offset, length, folding);
  }
  
  private static void checkParameters(byte[] array, int offset, int length) {
    if (array == null){
      throw new NullPointerException();
    }
    if ((length < 0) || (length > array.length)) {
      throw new IllegalArgumentException("length: " + length);
    }
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
