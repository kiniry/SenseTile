package ie.ucd.sensetile.sensorboard.simulator;

public class ClonePacketBuilder implements PacketBuilder {
  
  private CloneablePacket template;
  
  public ClonePacketBuilder(final CloneablePacket template) {
    this.template = makeClone(template);
  }

  public CloneablePacket getPacket() {
    return makeClone(template);
  }
  
  public CloneablePacket getTemplate() {
    return template;
  }
  
  public void setTemplate(final CloneablePacket template) {
    this.template = template;
  }
  
  private CloneablePacket makeClone(final CloneablePacket template) {
    try {
      return template.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnsupportedOperationException(e);
    }
  }
}
