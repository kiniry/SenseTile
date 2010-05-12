
package sensetile.devices;
/**
 *@author SenseTile
 */
public class VloopbackException extends Exception
{
 /**
   * Default Exception constructor.
   */
   public VloopbackException()
   {
	  super();
   }

  /**
   * Exception constructor.
   * @param message - exception message
   */
   public VloopbackException(final String message)
   {
        super(message);
   }

  /**
   * Exception constructor.
   * @param message - exception message
   * @param cause - exception cause provides
   * the stack trace information.
   */

  public VloopbackException(final String message, final Throwable cause)
  {
	  super(message,cause);
  }

  /**
   * Exception constructor.
   * @param cause - exception cause provides
   * the stack trace information.
   */
  public VloopbackException(final Throwable cause)
  {
	  super(cause);
  }

}
