package ie.ucd.sensetile.webservice.dataproducer.smlengine;

import ie.ucd.sensetile.webservice.dataproducer.Sensor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import org.w3c.dom.NodeList;

public class SensorMLSystem {

    private SMLSystem smlsystem;
    private SystemReaderV1 sysReaderv1;
    private DOMHelper domHelper;

    public SensorMLSystem(final InputStream inputxml) {
        initialise(inputxml);
    }

    /**
     * Initialises the SensorMLProcessEngine.
     * @param inputxml
     */
    private void initialise(final InputStream inputxml) {

        try {

            domHelper = new DOMHelper(inputxml, true);
            System.out.println(convertDOMtoString(domHelper));
        } catch (DOMHelperException e1) {
            e1.printStackTrace();
        }

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

    public final List < Sensor > getSensors() {
        List  < DataProcess > processlist = smlsystem.getProcessList();
        SMLSystem dp = null;
        List < DataProcess > sensorList = null;
        Iterator < DataProcess > iter = processlist.iterator();
        while (iter.hasNext()) {
            dp = (SMLSystem) iter.next();
            if (dp.getName().equals("sensorBoardSystem")) {
                sensorList = dp.getProcessList();
            }
        }
        return convertSensorList(sensorList);
    }

    /**
     * Produces a list of Sensor type given a list of DataProcess
     * @param dplist List <DataProcess>
     */
    private List < Sensor > convertSensorList(List <DataProcess> dplist){
        List <Sensor> aplist = new ArrayList <Sensor>();
        DataProcess dp = null;
        Iterator < DataProcess > iter = dplist.iterator();
        while (iter.hasNext()) {
            dp = iter.next();
            aplist.add((Sensor)dp);       
        }
        return aplist;
    }

    public final SMLSystem getSMLSystem(String systemName) {
        List  < DataProcess > processlist = smlsystem.getProcessList();
        SMLSystem dp = null;
        Iterator < DataProcess > iter = processlist.iterator();
        while (iter.hasNext()) {
            dp = (SMLSystem) iter.next();
            if (dp.getName().equals(systemName)) {
                break;
            }
        }
        return dp;
    }

    //public final SensorMLSystem getSensorMLSystem(String systemName) {

     //   return smlsystem.getProcessList();
    //}

    public final DataComponent getSystemOutput(SMLSystem sys) {
        return sys.getOutputList();

    }


    public final List < String > getSystemOutputList(DataComponent output) {
        List < String > outPutNames = new ArrayList < String >();
        int count = output.getComponentCount();
        for (int i = 0; i < count; i++) {
            outPutNames.add(output.getComponent(i).getName());
        }

        return outPutNames;

    }


    public String getDescrption() {
        return convertDOMtoString(domHelper);
    }

    private String convertDOMtoString(DOMHelper domHelper) {
        ByteArrayOutputStream stream =
            new ByteArrayOutputStream();
        try {
            domHelper.serialize(domHelper.getRootElement(), stream, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream.toString();
    }
    
    /**
     * Get xml file name for contained component.
     */
     String getComponentXMLhref(String componentname){
         NodeList nodeList = null;;
         String href = null;
         Element processElt = domHelper.getElement("member/System");
         if (domHelper.existElement(processElt, "components")){
             System.out.println("components "+processElt.getLocalName());
             
             nodeList = domHelper.getElements(processElt, "components/ComponentList/*");
             for (int i = 0; i < nodeList.getLength(); i++)
             {
                 Element processElement = (Element)nodeList.item(i);
                 if (processElement.getAttribute("name").equals(componentname)) {
                     href = processElement.getAttribute("xlink:href");
                 }
             }
         }
         return href;
     }
     

    public String getComponentXML(String componentname) {
         String xmlfile = null;
         String href = getComponentXMLhref(componentname);
         
         InputStream inputstream = this.getClass().getResourceAsStream("/"+href);
         
         try {
            xmlfile = convertDOMtoString(new DOMHelper(inputstream, true));
        } catch (DOMHelperException e) {
            e.printStackTrace();
        }
         System.out.println(xmlfile);
         return xmlfile;
     }
     
     public DOMHelper getDomHelper(){
         return this.domHelper;
     }


}
