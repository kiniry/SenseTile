package ie.ucd.sensetile.sensorboard;

import ie.ucd.sensetile.sensorboard.driver.ByteArrayPacket;
import ie.ucd.sensetile.sensorboard.driver.DriverGenerator;
import ie.ucd.sensetile.sensorboard.driver.PacketRawByteArrayBuilder;
import ie.ucd.sensetile.util.UnsignedByteArray;

import java.io.ByteArrayInputStream;
import java.io.InputStream;



public class Generator {

  public static int[] getIntArray() {
    return new int[] {
        Integer.MIN_VALUE, 
        -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        81, 82, 83, 
        100, 200, 300, 400,
        Integer.MAX_VALUE 
    };
  }

  public static int[] getIntArrayLimted() {
    return new int[] {
        -1, 0, 1, 30 
    };
  }

  protected static ByteArrayInputStream buildInputStream(int length, int offset) {
    byte[] raw = prepareByteArrayPacket(length, offset);
    return new ByteArrayInputStream(raw);
  }

  protected static byte[] prepareByteArrayPacket(int length) {
    return prepareByteArrayPacket(length, 0);
  }

  private static byte[] prepareByteArrayPacket(int length, int offset) {
    try {
      return PacketRawByteArrayBuilder.prepare(length, offset);
    } catch (SenseTileException e) {
      return null;
    }
  }

  public static InputStream getInputStream(int n) {
    switch (n) {
    case 0:
      return buildInputStream(0, 0);
    case 1:
      return buildInputStream(100, 0);
    case 2:
      return buildInputStream(100, 50);
    case 3:
      return buildInputStream(ByteArrayPacket.LENGTH, 0);
    case 4:
      return buildInputStream(ByteArrayPacket.LENGTH * 30, 0);
    case 5:
      return buildInputStream(ByteArrayPacket.LENGTH * 30, 1);
    case 6:
      return buildInputStream(ByteArrayPacket.LENGTH * 30, 100);
    case 7:
      return buildInputStream(ByteArrayPacket.LENGTH * 30, ByteArrayPacket.LENGTH - 1);
    case 9:
      return new ByteArrayInputStream(new byte[100]);
    case 10:
      return new ByteArrayInputStream(new byte[ByteArrayPacket.LENGTH * 30]);
    default:
      break;
    }
    throw new java.util.NoSuchElementException();
  }

  public static byte[][] getByteArrayArray() {
    return new byte[][] {
        prepareByteArrayPacket(ByteArrayPacket.LENGTH),
        prepareByteArrayPacket(ByteArrayPacket.LENGTH * 2),
        prepareByteArrayPacket(ByteArrayPacket.LENGTH * 50),
        prepareByteArrayPacket(ByteArrayPacket.LENGTH - 1),
        new byte[0],
        new byte[100],
        new byte[ByteArrayPacket.LENGTH], 
        new byte[ByteArrayPacket.LENGTH * 3] 
    };
  }

  public static Packet[][] getPacketArrayEmptyArray() {
      return new Packet[][] {
          new Packet[0],
          new Packet[1],
          new Packet[2],
          new Packet[3],
          new Packet[10],
          new Packet[100]
      };
  //    return new Packet[][] {
  //        new Packet[1]
  //    };
    }

  public static UnsignedByteArray getUnsignedByteArray(int n) {
    switch (n) {
    case 0:
      return UnsignedByteArray.create(
          prepareByteArrayPacket(ByteArrayPacket.LENGTH), 0,
          ByteArrayPacket.LENGTH);
    case 1:
      return UnsignedByteArray.create(
          prepareByteArrayPacket(ByteArrayPacket.LENGTH * 2),
          ByteArrayPacket.LENGTH, ByteArrayPacket.LENGTH);
    case 2:
      return UnsignedByteArray.create(new byte[100]);
    case 3:
      return UnsignedByteArray.create(new byte[ByteArrayPacket.LENGTH]);
    default:
      break;
    }
    throw new java.util.NoSuchElementException();
  }

  public static Frame getFrame(int n) {
    return DriverGenerator.getByteArrayFrame(n);
  }

  public static Packet getPacket(int n) {
    return DriverGenerator.getByteArrayPacket(n);
  }

  public static PacketInputStream getPacketInputStream(int n) {
    return DriverGenerator.getInputStreamPacketInputStream(n);
  }

}
