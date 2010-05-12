
package sensetile.devices;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensetile.common.services.BroadcasterService;
import sensetile.common.messages.DeviceMessage;
import sensetile.common.messages.IMessage;
import sensetile.common.utils.ListUtils;
import sensetile.devices.IDevice.Type;
import java.io.FilenameFilter;
import sensetile.common.messages.MessageType.Validity;

/**
 *
 * @author SenseTiles
 */
public class DeviceDetectorService  implements Runnable
{
    public static final DeviceDetectorService NO_DETECTOR = null;
    private static final String VIDEO = "video";
    private static final String TTY_USB0 = "ttyUSB0";
    private static final String UNKNOWN = "Unknown";
    private static final String DEV = "/dev";
    private List<IDevice> _deviceList = null;
    private boolean isStarted = Boolean.FALSE;
    private DeviceDetectorService()
    {
        super();
        _deviceList = new ArrayList<IDevice>();
    }


    public void initialize()
    {
        if(!isStarted)
        {
          load();
          new Thread(this).start();
          isStarted = Boolean.TRUE;
        }
    }

    private void load()
    {
        _deviceList.clear();
        ListUtils.addToTypedList(_deviceList, getVideoDevices().toArray());
        ListUtils.addToTypedList(_deviceList, getTTYDevices().toArray());
    }

    public static DeviceDetectorService getInstance()
    {
        return Instance.sole_Instance;
    }

    private static class Instance
    {
        static final DeviceDetectorService
                sole_Instance = new DeviceDetectorService();
    }
    public synchronized List<IDevice> getVideoDevices()
    {
        File[] fs = new File(DEV).listFiles();
        List<VideoDevice> devices = new ArrayList<VideoDevice>();
        List<IDevice> allDevices = new ArrayList<IDevice>();
        for (int i = 0; i < fs.length; i++)
        {
            boolean isNotDir  =(fs[i].getName().startsWith(VIDEO) &&
                                !fs[i].isDirectory());
            if (isNotDir)
            {
                VideoDevice videoDevice =
                        new VideoDevice(fs[i].getAbsolutePath());
                 setNameIfEmpty(videoDevice);
                devices.add(videoDevice);
            }
        }
        ListUtils.addToTypedList(allDevices,devices.toArray());
        devices = null;
        return allDevices;
    }
    public synchronized List<VideoDevice> getInputVideoDevices()
   {
        // CREATE ONE LAZY LOAD LIST OF INPUT VIDEO DEVICES
        List<VideoDevice> videoDevices = new ArrayList<VideoDevice>();
        for(IDevice currentDevice : _deviceList)
        {
            if( !currentDevice.getClass().isAssignableFrom(VideoDevice.class))
            {
                continue;
            }
            if(currentDevice.getType() == Type.INPUT)
            {
                VideoDevice videoDevice = (VideoDevice)currentDevice;
                videoDevices.add(videoDevice);
            }
        }
        return videoDevices;
    }
    public synchronized List<VideoDevice> getOutputVideoDevices()
    {
        // CREATE ONE LAZY LOAD LIST -- OUTPUT VIDEO DEVICES
        List<VideoDevice> videoDevices = new ArrayList<VideoDevice>();
        for(IDevice currentDevice : _deviceList)
        {
            if( !currentDevice.getClass().isAssignableFrom(VideoDevice.class))
            {
                continue;
            }
            if(currentDevice.getType() == Type.OUTPUT)
            {
                VideoDevice videoDevice = (VideoDevice) currentDevice;
                videoDevices.add(videoDevice);
            }
        }
        return videoDevices;
    }

    public synchronized List<TelosDevice> getOutputTTYDevices()
    {
        // CREATE ONE LAZY LOAD LIST OF OUTPUT VIDEO DEVICES
        List<TelosDevice> telosDevices = new ArrayList<TelosDevice>();
        for(IDevice currentDevice : _deviceList)
        {
            if( !currentDevice.getClass().isAssignableFrom(TelosDevice.class))
            {
                continue;
            }
            if(currentDevice.getType() == Type.OUTPUT)
            {
                TelosDevice telosDevice = (TelosDevice)currentDevice;
                telosDevices.add(telosDevice);
            }
        }
        return telosDevices;
    }

    private void setNameIfEmpty(IDevice device )
    {
        final boolean isEmptyName = device.getName().equalsIgnoreCase("");
        if(isEmptyName)
        {
            device.setName(UNKNOWN);
        }
    }

    private synchronized List<IDevice> getTTYDevices()
    {
        File[] fs = new File(DEV).listFiles();
        List<TelosDevice> devices = new ArrayList<TelosDevice>();
        List<IDevice> allDevices = new ArrayList<IDevice>();
        for (int i = 0; i < fs.length; i++)
        {
            boolean isNotDir  =(fs[i].getName().startsWith(TTY_USB0) &&
                                !fs[i].isDirectory());
            if (isNotDir)
            {
                TelosDevice telosDevice = new TelosDevice(fs[i].getAbsolutePath());
                 setNameIfEmpty(telosDevice);
                devices.add(telosDevice);
                
            }
        }
        ListUtils.addToTypedList(allDevices,devices.toArray());
        devices = null;
        return allDevices;
    }
    // control devices
    public void run() {
        FilenameFilter filter = new FilenameFilter()
        {
           @Override
            public boolean accept(File dir, String name)
            {
                return name.startsWith(VIDEO) ||
                        name.startsWith(TTY_USB0);
            }
        };

        int nbDevices = new File(DEV).list(filter).length;
        int delay = 0;

        while (true)
        {
            try
            {
                Thread.sleep(300);
                delay = delay++ % 5;
                boolean isflagTrue =
                    delay == 0 && new File(DEV).list(filter).length != nbDevices;
                if ( isflagTrue )
                {
                    Thread.sleep(1700); // WAITING OP SYSTEM TO VALIDATE DRIVERS
                    List<IMessage> messagesList = createMessages();
                    load();
                    doNotification(messagesList);
                    nbDevices = new File(DEV).list(filter).length;
                    delay = 0;
                 }
            }
            catch (InterruptedException ex)
            {
                 Logger.getLogger(DeviceDetectorService.class.getName()).
                         log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
      }

      private List<IMessage> createMessages()
      {
            // create new device list
            List<IDevice> devices = new ArrayList<IDevice>();
            ListUtils.addToTypedList(devices, getVideoDevices().toArray());
            ListUtils.addToTypedList(devices, getTTYDevices().toArray());
            // Create list with both ready and non ready devices.
            List<IDevice> dirtyList = createDirtyListFrom(devices);
            _deviceList.clear();
            // doCombine!
            ListUtils.addToTypedList(_deviceList, dirtyList.toArray());
            return createMessages(dirtyList);
      }

      private void doNotification(List<IMessage> messages)
      {
            Logger.getLogger(DeviceDetectorService.class.getName()).
                    log(Level.INFO, "Sequence of messages have been broadcasted. " +
                    "REASON: [Device(s) is(are) either connected or diconnected].");
            BroadcasterService.getInstance().processMessageList(messages);
      }


      private List<IMessage> createMessages( List<IDevice> dirtyList)
      {
        List<IMessage> messages = new ArrayList<IMessage>();
        for(IDevice currentDevice : dirtyList)
        {
            IMessage deviceMessage = null;
            if(currentDevice.isReady())
            {
                deviceMessage =
                        DeviceMessage.createDeviceMessage(currentDevice,
                        Validity.VALID);
            }
            else
            {
                deviceMessage =
                        DeviceMessage.createDeviceMessage(currentDevice,
                        Validity.INVALID);
            }
            messages.add(deviceMessage);
        }
        return messages;
      }

      /*
       * @TODO do refactoring !
       */
      private List<IDevice> createDirtyListFrom( List<IDevice> validTempList)
     {
        List<IDevice> dirtyList =new ArrayList<IDevice>();
        final boolean isBothEmpty       =
                validTempList.size() == 0 && _deviceList.size() == 0;
        final boolean isValidListEmpty  =
                validTempList.size() == 0 && _deviceList.size() > 0;
        final boolean isDeviceListEmpty =
                validTempList.size() > 0 && _deviceList.size() == 0;

        if(isBothEmpty)
        {
            return dirtyList;
        }
        else if (isValidListEmpty)
        {
            dirtyList =  createDirtyList(_deviceList, Boolean.FALSE);
        }
        else if (isDeviceListEmpty)
        {
            dirtyList =  createDirtyList(validTempList, Boolean.TRUE);
        }
        else
        {
            final boolean isTempListSizeLessThanDeviceList =
                    _deviceList.size() > validTempList.size();

            if(isTempListSizeLessThanDeviceList)
            {
              dirtyList =
                      fillDirtyList(_deviceList,validTempList, Boolean.FALSE);
            }
            else
            {
               dirtyList =
                       fillDirtyList(validTempList,_deviceList, Boolean.TRUE);
            }
        }
        return dirtyList;
    }

    private List<IDevice> fillDirtyList(final List<IDevice> inspectedList,
                                        final List<IDevice> currentList,
                                        final boolean isReady )
    {
       List<IDevice> dirtyList = new ArrayList<IDevice>();
       for(IDevice currentDevice : inspectedList )
       {
            if(contains(currentList, currentDevice))
            {
                dirtyList.add(currentDevice);
            }
            else
            {
                currentDevice.setReady(isReady);
                dirtyList.add(currentDevice);
            }
       }
       return dirtyList;
    }

    private boolean contains( final List<IDevice> targetList,
                              final IDevice newDevice)
    {
        boolean contains = Boolean.FALSE;
        for(IDevice existingDevice : targetList)
        {
            String pathExisting = existingDevice.getFile().getAbsolutePath();
            String pathNew = newDevice.getFile().getAbsolutePath();
            final boolean isEqual = pathExisting.equalsIgnoreCase(pathNew);
            if(isEqual)
            {
                contains = Boolean.TRUE;
                break;
            }
        }
        return contains;
    }

    private List<IDevice> createDirtyList(final List<IDevice> list,
                                          final boolean isReady)
    {
        List<IDevice> dirtyList = new ArrayList<IDevice>();
        for(IDevice validDevice : list)
        {
            validDevice.setReady(isReady);
        }
        ListUtils.addToTypedList(dirtyList, list.toArray());
        return dirtyList;
    }

    public List<IDevice> getDevice()
    {
        return _deviceList;
    }

    
//        public static void main(String[] args)
//        {
//            int t = 100000;
//            int i = 0;
//            DeviceDetectorService _det = DeviceDetectorService.getInstance();
//            _det.initialize();
//            _det.getOutputVideoDevices();
//            System.out.println("Grab Telos Now");
//            _det.getOutputTTYDevices();
//
//            while (i< t)
//            {
//
//                i++;
//            }
//
//        }
  }
