/**
 * 
 */
package ie.ucd.sensetile.sensorboard;

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