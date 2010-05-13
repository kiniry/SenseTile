package ie.ucd.sensetile.tinyos.telos.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import net.tinyos.message.Message;
import net.tinyos.message.MessageListener;
import net.tinyos.message.MoteIF;
import net.tinyos.packet.BuildSource;
import net.tinyos.packet.PhoenixSource;
import net.tinyos.util.PrintStreamMessenger;

public class TelosService implements MessageListener {

  private String source;
  private int cycleTime;
  private int cycleMessagesMax;
  private boolean isActive;
  private TelosMessageListener listener;

  private final transient Logger logger = Logger.getLogger(this.getClass().getName());

  public TelosService() {
    setSource(SOURCE);
    setCycleTime(CYCLE_TIME); 
    setCycleMessagesMax(CYCLE_MESSAGES_MAX);
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    if(! isActive) {
      this.source = source;
    } else {
      throw new IllegalStateException("instance is active");
    }
  }

  public int getCycleTime() {
    return cycleTime;
  }

  public void setCycleTime(int cycleTime) {
    if(! isActive) {
      this.cycleTime = cycleTime;
    } else {
      throw new IllegalStateException("instance is active");
    }
  }

  public int getCycleMessagesMax() {
    return cycleMessagesMax;
  }

  public void setCycleMessagesMax(int cycleMessagesMax) {
    if(! isActive) {
      this.cycleMessagesMax = cycleMessagesMax;
    } else {
      throw new IllegalStateException("instance is active");
    }
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }

  public TelosMessageListener getListener() {
    return listener;
  }

  public void setListener(TelosMessageListener listener) {
    this.listener = listener;
  }

  private MoteIF moteIF;
  private PacketSender sender;
  private int countMessage = 0;
  private StringBuffer buffer;

  public boolean initialize() {
    isActive = true;
    buffer = new StringBuffer();
    initializeMif();
    getMoteIF().registerListener(new PrintfMsg(), this);
    initializeSender();
    return true;
  }

  private void initializeMif() {
    PhoenixSource phoenix = 
      BuildSource.makePhoenix(getSource(), PrintStreamMessenger.err);
    moteIF = new MoteIF(phoenix);
  }

  private void initializeSender() {
    sender = new PacketSender();
    new Thread(sender).start();
  }

  private MoteIF getMoteIF() {
    return moteIF;
  }

  private class PacketSender implements Runnable {

    @Override
    public void run() {
      int counter = 0;
      TelosMsg payload = new TelosMsg();
      try {
        while (isActive()) {
          String time = now("dd/MM/yyyy HH mm ss");
          logger.log(Level.INFO,
            "Packet[mode:SEND]: " + time + " 5 " + counter + " 0 0 0 ");
          payload.set_counter(counter);
          getMoteIF().send(0, payload);
          counter++;
          if (counter == 3) {
            counter = 1;
          }
          try {
            Thread.sleep(getCycleTime());
          } catch (InterruptedException exception) {
          }
        }
      } catch (Exception exception) {
        logger.log(Level.ERROR,
            "Exception thrown when sending packets. " + "Exiting:[ "
                + exception.getMessage() + " ]", exception);
      }
    }
  }

  @Override
  public void messageReceived(int to, Message rawMessage) {
    countMessage++;
    PrintfMsg message = (PrintfMsg) rawMessage;
    String time = now("dd/MM/yyyy HH mm ss");
    for (int i = 0; i < PrintfMsg.totalSize_buffer(); i++) {
      char nextChar = (char) (message.getElement_buffer(i));
      if (nextChar == 'z') {
        buffer.append(time);
      } else {
        if (nextChar != 0) {
          buffer.append(nextChar);
        }
      }
    }
    if (countMessage == getCycleMessagesMax()) {
      List<TelosMessage> list = parseBuffer(buffer);
      logger.log(Level.INFO,
          "Packets received and parsed: " + list.size());
      for (TelosMessage telosMessage : list) {
        sendMessage(telosMessage);
      }
      countMessage = 0;
      buffer = new StringBuffer();
    }
  }

  private void sendMessage(TelosMessage telosMessage) {
    if (getListener() != null ) {
      getListener().messageReceived(telosMessage);
    }
  }

  private List<TelosMessage> parseBuffer(final StringBuffer buffer) {
    StringTokenizer st = new StringTokenizer(buffer.toString(), MESSAGE_DELIMITER);
    List<TelosMessage> list = new ArrayList<TelosMessage>(st.countTokens());
    TelosMessageBuilder builder = new TelosMessageBuilder();
    while (st.hasMoreTokens()) {
      String nextToken = st.nextToken();
      builder.setMessageString(nextToken);
      try{
        TelosMessage message = builder.getMessage();
        if ( builder.isMessageValid() ) {
          list.add(message);
          logger.log(Level.INFO,
              "Packet[mode:VALID_RECEIVED]: " + message.toString());
        } else {
          logger.log(Level.DEBUG,
              "Packet[mode:INVALID_RECEIVED]: " + message.toString());
        }
      } catch (MessageParserException e) {
        logger.log(Level.INFO,
            "Packet : BAD received.");
      }
    }
    return list;
  }

  private static final String MESSAGE_DELIMITER = "\n";
  private static final int CYCLE_TIME = 60 * 1000;
  private static final String SOURCE = "serial@/dev/ttyUSB0:telosb";
  private static final int CYCLE_MESSAGES_MAX = 8;

  private static String now(String dateFormat) {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    return sdf.format(cal.getTime());
  }

}
