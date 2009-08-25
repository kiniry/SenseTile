package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.Packet;

public class InstancePacket implements CloneablePacket {
  
  /*
   * counters
   */
  private TimeInstance time = new TimeInstance();
  private char counter;
  
  /*
   * sensors
   */
  private short temperature;
  private short pressure;
  private short lightLevel;
  private short accelerometerX;
  private short accelerometerY;
  private short accelerometerZ;
  
  /*
   * monitors
   */
  private int supplyCurrent;
  private int supplyVoltage;
  
  /*
   * frames
   */
  private FrameInstance[] frames = new FrameInstance[FRAMES];
  
  public InstancePacket() {
    setTime(new TimeInstance());
    for (int frameIndex = 0; frameIndex < frames.length; frameIndex++) {
      frames[frameIndex] = new FrameInstance();
    }
  }
  
  public InstancePacket(final Packet template) {
    setTime(new TimeInstance(template.getTime()));
    setCounter(template.getCounter());
    setCounter(template.getCounter());
    setPressure(template.getPressure());
    setLightLevel(template.getLightLevel());
    setAccelerometerX(template.getAccelerometerX());
    setAccelerometerY(template.getAccelerometerY());
    setAccelerometerZ(template.getAccelerometerZ());
    setSupplyVoltage(template.getSupplyVoltage());
    setSupplyCurrent(template.getSupplyCurrent());
    for (int frameIndex = 0; frameIndex < frames.length; frameIndex++) {
      frames[frameIndex] = new FrameInstance(template.getFrame(frameIndex));
    }
  }
  
  public Time getTime() {
    return time;
  }
  
  public void setTime(final TimeInstance time) {
    this.time = time;
  }
  
  public char getCounter() {
    return counter;
  }
  
  public void setCounter(final char counter) {
    this.counter = counter;
  }
  
  public short getTemperature() {
    return temperature;
  }
  
  public void setTemperature(final short temperature) {
    this.temperature = temperature;
  }
  
  public short getPressure() {
    return pressure;
  }
  
  public void setPressure(final short pressure) {
    this.pressure = pressure;
  }
  
  public short getLightLevel() {
    return lightLevel;
  }
  
  public void setLightLevel(final short lightLevel) {
    this.lightLevel = lightLevel;
  }
  
  public short getAccelerometerX() {
    return accelerometerX;
  }
  
  public void setAccelerometerX(final short accelerometerX) {
    this.accelerometerX = accelerometerX;
  }
  
  public short getAccelerometerY() {
    return accelerometerY;
  }
  
  public void setAccelerometerY(final short accelerometerY) {
    this.accelerometerY = accelerometerY;
  }
  
  public short getAccelerometerZ() {
    return accelerometerZ;
  }
  
  public void setAccelerometerZ(final short accelerometerZ) {
    this.accelerometerZ = accelerometerZ;
  }

  public int getSupplyVoltage() {
    return supplyVoltage;
  }
  
  public void setSupplyVoltage(final int supplyVoltage) {
    this.supplyVoltage = supplyVoltage;
  }
  
  public int getSupplyCurrent() {
    return supplyCurrent;
  }
  
  public void setSupplyCurrent(final int supplyCurrent) {
    this.supplyCurrent = supplyCurrent;
  }
  
  public Frame getFrame(final int index) {
    return frames[index];
  }
  
  public Object clone() throws CloneNotSupportedException {
    return (InstancePacket) super.clone();
  }
  
  public static class TimeInstance implements Time, Cloneable {
    
    private byte hours;
    private byte minutes;
    private byte seconds;
    private byte centiSeconds;
    
    public TimeInstance() {
    }
    
    public TimeInstance(final Time template) {
      setHours(template.getHours());
      setMinutes(template.getMinutes());
      setSeconds(template.getSeconds());
      setCentiSeconds(template.getCentiSeconds());
    }
    
    public byte getHours() {
      return hours;
    }
    
    public void setHours(final byte hours) {
      this.hours = hours;
    }
    
    public byte getMinutes() {
      return minutes;
    }
    
    public void setMinutes(final byte minutes) {
      this.minutes = minutes;
    }
    
    public byte getSeconds() {
      return seconds;
    }
    
    public void setSeconds(final byte seconds) {
      this.seconds = seconds;
    }
    
    public byte getCentiSeconds() {
      return centiSeconds;
    }
    
    public void setCentiSeconds(final byte centiSeconds) {
      this.centiSeconds = centiSeconds;
    }
    
    public Object clone() throws CloneNotSupportedException {
      return (TimeInstance) super.clone();
    }
    
  }
  
  public static class FrameInstance implements Frame, Cloneable {
    
    private boolean isIRDSynachronizationActive;
    private boolean isAudioActive;
    private int audioFrequency;
    private char[] audioSample = new char[AUDIO_CHANNNELS];
    private int adcChannel;
    private char adcSample;
    private boolean isADCActive;
    
    public FrameInstance() {
    }
    
    public FrameInstance(final Frame template) {
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
      return (FrameInstance) super.clone();
    }
  }
}
