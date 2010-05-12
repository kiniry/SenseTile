package sensetile.sensor.sources.telos;
/**
 *
 * @author SenseTile
 */
public interface ITelosHolder
{
    public static final int VALID_IDENTIFIER = 1;
    public static final ITelosHolder NONE = null;

    void initialize( final String message);

    String getDate();

    String getHour();

    String getMinute();

    String getSecond();

    int getIdentifier() ;

    String getNodeId();
    
    double getTemperature();

    double getHumidity();
}
