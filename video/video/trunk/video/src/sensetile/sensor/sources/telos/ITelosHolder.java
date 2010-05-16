package sensetile.sensor.sources.telos;

import java.util.Date;

/**
 *
 * @author SenseTile
 */
public interface ITelosHolder
{
    public static final int VALID_IDENTIFIER = 1;
    public static final ITelosHolder NONE = null;

    void initialize( final String message);

    Date getDate();

    String getHour();

    String getMinute();

    String getSecond();

    int getIdentifier() ;

    int getNodeId();
    
    double getTemperature();

    double getHumidity();
}
