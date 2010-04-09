package ie.ucd.sensetile.webservice.dataproducer.converters;

import ie.ucd.sensetile.webservice.dataproducer.smlengine.AbstractProcess;

import org.vast.process.ProcessException;

/**
 * converts sensor data to a celcius value.
 */
public class CelciusConverter extends AbstractProcess {
    /**
     * conversion value need to divide by to get a celcius value.
     */
    private static double CONVERSIONVALUE = 16.0;

    /**
     *set type at construction.
     */
    public CelciusConverter() {
        super();
    }

    /**
     * process sensor data and output.
     * @throws ProcessException - Vast exception
     */
    public final void execute() throws ProcessException {
        super.logger.info("CelciusConveter execute");

        double temp = this.getIntInput();
        this.setDoubleOutput(temp / CONVERSIONVALUE);
    }

}
