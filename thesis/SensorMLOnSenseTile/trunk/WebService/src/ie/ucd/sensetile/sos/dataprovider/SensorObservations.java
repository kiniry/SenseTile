package ie.ucd.sensetile.sos.dataprovider;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ie.ucd.sensetile.dataprovider.rmi.SensorObservationIF;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.ByteArrayOutputStream;
import java.io.StringBufferInputStream;

import net.opengis.sos._1.Contents;
import net.opengis.sos._1.DescribeSensor;
import net.opengis.sos._1.GetObservation;
import net.opengis.sos._1.InsertObservation;
import net.opengis.sos._1.ObjectFactory;

public class SensorObservations
    implements SensorObservationIF {
    private String observation;
    private String sensorIdUrnRoot ="urn:ie.ucd.sensetile:sensor:";
    private Long sensorId = 0l;
    //private Long offeringId = 0l;
    private String serverObjectName ="rmi://localhost/SenseTileService";
    private Map <String, SensorIF> sensors= new HashMap <String, SensorIF>();
    //private Map <String, ObservationOffering> offerings= new HashMap <String, ObservationOffering>();
    @SuppressWarnings("unused")
    private DataProducerService dataProducerService;
    private ObservationOffering observationOffering = null;
    static final String JAXB_SOS_PACKAGE = "net.opengis.sos._1";

    public SensorObservations(){
        try {
            this.dataProducerService = new DataProducerService(serverObjectName,this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("SenseTileService running!!!!");
    }

    public void insertObservation(String sosId, String obs) {
        this.sensors.get(sosId).addObservation(obs);
        System.out.println("Observations bean = "+obs + "id "+sosId);       
    }



    public void setSensorId(String sensorId) {
        this.sensorIdUrnRoot = sensorId;
    }

    public String getSensorId() {
        return sensorIdUrnRoot;
    }

    public String RegisterSensor(String service, String version,
            String SensorDescription, String ObservationTemplate) {
        String sensorurn = generateSensorURN();
        Sensor sensor = new Sensor(sensorurn, SensorDescription, version,
                ObservationTemplate,service);
        this.sensors.put(sensorurn,sensor);
        System.out.println("Observations bean service = " + sensorurn);
        
        if (observationOffering == null) {
            observationOffering = new ObservationOffering();
        }
        observationOffering.addOffering(sensor);
            //this.offerings.put(sensor.getSystemName(), observationOffering);
        System.out.println("Thread in registerSensor " + Thread.currentThread());

        return sensorurn;

    }
    
    private String generateSensorURN(){
        String id = sensorIdUrnRoot+sensorId;
        sensorId++;
        return id;
    }

    public String getCapabilites() {

       /* try {
            JAXBContext jaxbContext = JAXBContext.newInstance("net.opengis.sos._1");
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT ,
                   new Boolean(true));

            ObjectFactory sosObjFactory = new ObjectFactory();
            Contents contents = sosObjFactory.createContents();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            marshaller.marshal(contents,
                    stream);
            System.out.println(stream.toString());

        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        return observationOffering.getCapabilitiesXml();
    }

    public final String describeSensor(String describeSensor) {
        
        return sensors.get(parseDescribeSensor(describeSensor)).getXMLDescription();
    }

    private final String parseDescribeSensor(String describeSensor) {
        System.out.println("DescribeSensor\n" + describeSensor);
        System.out.println("Thread in SensorObservations " + Thread.currentThread());

        DescribeSensor desSensor = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(JAXB_SOS_PACKAGE,
                    this.getClass().getClassLoader());
            //JAXBContext jc = JAXBContext.newInstance(JAXB_SOS_PACKAGE);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StringBufferInputStream stream = new StringBufferInputStream(describeSensor);
            desSensor  = (DescribeSensor) unmarshaller.unmarshal(stream);

        } catch (JAXBException e) {
           e.printStackTrace();
        }
        return desSensor.getProcedure();
    }

    public String getObservation(String getObservation) {
        GetObservation  obs = parseGetObservation(getObservation);
        return "returned "+obs.getOffering();
    }
    private GetObservation parseGetObservation(String getObservation){
        return  (GetObservation)unmarshall(getObservation);
    }
    private Object unmarshall(String value){
        Object rootObj = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(JAXB_SOS_PACKAGE,
                    this.getClass().getClassLoader());
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StringBufferInputStream stream = new StringBufferInputStream(value);
            rootObj  = unmarshaller.unmarshal(stream);

        } catch (JAXBException e) {
           e.printStackTrace();
        }
        return rootObj;        
    }
}
