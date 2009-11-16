package ie.ucd.sensetile.sensorml.process;

public class Link {
    
    private Source sourceref;
    private String destinationref;
    
    Link() {

    }
    public Link(Source source, String destination){
        sourceref = source;
        destinationref = destination;
    }
    
}
