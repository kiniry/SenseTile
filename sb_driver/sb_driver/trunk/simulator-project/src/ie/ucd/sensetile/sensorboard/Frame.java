package ie.ucd.sensetile.sensorboard;

/**
 * Sensor board internal grìframe of output packet data.
 * @title         "Frame"
 * @date          "2009/10/10"
 * @author        "delbianc & Dragan"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
//@pure
public interface Frame {  
	
	//@ public model instance non_null char[] mod_audioSample;
	
	/** Audio frequency: 48 KHz. */
	//@ spec_public  
	static final  int  AUDIO_FREQUENCY_48KHZ = 0; 
	
	/** Audio frequency: 96 KHz. */
	//@ spec_public
	static final  int AUDIO_FREQUENCY_96KHZ = 1; 
	
	/** Audio channels.*/
	//@ spec_public
	static final  int AUDIO_CHANNELS = 4;
	
	 /** ADC channels.*/
	//@ spec_public
	static final  int  ADC_CHANNELS = 8;
	
    /**
	  * IRD synch signal active
	  * @return signal active
	  */
	 boolean isIRDSynachronizationActive();
	/**
	  * Audio active. 
	  * @return audio active
	 */
	 boolean isAudioActive();
	    
	 /**
	   * Audio frequency.
	   * @return audio frequency
	   */
	 /*@
	   @ ensures 
	   @   (\result == AUDIO_FREQUENCY_48KHZ) || 
	   @   (\result == AUDIO_FREQUENCY_96KHZ);
	   @*/
	  int getAudioFrequency();
	  
	  /**
	   * Get audio sample.
	   * channel >= 0, channel < AUDIO_CHANNELS 
	   * @param channel audio channel
	   * @return audio sample
	   */
	  /*@ requires channel >= 0 && channel  < mod_audioSample.length;
        @ ensures \typeof(\result) <: \type(char);
        @*/
	  char getAudio(final int channel);
	  
	  /**
	   * ADC active.  
	   * @return ADC active
	   */
	  
	  boolean isADCActive();
	  
	  /**
	   * Get slow ADC channel.
	   * Minimum: 0, Maximum: 8
	   * @return ADC channel
	   */
	  /*@ ensures \result >= 0;
	    @ ensures \result < ADC_CHANNELS;
	    @*/
	  int getADCChannel();
	  
	  /**
	   * Get slow ADC sample.
	   * @return ADC sample
	   */
	  //@ ensures \typeof(\result) <: \type(char);
	  char getADC();
	  
}
