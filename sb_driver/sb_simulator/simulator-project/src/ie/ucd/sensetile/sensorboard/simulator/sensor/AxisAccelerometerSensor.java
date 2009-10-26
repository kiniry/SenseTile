package ie.ucd.sensetile.sensorboard.simulator.sensor;

import ie.ucd.sensetile.sensorboard.simulator.sensor.type.AccelerometerSensorType;

/**
 * An accelerometer measures the acceleration it experiences
 * relative to freefall. Single model is available
 * to detect magnitude and direction of the acceleration as 
 * a vector quantity, and can be used to sense orientation, 
 * vibration and shock.
 * @title         "AxisAccelerometerSensor"
 * @date          "2009/07/07"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class AxisAccelerometerSensor implements ISensor 
{

	//@spec_public
	private   transient int value= 1860;//@ in mod_value;
	  //@ represents mod_value <- value;
	
	//@spec_public
	private  transient boolean enabled;//@ in mod_enabled;
	  //@ represents mod_enabled <-enabled;
	
	private  final static transient int MAX = 2232;
	  //@ represents mod_max <-MAX;
	
	private final static transient int MIN = 1488;
	  //@ represents mod_min <-MIN;
	
	private transient int index = 0;//@ in mod_mesure;

	
	//@ public model non_null int[] mod_set;
	//@spec_public non_null
	private transient  final int[] a_set;//@ in mod_set; 
	  //@ represents mod_set <-a_set;
		
	//@spec_public  
	private final transient  int a_unit = 
		MeasurementUnit.MVOLT;
	
	//@spec_public
	private final transient  int a_type= 
		AccelerometerSensorType.PFPS;
	//@ invariant mod_min <= mod_value && mod_value <= mod_max;
	
	  //@ constraint mod_max == 2232;
	  //@ constraint mod_min == 1488;
	  //@ constraint a_unit == MeasurementUnit.MVOLT;
	  //@ constraint a_type == AccelerometerSensorType.PFPS;
	  	
	  /**
	   * Create component in initial state.
	   */
	  /*@ assignable  mod_enabled, mod_set, mod_set[*]; 
	    @ ensures mod_enabled == true;
        @ ensures (\forall int i; 0 <= i && i < arr.length;
        @          arr[i] == a_set[i]);
	    @*/
	  public  AxisAccelerometerSensor(final /*@non_null@*/ int[] arr) 
	  {
		  enabled = true;
		  a_set = new int[arr.length];
		  int count = 0;		
		/*@ loop_invariant
		  @ 0 <= count  && count <= arr.length &&
	      @ (\forall int j; 0 <= j && j < count; a_set[j] == arr[j]);
	      @ decreases arr.length - count;
	      @*/
	      while (count >= 0 && count < arr.length ) 
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
	  //@ ensures \result == MeasurementUnit.MVOLT;
	  public/*@pure@*/ int getUnit()
	  {
		  return a_unit;
	  }
	  
	  /**
	   * What is the type for this component ?
	   * @return a_type- NTC type.
	   */
	 //@ requires isEnabled();
	 //@ ensures \result == AccelerometerSensorType.PFPS;
	  public/*@pure@*/ int getType()
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
	    	  setVoltage ( index );
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
	 private void  setVoltage ( final int index )
	 {
		 if( a_set[index] < MIN )
		 {
			  value = MIN;
		 }
		 else if (a_set[index]> MAX ) 
		 {
			 value = MAX;
		 }
		 else 
		 {
			  value = a_set[index];
		 }
	  }
}
