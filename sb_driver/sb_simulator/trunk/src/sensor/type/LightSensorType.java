package sensor.type;

/**
 * This class represents light sensor types.
 * @title         "LightSensorType"
 * @date          "2009/07/05 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class LightSensorType {

	/** The index of light sensor type. */
	private final transient/*@spec_public@*/int index;
	
	/** The name of light sensor type. */
	private final transient/*@spec_public non_null@*/String name;
    
	private static/*@spec_public@*/ int count = 0;

	// "All indices are greater than zero."
	
	//@ invariant index == 0 || index > 0;
	//@ static invariant count == 0 || count > 0;
	
    /*@ assignable index, name, count;
	  @ ensures name == theName;
	  @ ensures index == \old(count);
	  @*/
	private LightSensorType(final /*@non_null*/String theName) 
	{
		name = theName;
		index = count++;
	}
		
	/** The Photo-emissive Cells light sensor.*/
	public static final LightSensorType PEC = 
		 new LightSensorType("PEC");
	
	/** The Photo-conductive Cells light sensor.*/
	public static final  LightSensorType PCC = 
		 new LightSensorType("PCC");

	/** The Photo-voltaic Cells light sensor.*/
	public static final LightSensorType PVC = 
		 new LightSensorType("PVC");

	/** The Photo-junction light sensor.*/
	public static final LightSensorType PJU = 
		 new LightSensorType("PJU");
	
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

