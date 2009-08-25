/*
 * SenseTileException.java
 *
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */

package ie.ucd.sensetile.sensorboard;

public class SenseTileException extends Exception {
  
  public SenseTileException() {
    super();
  }
  
  public SenseTileException(final String message, final Throwable innerException) {
    super(message, innerException);
  }
  
  public SenseTileException(final String message) {
    super(message);
  }
  
  public SenseTileException(final Throwable innerException) {
    super(innerException);
  }
  
  private static final long serialVersionUID = -232360547537958698L;
  
}
