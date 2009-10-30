/*
 * SenseTileException.java
 *
 * Copyright 2009 SenseTile, UCD. All rights reserved.
 */

package ie.ucd.sensetile.sensorboard;

/**
 * SenseTile exception.
 * 
 * @author Vieri del Bianco (vieri.delbianco@gmail.com)
 */
public class SenseTileException extends Exception {
  
  /**
   * SenseTile exception constructor.
   */
  public SenseTileException() {
    super();
  }
  
  /**
   * SenseTile exception constructor.
   * 
   * @param message text message
   * @param innerException root cause
   */
  public SenseTileException(
      final String message, final Throwable innerException) {
    super(message, innerException);
  }
  
  /**
   * SenseTile exception constructor.
   * 
   * @param message text message
   */
  public SenseTileException(final String message) {
    super(message);
  }
  
  /**
   * SenseTile exception constructor.
   * 
   * @param innerException root cause
   */
  public SenseTileException(final Throwable innerException) {
    super(innerException);
  }
  
  private static final long serialVersionUID = -232360547537958698L;
  
}
