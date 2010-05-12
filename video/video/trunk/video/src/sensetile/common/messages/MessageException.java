/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.common.messages;

/**
 *
 * @author dragan
 */
public class MessageException extends RuntimeException
{
        /**
   * Default Exception constructor.
   */
   public MessageException(){
	  super();
   }

  /**
   * Exception constructor.
   * @param message - exception message
   */
   public MessageException(final String message){
        super(message);
   }

  /**
   * Exception constructor.
   * @param message - exception message
   * @param cause - exception cause provides
   * the stack trace information.
   */

  public MessageException(final String message, final Throwable cause)
  {
	  super(message,cause);
  }

  /**
   * Exception constructor.
   * @param cause - exception cause provides
   * the stack trace information.
   */
  public MessageException(final Throwable cause)
  {
	  super(cause);
  }

}
