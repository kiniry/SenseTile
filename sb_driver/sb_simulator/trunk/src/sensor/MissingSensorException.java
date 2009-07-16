package sensor;

/**
 * Class implementation of sensor.MissingSensorException. 
 * Exception is used during measurement operations.
 * @title         "MIssingTypeException"
 * @date          "2009/07/16 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */

public class MissingSensorException extends RuntimeException {
	
		
  protected static final long serialVersionUID = 22119747101947789L;
	
  //@ pure
  public MissingSensorException(){
	  super();
  }


  //@ pure
  public  MissingSensorException(final String message){
	  super(message);
  }


  //@ pure
  public MissingSensorException(final String message, final Throwable cause){
	  super(message,cause);
  }


  //@ pure
  public MissingSensorException(final Throwable cause){
	  super(cause);
  }
}
