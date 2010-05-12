package sensetile.sensor.sources.telos;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.tinyos.message.*;
import net.tinyos.packet.*;
import net.tinyos.util.*;
import sensetile.common.messages.DeviceMessage;
import sensetile.common.services.BroadcasterService;
import sensetile.common.messages.IMessage;
import sensetile.common.messages.MessageType;
import sensetile.common.messages.PacketMessage;
import sensetile.common.utils.Guard;
import sensetile.devices.TelosDevice;

public class TelosService implements MessageListener, Runnable
{
    private MoteIF moteIF;
    public static final int MAX_MESSAGE_PER_CYCLE = 8;
    private String time;
    private ITelosHolder holder = ITelosHolder.NONE;
    private StringBuffer buffer = null;
    private int countMessage = 0;

      public static String now(String dateFormat)
      {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());

      }
      public TelosService(MoteIF moteIF)
      {
        this.moteIF = moteIF;
        buffer = new StringBuffer();
        this.moteIF.registerListener(new PrintfMsg(), this);
        new Thread(this).start();
      }

      
      public void sendPacket()
      {
        int counter = 0;
        int transmitonce=0;
        TelosMsg payload = new TelosMsg();
        try
        {
          while (Boolean.TRUE)
          {
            int second = Integer.parseInt(TelosService.now("ss"));
            if (second==0)
            {
                if (transmitonce==0)
                {
                    String t=PrintfClient.now("dd/MM/yyyy HH mm ss");
                    Logger.getLogger(TelosService.class.getName()).log(Level.INFO,
                            "Packet[mode:SEND]: " + t + " 5 " + counter + " 0 0 0 ");

                    payload.set_counter(counter);
                    moteIF.send(0, payload);
                    counter++;
                    if (counter==3)
                    {
                        counter=1;
                    }
                    transmitonce=1;
                }
            }
            else
            {
                transmitonce=0;
            }
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException exception)
            {
                // do nothing.
            }
          }
        }

        catch ( Exception exception )
        {
            // EXCEPTION FROM moteIF.send REASON - DEVICE DICONNECTED !!!!
            Logger.getLogger(TelosService.class.getName()).
                    log(Level.SEVERE, "Exception thrown when sending packets. " +
                    "Exiting:[ " + exception.getMessage() + " ]", exception);
            // SEND ONE ERROR-MESSAGE URGENT !
          errorBroadcasting();
        }
     }


      private void errorBroadcasting()
      {
          TelosDevice DUMMY_DEVICE = new TelosDevice("/dev/ttyUSB0");
          IMessage DUMMY_MESSAGE = DeviceMessage.
                  createDeviceMessage(DUMMY_DEVICE, MessageType.Validity.INVALID);
          BroadcasterService.getInstance().broadcastMessage(DUMMY_MESSAGE);
           Logger.getLogger(TelosService.class.getName()).
                        log(Level.SEVERE, "Message broadcasted with DUMMY TelosDevice object.");
      }


    @Override
      public void messageReceived(int to, Message message)
      {
          countMessage ++;
           
          PrintfMsg msg = (PrintfMsg)message;
            String data="";
            String time=PrintfClient.now("dd/MM/yyyy HH mm ss");
            for(int i=0; i<msg.totalSize_buffer(); i++)
            {
              char nextChar = (char)(msg.getElement_buffer(i));

              if (nextChar == 'z')
              {
                  buffer.append(time);
              }
              else
              {
                 if(nextChar != 0)
                 {
                     buffer.append(nextChar);
                 }
              }
            }
        if (countMessage == MAX_MESSAGE_PER_CYCLE)
        {
           List<ITelosHolder> telosList = createValidTelosHolderList(buffer);
           for(ITelosHolder h : telosList) 
           {
               IMessage telosMessage =PacketMessage.
                       createMessage(h, MessageType.PacketType.PACKET_RECEIVED_CORRECTLY);
               
               BroadcasterService.getInstance().broadcastMessage(telosMessage);
           }
           
           countMessage = 0;
           buffer = new StringBuffer();
        }
      }
    private List<ITelosHolder> createValidTelosHolderList( final StringBuffer buffer)
    {
        // defense !
        if( buffer == null || buffer.toString().equalsIgnoreCase("") )
        {
            throw new InvalidTelosMessageException( "Input buffer cannot be a null or empty.");
        }
        List<ITelosHolder> telosList = new ArrayList<ITelosHolder>();
        String bufferedMessage = buffer.toString();
        StringTokenizer st = new StringTokenizer(bufferedMessage,"\n");
        while (st.hasMoreTokens())
        {
            String nextToken = st.nextToken();
            if( nextToken != null || !nextToken.equalsIgnoreCase(""))
            {
                ITelosHolder telosHolder = new TelosHolder();
                telosHolder.initialize(nextToken);
                if( isValidHolder (telosHolder))
                {
                    Logger.getLogger(TelosService.class.getName()).log(Level.INFO,
                                    "Packet[mode:VALID_RECEIVED]: " + telosHolder.toString());
                    telosList.add(telosHolder);
                }
            }
        }

        return telosList;
    }
    private boolean isValidHolder( final ITelosHolder holder )
    {
        boolean isValid = Boolean.FALSE;
        boolean isValidHumidity = holder.getHumidity() >=0;
        boolean isValidIdentifier = holder.getIdentifier() ==
                ITelosHolder.VALID_IDENTIFIER;
        if(isValidIdentifier && isValidHumidity )
        {
            isValid = Boolean.TRUE;
        }
        return isValid;
    }

      private static void usage()
      {
        Logger.getLogger(TelosService.class.getName()).
                log(Level.SEVERE, "usage: TestSerial [-comm <source>]");
      }

      public static TelosService create(String[] args ) {
        Guard.ArgumentNotNull(args, "Telos command cannot be a null.");
        String source = null;
        if (args.length == 2)
        {
          if (!args[0].equals("-comm"))
          {
            usage();
            throw new InvalidTelosCommandException("Telos create method: " +
                    "command does not contains -comm argument.");
          }
          source = args[1];
        }
        else if (args.length != 0)
        {
          usage();
          throw new InvalidTelosCommandException("Telos create method:" +
                  " Length is not appropriate.");
        }
        PhoenixSource phoenix;
        if (source == null)
        {
          phoenix = BuildSource.makePhoenix(PrintStreamMessenger.err);
        }
        else
        {
          phoenix = BuildSource.makePhoenix(source, PrintStreamMessenger.err);
        }

        MoteIF mif = new MoteIF(phoenix);
        TelosService serial = new TelosService(mif);
        return serial;
      }

    @Override
    public void run() 
    {
        Logger.getLogger(TelosService.class.getName()).
                log(Level.INFO, "TELOS SENSOR [PACKET SENT]");
        this.sendPacket();
    }
}
