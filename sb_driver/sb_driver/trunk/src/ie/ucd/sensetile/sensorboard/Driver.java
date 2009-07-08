/*
 * Driver.java
 *
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */


package ie.ucd.sensetile.sensorboard;

import ie.ucd.sensetile.sensorboard.driver.InputStreamPacketInputStream;

import com.ftdichip.ftd2xx.BitBangMode;
import com.ftdichip.ftd2xx.Device;
import com.ftdichip.ftd2xx.FTD2xxException;
import com.ftdichip.ftd2xx.Service;

public class Driver {
  
  static private final int RX_BUFFER_SIZE = 1024 * 1024;
  static private final int TX_BUFFER_SIZE = 1024 * 1024;
  static private final int TIMEOUT = 500;
  static private final int PORT_BAUDRATE = 12000000;
  static private final int BITBANG_MASK = 0xff;
  static private final BitBangMode BITBANG_MODE = BitBangMode.ASYNCHRONOUS;
  
  private final Device device;
  
  public Driver(final Device device) {
    this.device = device;
  }
  
  public Driver(int deviceNumber) throws FTD2xxException {
    this.device = Service.listDevices()[deviceNumber];
  }
  
  public void open() throws FTD2xxException {
    device.open();
    device.setUSBParameters(RX_BUFFER_SIZE, TX_BUFFER_SIZE);
    reset();
  }
  
  public void close() throws FTD2xxException {
    device.close();
  }
  
  public void reset() throws FTD2xxException {
    device.reset();
    device.setTimeout(TIMEOUT);
    device.getPort().setBaudRate(PORT_BAUDRATE);
    device.setBitBangMode(BITBANG_MASK, BITBANG_MODE);
  }
  
  public PacketInputStream getStream() {
    return new InputStreamPacketInputStream(device.getInputStream());
  }
  
}
