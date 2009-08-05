package channel;

/**
 * Class implementation of channel.ChannelException. 
 * Exception is used during file system operations.
 * @title         "ChannelException"
 * @date          "2009/07/16 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */

public class ChannelException extends RuntimeException {
	
		
  protected static final long serialVersionUID = 221197;
	
  /**
   * Default Exception constructor.
   */
  //@ pure
  public ChannelException(){
	  super();
  }

  /**
   *Channel exception constructor.
   * @param message - exception message
   */
  //@ pure
  public  ChannelException(final String message){
	  super(message);
  }

  /**
   * Channel exception constructor.
   * @param message - exception message
   * @param cause - exception cause provides 
   * the stack trace information.
   */
  //@ pure
  public ChannelException(final String message, final Throwable cause){
	  super(message,cause);
  }

  /**
   * Channel exception constructor.
   * @param cause - exception cause provides 
   * the stack trace information.
   */
  //@ pure
  public ChannelException(final Throwable cause){
	  super(cause);
  }
}
