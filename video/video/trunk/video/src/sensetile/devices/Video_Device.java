/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensetile.devices;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensetile.common.utils.Guard;
import sensetile.common.utils.ListUtils;

/**
 * @author SenseTile
 */
public class Video_Device {

    private Version _version = Version.UNKNOWN;
    private Type _type = Type.UNKNOWN;
    private String _name = "";
    private File _file = null;
    private int _deviceFD = 0;
    final static int O_RDWR = 2;
    final static int VIDIOCGCAP = -2143521279;
    final static int VIDIOC_QUERYCAP = -2140645888;

    public enum Type
    {
        INPUT,
        OUTPUT,
        UNKNOWN;
    }

    public enum Version
    {
        V4L,
        V4L2,
        UNKNOWN;
    }
    public static Video_Device createVideoDevice(final String name)
    {
        Guard.ArgumentNotNullOrEmptyString(name, "Name cannot be a null or an empty string.");
        return new Video_Device(name);
    }
    private Video_Device(final String fileName)
    {
        _file = new java.io.File(fileName);
        openDevice();
        initDevice();
        closeDevice();
    }

    private void openDevice()
    {
        _deviceFD = CLibrary.INSTANCE.open(_file.getAbsolutePath(), 4002);
    }

    /**
     * This method initializes video device. There is a two important
     * capabilities.
     * VIDIOC_QUERYCAP -- Query device capabilities.
     * All V4L2 devices support the VIDIOC_QUERYCAP ioctl.
     * It is used to identify kernel devices compatible with this specification and to obtain
     * information about driver and hardware capabilities.
     * The ioctl takes a pointer to a struct v4l2_capability which is filled by the driver.
     * When the driver is not compatible with this specification the ioctl
     * returns an EINVAL error code.
     * Detection of a V4L device is done using the VIDIOCGCAP ioctl().
     */
    private void initDevice()
    {
    try {
            Thread.sleep(300);
            
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Video_Device.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Video_Device.class.getName()).log(Level.SEVERE,
                    "Video Capability Problem: Current device is nether v4l2Caps nor vidCaps.");
        }
    }

    public synchronized  static List<Video_Device> getInputDevices()
    {
        List<Video_Device> inputDevices = new ArrayList<Video_Device>();
        List<Video_Device> dAll = new ArrayList<Video_Device>();
        List<Video_Device> devices = new ArrayList<Video_Device>();
        ListUtils.addToTypedList(dAll,getDevices().toArray());
        for (int i = 0; i < dAll.size(); i++)
        {
            Video_Device currentDevice = dAll.get(i);
            final boolean isInputDevice = currentDevice.getType() == Type.INPUT;
            if (isInputDevice)
            {
                devices.add(currentDevice);
            }
        }
        ListUtils.addToTypedList(inputDevices,devices.toArray());
        devices.clear();
        devices = null;
        return inputDevices;
    }

  

    public synchronized static List<Video_Device> getOutputDevices()
    {
        List<Video_Device> outputDevices = new ArrayList<Video_Device>();
        List<Video_Device> dAll = new ArrayList<Video_Device>();
        List<Video_Device> devices = new ArrayList<Video_Device>();
        ListUtils.addToTypedList(dAll,getDevices().toArray());
        for (int i = 0; i < dAll.size(); i++)
        {
            Video_Device currentDevice = dAll.get(i);
            final boolean isOutputDevice =
                    currentDevice.getType() == Type.OUTPUT;

            if (isOutputDevice)
            {
                devices.add(currentDevice);
            }
        }
        ListUtils.addToTypedList(outputDevices,devices.toArray());
        devices.clear();
        devices = null;
        return outputDevices;
    }

    public synchronized  static List<Video_Device> getDevices()
    {
        List<Video_Device> allDevices = new ArrayList<Video_Device>();
        File[] fs = new File("/dev/").listFiles();
        List<Video_Device> devices = new ArrayList<Video_Device>();
        for (int i = 0; i < fs.length; i++)
        {
            if (fs[i].getName().startsWith("video") && !fs[i].isDirectory())
            {
                devices.add(Video_Device.createVideoDevice(fs[i].getAbsolutePath()));
            }
        }
        ListUtils.addToTypedList(allDevices,devices.toArray());
        devices = null;
        return allDevices;
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

    @Override
    public String toString() {
        return _name;
    }
}
