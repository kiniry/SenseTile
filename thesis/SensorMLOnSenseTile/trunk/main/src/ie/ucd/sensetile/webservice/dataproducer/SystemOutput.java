package ie.ucd.sensetile.webservice.dataproducer;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SystemOutput {

    SOSProxyIf sosProxy = null;
    String xmlDescription = null;
    ObservationIf observation = null;
    String sosId = null;
    String observationTemplate="initial";

    SystemOutput(){
        init();
    }

    private void init(){
        sosProxy = SOSProxyFactory.getProxy();
    }

    public String getSosId() {
        return sosId;
    }


    public void setSosId(String sosId) {
        this.sosId = sosId;
    }


    public SOSProxyIf getDataProvider() {
        return sosProxy;
    }


    public void setDataProvider(SOSProxyIf dataProvider) {
        this.sosProxy = dataProvider;
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


    public SystemOutput(SOSProxyIf dataProvider) {
        this.sosProxy = dataProvider;
    }


    /*Register with SOS */
    public final void registerSOS() {
        this.sosId = sosProxy.registerSensor("SOS", "1.0",
                                    this.xmlDescription,
                                    this.observationTemplate);
        //System.out.println(this.xmlDescription);

    }

    /*Update SOS with system outputs given sosId*/
    public final void insertObservation(int observation) {
        sosProxy.insertObservation(this.sosId, createObservationQuantity(
                Integer.toString(observation),sosId));
    }

    /*Update SOS with system outputs given sosId*/
    public final void insertObservation(double observation) {
        sosProxy.insertObservation(this.sosId,createObservationQuantity(
                Double.toString(observation),sosId));
    }

    String createObservationQuantity(String value, String sosId) {
        
        String obsStart = "<om:Observation>";
        String procedure = " <om:procedure xlink:href=\""+sosId+"\"/>";
        String resultStart = "<om:result>";
        String simpleDataRecord = "<swe:SimpleDataRecord>";
        String timefield =      "<swe:field name=\"time\">";
        String timedef =           "<swe:Time definition=\"urn:ogc:property:time:iso8601\">";
        String swevalue =               "<swe:value>";
        String swevalueend =            "</swe:value>";
        String timefieldend =       "</swe:Time>";
        String fieldend =           "</swe:field>";
        String temperaturefield =         "<swe:field name=\"temperature\">";
        String quantity = "<swe:Quantity definition=\"urn:ogc:property:temperature\">";
        String code =         "<swe:uom code=\"deg\"/>";
        String quantityend =      "</swe:Quantity>";
        String simpleDataRecordEnd = "</swe:SimpleDataRecord>";
        String resultend =      "</om:result>";
        String obsEnd = "</om:Observation>";


        return getObservationHeader(sosId)+obsStart+procedure+resultStart+simpleDataRecord+timefield+timedef+swevalue
                +getISO8601Time()+swevalueend+timefieldend+fieldend+temperaturefield+
                quantity+code+swevalue+value+swevalueend+quantityend+fieldend+
                simpleDataRecordEnd+resultend+obsEnd+getObservationFooter();
    }
    
    private String getObservationHeader(final String sosId){
        String obsHeader = "<InsertObservation xmlns=\"http://www.opengis.net/sos/1.0\" xmlns:om=\"http://www.opengis.net/om/1.0\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:swe=\"http://www.opengis.net/swe/1.0.1\">";
        obsHeader+= "<AssignedSensorId>"+sosId+"</AssignedSensorId>";
        
        return obsHeader;
    }
    
    private String getObservationFooter(){
        return "</InsertObservation>";
    }
    String createObservationCount(String value, String sosId) {
        String obsStart = "<om:Observation>";
        String procedure = " <om:procedure xlink:href=\""+sosId+"\"/>";
        String resultStart = "<om:result>";
        String simpleDataRecord = "<swe:SimpleDataRecord>";
        String timefield =      "<swe:field name=\"time\">";
        String timedef =           "<swe:Time definition=\"urn:ogc:property:time:iso8601\">";
        String swevalue =               "<swe:value>";
        String swevalueend =            "</swe:value>";
        String timefieldend =       "</swe:Time>";
        String fieldend =           "</swe:field>";
        String temperaturefield =         "<swe:field name=\"temperature\">";
        String count = "<swe:Count definition=\"urn:ogc:property:temperature\">";
        String countend =      "</swe:Count>";
        String simpleDataRecordEnd = "</swe:SimpleDataRecord>";
        String resultend =      "</om:result>";
        String obsEnd = "</om:Observation>";

        return obsStart+procedure+resultStart+simpleDataRecord+timefield+timedef+swevalue
                +getISO8601Time()+swevalueend+timefieldend+fieldend+temperaturefield+
                count+swevalue+value+swevalueend+countend+fieldend+
                simpleDataRecordEnd+resultend+obsEnd;
    }
    
    private String getISO8601Time(){
       Date date = new Date();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
       return sdf.format( date );
    }
}
