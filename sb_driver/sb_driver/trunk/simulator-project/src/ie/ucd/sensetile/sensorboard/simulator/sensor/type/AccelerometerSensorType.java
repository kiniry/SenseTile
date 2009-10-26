package ie.ucd.sensetile.sensorboard.simulator.sensor.type;
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
	  @ public pure static model boolean legal_AccSensorType(final int e);
	  @ */	
	
	//@ static invariant legal_AccSensorType(PFPS);
	//@ static invariant legal_AccSensorType(SMA);
	//@ static invariant legal_AccSensorType(MEMS);
	//@ static invariant legal_AccSensorType(TCMOS);
	
	private AccelerometerSensorType(){}
	
	
	/** The Piezo-film or piezoelectric sensor.*/
	public static final  int PFPS  = 0;
	
	/** The Shear Mode Accelerometer.*/
	public static final  int SMA   = 1;
	
	/** The Surface Micromachined Capacitive.*/
	public static final  int MEMS  = 2;

	/** Thermal (submicrometre CMOS process).*/
	public static final  int TCMOS = 3;
	
	public  static final  /*@non_null*/ int[] array = {PFPS, SMA, MEMS, TCMOS };
	
	
	
}

