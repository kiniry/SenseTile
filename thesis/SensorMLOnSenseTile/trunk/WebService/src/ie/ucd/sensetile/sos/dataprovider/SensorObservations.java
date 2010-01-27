/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package ie.ucd.sensetile.sos.dataprovider;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import ie.ucd.sensetile.webservice.sos.SensorObservationIF;

public class SensorObservations
    implements SensorObservationIF {
    private int observation;
    private String sensorIdUrnRoot ="urn:ie.ucd.sensetile:sensor:";
    private String sensor1 = null;
    private Long sensorId = 0l;
    private String serverObjectName ="rmi://localhost/SenseTileService";
    private int counter =0;
    
    public SensorObservations() throws RemoteException{
        //super();
        try {
            SensorObservationIF stub = (SensorObservationIF) UnicastRemoteObject.exportObject(this, 0);

            // Bind the remote object's stub in the Registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind(serverObjectName, stub);

        }
        catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
        System.out.println("SenseTileService RMI running");
    }

    @Override
    public void insertObservation(int obs) throws RemoteException {
        this.setObservation(obs);
        System.out.println("Observations bean = "+obs);       
    }

    public void setObservation(int observation) {
        this.observation = observation;
    }

    public int getObservation() {
        return observation;
    }

    public void setSensorId(String sensorId) {
        this.sensorIdUrnRoot = sensorId;
    }

    public String getSensorId() {
        return sensorIdUrnRoot;
    }

    @Override
    public String RegisterSensor(String service, String version,
            String SensorDescription, String ObservationTemplate)
            throws RemoteException {
        counter++;
        System.out.println("Observations bean service = " + counter);       
        return this.sensorIdUrnRoot;

    }
    
    private String generateSensorURN(){
        String id = sensorIdUrnRoot+sensorId;
        sensorId++;
        return id;
    }
}
