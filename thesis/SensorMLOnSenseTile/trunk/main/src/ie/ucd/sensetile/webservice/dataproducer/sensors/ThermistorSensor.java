package ie.ucd.sensetile.webservice.dataproducer.sensors;

import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.webservice.dataproducer.Sensor;
import ie.ucd.sensetile.webservice.dataproducer.smlengine.AbstractProcess;

import org.vast.process.ProcessException;

/**
 *Stores sum of sensor data readings received until requested to
 *process the sensor data.
 */
public class ThermistorSensor extends AbstractProcess implements Sensor {

    /**
     *sum of sensor data readings received.
     */
    private int sensorDataSum = 0;

    /**
     *number of sensor data readings received.
     */
    private int numSensorData = 0;

    /**
     *set type at construction.
     */
    public ThermistorSensor() {
        super();
    }

    /**
     * process sensor data and output.
     * @throws ProcessException - Vast exception
     */
    public final void execute() throws ProcessException {
        AbstractProcess.logger.info("Thermistor execute");
        this.setIntOutput(sensorDataSum / numSensorData);
        sensorDataSum = 0;
        numSensorData = 0;
    }

    /**
     *sum of sensor data readings received.
     *@param packet Packet
     */
    @Override
    public final void processSensorData(final Packet packet) {
        AbstractProcess.logger.info("Thermistor processSensorData");
        sensorDataSum += (int) packet.getTemperature();
        numSensorData++;
    }
}
