package ie.ucd.sensetile.webservice.dataproducer;

import ie.ucd.sensetile.sensorboard.Packet;

public interface Sensor {

    String getName();

    void processSensorData(Packet packet);

}
