package sensetile.sensor.sources.telos;

import java.util.Date;

public class TelosMessage implements ITelosHolder{

  private Date date;
  private int identifier;
  private int nodeId;
  private double humidity;
  private double temperature;

  public TelosMessage() {
    date = new Date();
    identifier = 0;
    nodeId = 0;
    humidity = 0.;
    temperature = 0.;
  }
  
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getIdentifier() {
    return identifier;
  }

  public void setIdentifier(int identifier) {
    this.identifier = identifier;
  }

  public int getNodeId() {
    return nodeId;
  }

  public void setNodeId(int nodeId) {
    this.nodeId = nodeId;
  }

  public double getHumidity() {
    return humidity;
  }

  public void setHumidity(double humidity) {
    this.humidity = humidity;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  @Override
  public String toString() {
    return new StringBuffer()
        .append(" date: ").append(getDate())
        .append(" nodeId: ").append(getNodeId())
        .append(" temperature: ").append(getTemperature())
        .append(" humidity: ").append(getHumidity())
        .toString();
  }

    public void initialize(String message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   

    public String getHour() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getMinute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getSecond() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  

}
