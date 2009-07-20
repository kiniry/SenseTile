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


	/*@ ensures \result <==> (e == array[0] || 
	  @						  e == array[1] || 
	  @						  e == array[2] ||
	  @						  e == array[3] ||
	  @						  e == array[4]);
	  @ pure model boolean legal_PressSensorType(final int e);
	  @ */	
	
	//@ invariant legal_PressSensorType(0);
	//@ invariant legal_PressSensorType(1);
	//@ invariant legal_PressSensorType(2);
	//@ invariant legal_PressSensorType(3);
	//@ invariant legal_PressSensorType(4);
	
	private PressureSensorType () {}
		
	/** The Absolute pressure sensor.*/
	public static final  int  APS = 0;
	
	/** The Gauge pressure sensor.*/
	public static final  int  GPS = 1;
	
	/** The Vacuum pressure sensor.*/
	public static final  int VPS  = 2;
	
	/** The Differential pressure sensor.*/
	public static final  int  DPS = 3;

	/** The Sealed pressure sensor.*/
	public static final  int  SPS = 4;
	
	public  final  /*@non_null*/ int[] array = {APS, GPS, VPS, DPS, SPS };
}

