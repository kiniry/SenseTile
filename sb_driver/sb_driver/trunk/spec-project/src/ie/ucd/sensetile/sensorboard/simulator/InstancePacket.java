package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.Frame;
import ie.ucd.sensetile.sensorboard.Packet;

/**
 * Packet from plain variables.
 * 
 * @author Vieri del Bianco (vieri.delbianco@gmail.com)
 *
 */
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
  
  private static final short PRESSURE_DEFAULT = 310;
  private static final short ACCELLEROMETER_DEFAULT = 1860;
  
  InstancePacket() {
    for (int frameIndex = 0; frameIndex < frames.length; frameIndex++) {
      frames[frameIndex] = new InstanceFrame();
      frames[frameIndex].setADCChannel(frameIndex % Frame.ADC_CHANNELS);
    }
    setTime(new TimeInstance());
    // defaults
    setPressure(PRESSURE_DEFAULT);
    setAccelerometerX(ACCELLEROMETER_DEFAULT);
    setAccelerometerY(ACCELLEROMETER_DEFAULT);
    setAccelerometerZ(ACCELLEROMETER_DEFAULT);
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
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getTime()
   */
  public Packet.Time getTime() {
    return time;
  }
  
  void setTime(final TimeInstance time) {
    this.time = time;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getCounter()
   */
  public char getCounter() {
    return counter;
  }
  
  void setCounter(final char counter) {
    this.counter = counter;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getTemperature()
   */
  public short getTemperature() {
    return temperature;
  }
  
  void setTemperature(final short temperature) {
    this.temperature = temperature;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getPressure()
   */
  public short getPressure() {
    return pressure;
  }
  
  void setPressure(final short pressure) {
    this.pressure = pressure;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getLightLevel()
   */
  public short getLightLevel() {
    return lightLevel;
  }
  
  void setLightLevel(final short lightLevel) {
    this.lightLevel = lightLevel;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getAccelerometerX()
   */
  public short getAccelerometerX() {
    return accelerometerX;
  }
  
  void setAccelerometerX(final short accelerometerX) {
    this.accelerometerX = accelerometerX;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getAccelerometerY()
   */
  public short getAccelerometerY() {
    return accelerometerY;
  }
  
  void setAccelerometerY(final short accelerometerY) {
    this.accelerometerY = accelerometerY;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getAccelerometerZ()
   */
  public short getAccelerometerZ() {
    return accelerometerZ;
  }
  
  void setAccelerometerZ(final short accelerometerZ) {
    this.accelerometerZ = accelerometerZ;
  }

  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getSupplyVoltage()
   */
  public int getSupplyVoltage() {
    return supplyVoltage;
  }
  
  void setSupplyVoltage(final int supplyVoltage) {
    this.supplyVoltage = supplyVoltage;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getSupplyCurrent()
   */
  public int getSupplyCurrent() {
    return supplyCurrent;
  }
  
  void setSupplyCurrent(final int supplyCurrent) {
    this.supplyCurrent = supplyCurrent;
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.Packet#getFrame(int)
   */
  public Frame getFrame(final int index) {
    return frames[index];
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#clone()
   */
  public Object clone() throws CloneNotSupportedException {
    return (InstancePacket) super.clone();
  }
  
  /**
   * Packet.Time from plain variables.
   * 
   * @author Vieri del Bianco (vieri.delbianco@gmail.com)
   */
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
    
    /* (non-Javadoc)
     * @see ie.ucd.sensetile.sensorboard.Packet.Time#getHours()
     */
    public byte getHours() {
      return hours;
    }
    
    void setHours(final int hours) {
      this.hours = (byte) hours;
    }
    
    /* (non-Javadoc)
     * @see ie.ucd.sensetile.sensorboard.Packet.Time#getMinutes()
     */
    public byte getMinutes() {
      return minutes;
    }
    
    void setMinutes(final int minutes) {
      this.minutes = (byte) minutes;
    }
    
    /* (non-Javadoc)
     * @see ie.ucd.sensetile.sensorboard.Packet.Time#getSeconds()
     */
    public byte getSeconds() {
      return seconds;
    }
    
    void setSeconds(final int seconds) {
      this.seconds = (byte) seconds;
    }
    
    /* (non-Javadoc)
     * @see ie.ucd.sensetile.sensorboard.Packet.Time#getCentiSeconds()
     */
    public byte getCentiSeconds() {
      return centiSeconds;
    }
    
    void setCentiSeconds(final int centiSeconds) {
      this.centiSeconds = (byte) centiSeconds;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
      return (TimeInstance) super.clone();
    }
    
  }
}
