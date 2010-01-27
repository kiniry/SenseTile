package ie.ucd.sensetile.webservice.dataproducer;

import ie.ucd.sensetile.dataprovider.SensorObservations;
import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.PacketInputStream;
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

    private SensorMLSystem smlSenseTileSystem;
    private SystemOutput sensorBoardOutput;
    private SensorMLSystem smlConvetorSystem;

    private DataProviderProxyIf dataProviderProxy = null;

    public DataProviderProxyIf getDataProviderProxy() {
        return dataProviderProxy;
    }

    public void setDataProviderProxy(DataProviderProxyIf dataProviderProxy) {
        this.dataProviderProxy = dataProviderProxy;
    }

    private static Logger logger =
        Logger.getLogger(SenseTileSystem.class);

    public SenseTileSystem() {
       BasicConfigurator.configure();
       //init();
    }
    /**
     * Get Clients for Observations.
     */

    /**
     * Initialise the SensoMLProcessEngine with the sensorml files.
     */
     public final void init() {
         InputStream inputxml = getSensorBoardInputStream("SenseTileSystem.xml");
         smlSenseTileSystem = new SensorMLSystem(inputxml);
         //dataProviderProxy = DataProviderFactory.getProxy();
         registerSensorsSOS();

         System.out.println(smlSenseTileSystem.getComponentXML("sensorBoardSystem"));
     }


    /**
     * ProcessSensorData - get packets and send
     * data toSensorMLProcessEngine.
     * @param packet  packet from sensor board
     * @throws NotBoundException
     * @throws RemoteException
     * @throws MalformedURLException
     * @requires this.packetInputStream /= null
     */
     public final void processSensorData(final Packet packet) {
         //logger.info("Received Packet to process");
         DataBlockInt pktemp = new DataBlockInt(1);
         pktemp.setIntValue(packet.getTemperature());
         smlSenseTileSystem.setInput("txPacketTemperature", pktemp);
         smlSenseTileSystem.execute();
         dataProviderProxy.insertObservation((int) smlSenseTileSystem.
                               getOutput("temperature"));

     }

    /**
     * Set the packet reader.
     * @param packetReader  reference to PacketStreamReader
     */
    public final void setPacketReader(final /*@ non_null @*/
                                            PacketStreamReader packetReader) {
         this.packetInputStream = packetReader;
    }

    /**
     * Get an Input stream for the sensetilesystem xml.
     * @return InputSteam
     */
     private InputStream getSensorBoardInputStream(String name) {
         return this.getClass().getResourceAsStream("/"+name);

     }

     /**
      * Register Sensors for to receive packets from a PacketStreamReader.
      */
     public final void registerSensors() {
         List < DataProcess > sensorlist = smlSenseTileSystem.getSensors();
         Iterator < DataProcess > iter = sensorlist.iterator();
         while (iter.hasNext()) {
             AbstractProcess sensor = (AbstractProcess) iter.next();
             this.packetInputStream.registerSensor((Sensor) sensor);
         }
     }

     private final void registerSensorsSOS() {
         sensorBoardOutput = new SystemOutput(dataProviderProxy);
         sensorBoardOutput.setXmlDescription(
                 smlSenseTileSystem.getComponentXML("sensorBoardSystem"));
         sensorBoardOutput.registerSOS();
     }

     /**
      * Execute the SensorML processes.
      */
    public final void execute() {
       smlSenseTileSystem.execute();
       dataProviderProxy.insertObservation((int)
                   smlSenseTileSystem.getOutput("temperatureOutput"));
       dataProviderProxy.insertObservation((int)
               smlSenseTileSystem.getOutput("temperatureDNOutput"));
    }
}
