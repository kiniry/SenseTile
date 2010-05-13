package ie.ucd.sensetile.tinyos.telos.service;

public class MessageParserException extends RuntimeException {

  private static final long serialVersionUID = 2866557404184941619L;

  public MessageParserException() {
    super();
  }

  public MessageParserException(String message, Throwable cause) {
    super(message, cause);
  }

  public MessageParserException(String message) {
    super(message);
  }

  public MessageParserException(Throwable cause) {
    super(cause);
  }

}
