package sensor.type;

/**
 * This class represents accelerometer sensor types.
 * @title         "MotionSensorType"
 * @date          "2009/07/05 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class AccelerometerSensorType {

	/** The index of accelerometer sensor type. */
	private transient final /*@spec_public@*/int index;
	/** The name of accelerometer sensor type. */
	private transient final /*@spec_public non_null@*/String name;
    
	private static/*@spec_public@*/ int count = 0;

	// "All indices are greater than or equal 0 and less than or equal 3."
	
	//@ invariant index >= 0 && index <= 3;
	//@ static invariant count >= 0 && count <= 4;
	
   /*@ requires  count <= 3;
     @ assignable index, name, count;
	 @ ensures name == theName;
	 @ ensures index == \old( count );
	 @*/
	private AccelerometerSensorType(final /*@non_null*/String theName) 
	{
		name = theName;
		index = count++;
	}
		
	/** The Piezo-film or piezoelectric sensor.*/
	public static final  AccelerometerSensorType PFPS = 
		 new AccelerometerSensorType("PFPS");
	
	/** The Shear Mode Accelerometer.*/
	public static final  AccelerometerSensorType SMA = 
		 new AccelerometerSensorType("SMA");
	
	/** The Surface Micromachined Capacitive.*/
	public static final  AccelerometerSensorType MEMS = 
		 new AccelerometerSensorType("MEMS");

	/** Thermal (submicrometre CMOS process).*/
	public static final  AccelerometerSensorType TCMOS = 
		 new AccelerometerSensorType("TCMOS");
	
	/**
	 * @return "What is the index 
	 * for this enumerated type?"
	 */	
	//@ ensures \result >= 0 && \result <= 3;
	public /*@pure@*/ int  getIndex() 
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

