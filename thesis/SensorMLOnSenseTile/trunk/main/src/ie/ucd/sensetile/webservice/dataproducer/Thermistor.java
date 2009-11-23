package ie.ucd.sensetile.webservice.dataproducer;

import ie.ucd.sensetile.sensorboard.Packet;

import org.vast.process.ProcessException;

public class Thermistor extends AbstractProcess implements Sensor {

    private int sensordatasum = 0;
    private int nosensordata = 0;

    public void execute() throws ProcessException {
        super.logger.info("Thermistor execute");
        this.setIntOutput(sensordatasum / nosensordata);
        sensordatasum = 0;
        nosensordata = 0;
    }

    @Override
    public final void processSensorData(final Packet packet) {
        super.logger.info("Thermistor processSensorData");
        sensordatasum += (int) packet.getTemperature();
        nosensordata++;

    }

}
