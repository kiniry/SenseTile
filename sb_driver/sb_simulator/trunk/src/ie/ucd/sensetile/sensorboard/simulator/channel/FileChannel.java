package ie.ucd.sensetile.sensorboard.simulator.channel;

import java.io.File;
import java.io.FileInputStream;

/**
 * This class provides methods for file handling.
 * @title         "FileChannel"
 * @date          "2009/08/07"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class FileChannel implements IChannel {

	//@ spec_public
	private final   String a_fileName;//@ in mod_name;
	//@ represents mod_name <- a_fileName;
	
	/**
     * Public default contructor.
     * @param fileName name of file.
     */
	//@ requires !fileName.equals("");
	//@ assignable mod_name;
	//@ ensures mod_name == fileName;
	public FileChannel(/*@non_null@*/final String fileName)
	{
		a_fileName = fileName;
	}

     /**
      * @see ie.ucd.sensetile.sensorboard.simulator.channel.IChannel#processArray()
      */
	
	  public /*@pure*/ int[] processArray() throws ChannelException
	  {
		 byte[] bytes = new byte[]{};
		
		 try
		 {	 
			 final File file = new File(a_fileName);
			 
			 final int length =  (int)file.length();
			 final FileInputStream fis = new FileInputStream(file);
             
			 if(length >= 0)
			 { 
				 bytes = new byte[length];
				 fis.read(bytes);    
			 }
			 fis.close();    
		 }
		 catch (Exception e) 
		 {
			 throw new ChannelException(e);
		 }
		 return convertToInt(bytes);
		 
	  }
	 
	 /*@ensures \result != null; 
	   @ assignable \nothing;
	   @signals (ChannelException ce) true;  
	   @*/
	 private /*@pure non_null*/int[] convertToInt(/*@ non_null*/ final byte[] bytes) throws ChannelException
	 {
		 int[] int_array = new int[]{};
		 try{
			  final String[] valueStr = new String(bytes).trim().split("\\s+");
	     
			  final int length =  (int)valueStr.length;		 
			  if( length >= 0 )
			  {
				  int_array = new int[length];
				  int count = 0;
			      
				  while (count >= 0 && count < length ) 
			      {
			    	  final int current = Integer.parseInt(valueStr[count]);
			    	  int_array[count] = current;
			          count++;
			      }
		     }
			  
		    }
		     catch (NumberFormatException nfe)
		     {
			   throw new ChannelException("FileChannel is invalid." +
	                    "The string does not contain a parsable integer.",nfe);
		     }
		 return int_array;
	 }		 
 }
	 

