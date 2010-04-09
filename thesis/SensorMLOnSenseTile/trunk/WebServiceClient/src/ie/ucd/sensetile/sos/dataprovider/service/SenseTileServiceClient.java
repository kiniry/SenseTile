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
package ie.ucd.sensetile.sos.dataprovider.service;


import java.io.ByteArrayOutputStream;
import java.io.StringBufferInputStream;
import java.rmi.RemoteException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.axis2.AxisFault;

//import net.opengis.sensorml._1_0.Capabilities;
import net.opengis.sensorml._1_0.SensorML;
import net.opengis.sos._1.Capabilities;
import net.opengis.sos._1.ObservationOfferingType;
import ie.ucd.sensetile.sos.dataprovider.service.PrefixMapper;
import ie.ucd.sensetile.sos.dataprovider.service.SenseTileServiceStub.DescribeSensor;
import ie.ucd.sensetile.sos.dataprovider.service.SenseTileServiceStub.DescribeSensorResponse;
import ie.ucd.sensetile.sos.dataprovider.service.SenseTileServiceStub.GetCapabilitiesResponse;
import ie.ucd.sensetile.sos.dataprovider.service.SenseTileServiceStub.GetObservation;
import ie.ucd.sensetile.sos.dataprovider.service.SenseTileServiceStub.GetObservationResponse;

public class SenseTileServiceClient {
    
    static final String JAXB_SOS_PACKAGE = "net.opengis.sos._1";
    static final String JAXB_SENSORML_PACKAGE = "net.opengis.sensorml._1_0";
    SenseTileServiceStub stub;
    public static void main(String[] args){
        
        if (args.length>0) {
             new SenseTileServiceClient(
            "http://"+args[0]+":8080/axis2/services/SenseTileService.SenseTileServiceHttpSoap12Endpoint/");
        }
        else
            new SenseTileServiceClient();
    } 
             
        
    SenseTileServiceClient(){
       try {
           stub = new SenseTileServiceStub();
       } catch (AxisFault e) {
             e.printStackTrace();
       }
       getSensorInfo();
    }
        
    SenseTileServiceClient(String endpoint){
        try {
            stub = new SenseTileServiceStub(endpoint);
        } catch (AxisFault e) {
            e.printStackTrace();
        }
        getSensorInfo();
        }
        
    private void getSensorInfo(){
        GetCapabilitiesResponse capabilities = null;
        try {
            capabilities = stub.getCapabilities();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("SOS Capabilities : \n\n" + capabilities.get_return()); 

        Capabilities capability = readCapabilities(capabilities.get_return() );
		String sensor1 = capability.getContents().getObservationOfferingList().
        getObservationOffering().get(0).getProcedure().get(0).getHref();
        //Invoke the service
        System.out.println("SOS Capabilities : \n\n" + sensor1); 
        
        
        net.opengis.sos._1.ObjectFactory objFactorySOS = new net.opengis.sos._1.ObjectFactory();
        net.opengis.sos._1.DescribeSensor desSensor = objFactorySOS.createDescribeSensor();
        desSensor.setProcedure(sensor1);
        DescribeSensor describeSensor = new DescribeSensor();
        Marshaller marshaller = null;
        ByteArrayOutputStream stream = null;
        JAXBContext jaxbContext = null;
        PrefixMapper preFixMapper = new PrefixMapper();

        try {
            jaxbContext = JAXBContext.newInstance("net.opengis.sos._1");

            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper",
                    preFixMapper);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT ,
                    new Boolean(true));
            stream = new ByteArrayOutputStream();
            marshaller.marshal(desSensor,
                    stream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        
        describeSensor.setDescribeSensor(stream.toString());
        DescribeSensorResponse describeSensorResponse = null;
        try {
            describeSensorResponse = stub.describeSensor(describeSensor);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("DescribeSensor Response : \n\n" + describeSensorResponse.get_return());
        //SensorML sensordesc = readSensorML(describeSensorResponse.get_return());
        
        net.opengis.sos._1.GetObservation getObs = objFactorySOS.createGetObservation();
        getObs.setOffering(sensor1);
        GetObservation observation = new GetObservation();
        try {

            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper",
                    preFixMapper);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT ,
                    new Boolean(true));
            stream = new ByteArrayOutputStream();
            marshaller.marshal(getObs,
                    stream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
 
        System.out.println("GetObservation : \n\n" +stream.toString());

        observation.setGetObservation(stream.toString());
        GetObservationResponse response = null;
        try {
            response = stub.getObservation(observation);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
        System.out.println("Observation Response : \n\n" + response.get_return());
    }
    
    private Capabilities readCapabilities(String value) {
        Capabilities capability = null;
        capability  = (Capabilities)unmarshall(value);
        return capability;
    }
    private SensorML readSensorML(String value) {
        SensorML sensorML = null;
        sensorML  = (SensorML)unmarshall(value);
        return sensorML;
    }
    private Object unmarshall(String value){
        Object rootObj = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(JAXB_SOS_PACKAGE);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StringBufferInputStream stream = new StringBufferInputStream(value);
            rootObj  = unmarshaller.unmarshal(stream);

        } catch (JAXBException e) {
           e.printStackTrace();
        }
        return rootObj;        
    }
 
}
    
