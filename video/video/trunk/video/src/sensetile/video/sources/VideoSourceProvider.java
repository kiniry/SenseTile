package sensetile.video.sources;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensetile.common.messages.IMessage;
import sensetile.common.services.BroadcasterService;
import sensetile.common.services.IObservable;
import sensetile.common.utils.Guard;
import sensetile.common.utils.ListUtils;
import sensetile.devices.DeviceDetectorService;
import sensetile.devices.VideoDevice;
/**
 * @author SenseTile
 */
public class VideoSourceProvider implements IObservable
{
    public static final VideoSourceProvider NONE = null;
    private List<IVideoSource> _sourcesWebcams = null;
    private VideoSourceAwareService _awareService =
            VideoSourceAwareService.UNAWARE;

    private VideoSourceProvider()
    {
        _sourcesWebcams = new ArrayList<IVideoSource>();
        initialize();
        BroadcasterService.getInstance().attachObserver(this);
    }

    public static VideoSourceProvider getInstance()
    {
        return Instance.sole_Instance;
    }
    private static class Instance
    {
        static final VideoSourceProvider
                sole_Instance = new VideoSourceProvider();
    }

    public List<IVideoSource> getSourcesWebcams()
    {

        return _sourcesWebcams;
    }

    public IVideoSource getVideoSourceAt( final int index)
    {
        Guard.IndexOutOfBounds(_sourcesWebcams, index);
        return _sourcesWebcams.get(index);
    }

    private void initialize()
    {
        DeviceDetectorService.getInstance().initialize();
         List<VideoDevice> newList = DeviceDetectorService.getInstance().
                 getOutputVideoDevices();
         _awareService = VideoSourceAwareService.makeAware();
         for(VideoDevice device : newList)
         {
             IVideoSource videoSource =
                     _awareService.createVideoSource(device);
             _sourcesWebcams.add(videoSource);
         }
         _awareService = VideoSourceAwareService.UNAWARE;
    }

    public void update(IMessage message) 
    {
        // CLASS IS NOT INTERESTED FOR MESSAGE.
        return;
    }

    public void updateSequence( List<IMessage> messages)
    {
        // DO UPDATE WEB CAM SOURCES AND BROADCAST MESSAGE SEQUENCE.
        Guard.ArgumentNotNull(messages, "List of messages cannot be a null.");
        if(!ListUtils.listIsAssignableFrom(messages,
                "sensetile.common.messages.DeviceMessage"))
        {
            // CLASS IS NOT INTERESTED FOR THIS TYPE OF MESSAGE SEQUENCE.
            return;
        }
        _awareService = VideoSourceAwareService.makeAware();
        List<IVideoSource> tempList =
                _awareService.createTempList(_sourcesWebcams,messages);
        List<IMessage> videoMessages =_awareService.createVideoMessages(tempList);
        List<IVideoSource> destiledList =
                _awareService.destileCurrentVideoSources(_sourcesWebcams,tempList);
        _sourcesWebcams.clear();
        ListUtils.addToTypedList(_sourcesWebcams, destiledList.toArray());
        doNotification(videoMessages);
        _awareService = VideoSourceAwareService.UNAWARE;
    }
 

    private void doNotification(final List<IMessage> messages)
    {
         Logger.getLogger(DeviceDetectorService.class.getName()).
                    log(Level.INFO, "Video source provider is able to provide. " +
                    _sourcesWebcams.size() + " source(s)");
        Logger.getLogger(DeviceDetectorService.class.getName()).
                    log(Level.INFO, "Sequence of messages have been broadcasted. " +
                    "REASON: [VideoSource(s) is(are) changed].");
         BroadcasterService.getInstance().processMessageList(messages);
    }
}
