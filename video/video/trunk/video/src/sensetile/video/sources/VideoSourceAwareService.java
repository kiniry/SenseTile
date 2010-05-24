package sensetile.video.sources;

import java.util.ArrayList;
import java.util.List;
import sensetile.common.messages.DeviceMessage;
import sensetile.common.messages.IMessage;
import sensetile.common.messages.MessageType.Validity;
import sensetile.common.messages.SourceMessage;
import sensetile.common.utils.CommonUtils;
import sensetile.devices.VideoDevice;

/**
 *
 * @author SenseTile
 */
public class VideoSourceAwareService
{
    public static final VideoSourceAwareService UNAWARE = null;

    private VideoSourceAwareService()
    {
       super();
    }

    public static VideoSourceAwareService makeAware()
    {
       return  new VideoSourceAwareService();
    }
    

    public List<IVideoSource> createTempList(final List<IVideoSource> sourcesWebcams,
            final List<IMessage> messages)
    {
        assert sourcesWebcams != null : "Sources Webcams cannot be a null.";
        assert messages != null : "Messages cannot be a null.";

        List<IVideoSource> tempList = new ArrayList<IVideoSource>();
        for(IMessage message : messages)
        {
            DeviceMessage deviceMessage = (DeviceMessage)message;
            
            if(!CommonUtils.isTypeOf(message.getMessage(),
                    "sensetile.devices.VideoDevice"))
            {
                continue;
            }

            assert deviceMessage.getMessage().getClass().isAssignableFrom(VideoDevice.class);

            VideoDevice videoDevice = (VideoDevice)deviceMessage.getMessage();
            IVideoSource source = findByPath(sourcesWebcams,videoDevice);

            if( source == IVideoSource.NO_VIDEO_SOURCE)
            {
                IVideoSource newVideoSource =
                        createVideoSource(videoDevice);
                assert newVideoSource != null;
                tempList.add(newVideoSource);
                assert tempList.contains(newVideoSource): "Video source is not added properly.";
            }
            else
            {
                updateSource(source, videoDevice);
                tempList.add(source);
                assert tempList.contains(source): "Source is not added properly.";
            }
        }
        return tempList;
    }

    public IVideoSource createVideoSource(final VideoDevice device)
    {
        assert device != null: "Video device cannot be a null.";
        assert device.getFile().getAbsolutePath() != null && 
                        !device.getFile().getAbsolutePath().equalsIgnoreCase(""):
                            "AbsolutePath cannot be a null or empty string.";
        assert device.getName() != null && !device.getName().equalsIgnoreCase(""):
                    "Device name cannot be a null or empty string.";

        IVideoSource videoSource = IVideoSource.NO_VIDEO_SOURCE;
        switch( device.getVersion() )
        {
            case V4L:
                videoSource =
                        VideoSource.createVideoSource("v4lsrc",
                        device.getFile().getAbsolutePath(),
                        device.getName());

                break;
            case V4L2:
                videoSource =
                        VideoSource.createVideoSource("v4l2src",
                        device.getFile().getAbsolutePath(),
                        device.getName());
                break;
             default:
                 throw new UnsupportedOperationException("VideoSourceProvider:" +
                         "Cannot create new VideoSource. Version: "
                         +  device.getVersion() + " is not supported.");
        }
        assert videoSource != null;
        videoSource.enableProcess(Boolean.TRUE);
        return videoSource;
    }

    public List<IVideoSource> destileCurrentVideoSources(
            final List<IVideoSource> sourcesWebcams,
            final List<IVideoSource> tempList)
    {

        assert sourcesWebcams != null : "Sources Webcams cannot be a null.";
        assert tempList != null : "TempList cannot be a null.";

        List<IVideoSource> destiledList = new ArrayList<IVideoSource>();
       
        if(tempList.size() >= sourcesWebcams.size())
        {
            for(IVideoSource currentSource : tempList)
            {
                IVideoSource validOldSource =
                        findValidFor(sourcesWebcams, currentSource);
                if(validOldSource != IVideoSource.NO_VIDEO_SOURCE)
                {
                   destiledList.add(validOldSource);
                }
                else
                {
                    if(currentSource.isReadyToProcess())
                    {

                       destiledList.add(currentSource);
                    }
                    
                }
            }
        }else
        {
            for(IVideoSource currentSource : sourcesWebcams)
            {
                IVideoSource validOldSource =
                        findValidFor(tempList, currentSource);
                if(validOldSource != IVideoSource.NO_VIDEO_SOURCE)
                {
                    // MATCHING
                   destiledList.add(validOldSource);
                }
            }
        }
        return destiledList;
    }

     private IVideoSource findValidFor(List<IVideoSource> targetList,
                                        IVideoSource videoSource)
    {
        assert videoSource != null : "Video source cannot be a null.";
        assert targetList != null : "Target list cannot be a null.";
        IVideoSource target = IVideoSource.NO_VIDEO_SOURCE;
        for(IVideoSource currentSource : targetList)
        {
            boolean pathIsEqual = currentSource.hasPathAs(videoSource.getLocation()) ;
            boolean isReadyToProcess = currentSource.isReadyToProcess();

            if(pathIsEqual && isReadyToProcess)
            {
                
                target = currentSource;
            }

            break;
        }
        return target;
    }


    private IVideoSource findByPath(List<IVideoSource> sourcesWebcams,
            final VideoDevice videoDevice)
    {
        assert videoDevice != null : "Video dvice cannot be a null.";
        assert sourcesWebcams != null : "SourcesWebcams dvice cannot be a null.";
        IVideoSource targetSource = IVideoSource.NO_VIDEO_SOURCE;
        for(IVideoSource videoSource : sourcesWebcams)
        {
            if(pathIsEqual(videoSource, videoDevice))
            {
                targetSource = videoSource;
                break;
            }
        }
        return targetSource;
    }

    private boolean pathIsEqual(final IVideoSource videoSource,
                                final VideoDevice videoDevice)
    {
        assert videoSource != null && videoDevice != null;
        assert videoDevice.getFile().getAbsolutePath() != null &&
                !videoDevice.getFile().getAbsolutePath().equalsIgnoreCase(""):
                    "Absolute path cannot be a null or empty string.";

        final boolean hasSamePath = videoSource.hasPathAs(videoDevice.getFile().getAbsolutePath());
        return hasSamePath;
    }

    private void updateSource( final IVideoSource source,
              final VideoDevice device)
    {
        assert source != null && device != null;
        assert device.getName() != null && !device.getName().equalsIgnoreCase(""):
            "device name cannot be a null or empty string.";
        assert source.getDeviceName() != null && !source.getDeviceName().equalsIgnoreCase(""):
            "device name from source  cannot be a null or empty string.";

        if(!device.isReady())
         {
             source.enableProcess(Boolean.FALSE);
             assert !source.isReadyToProcess();
         }
         if(!device.getName().equalsIgnoreCase(
                 source.getDeviceName()))
         {
             source.setDeviceName(device.getName());
             assert source.getDeviceName().equalsIgnoreCase(device.getName());
         }

    }

    public List<IMessage> createVideoMessages(final List<IVideoSource> tempList)
    {
        assert tempList != null : "Temp list cannot be a null.";
        List<IMessage> messageList = new ArrayList<IMessage>();
        IMessage videoMessage = IMessage.NO_MESSAGE;
        for(IVideoSource videoSource : tempList)
        {
            assert videoSource != null: "Video source cannot be a null.";
            if( videoSource.isReadyToProcess())
            {
                videoMessage = SourceMessage.
                        createSourceMessage(videoSource, Validity.VALID);
            }
            else
            {
                videoMessage = SourceMessage.
                        createSourceMessage(videoSource, Validity.INVALID);
            }
            assert videoMessage != null &&
                    videoMessage.getClass().isAssignableFrom(SourceMessage.class);

            messageList.add(videoMessage);
        }
        return messageList;
    }
}
