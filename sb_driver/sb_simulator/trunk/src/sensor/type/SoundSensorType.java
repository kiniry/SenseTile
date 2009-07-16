package sensor.type;

/**
 * This class represents microphone sensor types.
 * @title         "SoundSensorType"
 * @date          "2009/07/05 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class SoundSensorType {

	/** The index of sound sensor type. */
	private final transient /*@spec_public@*/int index;
	
	/** The name of sound sensor type. */
	private final transient /*@spec_public non_null@*/String name;
    
	private static/*@spec_public@*/ int count = 0;

	// "All indices are greater than or equal 0 and less than or equal 7."

	//@ invariant index >= 0 && index <= 7;
	//@ static invariant count >= 0 && count <= 8;
	
   /*@ requires  count <= 7;
     @ assignable index, name, count;
	 @ ensures name == theName;
	 @ ensures index == \old( count );
	 @*/
	private SoundSensorType(final /*@non_null@*/String theName) 
	{
		name = theName;
		index = count++;
	}
		
	/** The Dynamic microphone sensor.*/
	public static final  SoundSensorType DMS = 
		 new SoundSensorType("DMS");
	
	/** The Carbon microphone sensor.*/
	public static final  SoundSensorType CMS = 
		 new SoundSensorType("CMS"); 
	
	/** The Piezoelectric microphone sensor.*/
	public static final  SoundSensorType PMS = 
		 new SoundSensorType("PMS");   
	
	/** The Fiber optical microphone sensor.*/
	public static final  SoundSensorType FOS = 
		 new SoundSensorType("FOS");  
	
	/** The Laser microphone sensor.*/
	public static final  SoundSensorType LMS = 
		 new SoundSensorType("LMS"); 
	
	/** The Liquid microphone sensor.*/
	public static final  SoundSensorType LQMS = 
		 new SoundSensorType("LQMS");
	
	/** The MEMS microphone sensor.*/
	public static final  SoundSensorType MEMS = 
		 new SoundSensorType("MEMS"); 
	
	/** The Speaker as microphone sensor.*/
	public static final  SoundSensorType SMS = 
		 new SoundSensorType("SMS");   
	
	/** Condenser, capacitor or 
	 * electrostatic microphone sensor.
	 */
	public static final  SoundSensorType CCEM = 
		 new SoundSensorType("CCEM");  
		
	/**
	 * @return "What is the index 
	 * for this enumerated type?"
	 */	
	//@ ensures \result >= 0 && \result <= 7;
	public /*@pure*/ int  getIndex() 
	{
		return index;
	}

	/**
	 * @return "What is the name for 
	 * this enumerated type?"
	 */	
	public /*@pure non_null@*/ String  getName() 
	{
		return name;
	}
}
