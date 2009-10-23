package test;


import java.io.IOException;

import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.simulator.InstancePacket;
import ie.ucd.sensetile.sensorboard.simulator.SensorAndClonePacketBuilder;
import ie.ucd.sensetile.sensorboard.simulator.channel.FilePathProvider;
import ie.ucd.sensetile.sensorboard.simulator.sensor.type.SensorIndexer;

import org.junit.Test;
import junit.framework.TestCase;
public class SensetileTest  extends TestCase
{
    @Test
    public void testSenseTile() throws IOException 
    {
    	String[] thePaths = new String[]{"./audios/Temperature","./audios/Light","./audios/Pressure",
    									 "./audios/AxesX","./audios/AxesY","./audios/AxesZ"};
    	String[] theSoundPaths = new String[]{"./audios/Sound0","./audios/Sound1","./audios/Sound2","./audios/Sound3"};
    	String[] theUltraSoundPaths = new String[]{"","","","",""};
    	FilePathProvider fpp = new FilePathProvider(thePaths,theSoundPaths,theUltraSoundPaths);
    	InstancePacket instancePacket = new InstancePacket();
    	SensorAndClonePacketBuilder sapc = new SensorAndClonePacketBuilder(instancePacket,fpp,SensorIndexer.AUDIO_FREQUENCY_48KHZ);
    	
    	 PacketPrinter printer = new PacketPrinter();
    	    int count = 0;
    	    
    	    while (true) 
    	    {
    	      System.out.println(count);
    	      Packet packet = sapc.getPacket();
    	      printer.print(packet);
    	      count ++;
    	    }
    }
}
