package sensor.type;

/**
 * This class represents thermistor sensor types.
 * @title         "ThermistorSensorType"
 * @date          "2009/07/05 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class ThermistorSensorType {

	/** The index of thermistor sensor type. */
	private  final transient /*@spec_public@*/int index;
	
	/** The name of thermistor sensor type. */
	private transient final/*@spec_public non_null@*/String name;
    
	private  static/*@spec_public@*/ int count = 0;
	
   // "All indices are greater than zero."

   //@ invariant index == 0 || index > 0;
   //@ static invariant count == 0 || count > 0;
	
   /*@ assignable index, name, count;
	 @ ensures name == theName;
	 @ ensures index == \old(count);
	 @*/
	private ThermistorSensorType(final /*@non_null@*/String theName) 
	{
		name = theName;
		index = count++;
	}
		
   /** The Negative temperature coefficient thermistor.
	* For NTCs, the resistance decreases with temperature. 
    */
	public static final  ThermistorSensorType NTC = 
		 new ThermistorSensorType("NTC");
	
   /** The Positive temperature coefficient thermistor. 
	* For PTCs, the resistance increases with temperature.
    */
	public static final  ThermistorSensorType PTC = 
		 new ThermistorSensorType("PTC");  
			
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

