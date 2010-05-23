package sensetile.sensor.sources.telos;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import net.tinyos.message.*;

public class PrintfClient implements MessageListener
{
  private MoteIF moteIF;
  public static String now(String dateFormat)
  {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    return sdf.format(cal.getTime());
  }

  public static PrintfClient createPrintfClient(MoteIF moteIF)
  {
      assert moteIF != null : "MoteIF object cannot be a null.";
      return new PrintfClient(moteIF);
  }
  private PrintfClient(MoteIF moteIF)
  {
    this.moteIF = moteIF;
    this.moteIF.registerListener(new PrintfMsg(), this);
  }

    @Override
  public void messageReceived(int to, Message message)
  {
    PrintfMsg msg = (PrintfMsg)message;
    String data="";
    String time=PrintfClient.now("yyyy.MMMMM.dd HH mm ss");
    for(int i=0; i<msg.totalSize_buffer(); i++)
    {
      char nextChar = (char)(msg.getElement_buffer(i));
      if(nextChar != 0)
      {
        data=data+nextChar;
      }
    }
  }
}
