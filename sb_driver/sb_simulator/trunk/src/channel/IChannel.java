package channel;

import java.io.InputStream;
/**
 * Represents an interface for Channel implementation.
 * @title         "IChannel"
 * @date          "2009/07/23 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public interface IChannel {

	//@ public model instance String mod_name;
	
	//@ private invariant mod_name != null; 
	/**
     * Returns  <tt>InputStream</tt> representing the data
     * derived from an input channel.  
     * @return <tt>InputStream</tt> from an input channel.
     * @see <tt>InputStream</tt>
     * @throws an ChannelException. This exception will be thrown
     * when a file with the specified pathname does not exist.
     */
	/*@ ensures \result != null;
	  @ signals (ChannelException ce) true;
	  @*/
	  InputStream getInputStream() throws ChannelException;
  
    /**
     * Returns  int array representing the data
     * derived from an input stream.  
     * @return <tt>int[]</tt> from an input stream.
     * @throws an ChannelException. This exception will be thrown
     * in following cases:
     * a) Cannot create input stream from given pathname.
     * b) The string given from bytes does not contain a parsable integer.
     */
   
	/*@ public behavior
	  @ requires mod_name != null;
	  @ assignable \not_specified;
	  @ ensures \result != null;
	  @ signals (ChannelException ce) true; 
	  @*/ 
	 int[] getArray()throws ChannelException;
    
    /**
     * Returns a relative path and file name.
     * @return relative path and file name.
     */
	  //@ ensures mod_name != null; 
    /*@pure non_null@*/ String getFileName(); 
    
}
