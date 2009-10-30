/*
Copyright 2009 (C) SenseTile, UCD. All rights reserved.
*/

package ie.ucd.sensetile.sensorboard;

/**
 * Sensor board output packet data.
 * 
 * @author delbianc
 *
 */
public interface Packet {
  
  /*
   * invariant: only one change in audio active
   */
  /*@
  invariant (
    (
      \num_of int i; 
      0<= i && i<(FRAMES-1); 
      getFrame(i).isAudioActive() != getFrame(i+1).isAudioActive()
    ) <= 1
  );
  @*/
  
  /*
   * invariant: only one change in frequency
   */
  /*@
  invariant ((
      \num_of int i; 
      0<= i && i<(FRAMES-1); 
      (
        getFrame(i).isAudioActive() && 
        getFrame(i+1).isAudioActive() && 
        (getFrame(i).getAudioFrequency() != getFrame(i+1).getAudioFrequency())
      )
    ) <= 1
  );
  @*/
  
  /*
   * invariant: only 9 ADC samples at most, for each channel
   */
  /*@
  invariant (
    \forall int channel;
    0 <= channel && channel < Frame.ADC_CHANNELS; 
    (
      \num_of int i;
      0<= i && i<(FRAMES-1);
      (
        getFrame(i).isADCActive() &&
        (getFrame(i).getADCChannel() == channel)
      )
    ) <= (FRAMES / Frame.ADC_CHANNELS + 1)
  );
  @*/
  
  /**
   * Get time.
   * 
   * @return time
   */
  /*@ pure non_null */ Time getTime();
  
  /**
   * Get packet counter.
   * Minimum: 0. 
   * Maximum: 65535. 
   * 
   * @return counter
   */
  /*@ 
    ensures \result >= 0;
    ensures \result < 65536;
  @*/
  /*@ pure */ char getCounter();
  
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
  /*@ 
    ensures \result >= -880;
    ensures \result <= 2047;
  @*/
  /*@ pure */ short getTemperature();
  
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
  /*@ 
    ensures \result >= 310;
    ensures \result <= 5585;
  @*/
  /*@ pure */ short getPressure();
  
  /**
   * Get light level.
   * 
   * 0 lux -> 0 , 1000 lux -> 1000
   * 
   * Minimum: 0 , Maxmimum: 1000
   * 
   * @return light level
   */
  /*@ 
    ensures \result >= 0;
    ensures \result <= 1000;
  @*/
  /*@ pure */ short getLightLevel();
  
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
  /*@
    ensures \result >= 1488;
    ensures \result <= 2232;
  @*/
  /*@ pure */ short getAccelerometerX();
  
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
  /*@
    ensures \result >= 1488;
    ensures \result <= 2232;
  @*/
  /*@ pure */ short getAccelerometerY();
  
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
  /*@
    ensures \result >= 1488;
    ensures \result <= 2232;
  @*/
  /*@ pure */ short getAccelerometerZ();
  
  int getSupplyVoltage();
  
  int getSupplyCurrent();
  
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
  /*@
    @ requires index >= 0;
    @ requires index < FRAMES;
    @*/
  /*@ pure non_null */ Frame getFrame(int index);
  
  /**
   * Time interface.
   * 
   * @author delbianc
   */
  public interface Time {
    
    /**
     * Get hours.
     * 
     * Minimum: 0 , Maxmimum: 23
     * 
     * @return hours
     */
    /*@
      @ ensures \result >= 0;
      @ ensures \result < 24;
    @*/
    /*@ pure */ byte getHours();
    
    /**
     * Get minutes.
     * 
     * Minimum: 0 , Maxmimum: 59
     * 
     * @return minutes
     */
    /*@
      @ ensures \result >= 0;
      @ ensures \result < 60;
    @*/
    /*@ pure */ byte getMinutes();
    
    /**
     * Get seconds.
     * 
     * Minimum: 0 , Maxmimum: 59
     * 
     * @return seconds
     */
    /*@
      @ ensures \result >= 0;
      @ ensures \result < 60;
    @*/
    /*@ pure */ byte getSeconds();
    
    /**
     * Get centiseconds.
     * 
     * Minimum: 0 , Maxmimum: 99
     * 
     * @return centiseconds
     */
    /*@
      @ ensures \result >= 0;
      @ ensures \result < 100;
    @*/
    /*@ pure */ byte getCentiSeconds();
  }
  
}
