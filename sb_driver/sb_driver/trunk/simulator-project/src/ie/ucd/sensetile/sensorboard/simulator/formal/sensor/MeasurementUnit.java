package ie.ucd.sensetile.sensorboard.simulator.formal.sensor;
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

	/*@ ensures \result <==> (e == array[0] || 
	  @						  e == array[1] || 
	  @						  e == array[2] ||
	  @						  e == array[3] ||
	  @						  e == array[4] ||
	  @						  e == array[5] ||
	  @						  e == array[6] ||
	  @						  e == array[7]);
	  @ pure public static model boolean legal_unit(final int e);
	  @ */	
	
	//@ static invariant legal_unit(AMPER);
	//@ static invariant legal_unit(VOLT);
	//@ static invariant legal_unit(MVOLT);
	//@ static invariant legal_unit(KHERTZ);
	//@ static invariant legal_unit(DECIBEL);
	//@ static invariant legal_unit(CELSIUS);
	//@ static invariant legal_unit(PASCAL);
	//@ static invariant legal_unit(LUX);

	private MeasurementUnit () {}
		
	/** A -Amper (currency).*/
	public static final  int  AMPER = 0;
	
	/** V -Voltage (voltage).*/
	public static final  int  VOLT  = 1;

	/** mV -mili Voltage (voltage).*/
	public static final  int  MVOLT = 2;

	/** Hz - Hertz (frequency)*/
	public static final  int  KHERTZ = 3;
	
	/** dB - Decibel*/
	public static final  int  DECIBEL = 4;
	
	/** C - Temperature - Celsius.*/
	public static final  int  CELSIUS = 5;
	
    /** pa - pressure - Pascal.*/
	public static final  int  PASCAL = 6;
	
	/** lux - light - lux.*/
	public static final  int  LUX = 7;
	
	public  static final  /*@non_null*/ int[] array = {AMPER, VOLT, MVOLT, KHERTZ, DECIBEL, CELSIUS, PASCAL, LUX};

}

