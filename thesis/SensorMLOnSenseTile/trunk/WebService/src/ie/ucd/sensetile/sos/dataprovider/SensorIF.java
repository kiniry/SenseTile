package ie.ucd.sensetile.sos.dataprovider;

public interface SensorIF {
    
    /* Manages observations provided by the data producer */

    /* getObservation */
    Observation getObservation();
    
    /* addObservation */
    void addObservation(Observation obs);

    /* get service information */
    String getService();

    /* get version */
    String getVersion();


    /* get ObservationTemplate */
    String getObservationTemplate();

    String getXMLDescription();


}
