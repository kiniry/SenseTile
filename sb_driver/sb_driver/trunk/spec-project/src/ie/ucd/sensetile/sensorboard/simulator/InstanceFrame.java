/**
 * 
 */
package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.Frame;

public class InstanceFrame implements Frame, Cloneable {
  
  private boolean isIRDSynachronizationActive;
  private boolean isAudioActive;
  private int audioFrequency;
  //TODO solve JML2 problem with constants
  //private char[] audioSample = new char[AUDIO_CHANNNELS];
  private char[] audioSample = new char[4];
  private int adcChannel;
  private char adcSample;
  private boolean isADCActive;
  
  public InstanceFrame() {
  }
  
  public InstanceFrame(final Frame template) {
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

  public boolean isIRDSynachronizationActive() {
    return isIRDSynachronizationActive;
  }
  
  public void setIRDSynachronizationActive(final boolean value) {
    isIRDSynachronizationActive = value;
  }
  
  public boolean isAudioActive() {
    return isAudioActive;
  }
  
  public void setAudioActive(final boolean value) {
    isAudioActive = value;
  }
  
  public int getAudioFrequency() {
    return audioFrequency;
  }
  
  public void setAudioFrequency(final int value) {
    audioFrequency = value;
  }
  
  public char getAudio(final int channel) {
    return audioSample[channel];
  }
  
  public void setAudio(final int channel, final char audioSample) {
    this.audioSample[channel] = audioSample;
  }
  
  public boolean isADCActive() {
    return isADCActive;
  }
  
  public void setADCActive(final boolean value) {
    isADCActive = value;
  }
  
  public int getADCChannel() {
    return adcChannel;
  }
  
  public void setADCChannel(final int adcChannel) {
    this.adcChannel = adcChannel;
  }
  
  public char getADC() {
    return adcSample;
  }
  
  public void setADC(final char adcSample) {
    this.adcSample = adcSample;
  }
  
  public Object clone() throws CloneNotSupportedException {
    return (InstanceFrame) super.clone();
  }
}