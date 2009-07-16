package sensor.type;



/**
 * Class implementation of sensor.type.InvalidSensorTypeException. 
 * Exception is used during creation phase.
 * @title         "InvalidSensorTypeException"
 * @date          "2009/07/16 10:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */

public class InvalidSensorTypeException extends RuntimeException {
	
		
  protected static final long serialVersionUID = 22119747101947799L;
	
  //@ pure
  public InvalidSensorTypeException(){
	  super();
  }


  //@ pure
  public  InvalidSensorTypeException(final String message){
	  super(message);
  }


  //@ pure
  public InvalidSensorTypeException(final String message, final Throwable cause){
	  super(message,cause);
  }


  //@ pure
  public InvalidSensorTypeException(final Throwable cause){
	  super(cause);
  }
}
