package sensor;


import java.util.ArrayList;
import java.util.List;

import channel.ChannelException;
import channel.FileChannel;
import channel.FilePathHelper;
import channel.IChannel;

import sensor.type.SensorType;
 
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
	private final transient /*@ spec_public non_null@*/ List sensors;	
	//@ public model int mod_size;
	//@ represents mod_size \such_that mod_size == sensors.size();
	
	//@ invariant sensors.elementType == \type(ISensor);
	//@ invariant !sensors.containsNull;
	
	//@ constraint mod_size <= 8;
	
	//@ assignable sensors;
	public SensorBuilder()
	{
		 sensors = new ArrayList();
		//@ set sensors.elementType = \type(ISensor);
		//@ set sensors.containsNull = false;
	}
	
	//@ requires mod_size == 0;
	//@ assignable \not_specified;
	//@ ensures mod_size == 1;
	 public void initialize()
	 {
	 	createThermistorSensor();
		createLightSensor();
		createSoundSensor();
		createPressureSensor();
		createUltrasonicSensor();
		createAxesXSensor();
		createAxesYSensor();
		createAxesZSensor();
	  }
	
	/*@ requires mod_size == 0;
	  @ assignable \not_specified;
	  @ ensures mod_size == \old(mod_size) +1;
	  @*/
	private void createThermistorSensor()
	{
		final IChannel therm_channel = 
			new FileChannel(FilePathHelper.THERM_FILE_PATH);
		ISensor sensor;
		try
		{
			final int[] arr = therm_channel.getArray();
			
			sensor = new ThermistorSensor( arr );
		}
		catch (ChannelException cex)
		{
			final int[] arr = new int[]{};
			sensor = new ThermistorSensor( arr );
			sensor.setEnable(false);
		}
		sensors.add( sensor );
	}
	
	//@ requires mod_size == 1;
	//@ assignable \not_specified;
	//@ ensures mod_size == \old(mod_size) +1;
	private void createLightSensor()
	{
		final IChannel light_channel = 
			new FileChannel(FilePathHelper.LIGHT_FILE_PATH);
		ISensor sensor;
		try
		{
			final int[] arr = light_channel.getArray();
			sensor = new LightSensor( arr );
		}
		catch (ChannelException cex)
		{
			final int[] arr = new int[]{};
			sensor = new LightSensor( arr );
			sensor.setEnable(false);
		}
		sensors.add( sensor );
	}
	//@ requires mod_size == 2;
	//@ assignable \not_specified;
	//@ ensures mod_size == \old(mod_size) +1;
	private void createSoundSensor()
	{
		final IChannel sound_channel = 
			new FileChannel(FilePathHelper.AUDIO_FILE_PATH);
		ISensor sensor;
		try
		{
			final int[] arr = sound_channel.getArray();
			sensor = new SoundSensor( arr );
		}
		catch (ChannelException cex)
		{
			final int[] arr = new int[]{};
			sensor = new SoundSensor( arr );
			sensor.setEnable(false);
		}
		sensors.add( sensor );
	}
	//@ requires mod_size == 3;
	//@ assignable \not_specified;
	//@ ensures mod_size == \old(mod_size) +1;
	private void createPressureSensor()
	{
		final IChannel press_channel = 
			new FileChannel(FilePathHelper.PRESS_FILE_PATH);
		ISensor sensor;
		try
		{
			final int[] arr = press_channel.getArray();
			sensor = new PressureSensor( arr );
		}
		catch (ChannelException cex)
		{
			final int[] arr = new int[]{};
			sensor = new PressureSensor( arr );
			sensor.setEnable(false);
		}
		sensors.add( sensor );
	}
	
	//@ requires mod_size == 4;
	//@ assignable \not_specified;
	//@ ensures mod_size == \old(mod_size) +1;
	private void createUltrasonicSensor()
	{
		final IChannel ultra_channel = 
			new FileChannel(FilePathHelper.ULTRA_FILE_PATH);
		ISensor sensor;
		try
		{
			final int[] arr = ultra_channel.getArray();
			sensor = new UltrasonicSensor( arr );
		}
		catch (ChannelException cex)
		{
			final int[] arr = new int[]{};
			sensor = new UltrasonicSensor( arr );
			sensor.setEnable(false);
		}
		sensors.add( sensor );
	}
	
	//@ requires mod_size == 5;
	//@ assignable \not_specified;
	//@ ensures mod_size == \old(mod_size) +1;
	private void createAxesXSensor()
	{
		final IChannel accel_channel = 
			new FileChannel(FilePathHelper.ACCEL_X_FILE_PATH);
		ISensor sensor;
		try
		{
			final int[] arr = accel_channel.getArray();
			sensor = new AxisAccelerometerSensor( arr , SensorType.ACCEL_X );
		}
		catch (ChannelException cex)
		{
			final int[] arr = new int[]{};
			sensor = new AxisAccelerometerSensor( arr , SensorType.ACCEL_X );
			sensor.setEnable(false);
		}
		sensors.add( sensor );
	}
	
	//@ requires mod_size == 6;
	//@ assignable \not_specified;
	//@ ensures mod_size == \old(mod_size) +1;
	private void createAxesYSensor()
	{
		final IChannel accel_channel = 
			new FileChannel(FilePathHelper.ACCEL_Y_FILE_PATH);
		ISensor sensor;
		try
		{
			final int[] arr = accel_channel.getArray();
			sensor = new AxisAccelerometerSensor( arr , SensorType.ACCEL_Y );
		}
		catch (ChannelException cex)
		{
			final int[] arr = new int[]{};
			sensor = new AxisAccelerometerSensor( arr , SensorType.ACCEL_Y );
			sensor.setEnable(false);
		}
		sensors.add( sensor );
	}
	
	//@ requires mod_size == 7;
	//@ assignable \not_specified;
	//@ ensures mod_size == \old(mod_size) +1;
	private void createAxesZSensor()
	{
		final IChannel accel_channel = 
			new FileChannel(FilePathHelper.ACCEL_Z_FILE_PATH);
		ISensor sensor;
		try
		{
			final int[] arr = accel_channel.getArray();
			sensor = new AxisAccelerometerSensor( arr , SensorType.ACCEL_Z );
		}
		catch (ChannelException cex)
		{
			final int[] arr = new int[]{};
			sensor = new AxisAccelerometerSensor( arr , SensorType.ACCEL_Z );
			sensor.setEnable(false);
		}
		sensors.add( sensor );
	}
	
	
	//@ requires mod_size == 8;
	//@ requires typeIndex >=0 && typeIndex <=7;
	//@ requires SensorType.legal_SensorType(typeIndex);
	//@ ensures \result instanceof ISensor;
	public ISensor getSensor(final int typeIndex) 
	{
		return (ISensor)sensors.get(typeIndex);
	}
	  
}
