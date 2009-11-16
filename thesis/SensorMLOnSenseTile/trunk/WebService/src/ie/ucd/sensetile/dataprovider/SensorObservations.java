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
package ie.ucd.sensetile.dataprovider;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ie.ucd.sensetile.webservice.sos.SensorObservationIF;

public class SensorObservations extends UnicastRemoteObject
    implements SensorObservationIF {
    private int observation;
    private String sensorId;
    private String serverObjectName ="rmi://localhost/SenseTileService";
    
    public SensorObservations() throws RemoteException{
        super();
        try {
            Naming.rebind(serverObjectName, this);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("SenseTileService RMI running");
    }

    @Override
    public void insertObservation(int obs) throws RemoteException {
        this.setObservation(obs);
        System.out.println("Observations bean = "+obs);
        
    }
    @Override
    public void resgistorSensor(String sensorId) throws RemoteException {
        this.setSensorId(sensorId);
        
    }

    public void setObservation(int observation) {
        this.observation = observation;
    }

    public int getObservation() {
        return observation;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorId() {
        return sensorId;
    }
}
