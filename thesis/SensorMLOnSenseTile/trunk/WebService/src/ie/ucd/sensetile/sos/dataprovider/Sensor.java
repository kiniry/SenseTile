package ie.ucd.sensetile.sos.dataprovider;

//import java.io.InputStreamReader;

import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

//import org.vast.ogc.om.ObservationCollection;

//import net.opengis.om._1.ObservationPropertyType;
//import net.opengis.sensorml._1_0.AbstractProcessType;
import net.opengis.sensorml._1_0.IoComponentPropertyType;
import net.opengis.sensorml._1_0.SensorML;
import net.opengis.sensorml._1_0.SystemType;
import net.opengis.sensorml._1_0.SensorML.Member;
//import net.opengis.sos._1.Contents;
import net.opengis.sos._1.InsertObservation;
import net.opengis.sos._1.ObservationOfferingType;


/**
 * Class manages sensor observations for a sensor.
 *
 */
@SuppressWarnings("deprecation")
public class Sensor implements SensorIF {

    /**
     * Sensor SOS identity.
     */
    private String sensorURN = null;
    /**
     * Observation Identity base URN.
     */
    private final String observationIdURN = "obs:sensetile";
    /**
     * Observation Identity base URN.
     */
    private static long observationId=0;
    /**
     * SensorML description of sensor system.
     */
    private String xmldescription = null;
    /**
     * SensorML description of sensor system.
     */   
    private String version = null;
    /**
     * SensorML description of sensor system.
     */
    private String observationTemplate = null;
    /**
     * SensorML description of sensor system.
     */
    private String service = null;
    /**
     * SensorML description of sensor system.
     */
    
    static final String JAXB_SENSORML_PACKAGE = "net.opengis.sensorml._1_0";
    /**
     * SensorML description of sensor system.
     */
    static final String JAXB_OM_PACKAGE = "net.opengis.om._1";
    /**
     * SensorML description of sensor system.
     */
    static final String JAXB_SOS_PACKAGE = "net.opengis.sos._1";
    /**
     * SensorML description of sensor system.
     */
    private ObservationOfferingType observationOffering = null;
    /**
     * SensorML description of sensor system.
     */
    private SensorML sensorml = null;

    /**
     * SensorML description of sensor system.
     */
    private List < Observation > observations = new ArrayList < Observation >();
    /**
     * SensorML description of sensor system.
     */
    private SensorML description;

    /**
     * SensorML description of sensor system.
     */
    public Sensor(String sensorURN, String description, String version,
            String observationTemplate, String service) {
        super();
        this.sensorURN = sensorURN;
        this.xmldescription = description;
        this.version = version;
        this.observationTemplate = observationTemplate;
        this.service = service;
        this.sensorml = readDescription(xmldescription);
    }

    /**
     * SensorML description of sensor system.
     */
    Sensor(String sensorURN) {
        this.sensorURN = sensorURN;
    }

    /**
     * SensorML description of sensor system.
     */
    public final void setxmlDescription(String description) {
        this.xmldescription = description;
    }

    /**
     * SensorML description of sensor system.
     */
    public final void setVersion(String version) {
        this.version = version;
    }

    /**
     * SensorML description of sensor system.
     */
    public final void setObservationTemplate(String observationTemplate) {
        this.observationTemplate = observationTemplate;
    }

    /**
     * SensorML description of sensor system.
     */
    public final void setService(String service) {
        this.service = service;
    }

    /**
     * SensorML description of sensor system.
     */
    public final String getSensorURN() {
        return sensorURN;
    }
    /**
     * SensorML description of sensor system.
     */
    public final void setSensorURN(String sensorURN) {
        this.sensorURN = sensorURN;
    }
    /**
     * SensorML description of sensor system.
     */
    @Override
    public final void addObservation(String obs) {
        InsertObservation observation = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(JAXB_SOS_PACKAGE);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StringBufferInputStream stream = new StringBufferInputStream(obs);
            observation  = (InsertObservation) unmarshaller.unmarshal(stream);
            Observation sensorobs = new Observation();
            //sensorobs.setObs(observation.getObservation(),
            //                 observation.getObservation()., id);

        } catch (JAXBException e) {
           e.printStackTrace();
        }
    }
    /**
     * SensorML description of sensor system.
     */
    @Override
    public final Observation getObservation() {
        // TODO Auto-generated method stub
        return this.observations.get(0);
    }
    /**
     * SensorML description of sensor system.
     */
    public final int getNumberOfObservations() {
        // TODO Auto-generated method stub
        return this.observations.size();
    }
    /**
     * SensorML description of sensor system.
     */
    @Override
    public final String getXMLDescription() {
        return this.xmldescription;
    }
    /**
     * SensorML description of sensor system.
     */
    @Override
    public final String getService() {
        // TODO Auto-generated method stub
        return this.service;
    }
    /**
     * SensorML description of sensor system.
     */
    @Override
    public final String getVersion() {
        // TODO Auto-generated method stub
        return this.version;
    }
    /**
     * SensorML description of sensor system.
     */
    @Override
    public final String getObservationTemplate() {
        // TODO Auto-generated method stub
        return this.observationTemplate;
    }
    /**
     * SensorML description of sensor system.
     */
    public final ObservationOfferingType getObservationOffering() {
        return observationOffering;
    }

    /**
     * SensorML description of sensor system.
     */
    @SuppressWarnings("deprecation")
    private SensorML readDescription(String description) {
        SensorML smlDescription = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(JAXB_SENSORML_PACKAGE);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StringBufferInputStream stream = new StringBufferInputStream(description);
            smlDescription  = (SensorML)unmarshaller.unmarshal(stream);

        } catch (JAXBException e) {
           e.printStackTrace();
        }
        return smlDescription;
    }

    /**
     * SensorML description of sensor system.
     */
    String getSystemName() {
        List <Member> memberList = this.sensorml.getMember();
        Member member = memberList.get(0);
        JAXBElement<SystemType> smlsystem = (JAXBElement<SystemType>) member.getProcess();
        SystemType systype = smlsystem.getValue();
        System.out.println(systype.getId());
        return systype.getId();
    }
    /**
     * SensorML description of sensor system.
     */ 
    public final String getInputName(){
        List <Member> memberList = this.sensorml.getMember();
        Member member = memberList.get(0);
        JAXBElement<SystemType> smlsystem = (JAXBElement<SystemType>) member.getProcess();
        return smlsystem.getValue().getInputs().getInputList().getInput().get(0).getName();
    }
    /**
     * SensorML description of sensor system.
     */
    public final String getOutputDef(){
        List <Member> memberList = this.sensorml.getMember();
        Member member = memberList.get(0);
        JAXBElement<SystemType> smlsystem = (JAXBElement<SystemType>) member.getProcess();
        IoComponentPropertyType ioComp = smlsystem.getValue().getOutputs().getOutputList().getOutput().get(0);
        String definition = null;;
        if (ioComp.isSetCount()){
            definition = ioComp.getCount().getDefinition();
        }
        else if (ioComp.isSetQuantity()){
            definition = ioComp.getQuantity().getDefinition();
        }
        return definition;
    }
    /**
     * hashCode.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((sensorURN == null) ? 0 : sensorURN.hashCode());
        return result;
    }


    /**
     * equals.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sensor other = (Sensor) obj;
        if (sensorURN == null) {
            if (other.sensorURN != null)
                return false;
        } else if (!sensorURN.equals(other.sensorURN))
            return false;
        return true;
    }
}
