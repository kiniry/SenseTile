package sensor;

import java.util.ArrayList;
import java.util.List;

public class SensorBuilder {
 
	private transient final/*@spec_public non_null@*/ List sensorList;
	
	   //@ invariant sensorList.elementType == \type( ISensor );
	
	public SensorBuilder()
	{
		sensorList = new ArrayList();
		//@ set sensorList.elementType = \type( ISensor );
		
		
//		
//		arr = new int[]{500,1000};
//		sensor = new LightSensor(arr);
//		sensorList.add(sensor);
//		
//		arr = new int[]{25000,26000};
//		sensor = new PressureSensor(arr);
//		sensorList.add(sensor);
//		
//		arr = new int[]{-36,-38 };
//		sensor = new SoundSensor(arr);
//		sensorList.add(sensor);
//		
//		arr = new int[]{-44,-45 };
//		sensor = new UltrasonicSensor(arr);
//		sensorList.add(sensor);
//		
//		arr = new int[]{15000,15000 };
//		sensor = new AxisAccelerometerSensor(arr, AxeType.X_AXE);
//		sensorList.add(sensor);
//		
//		arr = new int[]{15000,15000 };
//		sensor = new AxisAccelerometerSensor(arr, AxeType.Y_AXE );
//		sensorList.add(sensor);
//		
//		arr = new int[]{15000,15000 };
//		sensor = new AxisAccelerometerSensor(arr, AxeType.Z_AXE );
//		sensorList.add(sensor);
	}
	
	public ISensor getSensor( final int index )
	{
		/*
		final ISensor result;
		
		int[] arr = new int[]{25,25};
		
		result = new ThermistorSensor(arr);		
		sensorList.add(result);
		*/
		return null;
		
	}
	
}
