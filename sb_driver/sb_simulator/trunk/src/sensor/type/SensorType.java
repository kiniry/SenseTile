package sensor.type;

/**
 * This class represents an implementation of sensor types.
 * @title         "SensorType"
 * @date          "2009/07/05 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class SensorType 
{
	private SensorType (){}
	
	//@ requires e >= ACCEL_X && e <= ACCEL_Z;
	//@ ensures  \result <==> (legal_SensorType(5) || legal_SensorType(6) || legal_SensorType(7));
	//@ pure public static model boolean isValidAxis(final int e);
	
	
	/*@ requires e >= THERM && e <= ACCEL_Z;
	  @ ensures \result <==> (e == array[0] || 
	  @						  e == array[1] || 
	  @						  e == array[2] ||
	  @						  e == array[3] ||
	  @						  e == array[4] ||
	  @						  e == array[5] ||
	  @						  e == array[6] ||
	  @						  e == array[7]);
	  @ pure public model static boolean legal_SensorType(final int e);
	  @ */	
	
	//@ static invariant legal_SensorType(THERM);
	//@ static invariant legal_SensorType(LIGHT);
	//@ static invariant legal_SensorType(PRESS);
	//@ static invariant legal_SensorType(SOUND);
	//@ static invariant legal_SensorType(ULTRA);
	//@ static invariant legal_SensorType(ACCEL_X);
	//@ static invariant legal_SensorType(ACCEL_Y);
	//@ static invariant legal_SensorType(ACCEL_Z);
	
   /** The Thermistor sensor type.*/
	public static final  int THERM = 0;
	
   /** The Light sensor type.*/
	public static final  int LIGHT = 1;  

	/** The Pressure sensor type.*/
	public static final  int PRESS = 2;
	
	/** The Sound sensor type.*/
	public static final  int SOUND = 3;
	
	/** The Ultrasonic sensor type.*/
	public static final  int ULTRA = 4;
	
	/** Accelerometer sensor - X axe orientation.*/
	public static final  int ACCEL_X = 5;
	
	/** Accelerometer sensor - Y axe orientation.*/
	public static final  int ACCEL_Y = 6;
	
	/** Accelerometer sensor - Z axe orientation..*/
	public static final  int ACCEL_Z = 7;
	
	public  static final  /*@non_null*/ int[] array = {THERM, LIGHT, PRESS, SOUND, ULTRA, ACCEL_X, ACCEL_Y, ACCEL_Z};
	
}

