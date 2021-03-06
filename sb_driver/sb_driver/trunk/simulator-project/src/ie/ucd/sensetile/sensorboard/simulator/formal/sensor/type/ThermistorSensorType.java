package ie.ucd.sensetile.sensorboard.simulator.formal.sensor.type;

/**
 * This class represents thermistor sensor types.
 * @title         "ThermistorSensorType"
 * @date          "2009/07/05 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class ThermistorSensorType {
	

	/*@ ensures \result <==> (e == array[0] || 
	  @						  e == array[1]);
	  @ pure public static model boolean legal_ThermSensorType(final int e);
	  @ */	
	
	//@ static invariant legal_ThermSensorType(NTC);
	//@ static invariant legal_ThermSensorType(PTC);
	
	private ThermistorSensorType() {}
		
   /** The Negative temperature coefficient thermistor.
	* For NTCs, the resistance decreases with temperature. 
    */
	public static final  int NTC = 0;
		 
   /** The Positive temperature coefficient thermistor. 
	* For PTCs, the resistance increases with temperature.
    */
	public static final  int PTC = 1;
	
	public  static final  /*@non_null*/ int[] array = {NTC, PTC};
	

}

