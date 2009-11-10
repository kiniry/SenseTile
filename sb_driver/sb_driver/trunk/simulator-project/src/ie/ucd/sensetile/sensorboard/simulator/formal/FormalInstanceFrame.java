package ie.ucd.sensetile.sensorboard.simulator.formal;

import ie.ucd.sensetile.sensorboard.Frame;

/**
 * Represents an instance of Frame interface.
 * @title         "FormalInstanceFrame"
 * @date          "2009/10/10"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */

public class FormalInstanceFrame implements Frame, Cloneable {	
//@ public model instance non_null char[] mod_audioSample;
	
  //@ spec_public
  private transient boolean isIRDSynachronizationActive;
  //@ spec_public
  private transient boolean isAudioActive;
  //@ spec_public
  private transient int audioFrequency;
  //@ spec_public 
  private  final char[] audioSample = new char[AUDIO_CHANNELS];//@ in mod_audioSample;
//@ represents mod_audioSample <-audioSample;
  //@ spec_public
  private transient int adcChannel;
  //@ spec_public
  private transient char adcSample;
  //@ spec_public
  private transient boolean isADCActive;
  
  /*@ invariant
    @   (audioFrequency == Frame.AUDIO_FREQUENCY_48KHZ || 
	@    audioFrequency == Frame.AUDIO_FREQUENCY_96KHZ) && 
    @   (adcChannel >= 0 &&  adcChannel < ADC_CHANNELS);
    @*/
     
   //@ invariant audioSample != null;
    
  /*@assignable isAudioActive,isADCActive;
    @ ensures isADCActive == true;
    @ ensures isAudioActive == true;
    @*/
  public FormalInstanceFrame() 
  {
    setAudioActive(true);
    setADCActive(true);
  }
  

  public boolean isIRDSynachronizationActive() {
    return isIRDSynachronizationActive;
  }
  
  /*@ assignable isIRDSynachronizationActive; 
    @ ensures isIRDSynachronizationActive == value;
    @*/
  void setIRDSynachronizationActive(final boolean value) {
    isIRDSynachronizationActive = value;
  }
  
  public boolean isAudioActive() {
    return isAudioActive;
  }
  
 /*@ assignable isAudioActive; 
   @ ensures isAudioActive == value;
   @*/
  void setAudioActive(final boolean value) {
    isAudioActive = value;
  }
  
  public int getAudioFrequency() {
    return audioFrequency;
  }
 /*@ requires (value == AUDIO_FREQUENCY_48KHZ || 
   @           value == AUDIO_FREQUENCY_96KHZ);
   @ assignable audioFrequency; 
   @ ensures audioFrequency == value;
   @*/
  void setAudioFrequency(final int value) {
    audioFrequency = value;
  }
  /*
   * (non-Javadoc)
   * @see packetbuilder.Frame#getAudio(int)
   */
  public char getAudio(final int channel) 
  {
    return audioSample[channel];
  }
  
 /*@ requires channel >=0 && channel < mod_audioSample.length;
   @ assignable mod_audioSample[*];
   @ ensures mod_audioSample[channel] == theAudioSample;
   @*/
  public void setAudio(final int channel, final char theAudioSample) {
    this.audioSample[channel] = theAudioSample;
  }
  
  /*
   * (non-Javadoc)
   * @see packetbuilder.Frame#isADCActive()
   */
  public boolean isADCActive() {
    return isADCActive;
  }
 
 /*@ assignable isADCActive; 
   @ ensures isADCActive == value;
   @*/
  void setADCActive(final boolean value) {
    isADCActive = value;
  }
  /*
   * (non-Javadoc)
   * @see packetbuilder.Frame#getADCChannel()
   */
  public int getADCChannel() {
    return adcChannel;
  }
 /*@ requires theAdcChannel >= 0; 
   @ requires theAdcChannel < Frame.ADC_CHANNELS;
   @ assignable adcChannel; 
   @ ensures adcChannel == theAdcChannel;
   @*/
  void setADCChannel(final int theAdcChannel) {
    this.adcChannel = theAdcChannel;
  }
  /*
   * (non-Javadoc)
   * @see packetbuilder.Frame#getADC()
   */
  public char getADC() {
    return adcSample;
  }
/*@ assignable adcSample; 
  @ ensures adcSample == theAdcSample;
  @*/
  void setADC(final char theAdcSample) {
    this.adcSample = theAdcSample;
  }
  /*
   * (non-Javadoc)
   * @see java.lang.Object#clone()
   */

//@public ghost Object g_frame;	
	
	/*@ also
	  @ public behavior
	  @ assignable \everything;
	  @   ensures \result == g_frame;
	  @   signals_only CloneNotSupportedException;
	  @*/
  public Object clone() throws CloneNotSupportedException 
  {
		Object frame = super.clone();
		//@ set g_frame = frame;
		return frame ;    
  }
}
