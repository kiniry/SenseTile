package test;


import ie.ucd.sensetile.sensorboard.Packet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Formatter;

public class PacketPrinter {
  
  private final Writer out;
  private Formatter formatter;
  
  private String realFormat = "%1$-16s: %3$ 8.4f %2$-4s %n";
  private String integerFormat = "%1$-16s: %3$ 8d %2$-4s %n";
  private String real3Format = "%1$-16s: %3$ 8.4fX %4$ 8.4fY %5$ 8.4fZ %2$-4s %n";
  private String timeFormat = "%1$-16s: %2$02dH:%3$02dM:%4$02dS:%5$02dcS %n";
  
  public PacketPrinter() {
    this(new OutputStreamWriter(System.out));
  }
  
  public PacketPrinter(final Writer out) {
    this.out = out;
  }
  
  public void print(Packet packet) throws IOException {
    formatter = new Formatter();
    printCounter(packet);
    printTime(packet);
    printTemperature(packet);
    printPressure(packet);
    printLightLevel(packet);
    printAccellerometer(packet);
    out.write(formatter.toString());
    out.flush();
  }
  
  private void printCounter(Packet packet) throws IOException {
    formatter.format(integerFormat, "counter", "", (int) packet.getCounter());
  }
  
//  private void printTime(Packet packet) throws IOException {
//    Packet.Time time = packet.getTime();
//    out.write("Time:        " + time.getHours() + "\n");
//  }
  
  private void printTemperature(Packet packet) throws IOException {
    formatter.format(realFormat, "temperature", "C°", packet.getTemperature()/ 16.0);
  }
  
  private void printPressure(Packet packet) throws IOException {
    formatter.format(realFormat, "pressure", "kpa", (packet.getPressure() * 4.0 + 1925.0) / 211.0);
  }
  
  private void printLightLevel(Packet packet) throws IOException {
    formatter.format(integerFormat, "light", "lux", (int) packet.getLightLevel());
  }
  
  private void printAccellerometer(Packet packet) throws IOException {
    double x = ( packet.getAccelerometerX() / 1860.0 - 1 ) * 5.0;
    double y = ( packet.getAccelerometerY() / 1860.0 - 1 ) * 5.0;
    double z = ( packet.getAccelerometerZ() / 1860.0 - 1 ) * 5.0;
    formatter.format(real3Format, "accelerometer", "g", x, y, z);
  }
  
  private void printTime(Packet packet) throws IOException {
    Packet.Time time = packet.getTime();
    formatter.format(timeFormat, "time", time.getHours(), time.getMinutes(), time.getSeconds(), time.getCentiSeconds());
  }
  
  
}
