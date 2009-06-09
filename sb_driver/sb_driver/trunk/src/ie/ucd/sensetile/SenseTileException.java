/*
 * SenseTileException.java
 *
 * Copyright 2009 Vieri del Bianco, SenseTile, UCD. All rights reserved.
 */

package ie.ucd.sensetile;

public class SenseTileException extends Exception {
  
  public SenseTileException() {
    super();
  }
  
  public SenseTileException(String message, Throwable innerException) {
    super(message, innerException);
  }
  
  public SenseTileException(String message) {
    super(message);
  }
  
  public SenseTileException(Throwable innerException) {
    super(innerException);
  }
  
  private static final long serialVersionUID = -232360547537958698L;
  
}
