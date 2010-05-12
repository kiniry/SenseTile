package sensetile.sensor.sources.telos;

import java.util.List;
import sensetile.common.sources.ISource;
import sensetile.common.utils.Guard;
import sensetile.devices.DeviceDetectorService;
import sensetile.devices.TelosDevice;

/**
 * @author SenseTile
 */
public class TelosSource implements ISource
{
    public static final TelosSource NONE = null;
    protected int _width = 0;
    protected int _height = 0;
    protected int _showAtX = 0;
    protected int _showAtY = 0;
    private String _deviceName = null;
    private String _deviceComm = "";
    private String _deviceCommand = "";
    private static final String TTY_USB_0 = "/dev/ttyUSB0";
    private boolean _isPlaying = Boolean.FALSE;
    private boolean _isPaused = Boolean.FALSE;
    private boolean _isSelected = Boolean.FALSE;
    private String _uuId = null;
    private TelosService _service = null;
    
    private TelosSource(final TelosDevice telosDevice)
    {
        initialize(telosDevice);
         _uuId = java.util.UUID.randomUUID().toString();
    }

    private void initialize(final TelosDevice telosDevice)
    {
        
            _deviceName = telosDevice.getName();
            _deviceComm = "-comm";
            _deviceCommand ="serial@/dev/ttyUSB0:telosb";
    }

    public static TelosSource createTelos()
    {
        TelosSource telosSource = null;
        DeviceDetectorService _detectorService = DeviceDetectorService.getInstance();
        _detectorService.initialize();
        List<TelosDevice> telosList = _detectorService.getOutputTTYDevices();
        if(!telosList.isEmpty())
        {
            telosSource = new TelosSource(telosList.get(0));
        }
        return telosSource;
    }

   @Override
   public String getDeviceName()
   {
       return _deviceName;
   }

   public String[] getTelosCommand()
   {
       return new String[]{_deviceComm,_deviceCommand};
   }

    @Override
    public String getUUID()
    {
        return _uuId;
    }

    @Override
    public int getWidth()
    {
        return _width;
    }

    @Override
    public void setWidth(final int outputWidth)
    {
        _width = outputWidth;
    }

    @Override
    public int getHeight()
    {
        return _height;
    }

    @Override
    public void setHeight(final int outputHeight)
    {
        _height = outputHeight;
    }

    @Override
    public boolean isPaused()
    {
        return _isPaused;
    }

    @Override
    public boolean isPlaying()
    {
        return _isPlaying;
    }

    @Override
    public String getLocation()
    {
        return TTY_USB_0;
    }
    
    @Override
    public String toString()
    {
        return _deviceName;
    }

    @Override
    public void setSelected(final boolean v)
    {
        _isSelected = v;
    }

    @Override
    public boolean isSelected()
    {
        return _isSelected;
    }

    @Override
    public void stopSource()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void pauseSource()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void startSource()
    {
        if(_service == null)
        {

        }
        final String[]  telosCommand = TelosSource.createTelos().getTelosCommand();
        _service = TelosService.create(telosCommand);
        _isPlaying = Boolean.TRUE;

    }

    @Override
    public void play()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEqual(final ISource targetSource) 
    {
        Guard.ArgumentNotNull(targetSource, "Target source cannot be a null.");
        boolean isEqual = Boolean.FALSE;
         boolean isNameIsEqual = targetSource.getDeviceName()
                    .equalsIgnoreCase(this.getDeviceName());
            if( isNameIsEqual && 
                hasPathAs(targetSource.getLocation()))
            {
                isEqual = Boolean.TRUE;

            }
            return isEqual;
    }

    @Override
    public boolean hasPathAs(final String path)
    {
        Guard.ArgumentNotNullOrEmptyString(path,
                "Path cannot be a null or empty string.");
        boolean isEqual = Boolean.FALSE;
        boolean isPathIsEqual = path
                    .equalsIgnoreCase(this.getLocation());
        if( isPathIsEqual )
        {
            isEqual = Boolean.TRUE;

        }
        return isEqual;
    }
}
