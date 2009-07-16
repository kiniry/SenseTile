package sensor.type;

/**
 * This class represents ultrasonic sensor types.
 * @title         "UltrasonicSensorType"
 * @date          "2009/07/05 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public  final class UltrasonicSensorType {

	/** The index of ultrasonic sensor type. */
	private final transient /*@spec_public@*/int index;
	
	/** The name of ultrasonic sensor type. */
	private final transient /*@spec_public non_null@*/String name;
    
	private static/*@spec_public@*/ int count = 0;

	// "All indices are greater than or equal zero and less than or equal 1."

	//@ invariant index >= 0 && index <= 1;
	//@ static invariant count >= 0 && count <= 2;
	
   /*@ requires  count <= 1;
     @ assignable index, name, count;
	 @ ensures name == theName;
	 @ ensures index == \old( count );
	 @*/
	private UltrasonicSensorType(final /*@non_null@*/String theName) 
	{
		name = theName;
		index = count++;
	}
		
	/** The Proximity ultrasonic sensor.*/
	public static final  UltrasonicSensorType PROX =
		 new UltrasonicSensorType("PROX");
	
	/** The Ranging ultrasonic sensor.*/
	public static final  UltrasonicSensorType RANG = 
		 new UltrasonicSensorType("RANG");  
			
	/**
	 * @return "What is the index 
	 * for this enumerated type?"
	 */	
	//@ ensures \result >= 0 && \result <= 1;
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

