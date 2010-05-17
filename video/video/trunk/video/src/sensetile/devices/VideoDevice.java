/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.devices;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensetile.common.utils.Guard;

/**
 *
 * @author SenseTile
 */
public class VideoDevice implements IDevice
{

    // VERSION SHOULD BE UNKNOWN - BUT WE HAVE CHANGED DEFAULT BECAUSE OF CAPABILITY PROBLEM!
    private Version _version = Version.UNKNOWN;
    private Type _type = Type.UNKNOWN;
    private String _name = "";
    private File _file = null;
    private int _deviceFD = 0;
    final static int O_RDWR = 2;
    final static int VIDIOCGCAP = -2143521279;
    final static int VIDIOC_QUERYCAP = -2140645888;
    private boolean _isReady = Boolean.FALSE;

    public VideoDevice(final String fileName)
    {
        Guard.ArgumentNotNullOrEmptyString(fileName, "Device name cannot be a null or empty string.");
        _file = new java.io.File(fileName);
        openDevice();
        initDevice();
        closeDevice();
        _isReady = Boolean.TRUE;
    }

    private void openDevice()
    {
        _deviceFD = CLibrary.INSTANCE.open(_file.getAbsolutePath(), 4002);
    }
    
    private void initDevice()
    {
    try {
            Thread.sleep(300);

        } catch (InterruptedException ex)
        {
            Logger.getLogger(VideoDevice.class.getName()).log(Level.SEVERE, null, ex);
        }

        VideoCapability.ByReference vidCaps = new VideoCapability.ByReference();
        V4l2Capability.ByReference v4l2Caps = new V4l2Capability.ByReference();

        boolean isVidiocQueryCap =
                CLibrary.INSTANCE.ioctl(_deviceFD, VIDIOC_QUERYCAP, v4l2Caps) == 0;

        boolean isVidiocGCap =
                CLibrary.INSTANCE.ioctl(_deviceFD, VIDIOCGCAP, vidCaps) == 0;

        if (isVidiocQueryCap )
        {
            _type = Type.UNKNOWN;
            _version = Version.V4L2;
            if ((v4l2Caps.capabilities & 1) == 1)
            {
                _type = Type.OUTPUT;
            }
            _name = new String(v4l2Caps.card).trim();
        }
        else if (isVidiocGCap)
        {
            _type = Type.INPUT;
            _version = Version.V4L;
            if ((vidCaps.type & 1) == 1)
            {
                _type = Type.OUTPUT;
            }
            _name = new String(vidCaps.name);

            if (_name.indexOf(0) >= 0)
            {
                _name = _name.substring(0, _name.indexOf(0));
            }
        }

        if(!isVidiocQueryCap && !isVidiocGCap )
        {
            Logger.getLogger(VideoDevice.class.getName()).log(Level.SEVERE,
                    "Video Capability Problem: " +
                    "Current device is nether v4l2Caps nor vidCaps.");
        }
    }

    private void closeDevice()
    {
        CLibrary.INSTANCE.close(_deviceFD);
    }

    public Version getVersion()
    {
        return _version;
    }

    public Type getType()
    {
        return _type;
    }

    public String getName()
    {
        return _name;
    }

    public File getFile()
    {
        return _file;
    }

    public int getDeviceFD()
    {
        return _deviceFD;
    }

    public boolean isReady() {
        return _isReady;
    }

    public void setReady(boolean isReady) {
        _isReady = isReady;
    }

    public void setName(final String name) {
        Guard.ArgumentNotNullOrEmptyString(name,
                "Name cannot be a null or empty string.");
        _name = name;
    }
}
