package channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
/**
 * This class provides methods for file handling.
 * to be described.
 * @author dragan
 */
public final class FileChannel implements IChannel {

	//@ spec_public
	private final  transient String a_fileName;//@ in mod_name;
	//@ represents mod_name <- a_fileName;
	
	//@ invariant mod_name != null; 
	
	/**
     * Public default contructor.
     * @param fileName name of file.
     */
	//@ assignable mod_name;
	//@ ensures mod_name == fileName;
	public FileChannel(/*@non_null@*/final String fileName)
	{
		a_fileName = fileName;
	}
	
	/*@pure non_null@*/
	public String getFileName() 
	{
		return a_fileName;
	}

	/**
	 * @see channel.IChannel#getInputStream()
	 */
	public InputStream getInputStream() throws ChannelException 
	{
		InputStream fis;
		try 
		{ 
			fis =  new FileInputStream( a_fileName );
            //@ set g_stream = (InputStream)fis;
			
        } catch ( FileNotFoundException nfe ) 
        {    
            throw new ChannelException("FileChannel is invalid." +
                    "Cannot create an input stream.",nfe);
        }
        return fis;
	}
	 
     /**
      * @see channel.IChannel#getArray()
      */
	 public int[] getArray() throws ChannelException
	  {
		 byte[] bytes = new byte[]{};
		
		 try
		 {	 
			 final File file = new File(a_fileName);
			 final FileInputStream fis = new FileInputStream(file);
			 final int length =  (int)file.length();
		  
			 if(length >= 0)
			 { 
				 bytes = new byte[length];
				 fis.read(bytes);    
			 }
			 fis.close();    
		 }
		 catch (IOException ioe) 
		 {
			 throw new ChannelException("FileChannel is invalid." +
	                    "Cannot create input stream.",ioe);
		 }
		 return convertToInt(bytes);
		 
	  }
	 
	 /*@ensures \result == g_int; 
	   @ also
	   @ signals_only ChannelException;  
	   @*/
	 private /*@non_null*/int[] convertToInt(/*@ non_null*/ final byte[] bytes) throws ChannelException
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
		//@ set g_int = int_array;
		 return int_array;
	 }		 
 }
	 

