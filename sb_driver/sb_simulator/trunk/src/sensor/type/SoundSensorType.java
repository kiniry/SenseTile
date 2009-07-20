package sensor.type;

/**
 * This class represents microphone sensor types.
 * @title         "SoundSensorType"
 * @date          "2009/07/05 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class SoundSensorType {

	/*@ ensures \result <==> (e == array[0] || 
	  @						  e == array[1] || 
	  @						  e == array[2] ||
	  @						  e == array[3] ||
	  @						  e == array[4] ||
	  @						  e == array[5] ||
	  @						  e == array[6] ||
	  @						  e == array[7] ||
	  @						  e == array[8]);
	  @ pure public static model boolean legal_SoundSensorType(final int e);
	  @ */	
	
	//@ static invariant legal_SoundSensorType(DMS);
	//@ static invariant legal_SoundSensorType(CMS);
	//@ static invariant legal_SoundSensorType(PMS);
	//@ static invariant legal_SoundSensorType(FOS);
	//@ static invariant legal_SoundSensorType(LMS);
	//@ static invariant legal_SoundSensorType(LQMS);
	//@ static invariant legal_SoundSensorType(MEMS);
	//@ static invariant legal_SoundSensorType(SMS);
	//@ static invariant legal_SoundSensorType(CCEM);
	
	private SoundSensorType() {}
		
	/** The Dynamic microphone sensor.*/
	public static final  int DMS = 0;
	
	/** The Carbon microphone sensor.*/
	public static final  int CMS = 1;
	
	/** The Piezoelectric microphone sensor.*/
	public static final  int PMS = 2;
	
	/** The Fiber optical microphone sensor.*/
	public static final  int FOS = 3;
 	
	/** The Laser microphone sensor.*/
	public static final  int LMS = 4;
	
	/** The Liquid microphone sensor.*/
	public static final  int LQMS = 5;
	
	/** The MEMS microphone sensor.*/
	public static final  int MEMS = 6;
	
	/** The Speaker as microphone sensor.*/
	public static final  int SMS = 7;  
	
	/** Condenser, capacitor or 
	 * electrostatic microphone sensor.
	 */
	public static final  int CCEM = 8;
		
	public  static final  /*@non_null*/ int[] array = {DMS, CMS, PMS, FOS, LMS, LQMS, MEMS, SMS, CCEM};
	
}
