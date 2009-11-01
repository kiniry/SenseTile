package ie.ucd.sensetile.sensorboard.simulator.formal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.SenseTileException;
import ie.ucd.sensetile.sensorboard.simulator.PacketBuilder;
import ie.ucd.sensetile.sensorboard.simulator.SimulatorPacketInputStream;
import ie.ucd.sensetile.sensorboard.simulator.formal.channel.FilePathProvider;
import ie.ucd.sensetile.sensorboard.simulator.formal.sensor.type.SensorIndexer;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimulatorPacketInputStreamWithFormalPacketBuilderUnitTest {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }
  
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  private SimulatorPacketInputStream simulator;
  
  @Before
  public void setUp() throws Exception {
    String[] thePaths = new String[]{
        "./audios/Temperature",
        "./audios/Light",
        "./audios/Pressure",
        "./audios/AxesX",
        "./audios/AxesY",
        "./audios/AxesZ"};
    String[] theSoundPaths = new String[]{
        "./audios/Sound0",
        "./audios/Sound1",
        "./audios/Sound2",
         "./audios/Sound3"};
    String[] theUltraSoundPaths = new String[]{
        "",
        "",
        "",
        "",
        ""};
    FilePathProvider fpp = new FilePathProvider(thePaths,theSoundPaths,theUltraSoundPaths);
    FormalInstancePacket instancePacket = new FormalInstancePacket();
    PacketBuilder builder = new FormalSensorPacketBuilder(instancePacket, fpp, SensorIndexer.AUDIO_FREQUENCY_48KHZ);
    simulator = new SimulatorPacketInputStream(builder);
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testAvailablePackets() throws IOException {
    assertTrue(simulator.availablePackets() > 0);
  }
  
  @Test (expected=IOException.class)
  public void testClose() throws IOException, SenseTileException {
    simulator.close();
    simulator.read();
  }
  
  @Test
  public void testRead() throws IOException, SenseTileException {
    assertNotNull(simulator.read());
  }
  
  @Test
  public void testReadArray() throws IOException, SenseTileException {
    final int length = 200;
    Packet[] array = new Packet[length];
    simulator.read(array);
    for (int index = 0; index < array.length; index++) {
      assertNotNull(array[index]);
    }
  }
  
  @Test
  public void testReadArrayOffset() throws IOException, SenseTileException {
    final int arrayLength = 200;
    final int offset = 10;
    final int readLength = 100;
    Packet[] array = new Packet[arrayLength];
    simulator.read(array, offset, readLength);
    for (int index = 0; index < array.length; index++) {
      if(index >= offset && index < offset + readLength ) {
        assertNotNull(array[index]);
      } else {
        assertNull(array[index]);
      }
    }
  }
}
