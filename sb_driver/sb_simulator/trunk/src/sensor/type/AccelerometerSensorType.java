package sensor.type;
/**
 * This class represents accelerometer sensor type.
 * @title         "MotionSensorType"
 * @date          "2009/07/05 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class AccelerometerSensorType {
		
	/*@ ensures \result <==> (e == array[0] || 
	  @						  e == array[1] || 
	  @						  e == array[2] ||
	  @						  e == array[3]);
	  @ pure model boolean legal_AccSensorType(final int e);
	  @ */	
	
	//@ invariant legal_AccSensorType(0);
	//@ invariant legal_AccSensorType(1);
	//@ invariant legal_AccSensorType(2);
	//@ invariant legal_AccSensorType(3);
	
	private AccelerometerSensorType(){}
	
	
	/** The Piezo-film or piezoelectric sensor.*/
	public static final  int PFPS  = 0;
	
	/** The Shear Mode Accelerometer.*/
	public static final  int SMA   = 1;
	
	/** The Surface Micromachined Capacitive.*/
	public static final  int MEMS  = 2;

	/** Thermal (submicrometre CMOS process).*/
	public static final  int TCMOS = 3;
	
	public  final  /*@non_null*/ int[] array = {PFPS, SMA, MEMS, TCMOS };
	
}

