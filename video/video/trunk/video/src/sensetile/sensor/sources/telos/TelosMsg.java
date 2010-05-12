package sensetile.sensor.sources.telos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TelosMsg extends net.tinyos.message.Message
{

    /** The default size of this message type in bytes. */
    public static final int DEFAULT_MESSAGE_SIZE = 27;

    /** The Active Message type associated with this message. */
    public static final int AM_TYPE = 137;

    /** Create a new TestSerialMsg of size 2. */
    public TelosMsg()
    {
        super(DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /** Create a new TestSerialMsg of the given data_length. */
    public TelosMsg(int data_length)
    {
        super(data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new TestSerialMsg with the given data_length
     * and base offset.
     */
    public TelosMsg(int data_length, int base_offset)
    {
        super(data_length, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new TestSerialMsg using the given byte array
     * as backing store.
     */
    public TelosMsg(byte[] data)
    {
        super(data);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new TestSerialMsg using the given byte array
     * as backing store, with the given base offset.
     */
    public TelosMsg(byte[] data, int base_offset)
    {
        super(data, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new TestSerialMsg using the given byte array
     * as backing store, with the given base offset and data length.
     */
    public TelosMsg(byte[] data, int base_offset, int data_length)
    {
        super(data, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new TestSerialMsg embedded in the given message
     * at the given base offset.
     */
    public TelosMsg(net.tinyos.message.Message msg, int base_offset)
    {
        super(msg, base_offset, DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new TestSerialMsg embedded in the given message
     * at the given base offset and length.
     */
    public TelosMsg(net.tinyos.message.Message msg, int base_offset, int data_length)
    {
        super(msg, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
    /* Return a String representation of this message. Includes the
     * message type name and the non-indexed field values.
     */
    @Override
    public String toString()
    {
      String s = "Message <TestSerialMsg> \n";
      try 
      {
        s += "  [counter=0x"+Long.toHexString(get_counter())+"]\n";
      }
      catch (ArrayIndexOutOfBoundsException aioobe)
      {
           Logger.getLogger(TelosMsg.class.getName()).
                log(Level.SEVERE, "Skip field");
          /* Skip field */
      }
      return s;
    }

    // Message-type-specific access methods appear below.

    /////////////////////////////////////////////////////////
    // Accessor methods for field: counter
    //   Field type: int, unsigned
    //   Offset (bits): 0
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'counter' is signed (false).
     */
    public static boolean isSigned_counter()
    {
        return false;
    }

    /**
     * Return whether the field 'counter' is an array (false).
     */
    public static boolean isArray_counter()
    {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'counter'
     */
    public static int offset_counter()
    {
        return (0 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'counter'
     */
    public static int offsetBits_counter()
    {
        return 0;
    }

    /**
     * Return the value (as a int) of the field 'counter'
     */
    public int get_counter()
    {
        return (int)getUIntBEElement(offsetBits_counter(), 16);
    }

    public int get_temp()
    {
        return (int)getUIntBEElement(16, 16);
    }

    public int get_source()
    {
        return (int)getUIntBEElement(16*2, 16);
    }

    public int get_destination()
    {
        return (int)getUIntBEElement(16*3, 16);
    }

    public int get_msgseq()
    {
        return (int)getUIntBEElement(16*4, 16);
    }

    public int get_dataextra()
    {
        return (int)getUIntBEElement(16*5, 16);
    }

  
    public int get_neighbor1()
    {
        return (int)getUIntBEElement((16*6), 8);
    }


    public int get_neighbor2()
    {
        return (int)getUIntBEElement((16*6)+8, 8);
    }

    public int get_neighbor3()
    {
        return (int)getUIntBEElement((16*6)+16, 8);
    }
    public int get_neighbor4()
    {
        return (int)getUIntBEElement((16*6)+24, 8);
    }

    public int get_neighbor5()
    {
        return (int)getUIntBEElement((16*6)+32, 8);
    }

    public int get_neighborlink1()
    {
        return (int)getUIntBEElement((16*6)+40, 16);
    }

    public int get_neighborlink2()
    {
        return (int)getUIntBEElement((16*6)+56, 16);
    }

    public int get_neighborlink3()
    {
        return (int)getUIntBEElement((16*6)+72, 16);
    }

    public int get_neighborlink4()
    {
        return (int)getUIntBEElement((16*6)+88, 16);
    }

    public int get_neighborlink5()
    {
        return (int)getUIntBEElement((16*6)+104, 16);
    }

    /**
    * Set the value of the field 'counter'
    */
    public void set_counter(int value)
    {
        setUIntBEElement(offsetBits_counter(), 16, value);
    }
    /**
     * Return the size, in bytes, of the field 'counter'
     */

    public static int size_counter()
    {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'counter'
     */
    public static int sizeBits_counter()
    {
        return 16;
    }
}
