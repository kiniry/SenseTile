/*
 * InstanceFrame.java
 *
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */
package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.Frame;

/**
 * Frame built from plain values.
 * 
 * @author Vieri del Bianco (vieri.delbianco@gmail.com)
 */
public class InstanceFrame implements Frame, Cloneable {
  
  private boolean isIRDSynachronizationActive;
  private boolean isAudioActive;
  private int audioFrequency;
  private char[] audioSample = new char[AUDIO_CHANNELS];
  private int adcChannel;
  private char adcSample;
  private boolean isADCActive;
  
  InstanceFrame() {
    setAudioActive(true);
    setADCActive(true);
  }
  
  InstanceFrame(final Frame template) {
    setIRDSynachronizationActive(template.isIRDSynachronizationActive());
    setAudioActive(template.isAudioActive());
    setAudioFrequency(template.getAudioFrequency());
    for (int audioIndex = 0; audioIndex < audioSample.length; audioIndex++) {
      setAudio(audioIndex, template.getAudio(audioIndex));
    }
    setADCActive(template.isADCActive());
    setADCChannel(template.getADCChannel());
    setADC(template.getADC());
  }

  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Frame#isIRDSynachronizationActive()
   */
  public boolean isIRDSynachronizationActive() {
    return isIRDSynachronizationActive;
  }
  
  void setIRDSynachronizationActive(final boolean value) {
    isIRDSynachronizationActive = value;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Frame#isAudioActive()
   */
  public boolean isAudioActive() {
    return isAudioActive;
  }
  
  void setAudioActive(final boolean value) {
    isAudioActive = value;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Frame#getAudioFrequency()
   */
  public int getAudioFrequency() {
    return audioFrequency;
  }
  
  void setAudioFrequency(final int value) {
    audioFrequency = value;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Frame#getAudio(int)
   */
  public char getAudio(final int channel) {
    return audioSample[channel];
  }
  
  void setAudio(final int channel, final char audioSample) {
    this.audioSample[channel] = audioSample;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Frame#isADCActive()
   */
  public boolean isADCActive() {
    return isADCActive;
  }
  
  void setADCActive(final boolean value) {
    isADCActive = value;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Frame#getADCChannel()
   */
  public int getADCChannel() {
    return adcChannel;
  }
  
  void setADCChannel(final int adcChannel) {
    this.adcChannel = adcChannel;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Frame#getADC()
   */
  public char getADC() {
    return adcSample;
  }
  
  void setADC(final char adcSample) {
    this.adcSample = adcSample;
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#clone()
   */
  public Object clone() throws CloneNotSupportedException {
    return (InstanceFrame) super.clone();
  }
}
