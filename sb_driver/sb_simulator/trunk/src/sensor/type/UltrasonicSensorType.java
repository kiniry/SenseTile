package sensor.type;

/**
 * This class represents ultrasonic sensor types.
 * @title         "UltrasonicSensorType"
 * @date          "2009/07/05 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public  final class UltrasonicSensorType {
	

	/*@ ensures \result <==> (e == array[0] || 
	  @						  e == array[1]);
	  @ pure model boolean legal_UltraSensorType(final int e);
	  @ */	
	
	//@ invariant legal_UltraSensorType(0);
	//@ invariant legal_UltraSensorType(1);

	private UltrasonicSensorType() {}
		
	/** The Proximity ultrasonic sensor.*/
	public static final  int PROX = 0;
	
	/** The Ranging ultrasonic sensor.*/
	public static final  int RANG = 1; 
	
	public  final  /*@non_null*/ int[] array = {PROX, RANG};
		   
}

