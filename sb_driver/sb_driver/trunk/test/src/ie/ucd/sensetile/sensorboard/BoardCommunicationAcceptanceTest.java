package ie.ucd.sensetile.sensorboard;

import java.io.IOException;

import org.junit.Test;

public class BoardCommunicationAcceptanceTest {
  
  @Test
  public void testOpen() throws IOException, SenseTileException{
    final Driver driver = new Driver(0);
    driver.open();
    final PacketInputStream input = driver.getStream();
    input.read();
    SensorBoardPacket[] array = new SensorBoardPacket[10];
    input.read(array);
    input.read(array, 2, 6);
    final int available = input.availablePackets();
    array = new SensorBoardPacket[available];
    input.read(array, 0, available);
    driver.close();
  }
  
//  @Test
//  public void testMotionInputStreamRead(){
//    PacketInputStream packetIn;
//    // setup packetIn
//    MotionInputStream in = MotionInputStream.create(packetIn);
//    Motion value = in.read();
//    // test value contents
//    Motion[] array = new Motion[10]
//    int read;
//    read = in.read(array);
//    // test array contents
//    read = in.read(array, 2, 6);
//    // test array contents
//    int available = in.available();
//    array = new Motion[available];
//    read = in.read(array, 0, available);
//    // test array contents
//  }
//  
//  @Test
//  public void testMotionInputStreamRateDiscardStrategy(){
//    PacketInputStream packetIn;
//    // setup packetIn
//    // define adn setup rate and discardStrategy
//    MotionInputStream in = 
//      MotionInputStream.create(packetIn, rate, discardStrategy);
//    Motion[] array = new Motion[10]
//    int read;
//    read = in.read(array);
//    // test array contents
//  }
//  
//  @Test
//  public void testMotionInputStreamRateInterpolateStrategy(){
//    PacketInputStream packetIn;
//    // setup packetIn
//    // define adn setup rate and interpolateStrategy
//    MotionInputStream in = 
//      MotionInputStream.create(packetIn, rate, interpolateStrategy);
//    Motion[] array = new Motion[10]
//    int read;
//    read = in.read(array);
//    // test array contents
//  }
  
}
