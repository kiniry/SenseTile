package ie.ucd.sensetile;

import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.SenseTileException;
import ie.ucd.sensetile.sensorboard.driver.InputStreamPacketInputStream;

import java.io.FileInputStream;
import java.io.IOException;

public class TestStream {
  
  static public void main(String[] args) throws IOException, SenseTileException{
    FileInputStream stream = new FileInputStream("work/test3.bin");
    PacketPrinter printer = new PacketPrinter();
    InputStreamPacketInputStream packetStream = InputStreamPacketInputStream.createInputStreamPacketInputStream(stream);
    int count = 0;
    while (true) {
      System.out.println(count);
      Packet packet = packetStream.read();
      printer.print(packet);
//      Packet.Time time = packet.getTime();
//      System.out.println("AX: " + (int) packet.getAccelerometerX());
//      System.out.println("AY: " + (int) packet.getAccelerometerY());
//      System.out.println("AZ: " + (int) packet.getAccelerometerZ());
//      System.out.println("Counter: " + (int) packet.getCounter());
//      System.out.println("L: " + (int) packet.getLightLevel());
//      System.out.println("P: " + (int) packet.getPressure());
//      System.out.println("SC: " + (int) packet.getSupplyCurrent());
//      System.out.println("SV: " + (int) packet.getSupplyVoltage());
//      System.out.println("T: " + (int) packet.getTemperature());
//      System.out.println("AF: " + (int) packet.getFrame(0).getAudioFrequency());
//      System.out.println("Time: " + time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds() + ":" + time.getCentiSeconds());
//      for (int frameIndex = 0; frameIndex < Packet.FRAMES; frameIndex++) {
//        Packet.Frame frame = packet.getFrame(frameIndex);
//        System.out.println("Frame: " + frameIndex);
//        System.out.println("Audio ok: " + frame.isAudioActive());
//        System.out.println("Audio sample 0: " + (int) frame.getAudio(0));
//        System.out.println("Audio sample 1: " + (int) frame.getAudio(1));
//        System.out.println("Audio sample 2: " + (int) frame.getAudio(2));
//        System.out.println("Audio sample 3: " + (int) frame.getAudio(3));
//        System.out.println("ADC ok: " + frame.isADCActive());
//        System.out.println("ADC channel: " + (int) frame.getADCChannel());
//        System.out.println("ADC sample: " + (int) frame.getADC());
//        System.out.println("Synch ok: " + frame.isIRDSynachronizationActive());
//      }
      count ++;
    }
  }
}
