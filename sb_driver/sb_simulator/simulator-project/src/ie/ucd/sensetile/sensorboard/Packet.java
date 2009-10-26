package ie.ucd.sensetile.sensorboard;


/**
 * Represents output packet data.
 * @title         "Packet"
 * @date          "2009/10/13"
 * @author        "delbianc & Dragan"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
//@pure
public interface Packet {
	  /** Frame number in packet.*/

	//@spec_public
	static final int  FRAMES = 82;
	  
	/** Get time.
     * @return time
     */
	/*@ non_null */ Time getTime();
  /**
   * Get packet counter.
   * Minimum: 0. 
   * Maximum: 65535. 
   * @return counter
   */
  /*@ ensures \result >= 0;
    @ ensures \result < 65536;
    @*/
   char getCounter();
  
  /**
   * Get temperature.
   * -55 C -> -880 (-55 * 16), 0 C -> 0, +127 C -> 2032 (127 * 16)
   * Minimum: -880 , Maxmimum: 2047
   * C = getTemperature() / 16.0
   * @return temperature
   */
  /*@ ensures \result >= -880;
    @ ensures \result <= 2047;
    @*/
   short getTemperature();
  
  /**
   * Get pressure.
   * 15 Kpa -> 310 , 115 Kpa -> 5585
   * Minimum: 310 , Maxmimum: 5585
   * Kpa = ( getPressure() * 4.0 + 1925.0 ) / 211.0; 
   * @return pressure
   */
  /*@ ensures \result >= 310;
    @ ensures \result <= 5585;
    @*/
    short getPressure();
  
  /**
   * Get light level.
   * 0 lux -> 0 , 1000 lux -> 1000
   * Minimum: 0 , Maxmimum: 1000
   * @return light level
   */
  /*@ ensures \result >= 0;
    @ ensures \result <= 1000;
    @*/
   short getLightLevel();
  
  /**
   * Get acceleration on X axis.
   * 0 g -> 1860 , 1 g -> 2232 , -1 g -> 1488
   * Minimum: 1488 , Maximum: 2232
   * g = ( getAccelerometerX() / 1860.0 - 1 ) * 5.0
   * @return acceleration
   */
  //@ ensures \result >= 1488 &&  \result <= 2232;
    short getAccelerometerX();
  
  /**
   * Get acceleration on Y axis.
   * 0 g -> 1860 , 1 g -> 2232 , -1 g -> 1488
   * Minimum: 1488 , Maximum: 2232
   * g = ( getAccelerometerY() / 1860.0 - 1 ) * 5.0
   * @return acceleration
   */
  //@ ensures \result >= 1488 &&  \result <= 2232;
   short getAccelerometerY();
  
  /**
   * Get acceleration on Z axis.
   * 0 g -> 1860 , 1 g -> 2232 , -1 g -> 1488
   * Minimum: 1488 , Maximum: 2232
   * g = ( getAccelerometerZ() / 1860.0 - 1 ) * 5.0
   * @return acceleration
   */
  //@ ensures \result >= 1488 &&  \result <= 2232;
   short getAccelerometerZ();
  
  int getSupplyVoltage();
  
  int getSupplyCurrent();
    
  /**
   * Get frame.
   * index >= 0, index < FRAME_NUMBER
   * @param index frame index
   * @return frame
   */
  //@ requires index >=0 && index < FRAMES;
  Frame getFrame(int index);
  
  /**
   * Reoresents an time interface.
   * @title         "Time"
   * @date          "2009/10/10"
   * @author        "Vieri & Dragan"
   * @organisation  "School of Computer Science and Informatics, UCD"
   * @copyright     "Copyright (C) 2009 UCD"
   * @version       "$ Revision: 1.00 $"
   *
   */
  //@pure
   public  interface Time {
  	//@ public model instance short mod_hours;
      //@ public model instance short mod_minutes;
  	//@ public model instance short mod_seconds;
  	//@ public model instance short mod_centiSeconds;
  	 
     /*@ invariant (mod_hours >= 0 && mod_hours < 24) &&
       @			   (mod_minutes >= 0 && mod_minutes <= 59) &&
       @             (mod_seconds >= 0 && mod_seconds <= 59) &&
       @             (mod_centiSeconds >= 0 && mod_centiSeconds <= 99);
       @*/
  	 
      /**
       * Get hours.
       * Minimum: 0 , Maxmimum: 23
       * @return hours
       */
      /*@ ensures \result >= 0;
        @ ensures \result < 24;
        @*/
       byte getHours();
      
      /**
       * Get minutes.
       * Minimum: 0 , Maxmimum: 59
       * @return minutes
       */
      /*@ ensures \result >= 0;
        @ ensures \result <= 59;
        @*/
       byte getMinutes();
      
      /**
       * Get seconds. 
       * Minimum: 0 , Maxmimum: 59
       * @return seconds
       */
      /*@ ensures \result >= 0;
        @ ensures \result <= 59;
        @*/
       byte getSeconds();
      
      /**
       * Get centiseconds.
       * Minimum: 0 , Maxmimum: 99
       * @return centiseconds
       */
      /*@ ensures \result >= 0;
        @ ensures \result <= 99;
        @*/
      byte getCentiSeconds();
    }

}
