/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.common.services;

/**
 *
 * @author SenseTile
 */
public class BroadcasterServiceException extends RuntimeException
{
        /**
   * Default Exception constructor.
   */
   public BroadcasterServiceException(){
	  super();
   }

  /**
   * Exception constructor.
   * @param message - exception message
   */
   public BroadcasterServiceException(final String message){
        super(message);
   }

  /**
   * Exception constructor.
   * @param message - exception message
   * @param cause - exception cause provides
   * the stack trace information.
   */

  public BroadcasterServiceException(final String message, final Throwable cause)
  {
	  super(message,cause);
  }

  /**
   * Exception constructor.
   * @param cause - exception cause provides
   * the stack trace information.
   */
  public BroadcasterServiceException(final Throwable cause)
  {
	  super(cause);
  }

}
