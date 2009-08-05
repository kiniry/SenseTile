package ie.ucd.sensetile.sensorboard.simulator;

import ie.ucd.sensetile.sensorboard.Packet;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister.Pack;

public class FileAndClonePacketBuilder implements PacketBuilder {
  
  private InstancePacket template;
  private DataInputStream temperature;
  private DataInputStream pressure;
  private DataInputStream lightLevel;
  private DataInputStream accelerometerX;
  private DataInputStream accelerometerY;
  private DataInputStream accelerometerZ;
  private final DataInputStream[] audio = new DataInputStream[4];
  
  public FileAndClonePacketBuilder(final InstancePacket template) {
    this.template = makeClone(template);
  }
  
  public InstancePacket getPacket() {
    InstancePacket packet = makeClone(template);
    readTemperature(packet);
    readPressure(packet);
    readLightLevel(packet);
    readAccelerometerX(packet);
    readAccelerometerY(packet);
    readAccelerometerZ(packet);
    for (
        int channelIndex = 0; 
        channelIndex < Packet.Frame.AUDIO_CHANNNELS; 
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
          packet.getFrame(frameIndex).setAudio(
              channel, audio[channel].readChar());
        }
      } catch (IOException e1) {
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
  
  public CloneablePacket getTemplate() {
    return template;
  }
  
  public void setTemplate(final InstancePacket template) {
    this.template = template;
  }
  
  private InstancePacket makeClone(final InstancePacket template) {
    try {
      return template.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnsupportedOperationException(e);
    }
  }
  
  public void setTemperatureInputStream(final InputStream is) {
    temperature = new DataInputStream(is);
  }
  
  public void setPressureInputStream(final InputStream is) {
    pressure = new DataInputStream(is);
    
  }
  
  public void setLightLevelInputStream(final InputStream is) {
    lightLevel = new DataInputStream(is);
  }
  
  public void setAccelerometerXInputStream(final InputStream is) {
    accelerometerX = new DataInputStream(is);
  }
  
  public void setAccelerometerYInputStream(final InputStream is) {
    accelerometerY = new DataInputStream(is);
  }
  
  public void setAccelerometerZInputStream(final InputStream is) {
    accelerometerZ = new DataInputStream(is);
  }
  
  public void setAudioInputStream(final int channel, final InputStream is) {
    audio[channel] = new DataInputStream(is);
  }
}
