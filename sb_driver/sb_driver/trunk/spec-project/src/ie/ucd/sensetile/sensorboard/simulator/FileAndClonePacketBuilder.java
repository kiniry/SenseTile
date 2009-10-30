package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.Frame;
import ie.ucd.sensetile.sensorboard.Packet;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * PacketBuilder building packets from a template and sensor streams.
 * 
 * @author delbianc
 * 
 * TODO: to be finished
 */
public class FileAndClonePacketBuilder implements PacketBuilder {
  
  private InstancePacket template = new InstancePacket();
  private /*@ nullable */ DataInputStream temperature;
  private /*@ nullable */ DataInputStream pressure;
  private /*@ nullable */ DataInputStream lightLevel;
  private /*@ nullable */ DataInputStream accelerometerX;
  private /*@ nullable */ DataInputStream accelerometerY;
  private /*@ nullable */ DataInputStream accelerometerZ;
  private final DataInputStream[] audio = 
    new DataInputStream[Frame.AUDIO_CHANNELS];
  
  /**
   * PacketBuilder based on template.
   * 
   * @param template packet template
   */
  public FileAndClonePacketBuilder(final InstancePacket template) {
    this.template = makeClone(template);
  }
  
  /* (non-Javadoc)
   * @see ie.ucd.sensetile.sensorboard.simulator.PacketBuilder#getPacket()
   */
  public Packet getPacket() {
    InstancePacket packet = makeClone(template);
    readTemperature(packet);
    readPressure(packet);
    readLightLevel(packet);
    readAccelerometerX(packet);
    readAccelerometerY(packet);
    readAccelerometerZ(packet);
    for (
        int channelIndex = 0; 
        channelIndex < Frame.AUDIO_CHANNELS; 
        channelIndex++) {
      readAudio(channelIndex, packet);
    }
    return packet;
  }
  
  private void readTemperature(final InstancePacket packet) {
    if (temperature != null) {
      try {
        packet.setTemperature(temperature.readShort());
      } catch (IOException e) {
        closeInputStream(temperature);
        temperature = null;
      }
    }
  }
  
  private void readPressure(final InstancePacket packet) {
    if (pressure != null) {
      try {
        packet.setPressure(pressure.readShort());
      } catch (IOException e) {
        closeInputStream(pressure);
        pressure = null;
      }
    }
  }
  
  private void readLightLevel(final InstancePacket packet) {
    if (lightLevel != null) {
      try {
        packet.setLightLevel(lightLevel.readShort());
      } catch (IOException e1) {
        closeInputStream(lightLevel);
        lightLevel = null;
      }
    }
  }
  
  private void readAccelerometerX(final InstancePacket packet) {
    if (accelerometerX != null) {
      try {
        packet.setAccelerometerX(accelerometerX.readShort());
      } catch (IOException e1) {
        closeInputStream(accelerometerX);
        accelerometerX = null;
      }
    }
  }
  
  private void readAccelerometerY(final InstancePacket packet) {
    if (accelerometerY != null) {
      try {
        packet.setAccelerometerY(accelerometerY.readShort());
      } catch (IOException e1) {
        closeInputStream(accelerometerY);
        accelerometerY = null;
      }
    }
  }
  
  private void readAccelerometerZ(final InstancePacket packet) {
    if (accelerometerZ != null) {
      try {
        packet.setAccelerometerZ(accelerometerZ.readShort());
      } catch (IOException e1) {
        closeInputStream(accelerometerZ);
        accelerometerZ = null;
      }
    }
  }
  
  private void readAudio(final int channel, final InstancePacket packet) {
    if (audio[channel] != null) {
      try {
        for (int frameIndex = 0; frameIndex < Packet.FRAMES; frameIndex++) {
          ((InstanceFrame) packet.getFrame(frameIndex)).setAudio(
              channel, audio[channel].readChar());
        }
      } catch (IOException e) {
        closeInputStream(audio[channel]);
        audio[channel] = null;
      }
    }
  }
  
  private void closeInputStream(final InputStream inputStream) {
    try {
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Get real packet builder template.
   * 
   * @return packet template
   */
  public CloneablePacket getTemplate() {
    return template;
  }
  
  /**
   * Set InstancePacket as a template.
   * 
   * @param template packet template
   */
  public void setTemplate(final InstancePacket template) {
    this.template = template;
  }
  
  private InstancePacket makeClone(final InstancePacket template) {
    try {
      return (InstancePacket) template.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnsupportedOperationException();
    }
  }
  
  /**
   * Set temperature input stream.
   * 
   * @param is temperature input stream.
   */
  public void setTemperatureInputStream(final InputStream is) {
    temperature = new DataInputStream(is);
  }
  
  /**
   * Set pressure input stream.
   * 
   * @param is pressure input stream.
   */
  public void setPressureInputStream(final InputStream is) {
    pressure = new DataInputStream(is);
    
  }
  
  /**
   * Set light input stream.
   * 
   * @param is light input stream.
   */
  public void setLightLevelInputStream(final InputStream is) {
    lightLevel = new DataInputStream(is);
  }
  
  /**
   * Set x accelerometer input stream.
   * 
   * @param is x accelerometer input stream.
   */
  public void setAccelerometerXInputStream(final InputStream is) {
    accelerometerX = new DataInputStream(is);
  }
  
  /**
   * Set y accelerometer input stream.
   * 
   * @param is y accelerometer input stream.
   */
  public void setAccelerometerYInputStream(final InputStream is) {
    accelerometerY = new DataInputStream(is);
  }
  
  /**
   * Set z accelerometer input stream.
   * 
   * @param is z accelerometer input stream.
   */
  public void setAccelerometerZInputStream(final InputStream is) {
    accelerometerZ = new DataInputStream(is);
  }
  
  /**
   * Set audio input stream.
   * 
   * @param channel audio channel
   * @param is audio input stream
   */
  public void setAudioInputStream(final int channel, final InputStream is) {
    audio[channel] = new DataInputStream(is);
  }
}
