package ie.ucd.sensetile.webservice.dataproducer;

import ie.ucd.sensetile.webservice.sos.SensorObservationIF;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DataProviderProxyRMI implements DataProviderProxyIf{

    private String dataproviderName = "rmi://localhost/SenseTileService";

    private SensorObservationIF sensorObs = null;


    public DataProviderProxyRMI() {
        getDataProviderRef();
    }

    final void getDataProviderRef() {
       try {
           //currently just local host
           Registry registry = LocateRegistry.getRegistry();
           sensorObs = (SensorObservationIF) registry.lookup(dataproviderName);
           //sensorObs = (SensorObservationIF) Naming.lookup(dataproviderName);
       } catch (RemoteException e1) {
           e1.printStackTrace();
       } catch (NotBoundException e1) {
           e1.printStackTrace();
       }
    }

    public final void insertObservation(final int observation) {
        try {
            sensorObs.insertObservation(observation);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public final String registerSensor(final String service,
                                       final String version,
                                       final String sensorDescription,
                                       final String observationTemplate) {
        String assignedSensorId = null;
        try {
            assignedSensorId = sensorObs.RegisterSensor(service, version,
                    sensorDescription, observationTemplate);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return assignedSensorId;
    }
}
