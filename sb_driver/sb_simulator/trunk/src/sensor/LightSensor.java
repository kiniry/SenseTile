package sensor;

import sensor.type.LightSensorType;
import sensor.type.SensorType;

public final class LightSensor implements ISensor 
{
	//@spec_public
	private  transient int value= 500;//@ in mod_value;
	  //@ represents mod_value <- value;
	
	//@spec_public
	private transient boolean enabled;//@ in mod_enabled;
	  //@ represents mod_enabled <-enabled;
	
	
	private final static transient int MAX = 1000;
	  //@ represents mod_max <-MAX;
	
	
	private final static transient int MIN = 0;
	  //@ represents mod_min <-MIN;
	
	
	private transient int index = 0;//@ in mod_mesure;
	 
	
	//@ public model non_null int[] mod_set;
	//@spec_public non_null
	private transient  final int[] a_set;//@ in mod_set; 
	  //@ represents mod_set <-a_set;
	
	//@spec_public
	private final transient  int a_type= 
		LightSensorType.PEC;
	
	//@spec_public
	private final transient  int a_unit = 
		MeasurementUnit.LUX;
	
	//@spec_public
	private final transient  int a_sensType = 
		SensorType.LIGHT; //@ in mod_type;
	//@ represents mod_type <-a_sensType;
	
	
	  //@ constraint mod_max == 1000;
	  //@ constraint mod_min == 0;
	  //@ constraint mod_type == SensorType.LIGHT;
	  //@ constraint a_type == LightSensorType.PEC;
	  //@ constraint a_unit == MeasurementUnit.LUX;
	   
	  /**
	   * Create component in initial state.
	   */
	  /*@ assignable  mod_enabled, mod_set, mod_set[*];
	    @ ensures mod_enabled == true;
	    @ ensures (\forall int i; 0 <= i && i < arr.length;
        @          arr[i] == a_set[i]);
	    @*/  
	  public LightSensor(final /*@non_null@*/ int[] arr) 
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
	  //@ ensures \result == MeasurementUnit.LUX;
	  public/*@pure@*/ int getUnit()
	  {
		  return a_unit;
	  }
	  /**
	   * What is the type for this component ?
	   * @return a_type- NTC type.
	   */
	 //@ requires isEnabled();
	 //@ ensures \result == LightSensorType.PEC;
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
	  * The {@link sensor.Isensor#getSensorType()}
	  * specification.
	  */
	  //@also
	  //@ensures \result == SensorType.LIGHT;
	  public int getSensorType()
	  {
		  return a_sensType;
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
	    	  setLight ( index );
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
	 private void  setLight ( final int index )
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
