package ie.ucd.sensetile.webservice.dataproducer;

import ie.ucd.sensetile.sensorboard.Packet;

import org.vast.process.ProcessException;

/**
 *sum of sensor data readings received.
 */
public class ThermistorSensor extends AbstractProcess implements Sensor {

    /**
     *sum of sensor data readings received.
     */
    private int sensordatasum = 0;

    /**
     *number of sensor data readings received.
     */
    private int nosensordata = 0;

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
        this.setIntOutput(sensordatasum / nosensordata);
        sensordatasum = 0;
        nosensordata = 0;
    }

    /**
     *sum of sensor data readings received.
     *@param packet Packet
     */
    @Override
    public final void processSensorData(final Packet packet) {
        AbstractProcess.logger.info("Thermistor processSensorData");
        sensordatasum += (int) packet.getTemperature();
        nosensordata++;
    }
}
