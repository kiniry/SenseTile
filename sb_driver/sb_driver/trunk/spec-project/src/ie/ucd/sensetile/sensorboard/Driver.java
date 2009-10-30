/*
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */

package ie.ucd.sensetile.sensorboard;

import ie.ucd.sensetile.sensorboard.driver.InputStreamPacketInputStream;

//import com.ftdichip.ftd2xx.BitBangMode;
import com.ftdichip.ftd2xx.Device;
import com.ftdichip.ftd2xx.FTD2xxException;
import com.ftdichip.ftd2xx.Service;

/**
 * Sensor Board driver.
 * 
 * @author Vieri del Bianco (vieri.delbianco@gmail.com)
 *
 */
public final class Driver {
  
  private static final int RX_BUFFER_SIZE = 1024 * 1024;
  private static final int TX_BUFFER_SIZE = 1024 * 1024;
  private static final int TIMEOUT = 500;
  private static final int PORT_BAUDRATE = 12000000;
  //private static final int BITBANG_MASK = 0xff;
  //private static final BitBangMode BITBANG_MODE = BitBangMode.ASYNCHRONOUS;
  
  private final Device device;
  
  /**
   * Driver built from real device.
   * 
   * @param device real device
   */
  public Driver(final Device device) {
    this.device = device;
  }
  
  /**
   * Driver built from real device.
   * 
   * @param deviceNumber device number
   * @throws FTD2xxException device do not exist
   */
  public Driver(final int deviceNumber) throws FTD2xxException {
    this.device = Service.listDevices()[deviceNumber];
  }
  
  /**
   * Open communication channels.
   * 
   * @throws FTD2xxException communication exception
   */
  public void open() throws FTD2xxException {
    device.open();
    device.setUSBParameters(RX_BUFFER_SIZE, TX_BUFFER_SIZE);
    resetToDefault();
  }
  
  /**
   * Close communication channels.
   * 
   * @throws FTD2xxException communication exception
   */
  public void close() throws FTD2xxException {
    device.close();
  }
  
  /**
   * Reset.
   * 
   * @throws FTD2xxException communication exception
   */  public void resetToDefault() throws FTD2xxException {
    device.reset();
    device.setTimeout(TIMEOUT);
    device.getPort().setBaudRate(PORT_BAUDRATE);
    /*
     *  TODO: discover the correct BigBangMode to set, the following one is 
     * breaking the communication
     */  
    //device.setBitBangMode(BITBANG_MASK, BITBANG_MODE);
  }
  
  /**
   * Get driver packet input stream.
   * 
   * @return packet input stream
   */
  public PacketInputStream getStream() {
    return InputStreamPacketInputStream.createInputStreamPacketInputStream(
        device.getInputStream());
  }
  
}
