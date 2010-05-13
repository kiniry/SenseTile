package ie.ucd.sensetile.gsn.telos.wrapper;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import gsn.beans.AddressBean;
import gsn.beans.DataField;
import gsn.beans.DataTypes;
import gsn.wrappers.AbstractWrapper;
import ie.ucd.sensetile.tinyos.telos.service.TelosMessage;
import ie.ucd.sensetile.tinyos.telos.service.TelosMessageListener;
import ie.ucd.sensetile.tinyos.telos.service.TelosService;

public class TelosWrapper extends AbstractWrapper implements TelosMessageListener{

  public static final String SOURCE_PARAMETER_NAME = "source";
  private static final String SOURCE_DEFAULT = "serial@/dev/ttyUSB0:telosb";
  public static final String CYCLE_TIME_PARAMETER_NAME = "cycle-time";
  private static final int CYCLE_TIME_DEFAULT = 30 * 1000;
  public static final String CYCLE_MESSAGES_MAX_PARAMETER_NAME = "cycle-messages-max";
  private static final int CYCLE_MESSAGES_MAX_DEFAULT = 8;

  private final transient Logger logger = Logger.getLogger(this.getClass().getName());
  private TelosService service;

  @Override
  public void dispose() {
    service.setActive(false);
  }

  @Override
  public DataField[] getOutputFormat() {
    DataField[] outputFormat = new DataField[4];
    outputFormat[0] = new DataField("NodeID", DataTypes.INTEGER_NAME, "Reading NodeID");
    outputFormat[1] = new DataField("Humidity", DataTypes.DOUBLE_NAME, "Reading humidity");
    outputFormat[2] = new DataField("Temperature", DataTypes.DOUBLE_NAME, "Reading temperature");
    outputFormat[3] = new DataField("Date", DataTypes.BIGINT_NAME, "Reading time");
    return outputFormat;
  }

  @Override
  public String getWrapperName() {
    return TelosWrapper.class.getName();
  }

  @Override
  public boolean initialize() {
    boolean result = false;
    try {
      result = initializeService();
    } catch (Exception e) {
      logger.log(Level.ERROR, 
          "initialization failed\n " + 
          "source: " + service.getSource(), 
          e);
      return false;
    }
    if (result) {
      logger.log(Level.INFO, 
          "initialization succesfull\n " + 
          "source: " + service.getSource());
    } else {
      logger.log(Level.ERROR, 
        "initialization failed\n " + 
        "source: " + service.getSource());
    }
    return result;
  }

  private boolean initializeService() {
    service = new TelosService();
    service.setListener(this);
    configureServiceWithParameters();
    boolean result = service.initialize();
    return result;
  }

  private void configureServiceWithParameters() {
    AddressBean address = getActiveAddressBean();
    if(address == null) {
      logger.log(Level.WARN, 
          "active AddressBean is null");
      return;
    }
    service.setSource(
        address.getPredicateValueWithDefault(SOURCE_PARAMETER_NAME, SOURCE_DEFAULT));
    logger.log(Level.DEBUG, 
        "telos service source is: " + service.getSource());
    service.setCycleTime(
        address.getPredicateValueAsInt(CYCLE_TIME_PARAMETER_NAME, CYCLE_TIME_DEFAULT));
    logger.log(Level.DEBUG, 
        "telos service cycle time is: " + service.getCycleTime());
    service.setCycleMessagesMax(
        address.getPredicateValueAsInt(CYCLE_MESSAGES_MAX_PARAMETER_NAME, CYCLE_MESSAGES_MAX_DEFAULT));
    logger.log(Level.DEBUG, 
        "telos service cycle message max is: " + service.getCycleMessagesMax());
  }

  @Override
  public void messageReceived(TelosMessage message) {
    logger.log(Level.DEBUG, 
        "message received");
    postStreamElement(
        message.getNodeId(),
        message.getHumidity(),
        message.getTemperature(), 
        message.getDate().getTime());
  }

}
