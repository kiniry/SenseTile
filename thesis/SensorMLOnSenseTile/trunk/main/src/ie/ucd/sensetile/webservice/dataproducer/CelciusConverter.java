package ie.ucd.sensetile.webservice.dataproducer;

import org.vast.process.ProcessException;

public class CelciusConverter extends AbstractProcess
{
    public static double CONVERSIONVALUE = 16.0;
    
    public void execute() throws ProcessException {
        super.logger.info("Thermistor execute");

        double temp = this.getDoubleInput();
        this.setDoubleOutput(temp /CONVERSIONVALUE);
    }

}
