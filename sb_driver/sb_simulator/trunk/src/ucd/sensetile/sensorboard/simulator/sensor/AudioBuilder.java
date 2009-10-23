package ie.ucd.sensetile.sensorboard.simulator.sensor;

import ie.ucd.sensetile.sensorboard.simulator.channel.ChannelException;
import ie.ucd.sensetile.sensorboard.simulator.channel.FileChannel;
import ie.ucd.sensetile.sensorboard.simulator.channel.FilePathProvider;
import ie.ucd.sensetile.sensorboard.simulator.channel.IChannel;
 
/**
 * Represents an sensor builder implementation. 
 * @title         "SensorBuilder"
 * @date          "2009/08/07"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
//@ model import ie.ucd.sensetile.sensorboard.simulator.sensor.type.SensorIndexer;
public final class AudioBuilder 
{
	//@ spec_public
	private static final int LENGTH = 4;
	//@ spec_public non_null
	  private  final ISensor[] audioArray = new ISensor[LENGTH];
	//@ spec_public non_null
	  private FilePathProvider provider;
	 
	//@ public ghost boolean inv = false;
	  
	//@ invariant inv ==> \nonnullelements(audioArray);
	 
	 //@ invariant audioArray.length == 4;
	  
	/*@ requires theFrequency == SensorIndexer.AUDIO_FREQUENCY_48KHZ ||
	  @ 		 theFrequency != SensorIndexer.AUDIO_FREQUENCY_48KHZ;
	  @ assignable provider,audioArray[*];
	  @ assignable inv;
	  @ ensures inv == true;
      @ ensures provider == theProvider;
      @ ensures audioArray!= null && audioArray.length==4;
      @ ensures  \nonnullelements(audioArray);
	  @*/
    public AudioBuilder(final int theFrequency, final /*@non_null@*/ FilePathProvider theProvider)
    {
    	//@ set inv = false;
    	provider = theProvider;
   	
    	if(theFrequency == 48)
    	{
    		audioArray[0] = createSoundSensor(0);
    		audioArray[1] = createSoundSensor(1);
    		audioArray[2] = createSoundSensor(2);
    		audioArray[3] = createSoundSensor(3);
		 
    	}
    	else
    	{
    		audioArray[0] = createUltrasonicSensor(0);	
    		audioArray[1] = createUltrasonicSensor(1);
   		 	audioArray[2] = createUltrasonicSensor(2);
   		 	audioArray[3] = createUltrasonicSensor(3);	
    	}
    	//@ set inv = true;
	}
    
    //@ requires  index >=0 && index < audioArray.length;
	//@ ensures \result != null && \result instanceof ISensor;
    private /*@pure  non_null@*/ISensor createSoundSensor( final int index)
	{
		ISensor sensor;
		int[] arr = new int[]{};
		
		final String path  = provider.getSoundPath(index);
	
		if( path != null && !path.equals("") ) 
		{
			final IChannel therm_channel = new FileChannel(path);
			try
			{
				arr = therm_channel.processArray();	
			}
			catch (ChannelException ce ) 
			{
				sensor = new SoundSensor( arr );
			
			}
		}
		sensor = new SoundSensor( arr );
		
		return sensor;
	}
	
	
	//@ requires index >=0 && index < audioArray.length;
    //@ ensures \result != null && \result instanceof ISensor;
    private /*@pure non_null@*/ISensor createUltrasonicSensor(final int index)
	{
    	ISensor sensor;
		int[] arr = new int[]{};
		
		final String path = provider.getUltraSoundPath(index);	
		
		if( path != null && !path.equals("") ) 
		{
			final IChannel channel = new FileChannel(path);
			try
			{
				arr = channel.processArray();	
			}
			catch (ChannelException ce ) 
			{
				sensor = new UltrasonicSensor( arr );
			
			}
		}
		sensor = new UltrasonicSensor( arr );
		
		return sensor;
	}

	
	//@ ensures \result == audioArray.length;
	public /*@pure*/ int getAudioArrayLength() 
	{
		return audioArray.length;
	}
		
	/**
	 * Returns an audio sensor instance in enabled (desabled) state.
	 * @param typeIndex represents index of sensor type.
	 * @return an sensor instance.
	 */
	/*@ requires inv;
	  @ requires typeIndex >= 0 && typeIndex < audioArray.length;
	  @ ensures \result == audioArray[typeIndex];
	  @ ensures \result instanceof ISensor;
	  @*/
	public/*@pure non_null@*/ ISensor getAudio(final int typeIndex) 
	{
		return audioArray[typeIndex];
	}
}
