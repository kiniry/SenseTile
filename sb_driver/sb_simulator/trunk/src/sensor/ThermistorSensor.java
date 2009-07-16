package sensor;

import sensor.type.SensorType;
import sensor.type.ThermistorSensorType;

public final class ThermistorSensor implements ISensor 
{
	private  transient int value= 25;//@ in mod_value;
	  //@ represents mod_value <- value;
	
	private transient boolean enabled;//@ in mod_enabled;
	  //@ represents mod_enabled <-enabled;
	
	private final static transient int MAX = 127;
	  //@ represents mod_max <-MAX;
	
	private final static transient int MIN = -55;
	  //@ represents mod_min <-MIN;
	
	private transient int index = 0;//@ in mod_index;
	  //@ represents mod_index <- index;
	
	//@spec_public non_null
	private transient  final int[] a_set;//@ in mod_set; 
	  //@ represents mod_set <-a_set;
	
	//@non_null
	private final transient  ThermistorSensorType a_type= 
		ThermistorSensorType.NTC;
	
	//@non_null  
	private final transient  MeasurementUnit a_unit = 
		MeasurementUnit.CELSIUS;

	//@non_null
	private final transient  SensorType a_sensType = 
		SensorType.THERM; //@ in mod_type;
	//@ represents mod_type <-a_sensType;
	
	  //@ constraint mod_max == 127;
	  //@ constraint mod_min == -55;
	  
	  //@ private invariant mod_type == SensorType.THERM;
	  //@ private invariant a_type == ThermistorSensorType.NTC;
	  //@ private invariant a_unit == MeasurementUnit.CELSIUS;
	  
	  //@ public invariant mod_min <= mod_value && mod_value <= mod_max;
	  
	
	  /**
	   * Create component in initial state.
	   */
	  //@ assignable  mod_enabled, mod_set, mod_set[*]; 
	  public ThermistorSensor(final /*@non_null@*/ int[] arr) 
	  {
		  enabled = true;
		  a_set = new int[arr.length];
		  System.arraycopy(arr, 0, a_set, 0, arr.length);
	  }
	    	  
	  /**
	   * What is the measurement unit for this component ?
	   * @return a_unit - unit of measurement.
	   */
	  //@ requires isEnabled();
	  //@ ensures \result == MeasurementUnit.CELSIUS;
	  public/*@pure non_null@*/ MeasurementUnit getUnit()
	  {
		  return a_unit;
	  }
	  /**
	   * What is the type for this component ?
	   * @return a_type- NTC type.
	   */
	 //@ requires isEnabled();
	 //@ ensures \result == ThermistorSensorType.NTC;
	  public/*@pure non_null@*/ ThermistorSensorType getType()
	  {
		  return a_type;
	  }
	  
	 /**
	  * The {@link sensor.Isensor#getMax()}
	  * specification.
	  */
	  public int getMax()
	  {
		  return MAX;
	  }

	 /**
	  * The {@link sensor.Isensor#getMin()}
      * specification.
	  */
	  public int getMin()
	  {
		  return MIN;
	  }

	 /**
	  * The {@link sensor.Isensor#isEnabled()}
      * specification.
	  */
	  public boolean isEnabled() 
	  {
			return enabled;
	  }

	 /**
	  * The {@link sensor.Isensor#setEnable(boolean)}
      * specification.
	  */
	  public void setEnable(final boolean theFlag) 
	  {
		 enabled = theFlag;	
	  }
	/**
	 * The {@link sensor.Isensor#getValue()}
     * specification.
	 */
	 public int getValue() 
	 {
		  return value;
	 }
	/**
	 * The {@link sensor.Isensor#getSensorType()}
	 * specification.
	 */
	 //@also
	 //@ensures \result == SensorType.THERM;
	 public SensorType getSensorType()
	 {
		 return a_sensType;
	 }
		 	 
	/**
	 * The {@link sensor.Isensor#measure()}
	 * specification.
	 */ 
     public void measure() throws MissingSensorException 
     {	
    	 if(!isEnabled())
    	 {
    		 throw new MissingSensorException("Sensor is not available.");
    	 }
    	 
    	 if(( index == 0 || index > 0 ) &&  
			   index < a_set.length )
	      {
	    	  setTemp ( index );
		      index ++;
	      }
	      else 
	      {
	    	  value = MIN;
		      index = 0;
	      }
     }
	  
	/*@ requires index >= 0; 
	  @ requires index < mod_set.length;
	  @
	  @ assignable mod_value;
	  @
	  @ ensures  mod_min <= mod_value && 
	  @          mod_value <= mod_max;
	  @*/
	 private void  setTemp ( final int index )
	 {
		 if( a_set[index] < MIN || a_set[index]> MAX )
		 {
			  value = MIN;
		 }
		 else 
		 {
			  value = a_set[index];
		 }
	  }
}
