package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.Frame;
import ie.ucd.sensetile.sensorboard.Packet;
import ie.ucd.sensetile.sensorboard.simulator.formal.channel.FilePathProvider;
import ie.ucd.sensetile.sensorboard.simulator.formal.sensor.AudioBuilder;
import ie.ucd.sensetile.sensorboard.simulator.formal.sensor.ISensor;
import ie.ucd.sensetile.sensorboard.simulator.formal.sensor.SensorBuilder;
import ie.ucd.sensetile.sensorboard.simulator.formal.sensor.type.SensorIndexer;
/**
 * Represents an instance of SensorAndClonePacketBuilder.
 * @title         "SensorAndClonePacketBuilder"
 * @date          "2009/10/10"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class SensorAndClonePacketBuilder implements PacketBuilder{
	
  //@ spec_public non_null
  private transient InstancePacket template;
  //@ spec_public non_null
  private  SensorBuilder sb;

  //@ spec_public non_null
  private  AudioBuilder ab;
  
  //@ invariant sb.g_inv;
  //@ invariant ab.inv;
  
  
  
  /*@ requires theFrequency == SensorIndexer.AUDIO_FREQUENCY_48KHZ ||
    @ 		   theFrequency == SensorIndexer.AUDIO_FREQUENCY_96KHZ;
    @ assignable template,ab,sb; 
    @ ensures template == theTemplate;
    @ ensures sb instanceof SensorBuilder;
    @ ensures ab.inv;
    @ ensures sb.g_inv;
    @*/
  public SensorAndClonePacketBuilder(final /*@non_null@*/ InstancePacket theTemplate, 
		  							final /*@non_null@*/ FilePathProvider thePathProvider,
		  							final int theFrequency) 
  {
    this.template = theTemplate;
    sb = new SensorBuilder(thePathProvider );
    ab = new AudioBuilder(theFrequency, thePathProvider );
  }
     
/*@ also
  @ signals_only UnsupportedOperationException;
  @*/
  public Packet getPacket() 
  {
	//@ set g_packet = null;
	  try
	  {
		  InstancePacket packet = makeClone();
	      //@ set g_packet = packet;
	      if(packet != null)
		  {
			  setTemperature(packet);
			  setPressure(packet);
			  setLightLevel(packet);
			  setAccelerometerX(packet);
			  setAccelerometerY(packet);
			  setAccelerometerZ(packet);
			  
			  for (int Index = 0; Index < Frame.AUDIO_CHANNELS; Index++) 
			  {
				  readAudio(Index, packet);
			  }
		  }
	      return packet;
	  }
	  
	  catch(CloneNotSupportedException cnse)
	  {
		throw new UnsupportedOperationException(cnse);  
	  }
	  
  }
  private void readAudio(final int channel,/*@non_null@*/ final InstancePacket packet) 
  {


	  for (int index = 0; index< Packet.FRAMES; index++ ) 
      {
		boolean isValidChannel =  channel >=0 && channel < ab.getAudioArrayLength();
		boolean isValidIndex =  index >=0 && index < Packet.FRAMES;
		
		if( isValidChannel && isValidIndex )
		{  
			final ISensor audio = ab.getAudio(channel);

			if( audio.isEnabled())
			{
				Frame frame = packet.getFrame(index);
	
				if( frame instanceof InstanceFrame ) 
				{
					InstanceFrame audioFrame =(InstanceFrame)frame; 
					final char sample = (char)audio.getValue();
					audioFrame.setAudio(channel, sample);
				}
		  }
       }
     }     
  }
  
 
  //@ ghost short g_value; 

  /*@ ensures
    @ (sb.getSensorsLength() > 0) ==>
    @ ((g_sensor.isEnabled()) ==>
    @ ((g_value >= -880 && g_value <= 2047) ==> (g_value == packet.temperature)));
    @*/
   private void setTemperature( /*@non_null*/ final InstancePacket packet  ) 
   {
	  if(sb.getSensorsLength() > 0)
	  {
	      final ISensor sensor = sb.getSensor(SensorIndexer.THERM);
	      //@ set g_sensor = sensor;
	    
	      if(sensor.isEnabled()) 
	      {
    		 sensor.measure();
    	     final short value = (short)sensor.getValue();
    	     //@ set g_value = value;
    	     if(value >= -880 && value <= 2047)
    	     {
    	           packet.setTemperature(value);
    	     }
	      }
	  }
  }

 /*@ ensures
   @ (sb.getSensorsLength() > 0) ==>
   @ ((g_sensor.isEnabled()) ==>
   @ ((g_value >= 310 && g_value <= 5585) ==> (g_value == packet.pressure)));
   @*/
  private void setPressure( /*@non_null*/ final InstancePacket packet  ) 
  {
	  if(sb.getSensorsLength() > 0)
	  {
	      final ISensor sensor = sb.getSensor(SensorIndexer.PRESS);
	      //@ set g_sensor = sensor;
	      if(sensor.isEnabled()) 
	      {
    		 sensor.measure();
    	     final short value = (short)sensor.getValue();
    	     //@ set g_value = value;
    	     if(value >= 310 && value <=5585)
    	     {
    	           packet.setPressure(value);
    	     }
	      }
	  }  
  }

  /*@ ensures
    @ (sb.getSensorsLength() > 0) ==>
    @ ((g_sensor.isEnabled()) ==>
    @ ((g_value >= 0 && g_value <= 1000) ==> (g_value == packet.lightLevel)));
    @*/
  private void setLightLevel( /*@non_null*/ final InstancePacket packet  ) 
  {
	  if(sb.getSensorsLength() > 0)
	  {
	      final ISensor sensor = sb.getSensor(SensorIndexer.LIGHT);
	      //@ set g_sensor = sensor;
	      if(sensor.isEnabled()) 
	      {
    		 sensor.measure();
    	     final short value = (short)sensor.getValue();
    	   //@ set g_value = value;
    	     if(value >= 0 && value <= 1000)
    	     {
    	           packet.setLightLevel(value);
    	     }
	      }
	  }  
  }

  /*@ ensures
    @ (sb.getSensorsLength() > 0) ==>
    @ ((g_sensor.isEnabled()) ==>
	@ ((g_value >= 1488 && g_value <= 2232) ==> (g_value == packet.accelerometerX)));
    @*/
  private void setAccelerometerX( /*@non_null*/ final InstancePacket packet  ) 
  {
	  if(sb.getSensorsLength() > 0)
	  {
	      final ISensor sensor = sb.getSensor(SensorIndexer.ACCEL_X);
	      //@ set g_sensor = sensor;
	      if(sensor.isEnabled()) 
	      {       
    		 sensor.measure();
    	     final short value = (short)sensor.getValue();
    	    //@ set g_value = value;
    	     if( value >=1488 && value <= 2232 )
    	     {
    	           packet.setAccelerometerX(value);
    	     }
	      }
	  }  
  }
  /*@ ensures
    @ (sb.getSensorsLength() > 0) ==>
    @ ((g_sensor.isEnabled()) ==>
	@ ((g_value >= 1488 && g_value <= 2232) ==> (g_value == packet.accelerometerY)));
    @*/
  private void setAccelerometerY( /*@non_null*/ final InstancePacket packet  ) 
  {
	  if(sb.getSensorsLength() > 0)
	  {
	      final ISensor sensor = sb.getSensor(SensorIndexer.ACCEL_Y);
	      //@ set g_sensor = sensor;
	      if(sensor.isEnabled()) 
	      {
    		 sensor.measure();
    	     final short value = (short)sensor.getValue();
    	   //@ set g_value = value;
    	     if( value >=1488 && value <= 2232 )
    	     {
    	           packet.setAccelerometerY(value);
    	     }
	      }
	  }  
  }
  /*@ ensures
    @ (sb.getSensorsLength() > 0) ==>
    @ ((g_sensor.isEnabled()) ==>
  	@ ((g_value >= 1488 && g_value <= 2232) ==> (g_value == packet.accelerometerZ)));
    @*/
  private void setAccelerometerZ( /*@non_null*/ final InstancePacket packet  ) 
  {
	  
	  if(sb.getSensorsLength() > 0)
	  {
	      final ISensor sensor = sb.getSensor(SensorIndexer.ACCEL_Z);
	      //@ set g_sensor = sensor;
	      if(sensor.isEnabled()) 
	      {     
    		 sensor.measure();
    	     final short value = (short)sensor.getValue();
    	   //@ set g_value = value;
    	     
    	     
    	     if(value >=1488 && value <= 2232 )
    	     {
    	           packet.setAccelerometerZ(value);
    	     }
	      }
	  }  
  }
    
/*@ public behavior
  @ requires template != null;
  @ ensures (g_o instanceof InstancePacket) ==> 
  @ 		\result == g_packet && 
  @ 		g_packet instanceof InstancePacket;
  @ signals (CloneNotSupportedException cse) true;
  @*/
  private InstancePacket makeClone() throws CloneNotSupportedException 
  {
	InstancePacket packet = null;
	//@ set g_packet = null;
	Object o = template.clone();
	//@ set g_o = o;
	if  (o instanceof InstancePacket) 
	{
		packet = (InstancePacket) o;
    	//@ set g_packet = packet;
	}
    return packet;
  }
  
}
