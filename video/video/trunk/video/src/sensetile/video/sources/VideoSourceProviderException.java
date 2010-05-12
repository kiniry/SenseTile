package sensetile.video.sources;

/**
 *
 * @author SenseTile
 */
public class VideoSourceProviderException extends RuntimeException
{
     /**
   * Default Exception constructor.
   */
   public VideoSourceProviderException()
   {
	  super();
   }

  /**
   * Exception constructor.
   * @param message - exception message
   */
   public VideoSourceProviderException(final String message){
        super(message);
   }

  /**
   * Exception constructor.
   * @param message - exception message
   * @param cause - exception cause provides
   * the stack trace information.
   */

  public VideoSourceProviderException(final String message, final Throwable cause)
  {
	  super(message,cause);
  }

  /**
   * Exception constructor.
   * @param cause - exception cause provides
   * the stack trace information.
   */
  public VideoSourceProviderException(final Throwable cause)
  {
	  super(cause);
  }

}
