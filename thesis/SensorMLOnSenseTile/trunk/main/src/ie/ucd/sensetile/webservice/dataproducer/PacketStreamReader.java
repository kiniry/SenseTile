package ie.ucd.sensetile.webservice.dataproducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.PacketInputStream;
import ie.ucd.sensetile.sensorboard.SenseTileException;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.NDC;
/**
 * Reads parameterised no of packets every second.
 *
 * @author Ciaran Palmer
 * @version 1
 */


public class PacketStreamReader {

       private static final int MAXNOPACKETS = 1000; //arbitrary
       private static final int MINNOPACKETS = 1; //arbitrary
       private int numpackets=MINNOPACKETS;

       private PacketInputStream packetInputStream;
       private boolean readpackets;
       private List<Sensor> sensorList;

       static Logger logger =
           Logger.getLogger(PacketStreamReader.class.getName());
       private SenseTileSystem senseTileSystem;

       public PacketStreamReader(SenseTileSystem senseTileSystem) {
           BasicConfigurator.configure();
           this.senseTileSystem = senseTileSystem;
           sensorList = new ArrayList < Sensor >();

       }
      /**
       * set no of packets to read.
       * @require num_packets >0;
       * @require num_packets<=MAXNOPACKETS;
       * @ensure numpackets = num_packets;
       */
       public void setNumPacketsToRead(final int numpackets) {
           this.numpackets = numpackets;
       }

       /**
        * register sensors.
        */
        public final void registerSensors(final List < Sensor > sensorlist) {
            this.sensorList = sensorlist;
        }

        public final List < Sensor > getSensors() {
            return this.sensorList;
        }

        /**
         * register sensor.
         */
         public final void registerSensor(final Sensor sensor) {
             this.sensorList.add(sensor);
         }
      /**
       * get no of packets to read.
       */
       public final /*@ pure @*/ int getNumPacketsToRead() {
           return this.numpackets;
       }

       public void setPacketInputStream(final /*@ non_null @*/ PacketInputStream packetInputStream) {
           this.packetInputStream = packetInputStream;
       }

       /**
        * read packets from input stream.
        */
       public void readPackets() {



           readpackets = true;
           while (readpackets) {
               try {
                   Thread.currentThread().sleep(5000);
                 } catch (InterruptedException e1) {
                   // TODO Auto-generated catch block
                   e1.printStackTrace();
                 }
               for (int i = 0; i < numpackets; i++) {
                updateSensors();
               }

               senseTileSystem.execute();
           }
       }

       /**
        * get no of packets to read.
        */
       private void updateSensors() {
         try {
            Packet packet = this.packetInputStream.read();
            Iterator<Sensor> iter = sensorList.iterator();
            while (iter.hasNext()) {
                iter.next().processSensorData(packet);
            }
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         } catch (SenseTileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
    }
}
