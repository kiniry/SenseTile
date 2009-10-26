package ie.ucd.sensetile.sensorboard.simulator.formal.channel;

/**
 * Represents an interface for Channel implementation.
 * @title         "IChannel"
 * @date          "2009/07/23 11:05:33"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
//@ model import java.io.FileInputStream;
public interface IChannel {

	//@ public model instance String mod_name;
	
	
	
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
	  @ ensures \result != null;
	  @ signals (ChannelException ce) true; 
	  @*/ 
	//@pure
	 int[] processArray()throws ChannelException;

}
