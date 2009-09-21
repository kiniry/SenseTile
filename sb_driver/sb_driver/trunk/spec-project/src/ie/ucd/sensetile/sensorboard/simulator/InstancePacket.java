package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.Frame;
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
  private InstanceFrame[] frames = new InstanceFrame[FRAMES];
  
  InstancePacket() {
    for (int frameIndex = 0; frameIndex < frames.length; frameIndex++) {
      frames[frameIndex] = new InstanceFrame();
      frames[frameIndex].setADCChannel(frameIndex % Frame.ADC_CHANNELS);
    }
    setTime(new TimeInstance());
    // defaults
    setPressure((short)310);
    setAccelerometerX((short)1860);
    setAccelerometerY((short)1860);
    setAccelerometerZ((short)1860);
  }
  
  InstancePacket(final Packet template) {
    for (int frameIndex = 0; frameIndex < frames.length; frameIndex++) {
      frames[frameIndex] = new InstanceFrame(template.getFrame(frameIndex));
    }
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
  }
  
  public Packet.Time getTime() {
    return time;
  }
  
  void setTime(final TimeInstance time) {
    this.time = time;
  }
  
  public char getCounter() {
    return counter;
  }
  
  void setCounter(final char counter) {
    this.counter = counter;
  }
  
  public short getTemperature() {
    return temperature;
  }
  
  void setTemperature(final short temperature) {
    this.temperature = temperature;
  }
  
  public short getPressure() {
    return pressure;
  }
  
  void setPressure(final short pressure) {
    this.pressure = pressure;
  }
  
  public short getLightLevel() {
    return lightLevel;
  }
  
  void setLightLevel(final short lightLevel) {
    this.lightLevel = lightLevel;
  }
  
  public short getAccelerometerX() {
    return accelerometerX;
  }
  
  void setAccelerometerX(final short accelerometerX) {
    this.accelerometerX = accelerometerX;
  }
  
  public short getAccelerometerY() {
    return accelerometerY;
  }
  
  void setAccelerometerY(final short accelerometerY) {
    this.accelerometerY = accelerometerY;
  }
  
  public short getAccelerometerZ() {
    return accelerometerZ;
  }
  
  void setAccelerometerZ(final short accelerometerZ) {
    this.accelerometerZ = accelerometerZ;
  }

  public int getSupplyVoltage() {
    return supplyVoltage;
  }
  
  void setSupplyVoltage(final int supplyVoltage) {
    this.supplyVoltage = supplyVoltage;
  }
  
  public int getSupplyCurrent() {
    return supplyCurrent;
  }
  
  void setSupplyCurrent(final int supplyCurrent) {
    this.supplyCurrent = supplyCurrent;
  }
  
  public Frame getFrame(final int index) {
    return frames[index];
  }
  
  public Object clone() throws CloneNotSupportedException {
    return (InstancePacket) super.clone();
  }
  
  public static class TimeInstance implements Packet.Time, Cloneable {
    
    private byte hours;
    private byte minutes;
    private byte seconds;
    private byte centiSeconds;
    
    TimeInstance() {
      setHours(0);
      setMinutes(0);
      setSeconds(0);
      setCentiSeconds(0);
    }
    
    TimeInstance(final Packet.Time template) {
      setHours(template.getHours());
      setMinutes(template.getMinutes());
      setSeconds(template.getSeconds());
      setCentiSeconds(template.getCentiSeconds());
    }
    
    public byte getHours() {
      return hours;
    }
    
    void setHours(final int hours) {
      this.hours = (byte) hours;
    }
    
    public byte getMinutes() {
      return minutes;
    }
    
    void setMinutes(final int minutes) {
      this.minutes = (byte) minutes;
    }
    
    public byte getSeconds() {
      return seconds;
    }
    
    void setSeconds(final int seconds) {
      this.seconds = (byte) seconds;
    }
    
    public byte getCentiSeconds() {
      return centiSeconds;
    }
    
    void setCentiSeconds(final int centiSeconds) {
      this.centiSeconds = (byte) centiSeconds;
    }
    
    public Object clone() throws CloneNotSupportedException {
      return (TimeInstance) super.clone();
    }
    
  }
}
