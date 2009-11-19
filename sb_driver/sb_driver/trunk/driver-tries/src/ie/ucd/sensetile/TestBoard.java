package ie.ucd.sensetile;

import java.io.DataInputStream;
import java.io.IOException;

import com.ftdichip.ftd2xx.Device;
import com.ftdichip.ftd2xx.Service;

public class TestBoard {


  public static void main(String[] args) throws IOException, InterruptedException {
    Device device = Service.listDevices()[1];
    device.open();
    device.setUSBParameters(1024 * 1024, 1024 * 1024);
    device.reset();
    DataInputStream is = new DataInputStream(device.getInputStream());
    // DataOutputStream dOut = new DataOutputStream(new FileOutputStream("stream.st"));
    byte[] array = new byte[1024 * 16 + 1];
    while (true) {
        System.out.println("1024 * 16 block:\n");
        is.readFully(array, 1, 1024 * 16);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        //dOut.write(array, 1, 1024);
        //dOut.flush();
    }

  }

}
