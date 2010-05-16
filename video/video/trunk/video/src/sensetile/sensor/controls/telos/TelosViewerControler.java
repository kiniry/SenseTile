
package sensetile.sensor.controls.telos;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensetile.common.messages.DeviceMessage;
import sensetile.common.services.BroadcasterService;
import sensetile.common.messages.IMessage;
import sensetile.common.messages.MessageType.Validity;
import sensetile.common.services.IObservable;
import sensetile.common.services.LayerService;
import sensetile.common.messages.PacketMessage;
import sensetile.common.messages.SourceMessage;
import sensetile.common.sources.ISource;
import sensetile.common.utils.CommonUtils;
import sensetile.common.utils.Guard;
import sensetile.common.utils.ListUtils;
import sensetile.devices.TelosDevice;
import sensetile.sensor.sources.telos.ITelosHolder;

/**
 *
 * @author SenseTile
 */
public class TelosViewerControler implements IObservable
{
    private LayerService _layerService = LayerService.NONE;
    private TelosViewer _frame = null;
    private TelosViewerControler()
    {
      _layerService = LayerService.getInstance();
      BroadcasterService.getInstance().attachObserver(this);
    }

    public static TelosViewerControler getInstance()
    {
        return Instance.sole_Instance;
    }

    private static class Instance
    {
        static final TelosViewerControler
                sole_Instance = new TelosViewerControler();
    }

    public void update(IMessage message)
    {
        Guard.ArgumentNotNull(message, "Message cannot be a null.");
        if (message.getClass().isAssignableFrom(PacketMessage.class))
        {
            PacketMessage packetMessage = (PacketMessage)message;
            Object obj = packetMessage.getMessage();
            processTelosHolder((ITelosHolder)obj);
        }else if(message.getClass().isAssignableFrom(DeviceMessage.class))
        {
            DeviceMessage devMessage = (DeviceMessage)message;
            if(devMessage.getValidity()== Validity.INVALID)
            {
                _frame.doDefaultCloseAction();
            }
        }
        
    }

    private void processTelosHolder(final ITelosHolder holder)
    {
        String[] line = new String[3];
        line[0] = new StringBuffer().append(holder.getDate().toString()).toString();
        line[1] = String.valueOf(holder.getTemperature());
        line[2] = String.valueOf(holder.getHumidity());
        _frame.getDefaultTableModel().addRow(line);
        _frame.getTableInfo().setModel(_frame.getDefaultTableModel());
    }

    public void updateSequence(List<IMessage> messages)
    {
        
        // DO UPDATE WEB CAM SOURCES AND BROADCAST MESSAGE SEQUENCE.
        Guard.ArgumentNotNull(messages, "List of messages cannot be a null.");
        if(!ListUtils.listIsAssignableFrom(messages,
                "sensetile.common.messages.DeviceMessage"))
        {
            // CLASS IS NOT INTERESTED FOR THIS TYPE OF MESSAGE SEQUENCE.
            return;
        }
       
        TelosDevice device = findTelosDevice(messages);
        if(device != null && !device.isReady())
        {
           if(_layerService.contains(_frame.getSource()))
           {
                 _layerService.remove(_frame.getSource());
                  
           }
           _frame.doDefaultCloseAction();
        }
    }

    private TelosDevice findTelosDevice( List<IMessage> messages )
    {
        TelosDevice device = null;
        for (IMessage message : messages)
        {
            if(CommonUtils.isTypeOf(message.getMessage(),
                    "sensetile.devices.TelosDevice"))
            {
               device = (TelosDevice)message.getMessage();
               break;
            }

        }
        return device;
    }


    public void addFrame( final TelosViewer frame)
    {
        Guard.ArgumentNotNull(frame, "Frame cannot be a null.");
        if(!contains(frame))
        {
            _frame = frame;
        }
    }

    public void removeFrame( final TelosViewer frame)
    {
        Guard.ArgumentNotNull(frame, "Frame cannot be a null.");
        if(contains(frame))
        {
            _frame = null;
        }
    }

    public boolean contains(final TelosViewer frame)
    {
        boolean contains = Boolean.FALSE;
        Guard.ArgumentNotNull(frame, "Frame cannot be a null.");
        if(_frame != null)
        {
            contains = Boolean.TRUE;
        }
        return contains;
    }

    public boolean containsFrameFrom(final  ISource source)
    {
        boolean contains = Boolean.FALSE;
        if( _frame != null && _frame.getSource().isEqual(source))
        {
            contains = Boolean.TRUE;
        }
        return contains;
    }


    public TelosViewer  createFrame(ISource source, final int width,
            final int height, int desktopTaskbarHeight)
    {
        Guard.ArgumentNotNull(source,"Source cannot be a null.");
        _frame = new TelosViewer();
        initialize(source,width,height,desktopTaskbarHeight);
        if (!_layerService.contains(source))
        {
            _layerService.add(source);
        }
        return _frame;
    }

   private void initialize(ISource source, final int width,
            final int height, int desktopTaskbarHeight )
    {
        _frame.setParentSize(width, height);
        _frame.setSource(source);
        _frame.setOutputSize(ISource.DEFAULT_WIDTH,
                             ISource.DEFAULT_HEIGHT);
        if (source.getWidth() == 0)
        {
            source.setWidth(ISource.DEFAULT_WIDTH);
        }
        if (source.getHeight() == 0)
        {
            source.setHeight(ISource.DEFAULT_HEIGHT);
        }
        _frame.setLocation(width /
                ISource.DEFAULT_WIDTH, (height - desktopTaskbarHeight) /
                ISource.DEFAULT_HEIGHT);
        _frame.setSize(source.getWidth() * width /
                ISource.DEFAULT_WIDTH, (source.getHeight() * (height - desktopTaskbarHeight) /
                ISource.DEFAULT_HEIGHT));
    }

    public void formInternalFrameClosed()
    {
        if(_frame == null)
        {
            return;
        }
        ISource source = _frame.getSource();
       // source.stopSource();
        _layerService.remove(source);
        doNotification(source);
        removeFrame(_frame);
    }

    private void doNotification( final ISource source)
    {
        IMessage message = SourceMessage.createSourceMessage(source, Validity.INVALID);
        BroadcasterService broadcasterService = BroadcasterService.getInstance();
        broadcasterService.broadcastMessage(message);
         Logger.getLogger(TelosViewerControler.class.getName()).
                    log(Level.INFO, "Source message is broadcasted. " +
                    "REASON: [TelosView has been closed].");
    }
    
    public void startSource(boolean autoStart)
    {
        ISource source = _frame.getSource();
        _frame.setTitle(getTitle(source));
        if (autoStart)
        {
            source.startSource();
        }
    }

    public String getTitle(final ISource source)
    {
        Guard.ArgumentNotNull(source, "Video source cannot be a null.");
        return source.getDeviceName()+
                " (" + (_layerService.size() - _layerService.getIndex(source)) + ")";
    }
}
