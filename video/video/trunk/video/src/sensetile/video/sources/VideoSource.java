package sensetile.video.sources;


import java.awt.image.BufferedImage;
import sensetile.common.sources.ISource;
import sensetile.common.utils.Guard;

import sensetile.video.controls.RGBListenerMediator;
import sensetile.video.sources.pipe.PipeProvider;
/**
 *
 * @author SenseTile
 */
public class VideoSource implements IVideoSource
{
    //v4l2src or v4lsrc
    private String _source = "";
    private int _width = IVideoSource.DEFAULT_WIDTH;
    private int _height = IVideoSource.DEFAULT_HEIGHT;
    private int _bpp = IVideoSource.DEFAULT_BPP;
    private int _depth = IVideoSource.DEFAULT_DEPTH;
    private int _frameRate = IVideoSource.DEFAULT_FRAME_RATE;
    private String _uuId = null;
    private String _location = null;
    private String _deviceName = null;
    private RGBListenerMediator rGBListenerMediator = null;
    private PipeProvider _provider;
    private boolean _isPlaying = Boolean.FALSE;
    private boolean _selected = Boolean.FALSE;
    private boolean _isReadyToProcess = Boolean.FALSE;


    public static VideoSource createVideoSource(
                       final String source,
                        final String location,
                        final String deviceName)
    {
        Guard.ArgumentNotNullOrEmptyString(source,
                "Source name cannot be a null or an empty string.");
        Guard.ArgumentNotNullOrEmptyString(location,
                "Location name cannot be a null or an empty string.");
        Guard.ArgumentNotNullOrEmptyString(deviceName,
                "Device name cannot be a null or an empty string.");
        return new VideoSource(source, location, deviceName);
    }

    private VideoSource(final String source,
                        final String location,
                        final String deviceName)
    {
        _source = source;
        rGBListenerMediator = new RGBListenerMediator();
        _location = location;
        _provider = new PipeProvider();
        _deviceName = deviceName;
        _uuId = java.util.UUID.randomUUID().toString();
    }

    @Override
    public int getWidth() {
        return _width;
    }

    @Override
    public void setWidth(final int width) {
        _width = width;
    }

    @Override
    public int getHeight() {
        return _height;
    }

    @Override
    public void setHeight(final int height)
    {
        _height = height;
    }

    @Override
    public int getBpp()
    {
        return _bpp;
    }

    @Override
    public void setBpp(final int bpp)
    {
        _bpp = bpp;
    }

    @Override
    public int getDepth()
    {
        return _depth;
    }

    @Override
    public void setDepth(final int depth)
    {
        _depth = depth;
    }

    @Override
    public int getFrameRate()
    {
        return _frameRate;
    }

    @Override
    public void setFrameRate(final int frameRate)
    {
        _frameRate = frameRate;
    }

    @Override
    public void stopSource()
    {
        if(!isReadyToProcess())
        {
            return;
        }
        _provider.stopPipe();
        _isPlaying = Boolean.FALSE;
    }

    @Override
    public void startSource()
    {
        if(! isReadyToProcess())
        {
            return;
        }
       _provider.initializePipe(this);
       _provider.startPipe();
       _isPlaying = Boolean.TRUE;
    }

    @Override
    public boolean isPlaying()
    {
        return _isPlaying;
    }

    @Override
    public String getSource()
    {
        return _source;
    }

    @Override
    public String getUUID()
    {
        return _uuId;
    }

    @Override
    public String getDeviceName() {
        return _deviceName;
    }

    @Override
    public String getLocation()
    {
        return _location;
    }

    @Override
    public BufferedImage getImage()
    {
        return getRGBMediatorListener().getCurrentImage();
    }


    @Override
     public int[] getPixels()
     {
         return getRGBMediatorListener().getPixels();
     }

    @Override
    public RGBListenerMediator getRGBMediatorListener()
    {
        return rGBListenerMediator;
    }

    @Override
    public void pauseSource()
    {
        if(!isReadyToProcess())
        {
            return;
        }
        _provider.pausePipe();
        _isPlaying = Boolean.FALSE;
    }
    @Override
    public void setSelected(boolean selected) {
        _selected = selected;
    }

    @Override
    public boolean isPaused() 
    {
       return _provider.isPaused();
    }

    @Override
    public boolean isSelected()
    {
        return _selected;
    }

    @Override
    public void play()
    {
        if(!isReadyToProcess())
        {
            return;
        }
        _provider.play();
        _isPlaying = Boolean.TRUE;
    }

    @Override
    public void reset()
    {
        rGBListenerMediator = new RGBListenerMediator();
    }
    
    @Override
    public boolean isEqual(final ISource targetSource) 
    {
        Guard.ArgumentNotNull(targetSource, "Target source cannot be a null.");
        boolean isEqual = Boolean.FALSE;
         boolean isNameIsEqual = targetSource.getDeviceName()
                    .equalsIgnoreCase(this.getDeviceName());
            if( isNameIsEqual && 
                    hasPathAs(targetSource.getLocation() ) )
            {
                isEqual = Boolean.TRUE;

            }
            return isEqual;
    }

    public boolean isReadyToProcess() {
        return _isReadyToProcess;
    }

    public void enableProcess(boolean isReadyToProcess) {
        _isReadyToProcess = isReadyToProcess;
        if(isReadyToProcess())
        {
            return;
        }
        else
        {
            stopSource();
        }
    }

    public boolean hasPathAs(final String path) {
         Guard.ArgumentNotNullOrEmptyString(path,
                "Path cannot be a null or empty string.");
        boolean isEqual = Boolean.FALSE;
            boolean isPathIsEqual = path.trim()
                    .equalsIgnoreCase(this.getLocation().trim());
            if( isPathIsEqual )
            {
                isEqual = Boolean.TRUE;

            }
            return isEqual;
    }

    public void setDeviceName(String deviceName)
    {
        Guard.ArgumentNotNullOrEmptyString(deviceName,
                "Device name cannot be a null or empty string.");
        _deviceName = deviceName;
    }
}
