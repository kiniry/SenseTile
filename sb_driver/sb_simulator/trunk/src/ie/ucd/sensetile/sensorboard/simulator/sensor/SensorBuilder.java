package ie.ucd.sensetile.sensorboard.simulator.sensor;

import ie.ucd.sensetile.sensorboard.simulator.channel.ChannelException;
import ie.ucd.sensetile.sensorboard.simulator.channel.FileChannel;
import ie.ucd.sensetile.sensorboard.simulator.channel.FilePathProvider;
import ie.ucd.sensetile.sensorboard.simulator.channel.IChannel;
import ie.ucd.sensetile.sensorboard.simulator.sensor.type.SensorIndexer;

 
/**
 * Represents an sensor builder implementation. 
 * @title         "SensorBuilder"
 * @date          "2009/08/07"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class SensorBuilder 
{
	//@ spec_public non_null
	  private  final ISensor[] sensors = new ISensor[6];
	
	//@ spec_public non_null
	  private FilePathProvider provider;
	  
	  
	//@ public ghost boolean g_inv;
	//@ invariant g_inv ==> \nonnullelements(sensors);
	  /**
	   * Main constructor for all sensors on sensor board.
	   */
	  
	/*@ assignable g_inv, provider,sensors[*];
      @ ensures provider == theProvider;
      @ ensures g_inv;
	  @*/
    public SensorBuilder(final /*@non_null@*/ FilePathProvider theProvider)
    {
    	//@ set g_inv = false;
    	provider = theProvider;
    	sensors[0]= createThermistorSensor();
    	sensors[1] =createLightSensor();
    	sensors[2] =createPressureSensor();
    	sensors[3]=createAxesSensor(3);
    	sensors[4]=createAxesSensor(4);
    	sensors[5]=createAxesSensor(5);
    	//@ set g_inv = true;
	}
	  
    //@ ensures \fresh(\result);
	private /*@pure non_null@*/ ISensor createThermistorSensor()
	{
		ISensor sensor;
		int[] arr = new int[]{};
		final String path = provider.getSensPath(SensorIndexer.THERM);
		if( path != null && !path.equals("") ) 
		{
			final IChannel channel = new FileChannel(path);
			try
			{
				arr = channel.processArray();	
			}
			catch (ChannelException ce ) 
			{
				sensor = new ThermistorSensor( arr );
			
			}
		}
		sensor = new ThermistorSensor( arr );
		
		return sensor;
	}
	//@ ensures \fresh(\result);
	private /*@pure  non_null@*/ ISensor createLightSensor()
	{
        ISensor sensor;	
		int[] arr = new int[]{};
		
		final String path = provider.getSensPath(SensorIndexer.LIGHT);
		if( path != null && !path.equals("") ) 
		{
			final IChannel channel = new FileChannel(path);
			try
			{
				arr = channel.processArray();	
			}
			catch (ChannelException ce ) 
			{
				sensor = new LightSensor( arr );
			
			}
		}
		sensor = new LightSensor( arr );
		
		return sensor;
	}
	//@ ensures \fresh(\result);
	private /*@pure  non_null@*/ ISensor createPressureSensor() 
	{
        ISensor sensor;	
		int[] arr = new int[]{};
		final String path = provider.getSensPath(SensorIndexer.PRESS);
		if( path != null && !path.equals("") ) 
		{
			final IChannel channel = new FileChannel(path);
			try
			{
				arr = channel.processArray();	
			}
			catch (ChannelException ce ) 
			{
				sensor = new PressureSensor( arr );	
			}
		}
		sensor = new PressureSensor( arr );
		
		return sensor;
	}
	//@ requires index >=3 && index <= 5;
	//@ ensures \fresh(\result);
	private /*@pure  non_null@*/ ISensor createAxesSensor(final int index)
	{
        ISensor sensor;
		int[] arr = new int[]{};
		final String path = provider.getSensPath(index);
		if( path != null && !path.equals("") ) 
		{
			final IChannel channel = new FileChannel(path);
			try
			{
				arr = channel.processArray();	
			}
			catch (ChannelException ce ) 
			{
				sensor = new AxisAccelerometerSensor( arr );
			}
		}
		sensor = new AxisAccelerometerSensor( arr );
		
		return sensor;
	}
	
	//@ ensures \result == sensors.length;
	public /*@pure*/ int getSensorsLength() 
	{
		return sensors.length;
	}
	

	/**
	 * Returns an sensor instance in enabled (desabled) state.
	 * @param typeIndex represents index of sensor type.
	 * @return an sensor instance.
	 */
	/*@ requires g_inv;
	  @ requires typeIndex >= 0 && typeIndex < sensors.length;
	  @ requires SensorIndexer.legal_SensorIndex(typeIndex);
	  @ ensures \result == sensors[typeIndex];
	  @*/
	public/*@pure non_null@*/ ISensor getSensor(final int typeIndex) 
	{
		return sensors[typeIndex];
	}
}
