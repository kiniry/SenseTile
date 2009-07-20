package sensor;

import sensor.type.SensorType;
import sensor.type.ThermistorSensorType;

public final class ThermistorSensor implements ISensor 
{
	//@spec_public
	private  transient int value= 25;//@ in mod_value;
	  //@ represents mod_value <- value;
	
	//@spec_public
	private transient boolean enabled;//@ in mod_enabled;
	  //@ represents mod_enabled <-enabled;
	
	private final static transient int MAX = 127;
	  //@ represents mod_max <-MAX;
	
	private final static transient int MIN = -55;
	  //@ represents mod_min <-MIN;
	
	//@spec_public
	private transient int index = 0;//@ in mod_mesure;
	
	//@ public model non_null int[] mod_set;
	//@spec_public non_null
	private transient  final int[] a_set;//@ in mod_set; 
	  //@ represents mod_set <-a_set;
	
	//@non_null
	private final transient  ThermistorSensorType a_type= 
		ThermistorSensorType.NTC;
	
	//@non_null  
	private final transient  MeasurementUnit a_unit = 
		MeasurementUnit.CELSIUS;

	//@spec_public non_null
	private final transient  SensorType a_sensType = 
		SensorType.THERM; //@ in mod_type;
	//@ represents mod_type <-a_sensType;
	
	  //@ constraint mod_max == 127;
	  //@ constraint mod_min == -55;
	  
	  //@ private invariant mod_type == SensorType.THERM;
	  //@ private invariant a_type == ThermistorSensorType.NTC;
	  //@ private invariant a_unit == MeasurementUnit.CELSIUS;
	  
	  /**
	   * Create component in initial state.
	   */
    /*@ assignable  mod_enabled, mod_set, mod_set[*];
      @ ensures mod_enabled == true;
      @ ensures (\forall int i; 0 <= i && i < arr.length;
      @          arr[i] == a_set[i]);
      @*/ 
	  public ThermistorSensor(final /*@non_null@*/ int[] arr) 
	  {
		  enabled = true;
		  a_set = new int[arr.length];
		  int count = 0;
		/*@ loop_invariant
		  @ 0 <= count  && count <= arr.length &&
	      @ (\forall int j; 0 <= j && j < count; a_set[j] == arr[j]);
	      @ decreases arr.length - count;
	      @*/
	      while (count >= 0 && count< arr.length ) 
	      {
	         a_set[count] = arr[count];
	         count++;
	      }
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
