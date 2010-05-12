package sensetile.sensor.sources.telos;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import sensetile.common.utils.Guard;

/**
 * Represents an holder for Telos received data.
 * @author SenseTile
 */
public class TelosHolder implements ITelosHolder
{
    private static final String EMPTY = "";
    private String _date = EMPTY;
    private String _hour = EMPTY;
    private String _minute = EMPTY;
    private String _second = EMPTY;
    private String _identifier = EMPTY;
    private String _temperature = EMPTY;
    private String _humidity = EMPTY;
    private String _nodeId = EMPTY;
    private List<String> _tokens = null;

    public TelosHolder( )
    {
        
        _tokens = new ArrayList<String>();
    }

    @Override
    public void initialize(final String message )
    {
        Guard.ArgumentNotNullOrEmptyString(message, "Message should not be null or empty.");
        StringTokenizer st = new StringTokenizer(message, " ");
        while (st.hasMoreTokens())
        {
            String nextToken = st.nextToken();
            _tokens.add(nextToken);
        }
    }

    @Override
    public String getDate()
    {
        _date = getTokenAt(0);
        return _date;
    }
    
    @Override
    public String getHour()
    {
        _hour = getTokenAt(1);
        return _hour;
    }
    
    @Override
    public String getMinute()
    {
        _minute = getTokenAt(2);
        return _minute;
    }
    @Override
    public String getSecond()
    {
        _second = getTokenAt(3);
        return _second;
    }
    
    @Override
    public int getIdentifier()
    {
        _identifier = getTokenAt(4);
        return Integer.parseInt( _identifier );
    }

    @Override
    public String getNodeId()
    {
        _nodeId = getTokenAt(5);
        return _nodeId;
    }


    @Override
    public double getTemperature()
    {
        _temperature = getTokenAt(7);
        int temp = Integer.parseInt(_temperature);
        return -39.6+(0.01*temp);
    }

    @Override
    public double getHumidity()
    {
        _humidity = getTokenAt(6);
        int humi = Integer.parseInt(_humidity);
        return -2.0468+(0.0367*humi)+(-0.0000015955*humi*humi);
    }


    private String getTokenAt(final int index)
    {
        Guard.IndexOutOfBounds(_tokens, index);
        return _tokens.get(index);
    }

    @Override
    public String toString()
    {
        return new StringBuffer().append(" date: ").append(getDate()).
                          append(" hour: ").append(getHour()).
                          append(" min: ").append(getMinute()).
                          append(" sec: ").append(getSecond()).
                          append(" id: ").append( getNodeId()).
                          append(" temp: ").append(getTemperature()).
                          append(" hum: ").append(getHumidity()).toString();
    }
}
