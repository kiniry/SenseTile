package ie.ucd.sensetile.sensorml.process;

import ie.ucd.sensetile.gml.Quantity;

public class Connection {
    private Link link;
    private Quantity value;
    Connection (){
        
    }
    
    public Connection(Link link, Quantity value){
        this.link=link;
        this.value = value;
        
    }

}
