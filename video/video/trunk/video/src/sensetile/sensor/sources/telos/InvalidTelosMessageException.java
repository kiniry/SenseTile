package sensetile.sensor.sources.telos;

/**
 *
 * @author SenseTile
 */
public class InvalidTelosMessageException  extends TelosException
{

 /**
   * Default Exception constructor.
   */
   public InvalidTelosMessageException(){
	  super();
   }

  /**
   * Exception constructor.
   * @param message - exception message
   */
   public InvalidTelosMessageException(final String message){
        super(message);
   }

  /**
   * Exception constructor.
   * @param message - exception message
   * @param cause - exception cause provides
   * the stack trace information.
   */

  public InvalidTelosMessageException(final String message, final Throwable cause)
  {
	  super(message,cause);
  }

  /**
   * Exception constructor.
   * @param cause - exception cause provides
   * the stack trace information.
   */
  public InvalidTelosMessageException(final Throwable cause)
  {
	  super(cause);
  }

}

