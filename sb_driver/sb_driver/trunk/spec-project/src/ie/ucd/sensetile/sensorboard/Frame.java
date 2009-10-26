/*
 * Frame.java
 *
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */

package ie.ucd.sensetile.sensorboard;

/**
 * Sensor board internal grìframe of output packet data.
 * 
 * @author delbianc
 *
 */
public interface Frame {
  
  /**
   * IRD synch signal active
   * 
   * @return signal active
   */
  /*@ pure */ boolean isIRDSynachronizationActive();
  
  /**
   * Audio active. 
   * 
   * @return audio active
   */
  /*@ pure */ boolean isAudioActive();
  
  /**
   * Audio frequency: 48 KHz.
   */
  int AUDIO_FREQUENCY_48KHZ = 0; 
  
  /**
   * Audio frequency: 96 KHz.
   */
  int AUDIO_FREQUENCY_96KHZ = 1; 
  
  /**
   * Audio frequency.
   * 
   * @return audio frequency
   */
  /*@
    ensures 
      (\result == AUDIO_FREQUENCY_48KHZ) || 
      (\result == AUDIO_FREQUENCY_96KHZ);
  @*/
  /*@ pure */ int getAudioFrequency();
  
  /**
   * Audio channels.
   */
  int AUDIO_CHANNELS = 4;
  
  /**
   * Get audio sample.
   * 
   * channel >= 0, channel < AUDIO_CHANNELS 
   * 
   * @param channel audio channel
   * @return audio sample
   */
  /*@
    requires channel >= 0;
    requires channel < AUDIO_CHANNELS;
  @*/
  /*@ pure */ char getAudio(int channel);
  
  /**
   * ADC active. 
   * 
   * @return ADC active
   */
  /*@ pure */ boolean isADCActive();
  
  /**
   * ADC channels.
   */
  int ADC_CHANNELS = 8;
  
  /**
   * Get slow ADC channel.
   * 
   * Minimum: 0, Maximum: 8
   * 
   * @return ADC channel
   */
  /*@
    ensures \result >= 0;
    ensures \result < ADC_CHANNELS;
  @*/
  /*@ pure */ int getADCChannel();
  
  /**
   * Get slow ADC sample.
   * 
   * @return ADC sample
   */
  /*@ pure */ char getADC();
  
}