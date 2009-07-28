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
  char getCounter();
  
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
  short getTemperature();
  
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
   * g = ( getAccelerometerXRaw() / 1860.0 - 1 ) * 5.0
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
   * g = ( getAccelerometerYRaw() / 1860.0 - 1 ) * 5.0
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
   * g = ( getAccelerometerZRaw() / 1860.0 - 1 ) * 5.0
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
  
  public interface Frame {
    
   /*
     * IRD synch signal active
     * 
     * @return signal active
     */
    boolean isIRDSynachronizationActive();
    
    /**
     * Audio active. 
     * 
     * @return audio active
     */
    boolean isAudioActive();
    
    /**
     * Audio frequency.
     * 
     * @return audio frequency
     */
    int getAudioFrequency();
    
    /**
     * Audio frequency: 48 KHz.
     */
    int AUDIO_FREQUENCY_48KHZ = 0; 
    
    /**
     * Audio frequency: 96 KHz.
     */
    int AUDIO_FREQUENCY_96KHZ = 1; 
    
    /**
     * Audio channels.
     */
    int AUDIO_CHANNNELS = 4;
    
    /**
     * Get audio sample.
     * 
     * channel >= 0, channel < AUDIO_CHANNELS 
     * 
     * @param channel audio channel
     * @return audio sample
     */
    char getAudio(int channel);
    
    /**
     * ADC active. 
     * 
     * @return ADC active
     */
    boolean isADCActive();
    
    /**
     * Get slow ADC channel.
     * 
     * Minimum: 0, Maximum: 8
     * 
     * @return ADC channel
     */
    int getADCChannel();
    
    /**
     * ADC channels.
     */
    int ADC_CHANNNELS = 8;
    
    /**
     * Get slow ADC sample.
     * 
     * @return ADC sample
     */
    char getADC();
    
    
    
  }
  
}
