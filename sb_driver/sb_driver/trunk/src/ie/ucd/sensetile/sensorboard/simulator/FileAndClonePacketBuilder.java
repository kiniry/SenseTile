package ie.ucd.sensetile.sensorboard.simulator;

public class FileAndClonePacketBuilder implements PacketBuilder {
  
  private CloneablePacket template;
  
  public FileAndClonePacketBuilder(final CloneablePacket template) {
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
