package sensetile.sensor.sources.telos;

/**
 *
 * @author SenseTile
 */
public class TelosException extends RuntimeException
{
    /**
   * Default Exception constructor.
   */
   public TelosException()
   {
	  super();
   }

  /**
   * Exception constructor.
   * @param message - exception message
   */
   public TelosException(final String message)
   {
        super(message);
   }

  /**
   * Exception constructor.
   * @param message - exception message
   * @param cause - exception cause provides
   * the stack trace information.
   */

  public TelosException(final String message, final Throwable cause)
  {
	  super(message,cause);
  }

  /**
   * Exception constructor.
   * @param cause - exception cause provides
   * the stack trace information.
   */
  public TelosException(final Throwable cause)
  {
	  super(cause);
  }
}
