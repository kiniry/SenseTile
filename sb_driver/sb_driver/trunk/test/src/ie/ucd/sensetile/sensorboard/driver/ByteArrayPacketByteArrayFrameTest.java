package ie.ucd.sensetile.sensorboard.driver;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import ie.ucd.sensetile.sensorboard.SenseTileException;
import ie.ucd.sensetile.sensorboard.driver.ByteArrayPacket.ByteArrayFrame;
import ie.ucd.sensetile.util.UnsignedByteArray;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class ByteArrayPacketByteArrayFrameTest {
  
  private UnsignedByteArray rawPacket;
  private ByteArrayPacket packet;
  private ByteArrayFrame currentFrame;
  
  @Before
  public void setUp() throws SenseTileException {
    UnsignedByteArray basePacket = UnsignedByteArray.create(new byte[1024]);
    // copy pattern structure into packet
    for (int index = 0; index < ByteArrayPacket.PATTERN.length; index++) {
      basePacket.setByte(
          (index + ByteArrayPacket.PATTERN_OFFSET) % ByteArrayPacket.LENGTH, 
          ByteArrayPacket.PATTERN[index]);
    }
    // initialisation
    rawPacket = UnsignedByteArray.create(
        Arrays.copyOf(basePacket.getArray(), basePacket.getArray().length));
    packet = ByteArrayPacket.createPacket(rawPacket);
    int packetIndex = 100;
    packet.setCounter(packetIndex);
    currentFrame = packet.getFrame(1);
  }
  
  @Test
  public void testGetBit() {
    rawPacket.setBit(
        ByteArrayPacket.ADC_DATA_OFFSET + ByteArrayPacket.FRAME_LENGTH + 1, 
        0, true);
    rawPacket.setBit(
        ByteArrayPacket.ADC_DATA_OFFSET + ByteArrayPacket.FRAME_LENGTH, 
        6, true);
    assertTrue(currentFrame.getBit(0));
    assertTrue(currentFrame.getBit(14));
  }
  
  @Test
  public void testSetBit() {
    currentFrame.setBit(0, true);
    currentFrame.setBit(14, true);
    assertTrue(currentFrame.getBit(0));
    assertTrue(currentFrame.getBit(14));
  }
  
  @Test
  public void testGetADCChannel() {
    rawPacket.setShortUnsigned(
        ByteArrayPacket.ADC_DATA_OFFSET + ByteArrayPacket.FRAME_LENGTH, 
        0xFFFF);
    assertEquals(7, currentFrame.getADCChannel());
  }
  
  @Test
  public void testSetADCChannel() {
    currentFrame.setADCChannel(5);
    assertEquals(5, currentFrame.getADCChannel());
  }
  
}
