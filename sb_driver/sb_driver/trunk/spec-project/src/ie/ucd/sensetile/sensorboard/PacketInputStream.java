package ie.ucd.sensetile.sensorboard;

import java.io.IOException;

/**
 * A packet input stream lets an application read Packet instance data
 * from an underlying input stream.
 * 
 * <p> PacketInputStream is not safe for multithreaded access.
 *
 * @author Vieri del Bianco (vieri.delbianco@gmail.com)
 */
public interface PacketInputStream {

  /**
   * Returns an estimate of the number of packets that can be read from this 
   * input stream without blocking.
   *
   * @return an estimate of the number of bytes that can be read from this 
   * input stream without blocking.
   * @exception IOException if an I/O error occurs.
   */
  /*@ pure */ int availablePackets() throws IOException;
  
  /**
   * Reads some number of packets from the contained input stream and stores 
   * them into the array.
   * 
   * <p>The number of packets actually read is returned as an integer.
   * 
   * <p>If b is null, a NullPointerException is thrown. 
   * 
   * @param array the buffer into which the data is read.
   * @return the total number of packets read into the buffer.
   * @throws IOException I/O error
   * @throws SenseTileException data is malformed
   */
  int read(Packet[] array) throws IOException, SenseTileException;
  
  /**
   * Reads some number of packets from the contained input stream and stores 
   * them into the array.
   * 
   * <p>The number of packets actually read is returned as an integer.
   * <p>If b is null, a NullPointerException is thrown. 
   * 
   * <p>The first packet read is stored into element array[offset], the next 
   * one into array[offset+1], and so on. 
   * The number of packets read is, at most, equal to length. Let k be the 
   * number of packets actually read; these packets will be stored in elements 
   * array[offset] through array[offset+k-1].
   * 
   * @param array the buffer into which the data is read.
   * @param offset the start offset in the destination array.
   * @param length the maximum number of packets read. 
   * @return the total number of packets read into the buffer.
   * @throws IOException I/O error
   * @throws SenseTileException if an invalid packet is read.
   */
  int read(Packet[] array, int offset, int length)
      throws IOException, SenseTileException;
  
  /**
   * Reads the next packet from the input stream. 
   * 
   * <p>This method blocks until input data is available, or an exception is 
   * thrown.
   * <ul>
   *   <li>packet input is available, a normal return is made.</li>
   *   <li>end of file is detected, in which case an EOFException is 
   *   thrown.</li>
   *   <li>an I/O error occurs, in which case an IOException is thrown.</li>
   *   <li>an invalid packet is detected, in which case a SenseTileException 
   *   is thrown.</li>
   * </ul>
   *
   * @return the next packet of data.
   * @throws IOException if an I/O error occurs.
   * @throws SenseTileException if an invalid packet is read.
   */
  Packet read() throws IOException, SenseTileException;

  /**
   * Reads packets from an input stream.
   * 
   * <p>This method blocks until one of the following conditions occurs:
   * <ul>
   *   <li>array.length packets of input data are available, in which case a 
   *   normal return is made.</li>
   *   <li>end of file is detected, in which case an EOFException is 
   *   thrown.</li>
   *   <li>an I/O error occurs, in which case an IOException other than 
   *   EOFException is thrown.</li>
   *   <li>an invalid packet is detected, in which case a SenseTileException 
   *   is thrown.</li>
   * </ul>
   * 
   * If array is null, a NullPointerException is thrown. 
   * If offset is negative, or length is negative, or offset + length is 
   * greater than the length of the array, then an IndexOutOfBoundsException 
   * is thrown. 
   * The first byte packet is stored into element array[offset], the next one 
   * into array[offset+1], and so on. 
   * The number of packets read is equal to length.
   * 
   * @param array the buffer into which the data is read.
   * @throws IOException if an I/O error occurs.
   * @throws SenseTileException if an invalid packet is read.
   **/
  void readFully(Packet[] array) throws IOException,
      SenseTileException;
  
  /**
   * Reads length packets from an input stream.
   * 
   * <p>This method blocks until one of the following conditions occurs:
   * <ul>
   *   <li>array.length packets of input data are available, in which case a 
   *   normal return is made.</li>
   *   <li>end of file is detected, in which case an EOFException is 
   *   thrown.</li>
   *   <li>an I/O error occurs, in which case an IOException other than 
   *   EOFException is thrown.</li>
   *   <li>an invalid packet is detected, in which case a SenseTileException 
   *   is thrown.</li>
   * </ul>
   * 
   * If array is null, a NullPointerException is thrown. 
   * If offset is negative, or length is negative, or offset + length is 
   * greater than the length of the array, then an IndexOutOfBoundsException 
   * is thrown. 
   * The first byte packet is stored into element array[offset], the next one 
   * into array[offset+1], and so on. 
   * The number of packets read is equal to length.
   * 
   * @param array the buffer into which the data is read.
   * @param offset an int specifying the offset into the data.
   * @param length an int specifying the number of bytes to read.
   * @throws IOException if an I/O error occurs.
   * @throws SenseTileException if an invalid packet is read.
   **/
  void readFully(Packet[] array, int offset, int length)
      throws IOException, SenseTileException;
  
  void close() throws IOException;

}
