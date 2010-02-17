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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ie.ucd.sensetile.webservice.sos.SensorObservationIF;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;

import net.opengis.sos._1.Contents;
import net.opengis.sos._1.ObjectFactory;

public class SensorObservations
    implements SensorObservationIF {
    private int observation;
    private String sensorIdUrnRoot ="urn:ie.ucd.sensetile:sensor:";
    private Long sensorId = 0l;
    private String serverObjectName ="rmi://localhost/SenseTileService";
    private Map <String, SensorIF> sensors= new HashMap <String, SensorIF>();
    
    private SOSCapability sosCapability = null;
    
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
        String sensorurn = generateSensorURN();
        Sensor sensor = new Sensor(sensorurn, SensorDescription, version,
                ObservationTemplate,service);
        this.sensors.put(sensorurn,sensor);
        System.out.println("Observations bean service = " + sensorurn);
        
        if (sosCapability == null) {
            sosCapability = new SOSCapability();
            sosCapability.addOffering(sensor);
        }
        return sensorurn;

    }
    
    private String generateSensorURN(){
        String id = sensorIdUrnRoot+sensorId;
        sensorId++;
        return id;
    }

    public String getCapabilites() {

       /* try {
            JAXBContext jaxbContext = JAXBContext.newInstance("net.opengis.sos._1");
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT ,
                   new Boolean(true));

            ObjectFactory sosObjFactory = new ObjectFactory();
            Contents contents = sosObjFactory.createContents();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            marshaller.marshal(contents,
                    stream);
            System.out.println(stream.toString());

        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        return sosCapability.getCapabilitiesXml();
    }
}
