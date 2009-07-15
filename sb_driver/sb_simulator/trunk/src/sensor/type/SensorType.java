package sensor.type;

/**
 * This class represents typesafe implementation of
 * sensor types.
 * @title         "SensorType"
 * @date          "2009/07/05 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class SensorType {

	/** The index of sensor type. */
	private  final transient /*@spec_public@*/int index;
	
	/** The name of sensor type. */
	private transient final/*@spec_public non_null@*/String name;
    
	private  static/*@spec_public@*/ int count = 0;
	
   // "All indices are greater than zero."

   //@ invariant index == 0 || index > 0;
   //@ static invariant count == 0 || count > 0;
	
   /*@ assignable index, name, count;
	 @ ensures name == theName;
	 @ ensures index == \old(count);
	 @*/
	private SensorType(final /*@non_null@*/String theName) 
	{
		name = theName;
		index = count++;
	}
		
   /** The Thermistor sensor.*/
	public static final  SensorType THERM = 
		 new SensorType("THERMISTOR");
	
   /** The Light sensor.*/
	public static final  SensorType LIGHT = 
		 new SensorType("LIGHT");  

	/** The Pressure sensor.*/
	public static final  SensorType PRESS = 
		 new SensorType("PRESSURE");
	
	/** The Sound sensor.*/
	public static final  SensorType SOUND = 
		 new SensorType("SOUND");
	/** The Ultrasonic sensor.*/
	public static final  SensorType ULTRA = 
		 new SensorType("ULTRA");
	
	/** Accelerometer sensor - X axe orientation.*/
	public static final  SensorType ACCEL_X = 
		 new SensorType("ACCEL_X");
	
	/** Accelerometer sensor - Y axe orientation.*/
	public static final  SensorType ACCEL_Y = 
		 new SensorType("ACCEL_Y");
	
	/** Accelerometer sensor - Z axe orientation..*/
	public static final  SensorType ACCEL_Z = 
		 new SensorType("ACCEL_Z");
	
	/**
	 * @return "What is the index 
	 * for this enumerated type?"
	 */	
	//@ ensures \result == 0 || \result > 0;
	public /*@pure*/ int  getIndex() 
	{
		return index;
	}

	/**
	 * @return ""What is the name for 
	 * this enumerated type?""
	 */	
	
	public /*@pure non_null@*/ String  getName() 
	{
		return name;
	}
}

