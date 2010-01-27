package ie.ucd.sensetile.webservice.dataproducer;

public interface DataProviderProxyIf {

    void insertObservation(int observation);
    
    public String registerSensor( String service,
             String version,
             String sensorDescription,
             String observationTemplate);
}
