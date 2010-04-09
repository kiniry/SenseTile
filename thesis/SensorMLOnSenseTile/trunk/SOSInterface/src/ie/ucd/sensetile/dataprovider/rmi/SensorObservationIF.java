package ie.ucd.sensetile.dataprovider.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SensorObservationIF extends Remote {

   public void insertObservation(String sosId, String observation) throws RemoteException;

   //AssignedSensorId urn returned.

   String RegisterSensor(String service, String version,
   String SensorDescription, String ObservationTemplate) throws RemoteException;
}
