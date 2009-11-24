package ie.ucd.sensetile.webservice.dataproducer;

import ie.ucd.sensetile.dataprovider.SensorObservations;
import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.PacketInputStream;
import ie.ucd.sensetile.webservice.sos.SensorObservationIF;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.vast.data.DataBlockInt;
import org.vast.process.DataProcess;

/**
 * Process packets and initiates processing of sensor data by the system.
 *
 * @author Ciaran Palmer
 * @version 1
 */
public class SenseTileSystem {

    private PacketStreamReader packetInputStream;

    private SensorMLProcessEngine smlProcessEngine;
    
    private String dataproviderName = "rmi://localhost/SenseTileService";

    SensorObservationIF sensorObs = null;

    private static Logger logger =
        Logger.getLogger(SenseTileSystem.class);

    public SenseTileSystem() {
       BasicConfigurator.configure();
       init();
    }
    /**
     * Get Clients for Observations.
     */

    /**
     * Initialise the SensoMLProcessEngine with the sensorml files.
     */
     public final void init() {
         InputStream inputxml = getSensorBoardInputStream();
         smlProcessEngine = new SensorMLProcessEngine(inputxml);
         try {
             sensorObs = (SensorObservationIF) Naming.lookup(dataproviderName);
         } catch (MalformedURLException e1) {
             // TODO Auto-generated catch block
             e1.printStackTrace();
         } catch (RemoteException e1) {
             // TODO Auto-generated catch block
             e1.printStackTrace();
         } catch (NotBoundException e1) {
             // TODO Auto-generated catch block
             e1.printStackTrace();
         }
     }


    /**
     * ProcessSensorData - get packets and send
     * data toSensorMLProcessEngine.
     * @throws NotBoundException
     * @throws RemoteException
     * @throws MalformedURLException
     *
     * @requires this.packetInputStream /= null
     */
     public final void processSensorData(final Packet packet) {
         //logger.info("Received Packet to process");
         DataBlockInt pktemp = new DataBlockInt(1);
         pktemp.setIntValue(packet.getTemperature());
         smlProcessEngine.setInput("txPacketTemperature",pktemp);
         smlProcessEngine.execute();

         //logger.info("result "+smlProcessEngine.getOutput("temperature"));

         SensorObservationIF sensorObs = null;

        try {
            sensorObs = (SensorObservationIF) Naming.lookup(dataproviderName);
        } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (NotBoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
             
            sensorObs.insertObservation((int)smlProcessEngine.getOutput("temperature"));
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     }

    /**
     * Register Clients for Observations.
     * @param packetReader  reference to PacketStreamReader
     */
    public final void setPacketReader(final /*@ non_null @*/
                                            PacketStreamReader packetReader) {
         this.packetInputStream = packetReader;
    }

     private InputStream getSensorBoardInputStream() {
         return this.getClass().getResourceAsStream("/SenseTileSensorBoard.xml");

     }

     /**
      * Register Sensors for to receive packets from a PacketStreamReader.
      */
     public final void registerSensors() {
         List<DataProcess> sensorlist = smlProcessEngine.getSensors();
         Iterator<DataProcess> iter = sensorlist.iterator();
         while (iter.hasNext()) {
             AbstractProcess sensor = (AbstractProcess)iter.next();
             if (sensor.getProcessType() == ProcessType.SENSOR) {
                 this.packetInputStream.registerSensor((Sensor)sensor);
             }
         }
     }

     /**
      * Execute the SensorML processes.
      */
    public final void execute() {
       smlProcessEngine.execute();
       try {
           
           sensorObs.insertObservation((int)smlProcessEngine.getOutput("temperature"));
           sensorObs.insertObservation((int)smlProcessEngine.getIntOutput("rawsensortemperature"));
       } catch (RemoteException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
    }

}
