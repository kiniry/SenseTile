package ie.ucd.sensetile.sensorboard.driver;

import java.util.Properties;

import ie.ucd.sensetile.sensorboard.Generator;
import ie.ucd.sensetile.sensorboard.SenseTileException;
import ie.ucd.sensetile.util.UnsignedByteArray;

public class DriverGenerator extends Generator {

  public static ByteArrayFrame getByteArrayFrame(int n) {
    UnsignedByteArray bytePacket;
    ByteArrayFrame frame;
    switch (n) {
    case 0:
      bytePacket = getUnsignedByteArray(0);
      frame = new ByteArrayFrame(bytePacket, 0);
      return frame;
    case 1:
      bytePacket = getUnsignedByteArray(1);
      frame = new ByteArrayFrame(bytePacket, 0);
      return frame;
    case 2:
      bytePacket = getUnsignedByteArray(0);
      frame = new ByteArrayFrame(bytePacket, 81);
      return frame;
    case 3:
      bytePacket = getUnsignedByteArray(1);
      frame = new ByteArrayFrame(bytePacket, 81);
      return frame;
    default:
      break;
    }
    throw new java.util.NoSuchElementException();
  }

  public static ByteArrayPacket getByteArrayPacket(int n) {
    try {
      switch (n) {
      case 0:
        return ByteArrayPacket.createPacket(getUnsignedByteArray(0));
      case 1:
        return ByteArrayPacket.createPacket(getUnsignedByteArray(1));
      default:
        break;
      }
      throw new java.util.NoSuchElementException();
    } catch (SenseTileException e) {
      throw new java.util.NoSuchElementException();
    }
  }

  public static InputStreamPacketInputStream getInputStreamPacketInputStream(int n) {
    return InputStreamPacketInputStream.createInputStreamPacketInputStream(getInputStream(n));
  }

  public static Properties[] getInputStreamPacketInputStreamPropertiesArray() {
    // public static final String TRIM_PACKETS_PROPERTY = "trimPackets";
    // public static final String VALIDATE_MINIMUM_PACKETS_PROPERTY = "validateMinimumPackets";
    // public static final String BUFFER_PACKETS_PROPERTY = "bufferPackets";
    //    
    // private static final Properties DEFAULT_PROPERTIES = new Properties();
    //    
    // static {
    // DEFAULT_PROPERTIES.setProperty(BUFFER_PACKETS_PROPERTY, "6");
    // DEFAULT_PROPERTIES.setProperty(VALIDATE_MINIMUM_PACKETS_PROPERTY, "3");
    // DEFAULT_PROPERTIES.setProperty(TRIM_PACKETS_PROPERTY, "2");
    //TODO to be complicated 
    return new Properties[] {
        new Properties()
    };
  }

}
