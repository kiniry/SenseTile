package ie.ucd.sensetile.sensorboard.simulator.sensor;

/**
 * Class implementation of sensor.MissingSensorException. 
 * Exception is used during measurement operations.
 * @title         "MissingSensorException"
 * @date          "2009/07/16 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */

public class MissingSensorException extends RuntimeException {
	
		
  protected static final long serialVersionUID = 221198;

  /**
   * Default Exception constructor.
   */
  //@ pure
  public MissingSensorException(){
	  super();
  }


  
  /**
   *Exception constructor.
   * @param message - exception message
   */
//@ pure
  public  MissingSensorException(final String message){
	  super(message);
  }


  /**
   * Exception constructor.
   * @param message - exception message
   * @param cause - exception cause provides 
   * the stack trace information.
   */
  //@ pure
  public MissingSensorException(final String message, final Throwable cause){
	  super(message,cause);
  }


  
  /**
   * Exception constructor.
   * @param cause - exception cause provides 
   * the stack trace information.
   */
//@ pure
  public MissingSensorException(final Throwable cause){
	  super(cause);
  }
}
