package ie.ucd.sensetile;

import com.ftdichip.ftd2xx.Device;
import com.ftdichip.ftd2xx.FTD2xxException;

public class Driver {
  
  private final Device device;
  
  public Driver(final Device device) {
    this.device = device;
  }
  
  public void open() throws FTD2xxException {
    device.open();
  }
  
  public void close() throws FTD2xxException {
    device.close();
  }
  
  public void reset() throws FTD2xxException {
    device.reset();
  }
  
  public int read(final Packet[] byte_array) throws FTD2xxException {
    // return device.read(byte_array);
    return 0;
  }
}
