package ie.ucd.sensetile.sos.dataprovider;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.namespace.QName;

import net.opengis.gml.v_3_1_1.CodeType;
import net.opengis.gml.v_3_1_1.ReferenceType;
import net.opengis.gml.v_3_1_1.StringOrRefType;
import net.opengis.ows._1.LanguageStringType;
import net.opengis.ows._1.ObjectFactory;
import net.opengis.ows._1.ServiceIdentification;
import net.opengis.ows._1.ServiceProvider;
import net.opengis.sensorml._1_0.SystemType;
import net.opengis.sos._1.Capabilities;
import net.opengis.sos._1.Contents;
import net.opengis.sos._1.ObservationOfferingType;
import net.opengis.swe._1_0.PhenomenonPropertyType;
import net.opengis.swe._1_0.PhenomenonType;

public class SOSCapability {
    
    private Capabilities capabilities= null;
    private String offeringXML = null;
    static final String OFFERID ="offering-";
    static final String OFFERURN ="urn:MyOrg:offering:";

    static long offerid = 0;
    
    SOSCapability(){
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
        
    }

    public void addOffering(Sensor sensor){
        //check if sensor is already an offering type
        if (existOffering(sensor.getSystemName())){
            //if already then add procedure to existing offering
            addProcedure(sensor);
        }
        else {
            //else create a new offering and add procedure.
            createOffering(sensor);
            addProcedure(sensor);
        }
    }
    
    private boolean existOffering(String name) {
        return false;
    }
    
    private void createOffering(Sensor sensor){
        net.opengis.sos._1.ObjectFactory objFactorySOS = new net.opengis.sos._1.ObjectFactory();
        ObservationOfferingType obsType = objFactorySOS.createObservationOfferingType();
        CodeType codeType = new CodeType();
        codeType.setValue(OFFERURN+offerid);
        //ToDo
        JAXBElement<CodeType> jaxbEl = new JAXBElement<CodeType>(new QName("name"),CodeType.class, codeType);

        obsType.getName().add(jaxbEl);
        obsType.setId(OFFERID+offerid);
        
        
        ReferenceType refType = new ReferenceType();

        refType.setHref(sensor.getSensorURN());
        obsType.getProcedure().add(refType);
        
        PhenomenonPropertyType phenType = new PhenomenonPropertyType();
        phenType.setHref("Inputs");
        obsType.getObservedProperty().add(phenType);
    
        Contents.ObservationOfferingList obsOfferList = new Contents.ObservationOfferingList();
        capabilities.getContents().setObservationOfferingList(obsOfferList);
        capabilities.getContents().getObservationOfferingList().getObservationOffering().add(obsType);

        
        offeringXML = marshalCapabilities();
    }

    private String marshalCapabilities() {
        System.out.println("marshall");

        Marshaller marshaller = null;
        ByteArrayOutputStream stream = null;
        JAXBContext jaxbContext = null;
        try {
            PreFixMapper preFixMapper = new PreFixMapper();
            jaxbContext = JAXBContext.newInstance("net.opengis.sos._1");

            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", preFixMapper);
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
    
    private void addProcedure(Sensor sensor){
        
    }
}
