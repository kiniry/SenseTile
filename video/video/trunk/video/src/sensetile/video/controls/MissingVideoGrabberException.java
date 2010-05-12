/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.video.controls;

/**
 *
 * @author dragan
 */
public class MissingVideoGrabberException extends RuntimeException
{
     public MissingVideoGrabberException(){
	  super();
   }

  /**
   * Exception constructor.
   * @param message - exception message
   */
   public MissingVideoGrabberException(final String message){
        super(message);
   }

  /**
   * Exception constructor.
   * @param message - exception message
   * @param cause - exception cause provides
   * the stack trace information.
   */

  public MissingVideoGrabberException(final String message, final Throwable cause)
  {
	  super(message,cause);
  }

  /**
   * Exception constructor.
   * @param cause - exception cause provides
   * the stack trace information.
   */
  public MissingVideoGrabberException(final Throwable cause)
  {
	  super(cause);
  }
}
