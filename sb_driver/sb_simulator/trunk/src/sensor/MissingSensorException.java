package sensor;

/**
 * Class implementation of sensor.MissingSensorException. Exception used
 * during measurement operations.
 * 
 * @version Revision: 1.1 $
 * @author Dragan Stosic
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
