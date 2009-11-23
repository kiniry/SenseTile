package ie.ucd.sensetile.webservice.sos;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SensorObservationIF extends Remote{

   public void insertObservation(int obs) throws RemoteException;

   public void resgistorSensor(String sensorId) throws RemoteException;
}
