package ie.ucd.sensetile.webservice.dataproducer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.vast.cdm.common.DataComponent;
import org.vast.data.DataBlockInt;
import org.vast.process.DataProcess;
import org.vast.process.ProcessException;
import org.vast.sensorML.SMLException;
import org.vast.sensorML.SystemReaderV1;
import org.vast.sensorML.system.SMLSystem;
import org.vast.xml.DOMHelper;
import org.vast.xml.DOMHelperException;
import org.vast.xml.XMLDocument;
import org.w3c.dom.Element;

public class SensorMLProcessEngine extends SMLSystem {

    private SMLSystem smlsystem;
    private SystemReaderV1 sysReaderv1;
    private DOMHelper domHelper;

    SensorMLProcessEngine(final InputStream inputxml) {
        super();
        initialise(inputxml);
    }

    /**
     * Initialises the SensorMLProcessEngine.
     * @param inputxml
     */
    private void initialise(final InputStream inputxml) {

        try {

            domHelper = new DOMHelper(inputxml, true);
        } catch (DOMHelperException e1) {
            e1.printStackTrace();
        }

        smlsystem = new SMLSystem();
        sysReaderv1 = new SystemReaderV1();
        Element processElt = domHelper.getElement("member/*");
        try {
            smlsystem = sysReaderv1.readSystem(domHelper, processElt);
            smlsystem.init();
         } catch (SMLException e) {
            e.printStackTrace();
         } catch (ProcessException e) {
            e.printStackTrace();
         }
    }

    public void execute(){
        try {
            smlsystem.execute();
        } catch (ProcessException e) {
            e.printStackTrace();
        }
    }

    private DataComponent getInput(final String inputname) {
       return smlsystem.getInputList().getComponent(inputname);
    }


    public void setInput(final String inputname, final DataBlockInt data) {
        getInput(inputname).setData(data);
    }

    public double getOutput(final String outputname) {
       return smlsystem.getOutputList().getComponent(outputname)
       .getData().getDoubleValue();
    }

    public double getIntOutput(final String outputname) {
        return smlsystem.getOutputList().getComponent(outputname)
        .getData().getIntValue();
     }
    public List<DataProcess >getSensors(){
        List<DataProcess> processlist = smlsystem.getProcessList();
        SMLSystem dp = null;
        List<DataProcess> sensorList = null;
        Iterator<DataProcess> iter = processlist.iterator();
        while (iter.hasNext()) {
            dp = (SMLSystem)iter.next();            
            if (dp.getName().equals("sensorBoardSystem")) {
                sensorList = dp.getProcessList();
            }
        }
        return sensorList;
    }
}
