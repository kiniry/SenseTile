package ie.ucd.sensetile.sensorboard;

/**
 * Sensor board output packet data.
 * 
 * @author delbianc
 *
 */
public interface SensorBoardPacket {
  
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
  int getCounter();
  
  // sensors
  
  /**
   * Get temperature.
   * 
   * -55 C -> -880 (-55 * 16), 0 C -> 0, +127 C -> 2032 (127 * 16)
   * 
   * Minimum: -880 , Maxmimum: 2047
   * 
   * C = getTemperatureRaw() / 16.0
   * 
   * @return temperature
   */
  int getTemperatureRaw();
  
  /**
   * Get pressure.
   * 
   * 15 Kpa -> 310 , 115 Kpa -> 5585
   * 
   * Minimum: 310 , Maxmimum: 5585
   * 
   * Kpa = ( getPressureRaw() * 4.0 + 1925.0 ) / 211.0; 
   * 
   * @return pressure
   */
  int getPressureRaw();
  
  /**
   * Get light level.
   * 
   * 0 lux -> 0 , 1000 lux -> 1000
   * 
   * Minimum: 0 , Maxmimum: 1000
   * 
   * @return light level
   */
  int getLighLevelRaw();
  
  /**
   * Get acceleration on X axis.
   * 
   * 0 g -> 1860 , 1 g -> 2232 , -1 g -> 1488
   * 
   * Minimum: 1488 , Maximum: 2232
   * 
   * g = ( getAccelerometerXRaw() / 1860.0 - 1 ) * 5.0
   * 
   * @return acceleration
   */
  int getAccelerometerXRaw();
  
  /**
   * Get acceleration on Y axis.
   * 
   * 0 g -> 1860 , 1 g -> 2232 , -1 g -> 1488
   * 
   * Minimum: 1488 , Maximum: 2232
   * 
   * g = ( getAccelerometerYRaw() / 1860.0 - 1 ) * 5.0
   * 
   * @return acceleration
   */
  int getAccelerometerYRaw();
  
  /**
   * Get acceleration on Z axis.
   * 
   * 0 g -> 1860 , 1 g -> 2232 , -1 g -> 1488
   * 
   * Minimum: 1488 , Maximum: 2232
   * 
   * g = ( getAccelerometerZRaw() / 1860.0 - 1 ) * 5.0
   * 
   * @return acceleration
   */
  int getAccelerometerZRaw();
  
  // supply
  
  int getSupplyVoltageRaw();
  
  int getSupplyCurrentRaw();
  
  // ADC channels fragments
  
  int[] getFastADC(int channel);
  
  int[] getSlowADC(int channel);
  
  public interface Time {
    
    /**
     * Get hours.
     * 
     * Minimum: 0 , Maxmimum: 23
     * 
     * @return hours
     */
    int getHours();
    
    /**
     * Get minutes.
     * 
     * Minimum: 0 , Maxmimum: 59
     * 
     * @return minutes
     */
    int getMinutes();
    
    /**
     * Get seconds.
     * 
     * Minimum: 0 , Maxmimum: 59
     * 
     * @return seconds
     */
    int getSeconds();
    
    /**
     * Get centiseconds.
     * 
     * Minimum: 0 , Maxmimum: 99
     * 
     * @return centiseconds
     */
    int getCentiSeconds();
    
  }
  
}
