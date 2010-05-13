package ie.ucd.sensetile.tinyos.telos.service;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintfMsg extends net.tinyos.message.Message
{
    /** The default size of this message type in bytes. */
    public static final int DEFAULT_MESSAGE_SIZE = 28;

    /** The Active Message type associated with this message. */
    public static final int AM_TYPE = 100;

    /** Create a new PrintfMsg of size 28. */
    public PrintfMsg() {
        super(DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /** Create a new PrintfMsg of the given data_length. */
    public PrintfMsg(int data_length)
    {
        super(data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new PrintfMsg with the given data_length
     * and base offset.
     */
    public PrintfMsg(int data_length, int base_offset)
    {
        super(data_length, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new PrintfMsg using the given byte array
     * as backing store.
     */
    public PrintfMsg(byte[] data)
    {
        super(data);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new PrintfMsg using the given byte array
     * as backing store, with the given base offset.
     */
    public PrintfMsg(byte[] data, int base_offset)
    {
        super(data, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new PrintfMsg using the given byte array
     * as backing store, with the given base offset and data length.
     */
    public PrintfMsg(byte[] data, int base_offset, int data_length)
    {
        super(data, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new PrintfMsg embedded in the given message
     * at the given base offset.
     */
    public PrintfMsg(net.tinyos.message.Message msg, int base_offset)
    {
        super(msg, base_offset, DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new PrintfMsg embedded in the given message
     * at the given base offset and length.
     */
    public PrintfMsg(net.tinyos.message.Message msg, int base_offset, int data_length)
    {
        super(msg, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
    /* Return a String representation of this message. Includes the
     * message type name and the non-indexed field values.
     */
    @Override
    public String toString() {
      String s = "Message <PrintfMsg> \n";
      try {
        s += "  [buffer=";
        for (int i = 0; i < 28; i++) {
          s += "0x"+Long.toHexString(getElement_buffer(i) & 0xff)+" ";
        }
        s += "]\n";
      }
      catch (ArrayIndexOutOfBoundsException aioobe) 
      {
          Logger.getLogger(PrintfMsg.class.getName()).
                log(Level.SEVERE, "Skip field");
      }
      return s;
    }

    // Message-type-specific access methods appear below.

    /////////////////////////////////////////////////////////
    // Accessor methods for field: buffer
    //   Field type: short[], unsigned
    //   Offset (bits): 0
    //   Size of each element (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'buffer' is signed (false).
     */
    public static boolean isSigned_buffer()
    {
        return false;
    }

    /**
     * Return whether the field 'buffer' is an array (true).
     */
    public static boolean isArray_buffer()
    {
        return true;
    }

    /**
     * Return the offset (in bytes) of the field 'buffer'
     */
    public static int offset_buffer(int index1)
    {
        int offset = 0;
        if (index1 < 0 || index1 >= 28) throw new ArrayIndexOutOfBoundsException();
        offset += 0 + index1 * 8;
        return (offset / 8);
    }

    /**
     * Return the offset (in bits) of the field 'buffer'
     */
    public static int offsetBits_buffer(int index1)
    {
        int offset = 0;
        if (index1 < 0 || index1 >= 28) throw new ArrayIndexOutOfBoundsException();
        offset += 0 + index1 * 8;
        return offset;
    }

    /**
     * Return the entire array 'buffer' as a short[]
     */
    public short[] get_buffer()
    {
        short[] tmp = new short[28];
        for (int index0 = 0; index0 < numElements_buffer(0); index0++)
        {
            tmp[index0] = getElement_buffer(index0);
        }
        return tmp;
    }

    /**
     * Set the contents of the array 'buffer' from the given short[]
     */
    public void set_buffer(short[] value)
    {
        for (int index0 = 0; index0 < value.length; index0++)
        {
            setElement_buffer(index0, value[index0]);
        }
    }

    /**
     * Return an element (as a short) of the array 'buffer'
     */
    public short getElement_buffer(int index1)
    {
        return (short)getUIntBEElement(offsetBits_buffer(index1), 8);
    }

    /**
     * Set an element of the array 'buffer'
     */
    public void setElement_buffer(int index1, short value)
    {
        setUIntBEElement(offsetBits_buffer(index1), 8, value);
    }

    /**
     * Return the total size, in bytes, of the array 'buffer'
     */
    public static int totalSize_buffer()
    {
        return (224 / 8);
    }

    /**
     * Return the total size, in bits, of the array 'buffer'
     */
    public static int totalSizeBits_buffer()
    {
        return 224;
    }

    /**
     * Return the size, in bytes, of each element of the array 'buffer'
     */
    public static int elementSize_buffer()
    {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of each element of the array 'buffer'
     */
    public static int elementSizeBits_buffer()
    {
        return 8;
    }

    /**
     * Return the number of dimensions in the array 'buffer'
     */
    public static int numDimensions_buffer()
    {
        return 1;
    }

    /**
     * Return the number of elements in the array 'buffer'
     */
    public static int numElements_buffer()
    {
        return 28;
    }

    /**
     * Return the number of elements in the array 'buffer'
     * for the given dimension.
     */
    public static int numElements_buffer(int dimension)
    {
      int array_dims[] = { 28,  };
        if (dimension < 0 || dimension >= 1)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (array_dims[dimension] == 0) 
        {
            throw new IllegalArgumentException("Array dimension "+dimension+" has unknown size");
        }
        return array_dims[dimension];
    }

    /**
     * Fill in the array 'buffer' with a String
     */
    public void setString_buffer(String s)
    {
         int len = s.length();
         int i;
         for (i = 0; i < len; i++)
         {
             setElement_buffer(i, (short)s.charAt(i));
         }
         setElement_buffer(i, (short)0); //null terminate
    }

    /**
     * Read the array 'buffer' as a String
     */
    public String getString_buffer()
    {
         char carr[] = new char[Math.min(net.tinyos.message.Message.MAX_CONVERTED_STRING_LENGTH,28)];
         int i;
         for (i = 0; i < carr.length; i++)
         {
             if ((char)getElement_buffer(i) == (char)0) break;
             carr[i] = (char)getElement_buffer(i);
         }
         return new String(carr,0,i);
    }
}
