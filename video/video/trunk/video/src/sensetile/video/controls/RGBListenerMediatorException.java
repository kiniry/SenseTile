/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.video.controls;

/**
 *
 * @author dragan
 */
public class RGBListenerMediatorException extends RuntimeException{

    public RGBListenerMediatorException(){
	  super();
   }

  /**
   * Exception constructor.
   * @param message - exception message
   */
   public RGBListenerMediatorException(final String message){
        super(message);
   }

  /**
   * Exception constructor.
   * @param message - exception message
   * @param cause - exception cause provides
   * the stack trace information.
   */

  public RGBListenerMediatorException(final String message, final Throwable cause)
  {
	  super(message,cause);
  }

  /**
   * Exception constructor.
   * @param cause - exception cause provides
   * the stack trace information.
   */
  public RGBListenerMediatorException(final Throwable cause)
  {
	  super(cause);
  }
}
