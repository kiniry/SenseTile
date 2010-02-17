package ie.ucd.sensetile.sos.dataprovider;

import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.opengis.sensorml._1_0.AbstractProcessType;
import net.opengis.sensorml._1_0.SensorML;
import net.opengis.sensorml._1_0.SystemType;
import net.opengis.sensorml._1_0.SensorML.Member;
import net.opengis.sos._1.ObservationOfferingType;



public class Sensor implements SensorIF{

    private String sensorURN = null;
    private String xmldescription = null;
    private String version = null;
    private String observationTemplate = null;
    private String service = null;
    static final String JAXB_SENSORML_PACKAGE = "net.opengis.sensorml._1_0";
    private ObservationOfferingType observationOffering = null;
    private SensorML sensorml = null;


    List <Observation> observations = new ArrayList <Observation>();
    private SensorML description;
        
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


    Sensor(String sensorURN) {
        this.sensorURN = sensorURN;
    }
    

    public void setxmlDescription(String description) {
        this.xmldescription = description;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setObservationTemplate(String observationTemplate) {
        this.observationTemplate = observationTemplate;
    }

    public void setService(String service) {
        this.service = service;
    }
    
    public String getSensorURN() {
        return sensorURN;
    }

    public void setSensorURN(String sensorURN) {
        this.sensorURN = sensorURN;
    }

    @Override
    public void addObservation(Observation obs) {
        this.observations.add(obs);        
    }
    
    @Override
    public Observation getObservation() {
        // TODO Auto-generated method stub
        return this.observations.get(0);
    }

    public int getNumberOfObservations() {
        // TODO Auto-generated method stub
        return this.observations.size();
    }
    @Override
    public String getXMLDescription() {
        return this.xmldescription;
    }

    @Override
    public String getService() {
        // TODO Auto-generated method stub
        return this.service;
    }

    @Override
    public String getVersion() {
        // TODO Auto-generated method stub
        return this.version;
    }

    @Override
    public String getObservationTemplate() {
        // TODO Auto-generated method stub
        return this.observationTemplate;
    }
    
    public ObservationOfferingType getObservationOffering() {
        return observationOffering;
    }


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


    String getSystemName() {
        List <Member> memberList = this.sensorml.getMember();
        Member member = memberList.get(0);
        JAXBElement<SystemType> smlsystem = (JAXBElement<SystemType>) member.getProcess();
        SystemType systype = smlsystem.getValue();
        System.out.println(systype.getId());
        return systype.getId();
    }

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((sensorURN == null) ? 0 : sensorURN.hashCode());
        return result;
    }



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
