package ie.ucd.sensetile.sensorboard;

public interface CloneablePacket extends Packet, Cloneable {

  Object clone() throws CloneNotSupportedException;

}
