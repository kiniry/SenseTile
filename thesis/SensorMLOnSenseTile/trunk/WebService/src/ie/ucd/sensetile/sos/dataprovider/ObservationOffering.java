package ie.ucd.sensetile.sos.dataprovider;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
//import javax.xml.bind.PropertyException;
import javax.xml.namespace.QName;

import net.opengis.gml.v_3_1_1.CodeType;
import net.opengis.gml.v_3_1_1.ReferenceType;
//import net.opengis.gml.v_3_1_1.StringOrRefType;
import net.opengis.ows._1.LanguageStringType;
//import net.opengis.ows._1.ObjectFactory;
import net.opengis.ows._1.ServiceIdentification;
import net.opengis.ows._1.ServiceProvider;
//import net.opengis.sensorml._1_0.SystemType;
import net.opengis.sos._1.Capabilities;
import net.opengis.sos._1.Contents;
import net.opengis.sos._1.ObservationOfferingType;
import net.opengis.swe._1_0.PhenomenonPropertyType;
//import net.opengis.swe._1_0.PhenomenonType;

/**
 * Manages SOS Observation Offerings.
 */
public class ObservationOffering {
    
    private Capabilities capabilities= null;
    private String offeringXML = null;
    static final String OFFERID ="offering-";
    static final String OFFERURN ="urn:SenseTile:offering:";
    private Map <String, ObservationOfferingType> offerings= new HashMap <String, ObservationOfferingType>();

    private static long offeringId = 0;


    ObservationOffering(){
        createCapability();
    }

    public String getCapabilitiesXml() {
        return offeringXML;
    }

    private void createCapability() {
        net.opengis.sos._1.ObjectFactory objFactorySOS = new net.opengis.sos._1.ObjectFactory();
        capabilities = objFactorySOS.createCapabilities();
        net.opengis.ows._1.ObjectFactory objFactoryOWS = new net.opengis.ows._1.ObjectFactory();
        ServiceIdentification serviceId = objFactoryOWS.createServiceIdentification();

        LanguageStringType title = new LanguageStringType();
        title.setValue("SenseTile SOS");
        serviceId.getTitle().add(title);

        capabilities.setServiceIdentification(serviceId);

        ServiceProvider serviceProvider = objFactoryOWS.createServiceProvider();
        Contents contents =  objFactorySOS.createContents();
        capabilities.setContents(contents);
        Contents.ObservationOfferingList obsOfferList = new Contents.ObservationOfferingList();
        capabilities.getContents().setObservationOfferingList(obsOfferList);

    }



    public void addOffering(Sensor sensor){
        
        if (!this.offerings.containsKey(sensor.getSystemName())) {
            net.opengis.sos._1.ObjectFactory objFactorySOS = new net.opengis.sos._1.ObjectFactory();
            ObservationOfferingType obsType = objFactorySOS.createObservationOfferingType();
            CodeType codeType = new CodeType();
            codeType.setValue(OFFERURN+offeringId);

            JAXBElement<CodeType> jaxbEl = new JAXBElement<CodeType>(new QName("http://www.opengis.net/gml","name"),CodeType.class, codeType);
            obsType.getName().add(jaxbEl);
            obsType.setId(OFFERID+offeringId);        
            ReferenceType refType = new ReferenceType();

            refType.setHref(sensor.getSensorURN());
            obsType.getProcedure().add(refType);

             PhenomenonPropertyType phenType = new PhenomenonPropertyType();

             phenType.setHref(sensor.getOutputDef());
             obsType.getObservedProperty().add(phenType);

             capabilities.getContents().getObservationOfferingList().getObservationOffering().add(obsType);
        
             this.offerings.put(sensor.getSystemName(), obsType);
        }
        else {
            addProcedure(sensor);
        }
        
        offeringId++;
        offeringXML = marshalCapabilities();
        System.out.println("OFFERING - " +offeringXML);

    }

    private String marshalCapabilities() {
        System.out.println("marshall");

        Marshaller marshaller = null;
        ByteArrayOutputStream stream = null;
        JAXBContext jaxbContext = null;
        try {
            PrefixMapper preFixMapper = new PrefixMapper();
            jaxbContext = JAXBContext.newInstance("net.opengis.sos._1");

            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper",
                    preFixMapper);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT ,
                    new Boolean(true));
            stream = new ByteArrayOutputStream();
            marshaller.marshal(capabilities,
                    stream);
            System.out.println(stream.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return stream.toString();
    }

    private void addProcedure(Sensor sensor) {
        ReferenceType refType = new ReferenceType();

        refType.setHref(sensor.getSensorURN());
        capabilities.getContents().getObservationOfferingList()
        .getObservationOffering().get(0).getProcedure().add(refType);
        
        offeringXML = marshalCapabilities();
        System.out.println("OFFERING - " +offeringXML);

    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (offeringId ^ (offeringId >>> 32));
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
        ObservationOffering other = (ObservationOffering) obj;
        if (offeringId != other.offeringId)
            return false;
        return true;
    }
}
