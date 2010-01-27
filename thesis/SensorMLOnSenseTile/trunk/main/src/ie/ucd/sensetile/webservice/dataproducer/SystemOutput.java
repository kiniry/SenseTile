package ie.ucd.sensetile.webservice.dataproducer;

public class SystemOutput {
    
    DataProviderProxyIf dataProvider = null;
    String xmlDescription = null;
    ObservationIf observation = null;
    String sosId = null;
    String observationTemplate="initial";
    
    public String getSosId() {
        return sosId;
    }


    public void setSosId(String sosId) {
        this.sosId = sosId;
    }


    public DataProviderProxyIf getDataProvider() {
        return dataProvider;
    }


    public void setDataProvider(DataProviderProxyIf dataProvider) {
        this.dataProvider = dataProvider;
    }


    public String getXmlDescription() {
        return xmlDescription;
    }


    public void setXmlDescription(String xmlDescription) {
        this.xmlDescription = xmlDescription;
    }


    public ObservationIf getObservation() {
        return observation;
    }


    public void setObservation(ObservationIf observation) {
        this.observation = observation;
    }


    public SystemOutput(DataProviderProxyIf dataProvider) {
        this.dataProvider = dataProvider;
    }
    

    /*Register with SOS */
    public void registerSOS() {
        this.sosId = dataProvider.registerSensor("SOS", "1.0",
                                    this.xmlDescription,
                                    this.observationTemplate);
    }
    
    /*Update SOS with system outputs */

}
