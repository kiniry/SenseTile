package ie.ucd.sensetile.sos.dataprovider;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import ie.ucd.sensetile.dataprovider.rmi.SensorObservationIF;

public class DataProducerService implements SensorObservationIF{
    
    private SensorObservations sensorObs;
    public DataProducerService(String serverObjectName, SensorObservations sensorObs) throws RemoteException{
        this.sensorObs = sensorObs;
        try {
            SensorObservationIF stub = (SensorObservationIF) UnicastRemoteObject.exportObject(this, 0);

            // Bind the remote object's stub in the Registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind(serverObjectName, stub);

        }
        catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String RegisterSensor(String service, String version,
            String SensorDescription, String ObservationTemplate)
            throws RemoteException {
        return sensorObs.RegisterSensor(service, version, SensorDescription, ObservationTemplate);
    }

    @Override
    public void insertObservation(String sosId, String observation)
            throws RemoteException {
            sensorObs.insertObservation(sosId, observation);
        
    }

}
