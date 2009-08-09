package sensor;


import java.util.ArrayList;
import java.util.List;

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
	 
	 //@ ghost int[] g_arr;
	 //@ ghost ISensor g_sensor;
	 
	/*@ requires mod_size == 0;
	  @ assignable \not_specified;
	  @ ensures mod_size == \old(mod_size) +1;
	  @ ensures (g_arr.length == 0 <==> !g_sensor.isEnabled());
	  @*/
	private void createThermistorSensor()
	{
		final IChannel therm_channel = 
			new FileChannel(FilePathHelper.THERM_FILE_PATH);
		ISensor sensor;
		int[] arr = new int[]{};
		try
		{
			arr = therm_channel.processArray();	
		}
		finally 
		{
			//@ set g_arr = arr;
			sensor = new ThermistorSensor( arr );
			//@ set g_sensor = sensor;
			if(arr.length == 0)
			{
				sensor.setEnable(false);
				//@ assert g_sensor.mod_enabled == false; 
			}
			//@ assert (g_arr.length == 0 <==> g_sensor.mod_enabled == false);
			sensors.add( sensor );
		}
	}
	
  /*@ requires mod_size == 1;
	@ assignable \not_specified;
	@ ensures mod_size == \old(mod_size) +1;
	@ ensures (g_arr.length == 0 <==> g_sensor.mod_enabled == false);
	@*/
	private void createLightSensor()
	{
		final IChannel light_channel = 
			new FileChannel(FilePathHelper.LIGHT_FILE_PATH);
		ISensor sensor;
		int[] arr = new int[]{};
		try
		{
			arr = light_channel.processArray();
		}
		finally 
		{
			//@ set g_arr = arr;
			sensor = new LightSensor( arr );
			//@ set g_sensor = sensor;
			
			if(arr.length == 0)
			{
				sensor.setEnable(false);
				//@ assert g_sensor.mod_enabled == false; 
			}
			//@ assert (g_arr.length == 0 <==> g_sensor.mod_enabled == false);
			sensors.add( sensor );
		}
	}
	/*@ requires mod_size == 2;
	  @ assignable \not_specified;
	  @ ensures mod_size == \old(mod_size) +1;
	  @ ensures (g_arr.length == 0 <==> g_sensor.mod_enabled == false);
	  @*/
	private void createSoundSensor()
	{
		final IChannel sound_channel = 
			new FileChannel(FilePathHelper.AUDIO_FILE_PATH);
		ISensor sensor;
		int[] arr = new int[]{};
		try
		{
			arr = sound_channel.processArray();
		}
		finally 
		{
			//@ set g_arr = arr;
			sensor = new SoundSensor( arr );
			//@ set g_sensor = sensor;
			
			if(arr.length == 0)
			{
				sensor.setEnable(false);
				//@ assert g_sensor.mod_enabled == false; 
			}
			//@ assert (g_arr.length == 0 <==> g_sensor.mod_enabled == false);
			sensors.add( sensor );
		}
		
	}
	/*@ requires mod_size == 3;
	  @ assignable \not_specified;
	  @ ensures mod_size == \old(mod_size) +1;
	  @ ensures (g_arr.length == 0 <==> g_sensor.mod_enabled == false);
	  @*/
	private void createPressureSensor()
	{
		final IChannel press_channel = 
			new FileChannel(FilePathHelper.PRESS_FILE_PATH);
		ISensor sensor;
		int[] arr = new int[]{};
		try
		{
			arr = press_channel.processArray();
		}
		finally 
		{
			//@ set g_arr = arr;
			sensor = new PressureSensor( arr );
			//@ set g_sensor = sensor;
			
			if(arr.length == 0)
			{
				sensor.setEnable(false);
				//@ assert g_sensor.mod_enabled == false; 
			}
			//@ assert (g_arr.length == 0 <==> g_sensor.mod_enabled == false);
			sensors.add( sensor );
		}
		
	}
	
	/*@ requires mod_size == 4;
	  @ assignable \not_specified;
	  @ ensures mod_size == \old(mod_size) +1;
	  @ ensures (g_arr.length == 0 <==> g_sensor.mod_enabled == false);
	  @*/
	private void createUltrasonicSensor()
	{
		final IChannel ultra_channel = 
			new FileChannel(FilePathHelper.ULTRA_FILE_PATH);
		ISensor sensor;
		int[] arr = new int[]{};
		try
		{
			arr = ultra_channel.processArray();
		}
		finally 
		{
			//@ set g_arr = arr;
			sensor = new UltrasonicSensor( arr );
			//@ set g_sensor = sensor;
			
			if(arr.length == 0)
			{
				sensor.setEnable(false);
				//@ assert g_sensor.mod_enabled == false; 
			}
			//@ assert (g_arr.length == 0 <==> g_sensor.mod_enabled == false);
			sensors.add( sensor );
		}
	}
	
	/*@ requires mod_size == 5;
	  @ assignable \not_specified;
	  @ ensures mod_size == \old(mod_size) +1;
	  @ ensures (g_arr.length == 0 <==> g_sensor.mod_enabled == false);
	  @*/
	private void createAxesXSensor()
	{
		final IChannel accel_channel = 
			new FileChannel(FilePathHelper.ACCEL_X_FILE_PATH);
		ISensor sensor;
		int[] arr = new int[]{};
		try
		{
			arr = accel_channel.processArray();
			
		}
		finally 
		{
			//@ set g_arr = arr;
			sensor = new AxisAccelerometerSensor( arr , SensorType.ACCEL_X );
			//@ assert SensorType.isValidAxis(SensorType.ACCEL_X);
			
			//@ set g_sensor = sensor;
			
			if(arr.length == 0)
			{
				sensor.setEnable(false);
				//@assert g_sensor.mod_enabled == false; 
			}
			//assert (g_arr.length == 0 <==> g_sensor.mod_enabled == false);
			sensors.add( sensor );
		}
	}
	
	/*@ requires mod_size == 6;
	  @ assignable \not_specified;
	  @ ensures mod_size == \old(mod_size) +1;
	  @ ensures (g_arr.length == 0 <==> g_sensor.mod_enabled == false);
	  @*/
	private void createAxesYSensor()
	{
		final IChannel accel_channel = 
			new FileChannel(FilePathHelper.ACCEL_Y_FILE_PATH);
		ISensor sensor;
		int[] arr = new int[]{};
		try
		{
			arr = accel_channel.processArray();
			
		}
		finally 
		{
			//@ set g_arr = arr;
			sensor = new AxisAccelerometerSensor( arr , SensorType.ACCEL_Y );
			//@ assert SensorType.isValidAxis(SensorType.ACCEL_Y);
			//@ set g_sensor = sensor;
			
			if(arr.length == 0)
			{
				sensor.setEnable(false);
				//@ assert g_sensor.mod_enabled == false; 
			}
			//@ assert (g_arr.length == 0 <==> g_sensor.mod_enabled == false);
			sensors.add( sensor );
		}
	}
	
	/*@ requires mod_size == 7;
	  @ assignable \not_specified;
	  @ ensures mod_size == \old(mod_size) +1;
	  @ ensures (g_arr.length == 0 <==> g_sensor.mod_enabled == false);
	  @*/
	private void createAxesZSensor()
	{
		final IChannel accel_channel = 
			new FileChannel(FilePathHelper.ACCEL_Z_FILE_PATH);
		ISensor sensor;
		int[] arr = new int[]{};
		try
		{
			arr = accel_channel.processArray();
			
		}
		finally 
		{
			//@ set g_arr = arr;
			sensor = new AxisAccelerometerSensor( arr , SensorType.ACCEL_Z );
			//@ assert SensorType.isValidAxis(SensorType.ACCEL_Z);
			//@ set g_sensor = sensor;
			
			if(arr.length == 0)
			{
				sensor.setEnable(false);
				//@ assert g_sensor.mod_enabled == false; 
			}
			//@ assert (g_arr.length == 0 <==> g_sensor.mod_enabled == false);
			sensors.add( sensor );
		}
	}
	
	/*@ requires mod_size == 8;
	  @ requires typeIndex >=0 && typeIndex <=7;
	  @ requires SensorType.legal_SensorType(typeIndex);
	  @ ensures \result instanceof ISensor;
	  @*/
	public ISensor getSensor(final int typeIndex) 
	{
		return (ISensor)sensors.get(typeIndex);
	}
	  
}
