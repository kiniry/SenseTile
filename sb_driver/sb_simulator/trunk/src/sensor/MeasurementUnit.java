package sensor;
/**
 * This class represents typesafe implementation of measurement units.
 * @title         "MeasurementUnit"
 * @date          "2009/07/07"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class MeasurementUnit {

	/** The index of measurement unit. */
	private transient final /*@spec_public@*/int index;
	
	/** The name of measurement unit. */
	private transient final /*@spec_public non_null@*/String name;
    
	private static/*@spec_public@*/ int count = 0;

   // "All indices are greater than zero."

   //@ invariant index == 0 || index > 0;
   //@ static invariant count == 0 || count > 0;
	
   /*@ assignable index, name, count;
	 @ ensures name == theName;
	 @ ensures index == \old( count );
	 @*/
	private MeasurementUnit (final /*@non_null@*/String theName) 
	{
		name = theName;
		index = count++;
	}
		
	/** A -Amper (currency).*/
	public static final  MeasurementUnit  AMPER = 
		 new MeasurementUnit ("Amper");
	
	/** V -Voltage (voltage).*/
	public static final  MeasurementUnit  VOLT = 
		 new MeasurementUnit ("Volt");

	/** mV -mili Voltage (voltage).*/
	public static final  MeasurementUnit  MVOLT = 
		 new MeasurementUnit ("Mvolt");

	
	/** Hz - Hertz (frequency)*/
	public static final  MeasurementUnit  HERTZ = 
		 new MeasurementUnit ("Hertz");
	
	/** dB - Decibel*/
	public static final  MeasurementUnit  DECIBEL = 
		 new MeasurementUnit ("Decibel");
	
	/** C - Temperature - Celsius.*/
	
	public static final  MeasurementUnit  CELSIUS = 
		 new MeasurementUnit ("Celsius");
	
    /** pa - pressure - Pascal.*/
	
	public static final  MeasurementUnit  PASCAL = 
		 new MeasurementUnit ("PASCAL");
	
	/** lux - light - lux.*/
	
	public static final  MeasurementUnit  LUX = 
		 new MeasurementUnit ("LUX");

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

