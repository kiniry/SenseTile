package ie.ucd.sensetile.sensorboard.simulator.formal;

import ie.ucd.sensetile.sensorboard.Frame;
import ie.ucd.sensetile.sensorboard.simulator.CloneablePacket;

/**
 * An packet instance derived from ClonablePacket interface.
 * @title         "FormalInstancePacket"
 * @date          "2009/10/12"
 * @author        "delbianc & Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class FormalInstancePacket implements CloneablePacket {
	
	//@ public model instance non_null FormalInstanceFrame[] mod_frames;  
	
	  //@ spec_public non_null
	  private TimeInstance time;
 	  //@ spec_public
	  private transient char counter;  
	  //@ spec_public
	  private transient short temperature;
	  //@ spec_public
	  private transient short pressure;
	  //@ spec_public
	  private transient short lightLevel;
	  //@ spec_public
	  private transient short accelerometerX;
	  //@ spec_public
	  private transient short accelerometerY;
	  //@ spec_public
	  private transient short accelerometerZ;
	  //@ spec_public
	  private transient int supplyCurrent;
	  //@ spec_public
	  private transient int supplyVoltage;
	  //@ spec_public non_null
	  private FormalInstanceFrame[] frames = new FormalInstanceFrame[FRAMES];//@ in mod_frames;
		//@ represents mod_frames <-frames;
	  
        
	  //@ invariant mod_frames.length == FRAMES;
	   
	  // to be discussed !!!! @ invariant \nonnullelements(mod_frames);
	     
	  //@ invariant  temperature >= -880 && temperature <= 2047;
	     
	  //@ invariant pressure >= 310 && pressure <=5585;
	     
	  //@ invariant lightLevel >=0 && lightLevel <=1000;
	     
	  //@ invariant accelerometerX >=1488 && accelerometerX <= 2232;
	     
	  //@ invariant accelerometerY >=1488 && accelerometerY <= 2232;
	     
	  //@ invariant accelerometerZ >=1488 && accelerometerZ <= 2232;
	     
	   
	  /*@ assignable accelerometerX,accelerometerY,accelerometerZ;
	    @ assignable time,mod_frames[*],pressure;
	    @ ensures  pressure == (short)310;
	    @ ensures accelerometerX == (short)1860;
	    @ ensures accelerometerY == (short)1860;
	    @ ensures accelerometerZ == (short)1860;
	    @ ensures time instanceof TimeInstance;
	    @ ensures  \nonnullelements(mod_frames);
	    @*/
	  public FormalInstancePacket() 
	  {  
		/*@ loop_invariant
		  @ 0 <= frameIndex  && frameIndex <= frames.length &&
	      @ (\forall int j; 0 <= j && j < frameIndex; frames[j] instanceof FormalInstanceFrame);
	      @ decreases frames.length - frameIndex;
	      @*/
	      for(int frameIndex = 0;frameIndex < frames.length; frameIndex ++  ) 
	      {
	    	  frames[frameIndex] = new FormalInstanceFrame();
	    	  frames[frameIndex].setADCChannel(frameIndex % Frame.ADC_CHANNELS);
	      }
	      time = new TimeInstance();
	      pressure = (short)310;
	      accelerometerX = (short)1860;
	      accelerometerY = (short)1860;
	      accelerometerZ = (short)1860;
	  }
	  
	  public/*@non_null@*/ Time getTime() {
	    return time;
	  }
	  /*@ assignable time; 
	    @ ensures time == theTime;
	    @*/
	  void setTime(final /*@non_null*/ TimeInstance theTime) {
	    this.time = theTime;
	  }
	  /*
	   * (non-Javadoc)
	   * @see packet.Packet#getCounter()
	   */
	  public char getCounter() {
	    return counter;
	  }
	  /*@ assignable counter; 
	    @ ensures counter == theCounter;
	    @*/
	  void setCounter(final char theCounter) {
	    this.counter = theCounter;
	  }
	  /*
	   * (non-Javadoc)
	   * @see packet.Packet#getTemperature()
	   */
	  public short getTemperature() {
	    return temperature;
	  }
	  
	  /*@ requires theTemperature >= -880 && theTemperature <= 2047;
	    @ assignable temperature;
	    @ ensures temperature ==  theTemperature;
	    @*/
	  void setTemperature(final short theTemperature) {
	    this.temperature = theTemperature;
	  }
	  /*
	   * (non-Javadoc)
	   * @see packet.Packet#getPressure()
	   */
	  public short getPressure() {
	    return pressure;
	  }
	  
	  /*@ requires thePressure >= 310 && thePressure <=5585;
	    @ assignable pressure;
	    @ ensures pressure == thePressure;
	    @*/
	  void setPressure(final short thePressure) {
	    this.pressure = thePressure;
	  }
	  /*
	   * (non-Javadoc)
	   * @see packet.Packet#getLightLevel()
	   */
	  public short getLightLevel() {
	    return lightLevel;
	  }
	  /*@ requires theLightLevel >=0 && theLightLevel <=1000;
	    @assignable lightLevel;
	    @ ensures lightLevel == theLightLevel;
	    @*/
	  void setLightLevel(final short theLightLevel) {
	    this.lightLevel = theLightLevel;
	  }
	  /*
	   * (non-Javadoc)
	   * @see packet.Packet#getAccelerometerX()
	   */
	  public short getAccelerometerX() {
	    return accelerometerX;
	  }
	  
	  /*@ requires theAccelerometerX >=1488 && theAccelerometerX <= 2232;
	    @ assignable accelerometerX;
	    @ ensures accelerometerX == theAccelerometerX;
	    @*/
	  void setAccelerometerX(final short theAccelerometerX) {
	    this.accelerometerX = theAccelerometerX;
	  }
	  /*
	   * (non-Javadoc)
	   * @see packet.Packet#getAccelerometerY()
	   */
	  public short getAccelerometerY() {
	    return accelerometerY;
	  }
	  /*@ requires theAccelerometerY >=1488 && theAccelerometerY <= 2232;
	    @ assignable accelerometerY;
	    @ ensures accelerometerY == theAccelerometerY;
	    @*/	  
	  void setAccelerometerY(final short theAccelerometerY) {
	    this.accelerometerY = theAccelerometerY;
	  }
	  /*
	   * (non-Javadoc)
	   * @see packet.Packet#getAccelerometerZ()
	   */
	  /*@ also
	    @ 
	    @*/
	  public short getAccelerometerZ() {
	    return accelerometerZ;
	  }
	  /*@ requires theAccelerometerZ >=1488 && theAccelerometerZ <= 2232;
	    @ assignable accelerometerZ;
	    @ ensures accelerometerZ == theAccelerometerZ;
	    @*/		  
	  void setAccelerometerZ(final short theAccelerometerZ) {
	    this.accelerometerZ = theAccelerometerZ;
	  }
      /*
       * (non-Javadoc)
       * @see packet.Packet#getSupplyVoltage()
       */
	  public int getSupplyVoltage() {
	    return supplyVoltage;
	  }
	  /*@ assignable supplyVoltage;
	    @ ensures supplyVoltage == theSupplyVoltage;
	    @*/
	  void setSupplyVoltage(final int theSupplyVoltage) {
	    this.supplyVoltage = theSupplyVoltage;
	  }
	  /*
	   * (non-Javadoc)
	   * @see packet.Packet#getSupplyCurrent()
	   */
	  public int getSupplyCurrent() {
	    return supplyCurrent;
	  }
	  /*@ assignable supplyCurrent; 
	    @ ensures supplyCurrent == theSupplyCurrent;
	    @*/
	  void setSupplyCurrent(final int theSupplyCurrent) {
	    this.supplyCurrent = theSupplyCurrent;
	  }

	  /*@ also
	    @ requires index >=0 && index < FRAMES;
	    @ requires (\forall int i; 0 <= i && i < mod_frames.length;
	    @          mod_frames[i] instanceof FormalInstanceFrame);
	    @*/
	  public/*@pure non_null*/ Frame getFrame(final int index) {
	    return frames[index];
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
	  
	  /**
	   * Represents an instance of Time interface
	   * @title         "TimeInstance"
	   * @date          "2009/10/10"
	   * @author        "Vieri & Dragan"
	   * @organisation  "School of Computer Science and Informatics, UCD"
	   * @copyright     "Copyright (C) 2009 UCD"
	   * @version       "$ Revision: 1.00 $"
	   */
	  public static class TimeInstance implements Time, Cloneable {
		  
		//@ public model instance short mod_hours;
	    //@ public model instance short mod_minutes;
	  	//@ public model instance short mod_seconds;
	  	//@ public model instance short mod_centiSeconds;
	  	 
		  
	      //@spec_public
	      private transient byte hours; //@ in mod_hours;
	      //@ represents mod_hours <- hours;
	      
	      //@spec_public
	      private transient byte minutes; //@ in mod_minutes; 
	      //@ represents mod_minutes <- minutes;
	      
	      //@spec_public
	      private transient byte seconds;//@ in mod_seconds;
	      //@ represents mod_seconds <- seconds;
	      
	      //@spec_public
	      private transient byte centiSeconds;//@ in mod_centiSeconds;
	      //@ represents mod_centiSeconds <- centiSeconds;
	      
	      
	      /*@ invariant 
	        @ 	mod_hours >= 0 && mod_hours < 24 		 &&
	        @ 	mod_minutes >= 0 && mod_minutes <= 59    &&
	        @ 	mod_seconds >= 0 && mod_seconds <= 59    &&
	        @ 	mod_centiSeconds >= 0 && mod_centiSeconds <= 99;
	        @*/
	      
	      
	      
	      /*@ assignable mod_hours,mod_minutes,mod_seconds,mod_centiSeconds;
	        @ ensures mod_hours ==0 && mod_minutes == 0;
	        @ ensures mod_seconds == 0 && mod_centiSeconds == 0;
	        @*/
	      public TimeInstance() {
	        setHours(0);
	        setMinutes(0);
	        setSeconds(0);
	        setCentiSeconds(0);
	      }
	      /*@ assignable mod_hours,mod_minutes,mod_seconds,mod_centiSeconds;
	        @ ensures mod_hours >= 0 && mod_hours < 24;
	        @ ensures mod_minutes >= 0 && mod_minutes <= 59;
	        @ ensures mod_seconds >= 0 && mod_seconds <= 59;
	        @ ensures mod_centiSeconds >= 0 && mod_centiSeconds <= 99;
	        @*/
	      public TimeInstance(final /*@non_null@*/ Time template) {
	        setHours(template.getHours());
	        setMinutes(template.getMinutes());
	        setSeconds(template.getSeconds());
	        setCentiSeconds(template.getCentiSeconds());
	      }
	      
	      
	      public /*@pure@*/ byte getHours() {
	        return hours;
	      }
	      
	  	/*@ requires aHours >= 0 && aHours < 24;
	  	  @ assignable hours;
	  	  @ ensures mod_hours == aHours;
	  	  @*/
	      void setHours(final int aHours) {
	        this.hours = (byte) aHours;
	      }
	      
	       
	      public byte getMinutes() {
	        return minutes;
	      }
	      
	      /*@ requires aMinutes >= 0 && aMinutes <= 59;
	  	  @ assignable minutes;
	  	  @ ensures mod_minutes == aMinutes;
	  	  @*/
	      void setMinutes(final int aMinutes) {
	        this.minutes = (byte) aMinutes;
	      }
	      
	      
	      public byte getSeconds() {
	        return seconds;
	      }
	      
	      /*@ requires aSeconds >= 0 && aSeconds <= 59;
	  	  @ assignable seconds;
	  	  @ ensures mod_seconds == aSeconds;
	  	  @*/
	      void setSeconds(final int aSeconds) {
	        this.seconds = (byte) aSeconds;
	      }
	          
	      
	      public  byte getCentiSeconds() {
	        return centiSeconds;
	      }
	      
	      /*@ requires aCentiSeconds >= 0 && aCentiSeconds <= 99;
	  	  @ assignable centiSeconds;
	  	  @ ensures mod_centiSeconds == aCentiSeconds;
	  	  @*/
	      void setCentiSeconds(final int aCentiSeconds) {
	        this.centiSeconds = (byte) aCentiSeconds;
	      }
	      
	      
	  //@public ghost Object g_time;	
	  	
	  	/*@ also
	  	  @ public behavior
	  	  @ assignable \everything;
	  	  @   ensures \result == g_time;
	  	  @   signals_only CloneNotSupportedException;
	  	  @*/
	    public Object clone() throws CloneNotSupportedException 
	    {
	  		Object time = super.clone();
	  		//@ set g_time = time;
	  		return time ;    
	    }
	 }
}
