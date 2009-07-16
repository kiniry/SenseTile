package sensor.type;

/**
 * This class represents pressure sensor types.
 * @title         "PressureSensorType"
 * @date          "2009/07/05 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class PressureSensorType {

	/** The index of pressure sensor type. */
	private final transient /*@spec_public@*/int index;
	
	/** The name of pressure sensor type. */
	private final transient /*@spec_public non_null@*/String name;
    
	private static/*@spec_public*/ int count = 0;

   // "All indices are greater than zero."

	//@ invariant index >= 0 && index <= 4;
	//@ static invariant count >= 0 && count <= 5;
	
   /*@ requires  count <= 4;
     @ assignable index, name, count;
	 @ ensures name == theName;
	 @ ensures index == \old(count);
	 @*/
	private PressureSensorType (final /*@non_null@*/String theName) 
	{
		name = theName;
		index = count++;
	}
		
	/** The Absolute pressure sensor.*/
	public static final  PressureSensorType  APS = 
		 new PressureSensorType ("APS");
	
	/** The Gauge pressure sensor.*/
	public static final  PressureSensorType  GPS = 
		 new PressureSensorType ("GPS");
	
	/** The Vacuum pressure sensor.*/
	public static final  PressureSensorType  VPS = 
		 new PressureSensorType ("VPS");
	
	/** The Differential pressure sensor.*/
	public static final  PressureSensorType  DPS = 
		 new PressureSensorType ("DPS");

	/** The Sealed pressure sensor.*/
	public static final  PressureSensorType  SPS = 
		 new PressureSensorType ("SPS");
	
	/**
	 * @return "What is the index 
	 * for this enumerated type?"
	 */	
	//@ ensures \result >= 0 && \result <= 4;
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

