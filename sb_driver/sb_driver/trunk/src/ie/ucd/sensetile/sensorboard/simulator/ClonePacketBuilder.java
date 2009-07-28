package ie.ucd.sensetile.sensorboard.simulator;

public class ClonePacketBuilder implements PacketBuilder {
  
  private CloneablePacket template;
  
  public ClonePacketBuilder(final CloneablePacket template) {
    this.template = template;
  }
  
  public CloneablePacket getPacket() {
    try {
      return template.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnsupportedOperationException(e);
    }
  }
  
  public CloneablePacket getTemplate() {
    return template;
  }
  
  public void setTemplate(final CloneablePacket template) {
    this.template = template;
  }
  
}
