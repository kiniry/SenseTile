package ie.ucd.sensetile.sensorboard.simulator.formal.sensor.type;

/**
 * This class represents light sensor types.
 * @title         "LightSensorType"
 * @date          "2009/07/05 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class LightSensorType {
	
	/*@ ensures \result <==> (e == array[0] || 
	  @						  e == array[1] || 
	  @						  e == array[2] ||
	  @						  e == array[3]);
	  @ pure public static model boolean legal_LightSensorType(final int e);
	  @ */	
	
	//@ static invariant legal_LightSensorType(PEC);
	//@ static invariant legal_LightSensorType(PCC);
	//@ static invariant legal_LightSensorType(PVC);
	//@ static invariant legal_LightSensorType(PJU);
	
	private LightSensorType(){}
		
	/** The Photo-emissive Cells light sensor.*/
	public static final int PEC  = 0;
	
	/** The Photo-conductive Cells light sensor.*/
	public static final  int PCC = 1;

	/** The Photo-voltaic Cells light sensor.*/
	public static final int PVC  = 2;

	/** The Photo-junction light sensor.*/
	public static final int PJU  = 3;
	
	public static  final  /*@non_null*/ int[] array = {PEC, PCC, PVC, PJU };
	
}

