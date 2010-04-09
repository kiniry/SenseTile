package ie.ucd.sensetile.webservice.dataproducer;

public interface SOSProxyIf {

    void insertObservation(String sosId, String observation);
    
    public String registerSensor( String service,
             String version,
             String sensorDescription,
             String observationTemplate);
}
