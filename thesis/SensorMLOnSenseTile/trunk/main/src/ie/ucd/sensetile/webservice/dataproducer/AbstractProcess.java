package ie.ucd.sensetile.webservice.dataproducer;

import ie.ucd.sensetile.sensorboard.Packet;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.vast.data.DataValue;
import org.vast.process.DataProcess;
import org.vast.process.ProcessException;

enum ProcessType { SENSOR, CONVERTER }

public abstract class AbstractProcess extends DataProcess {

    private DataValue input;
 
    private DataValue output;

    protected static Logger logger =
        Logger.getLogger(AbstractProcess.class);

    private ProcessType processType = ProcessType.SENSOR;

    public AbstractProcess() {
        BasicConfigurator.configure();
    }

    public ProcessType getProcessType() {
        return this.processType;
    }

    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }
    /**
     * * Initialises the process.
     * * Gets handles to input/output components.
     * @throws ProcessException - Vast Library processing exception
     */
    public final void init() throws ProcessException {
        try {
            System.out.println("abstact name ???? " + this.getName());
            // I/O mappings
            input =
                (DataValue) inputData.getComponent(this.getName() + "Input");

            output =
                (DataValue) outputData.getComponent(this.getName() + "Output");

        } catch (ClassCastException e) {
            throw new ProcessException("Invalid I/O data", e);
        }
    }

    protected final double getDoubleInput() {
        return input.getData().getDoubleValue();
    }

    protected final int getIntInput() {
        return input.getData().getIntValue();
    }

    protected final void setDoubleOutput(final double value) {
        output.getData().setDoubleValue(value);
    }

    protected final void setIntOutput(final int value) {
        output.getData().setIntValue(value);
    }

    /**
    * Executes process algorithm on inputs and set output data.
    * @throws ProcessException  - Vast Library processing exception
    */
    public abstract void execute() throws ProcessException;


}
