package ie.ucd.sensetile.sensorboard;

/**
 * Sensor board output packet data.
 * 
 * @author delbianc
 *
 */
public interface Packet {
  
  // indexes
  
  /**
   * Get time.
   * 
   * @return time
   */
  Time getTime();
  
  
  /**
   * Get packet counter.
   * 
   * Maximum: 65535
   * 
   * @return counter
   */
  char getCounter();
  
  // sensors
  
  /**
   * Get temperature.
   * 
   * -55 C -> -880 (-55 * 16), 0 C -> 0, +127 C -> 2032 (127 * 16)
   * 
   * Minimum: -880 , Maxmimum: 2047
   * 
   * C = getTemperature() / 16.0
   * 
   * @return temperature
   */
  short getTemperature();
  
  /**
   * Get pressure.
   * 
   * 15 Kpa -> 310 , 115 Kpa -> 5585
   * 
   * Minimum: 310 , Maxmimum: 5585
   * 
   * Kpa = ( getPressure() * 4.0 + 1925.0 ) / 211.0; 
   * 
   * @return pressure
   */
  short getPressure();
  
  /**
   * Get light level.
   * 
   * 0 lux -> 0 , 1000 lux -> 1000
   * 
   * Minimum: 0 , Maxmimum: 1000
   * 
   * @return light level
   */
  short getLightLevel();
  
  /**
   * Get acceleration on X axis.
   * 
   * 0 g -> 1860 , 1 g -> 2232 , -1 g -> 1488
   * 
   * Minimum: 1488 , Maximum: 2232
   * 
   * g = ( getAccelerometerX() / 1860.0 - 1 ) * 5.0
   * 
   * @return acceleration
   */
  short getAccelerometerX();
  
  /**
   * Get acceleration on Y axis.
   * 
   * 0 g -> 1860 , 1 g -> 2232 , -1 g -> 1488
   * 
   * Minimum: 1488 , Maximum: 2232
   * 
   * g = ( getAccelerometerY() / 1860.0 - 1 ) * 5.0
   * 
   * @return acceleration
   */
  short getAccelerometerY();
  
  /**
   * Get acceleration on Z axis.
   * 
   * 0 g -> 1860 , 1 g -> 2232 , -1 g -> 1488
   * 
   * Minimum: 1488 , Maximum: 2232
   * 
   * g = ( getAccelerometerZ() / 1860.0 - 1 ) * 5.0
   * 
   * @return acceleration
   */
  short getAccelerometerZ();
  
  // supply
  
  int getSupplyVoltage();
  
  int getSupplyCurrent();
  
  // Frames
  
  /**
   * Frame number in packet.
   */
  int FRAMES = 82;
  
  /**
   * Get frame.
   * 
   * index >= 0, index < FRAME_NUMBER
   * 
   * @param index frame index
   * @return frame
   */
  Frame getFrame(int index);
  
  public interface Time {
    
    /**
     * Get hours.
     * 
     * Minimum: 0 , Maxmimum: 23
     * 
     * @return hours
     */
    byte getHours();
    
    /**
     * Get minutes.
     * 
     * Minimum: 0 , Maxmimum: 59
     * 
     * @return minutes
     */
    byte getMinutes();
    
    /**
     * Get seconds.
     * 
     * Minimum: 0 , Maxmimum: 59
     * 
     * @return seconds
     */
    byte getSeconds();
    
    /**
     * Get centiseconds.
     * 
     * Minimum: 0 , Maxmimum: 99
     * 
     * @return centiseconds
     */
    byte getCentiSeconds();
  }
  
}
