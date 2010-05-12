package sensetile.sensor.sources.telos;

/**
 *
 * @author dragan
 */
public class InvalidTelosCommandException extends TelosException
{

      /**
   * Default Exception constructor.
   */
   public InvalidTelosCommandException(){
	  super();
   }

  /**
   * Exception constructor.
   * @param message - exception message
   */
   public InvalidTelosCommandException(final String message){
        super(message);
   }

  /**
   * Exception constructor.
   * @param message - exception message
   * @param cause - exception cause provides
   * the stack trace information.
   */

  public InvalidTelosCommandException(final String message, final Throwable cause)
  {
	  super(message,cause);
  }

  /**
   * Exception constructor.
   * @param cause - exception cause provides
   * the stack trace information.
   */
  public InvalidTelosCommandException(final Throwable cause)
  {
	  super(cause);
  }
       
}
