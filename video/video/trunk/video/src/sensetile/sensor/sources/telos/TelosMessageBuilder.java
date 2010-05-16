package sensetile.sensor.sources.telos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class TelosMessageBuilder {

  private String messageString;
  private TelosMessage message;

  private List<String> tokens;

  public TelosMessageBuilder() {
  }

  public String getMessageString() {
    return messageString;
  }

  public void setMessageString(String messageString) {
    this.message = null;
    this.messageString = messageString;
  }

  public TelosMessage getMessage() throws MessageParserException {
    parseMessage();
    return message;
  }

  public boolean isMessageValid() {
    try {
      parseMessage();
    } catch (MessageParserException e) {
      return false;
    }
    boolean isValidHumidity = ( message.getHumidity() >= 0 );
    boolean isValidIdentifier = ( message.getIdentifier() == VALID_IDENTIFIER );
    return isValidIdentifier & isValidHumidity;
  }

  private void parseMessage() throws MessageParserException {
    if (messageString == null) {
      throw new MessageParserException(new NullPointerException());
    }
    if (message != null) {
      return;
    }
    extractTokens();
    buildMessage();
  }

  private void extractTokens() throws MessageParserException {
    try {
      StringTokenizer st = new StringTokenizer(messageString, TOKEN_DELIMITER);
      tokens = new ArrayList<String>(st.countTokens());
      while (st.hasMoreTokens()) {
        String nextToken = st.nextToken();
        tokens.add(nextToken);
      }
    } catch (Exception e) {
      throw new MessageParserException(e);
    }
    if (tokens.size() < 8) {
      throw new MessageParserException("wrong number of tokens: " + tokens.size());
    }
  }

  private void buildMessage() throws MessageParserException {
    try {
      message = new TelosMessage();
      message.setDate(getDate());
      message.setIdentifier(getIdentifier());
      message.setNodeId(getNodeId());
      message.setTemperature(getTemperature());
      message.setHumidity(getHumidity());
    } catch (NoSuchElementException e) {
      throw new MessageParserException(e);
    } catch (NumberFormatException e) {
      throw new MessageParserException(e);
    }
  }

  private static final int TOKEN_OFFSET_DATE = 0;
  private static final int TOKEN_OFFSET_HOUR = 1;
  private static final int TOKEN_OFFSET_MINUTE = 2;
  private static final int TOKEN_OFFSET_SECOND = 3;
  private static final int TOKEN_OFFSET_IDENTIFIER = 4;
  private static final int TOKEN_OFFSET_NODEID = 5;
  private static final int TOKEN_OFFSET_HUMIDITY = 6;
  private static final int TOKEN_OFFSET_TEMPERATURE = 7;
  private static final String TOKEN_DELIMITER = " ";
  private static final String DATE_DELIMITER = "/";

  private static final int VALID_IDENTIFIER = 1;

  private Date getDate() throws MessageParserException {
    String date = getTokenAt(TOKEN_OFFSET_DATE);
    StringTokenizer st = new StringTokenizer(date, DATE_DELIMITER);
    String day = st.nextToken();
    String month = st.nextToken();
    String year = st.nextToken();
    String hour = getTokenAt(TOKEN_OFFSET_HOUR);
    String minute = getTokenAt(TOKEN_OFFSET_MINUTE);
    String second = getTokenAt(TOKEN_OFFSET_SECOND);
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(
        Integer.parseInt(year), 
        Integer.parseInt(month) - 1, 
        Integer.parseInt(day), 
        Integer.parseInt(hour), 
        Integer.parseInt(minute),
        Integer.parseInt(second));
    return calendar.getTime();
  }

  private int getIdentifier() {
    return Integer.parseInt(getTokenAt(TOKEN_OFFSET_IDENTIFIER));
  }

  private int getNodeId() {
    return Integer.parseInt(getTokenAt(TOKEN_OFFSET_NODEID));
  }

  private double getHumidity() {
    int value = Integer.parseInt(getTokenAt(TOKEN_OFFSET_HUMIDITY));
    return -2.0468 + (0.0367 * value) + (-0.0000015955 * value * value);
  }

  private double getTemperature() {
    int value = Integer.parseInt(getTokenAt(TOKEN_OFFSET_TEMPERATURE));
    return -39.6 + (0.01 * value);
  }

  private String getTokenAt(final int index) {
    return tokens.get(index);
  }
}
