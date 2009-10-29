package test;

import org.vast.data.DataValue;
import org.vast.process.DataProcess;
import org.vast.process.ProcessException;

public class TxPacketTemperatureToCelsius extends DataProcess
{
	DataValue inputTemp;

	DataValue outputTemp;
	public TxPacketTemperatureToCelsius() {}
	/**
	 * Initializes the process
	 * Gets handles to input/output components
	 */
	public void init() throws ProcessException
	{
		try
		{
			// I/O mappings
			inputTemp = (DataValue) inputData.getComponent("txPacketTemperature");
			outputTemp = (DataValue) outputData.getComponent("temperature");
		}
		catch (ClassCastException e)
		{
			throw new ProcessException("Invalid I/O data", e);
		}
	}
    /**
    * Executes process algorithm on inputs and set output data
    */
	public void execute() throws ProcessException
	{
		double T = inputTemp.getData().getDoubleValue();

		double Tc = 100 * T;
		outputTemp.getData().setDoubleValue(Tc);
	}
}
