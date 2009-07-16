package sensor;


import sensor.type.SensorType;


public final class AxisAccelerometerSensor implements ISensor 
{
 
	//@spec_public
	private   transient int value= 15000;//@ in mod_value;
	  //@ represents mod_value <- value;
	
	//@spec_public
	private  transient boolean enabled;//@ in mod_enabled;
	  //@ represents mod_enabled <-enabled;
	
	//@spec_public
	private  final static transient int MAX = 15300;
	  //@ represents mod_max <-MAX;
	
	private final static transient int MIN = 14700;
	  //@ represents mod_min <-MIN;
	
	private /*@spec_public@*/ transient int index = 0;//@ in mod_index;
	  //@ represents mod_index <- index;
	
	//@spec_public non_null
	private transient  final int[] a_set;//@ in mod_set; 
	  //@ represents mod_set <-a_set;
		
	//@spec_public non_null  
	private final transient  MeasurementUnit a_unit = 
		MeasurementUnit.MVOLT;
	
	//@spec_public non_null
	private final transient  SensorType a_sensType; //@ in mod_type;
	//@ represents mod_type <-a_sensType;
	
	  //@ constraint mod_max == 15300;
	  //@ constraint mod_min == 14700;
	
	  /*@ private invariant( mod_type == SensorType.ACCEL_X ||
	    @           		 mod_type == SensorType.ACCEL_Y ||
	    @           		 mod_type ==  SensorType.ACCEL_Z );
	    @*/
	  
	  //@ private invariant a_unit == MeasurementUnit.MVOLT;
	  //@ public invariant mod_min <= mod_value && mod_value <= mod_max;
	  
	  /**
	   * Create component in initial state.
	   */
	  /*@ requires (type == SensorType.ACCEL_X ||
	    @          type == SensorType.ACCEL_Y ||
	    @          type ==  SensorType.ACCEL_Z);
	    @ assignable  mod_enabled, mod_set, mod_set[*], mod_type; 
	    @ ensures mod_type == type;
	    @ ensures mod_enabled == true;
	    @*/
	  public  AxisAccelerometerSensor(final /*@non_null@*/ int[] arr, 
			  final /*@non_null@*/ SensorType type) 
	  {
		  enabled = true;
		  a_sensType = type;
		  a_set = new int[arr.length];
		  System.arraycopy(arr, 0, a_set, 0, arr.length);
	  }
	    	  
	  /**
	   * What is the measurement unit for this component ?
	   * @return a_unit - unit of measurement.
	   */
	  //@ requires isEnabled();
	  //@ ensures \result == MeasurementUnit.MVOLT;
	  public/*@pure non_null@*/ MeasurementUnit getUnit()
	  {
		  return a_unit;
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
	  * The {@link sensor.Isensor#getSensorType()}
	  * specification.
	  */
	
	  /*@ also
	    @ ensures (\result == SensorType.ACCEL_X ||
	    @          \result == SensorType.ACCEL_Y ||
	    @          \result == SensorType.ACCEL_Z);
	    @*/
	  public SensorType getSensorType()
	  {
		  return a_sensType;
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
