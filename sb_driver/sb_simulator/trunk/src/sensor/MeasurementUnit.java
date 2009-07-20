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

	/*@ ensures \result <==> (e == array[0] || 
	  @						  e == array[1] || 
	  @						  e == array[2] ||
	  @						  e == array[3] ||
	  @						  e == array[4] ||
	  @						  e == array[5] ||
	  @						  e == array[6] ||
	  @						  e == array[7]);
	  @ pure model boolean legal_unit(final int e);
	  @ */	
	
	//@ invariant legal_unit(0);
	//@ invariant legal_unit(1);
	//@ invariant legal_unit(2);
	//@ invariant legal_unit(3);
	//@ invariant legal_unit(4);
	//@ invariant legal_unit(5);
	//@ invariant legal_unit(6);
	//@ invariant legal_unit(7);

	private MeasurementUnit () {}
		
	/** A -Amper (currency).*/
	public static final  int  AMPER = 0;
	
	/** V -Voltage (voltage).*/
	public static final  int  VOLT  = 1;

	/** mV -mili Voltage (voltage).*/
	public static final  int  MVOLT = 2;

	/** Hz - Hertz (frequency)*/
	public static final  int  HERTZ = 3;
	
	/** dB - Decibel*/
	public static final  int  DECIBEL = 4;
	
	/** C - Temperature - Celsius.*/
	public static final  int  CELSIUS = 5;
	
    /** pa - pressure - Pascal.*/
	public static final  int  PASCAL = 6;
	
	/** lux - light - lux.*/
	public static final  int  LUX = 7;
	
	public  final  /*@non_null*/ int[] array = {AMPER, VOLT, MVOLT, HERTZ, DECIBEL, CELSIUS, PASCAL, LUX};

}

