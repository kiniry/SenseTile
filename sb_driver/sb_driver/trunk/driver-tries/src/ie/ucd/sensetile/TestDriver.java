package ie.ucd.sensetile;

import ie.ucd.sensetile.sensorboard.Driver;
//import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.PacketInputStream;
import ie.ucd.sensetile.sensorboard.SenseTileException;

import java.io.IOException;

public class TestDriver {
  
  static public void main(String[] args) throws IOException, SenseTileException {
    final Driver driver = new Driver(1);
    driver.open();
    final PacketInputStream packetStream = driver.getStream();
    PacketPrinter printer = new PacketPrinter();
    int count = 0;
    Packet packet;
    while (true) {
      System.out.println(count);
      packet = packetStream.read();
      printer.print(packet);
      count ++;
    }
  }
}
