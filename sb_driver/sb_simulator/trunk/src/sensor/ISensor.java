package sensor;

import sensor.type.SensorType;
/**
 * This class represents an abstraction of sensor component.
 * @title         "ISensor"
 * @date          "2009/07/05 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public interface ISensor {
	
	//@ public model instance int mod_value;
	//@ public model instance int mod_max;
	//@ public model instance int mod_min;
	//@ public model instance SensorType mod_type;

	//@ public model instance int mod_index;
	//@ public model instance boolean mod_enabled;
	
	  /**
	   * Are you enabled ?
	   * @return enabled.
	   */
	  /*@pure@*/ boolean isEnabled();
	  
	  /**
	    * Turn off / on this component.
	    * @param theFlag - on / off.
	    */
	  //@ assignable mod_enabled;
	  //@ensures mod_enabled == theFlag;
	  void setEnable(final boolean theFlag);
	  
	  /**
	   * What is the measured value for this component ?
	   * @return mod_value measured value.
	   */
	  //@ requires isEnabled();
	  //@ ensures \result == mod_value;
	  /*@pure@*/ int getValue();
	  
	  
	  /**
	   * Measure current value.If sensor is not available
	   * throws an MissingSensorException.
	   */
	  /*@ requires isEnabled();
	    @ assignable mod_value, mod_index;
	    @ ensures mod_min <= mod_value && mod_value <= mod_max;
	    @ also
	    @ requires !isEnabled();
        @ signals_only MissingSensorException;
        @*/ 
	  void measure() throws MissingSensorException; 
	  
	  /**
	   * What is the minimal value for this component ?
	   * @return mod_min - mimimum value.
	   */
	  //@ requires isEnabled();
	  //@ ensures \result == mod_min;
	  /*@ pure@*/ int getMin();
	  
	  /**
	   * What is the maximal value for this component ?
	   * @return mod_max - maximim value.
	   */
	  //@ requires isEnabled();
	  //@ ensures \result == mod_max;
	  /*@ pure@*/ int getMax();
	  
	  /**
	   * Which type of sensor are you?
	   * @return mod_type - sensor type.
	   */
	  //@ ensures \result instanceof SensorType;
	  /*@ pure*/ SensorType getSensorType();
		
}
