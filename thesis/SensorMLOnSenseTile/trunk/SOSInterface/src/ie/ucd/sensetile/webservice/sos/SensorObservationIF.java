package ie.ucd.sensetile.webservice.sos;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SensorObservationIF extends Remote {

   public void insertObservation(int obs) throws RemoteException;

   //AssignedSensorId urn returned.

   String RegisterSensor(String service, String version,
   String SensorDescription, String ObservationTemplate) throws RemoteException;
}
