package ie.ucd.sensetile.sensorboard.simulator.sensor.type;

/**
 * This class represents an implementation of sensor indexer array.
 * @title         "SensorType"
 * @date          "2009/07/05 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class SensorIndexer 
{
	private SensorIndexer (){}
	
	public static final int AUDIO_FREQUENCY_48KHZ = 48;
	
	public static final int AUDIO_FREQUENCY_96KHZ = 96;
	
	//@ requires e >= ACCEL_X && e <= ACCEL_Z;
	//@ ensures  \result <==> (legal_SensorIndex(3) || legal_SensorIndex(4) || legal_SensorIndex(5));
	//@ pure public static model boolean isValidAxis(final int e);
	
	/*@ requires e >= THERM && e <= ACCEL_Z;
	  @ ensures \result <==> (e == array[0] || 
	  @						  e == array[1] || 
	  @						  e == array[2] ||
	  @						  e == array[3] ||
	  @						  e == array[4] ||
	  @						  e == array[5]);
	  @ pure public model static boolean legal_SensorIndex(final int e);
	  @ */	
	
	//@ static invariant legal_SensorIndex(THERM);
	//@ static invariant legal_SensorIndex(LIGHT);
	//@ static invariant legal_SensorIndex(PRESS);
	//@ static invariant legal_SensorIndex(ACCEL_X);
	//@ static invariant legal_SensorIndex(ACCEL_Y);
	//@ static invariant legal_SensorIndex(ACCEL_Z);
	
   /** The Thermistor sensor index.*/
	public static final  int THERM = 0;
	
   /** The Light sensor index.*/
	public static final  int LIGHT = 1;  

	/** The Pressure sensor index.*/
	public static final  int PRESS = 2;
	
	/** Accelerometer sensor - X axe orientation.*/
	public static final  int ACCEL_X = 3;
	
	/** Accelerometer sensor - Y axe orientation.*/
	public static final  int ACCEL_Y = 4;
	
	/** Accelerometer sensor - Z axe orientation..*/
	public static final  int ACCEL_Z = 5;
	
	public  static final  /*@non_null*/ int[] array = {THERM, LIGHT, PRESS, ACCEL_X, ACCEL_Y, ACCEL_Z};
	
}

