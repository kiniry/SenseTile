/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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


            VideoDevice videoDevice = (VideoDevice)deviceMessage.getMessage();
            IVideoSource source = findByPath(sourcesWebcams,videoDevice);
            if( source == IVideoSource.NO_VIDEO_SOURCE)
            {
                IVideoSource newVideoSource =
                        createVideoSource(videoDevice);
                tempList.add(newVideoSource);
            }else
            {
                updateSource(source, videoDevice);
                tempList.add(source);
            }
        }
        return tempList;
    }

    public IVideoSource createVideoSource(final VideoDevice device)
    {
        assert device != null: "Video device cannot be a null.";
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
        final boolean hasSamePath = videoSource.hasPathAs(videoDevice.getFile().getAbsolutePath());
        return hasSamePath;
    }

    private void updateSource( final IVideoSource source,
              final VideoDevice device)
    {
         if(!device.isReady())
         {
             source.enableProcess(Boolean.FALSE);
         }
         if(!device.getName().equalsIgnoreCase(
                 source.getDeviceName()))
         {
             source.setDeviceName(device.getName());
         }

    }

    public List<IMessage> createVideoMessages(final List<IVideoSource> tempList)
    {
        assert tempList != null : "Temp list cannot be a null.";
        List<IMessage> messageList = new ArrayList<IMessage>();
        IMessage videoMessage = IMessage.NO_MESSAGE;
        for(IVideoSource videoSource : tempList)
        {
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
            messageList.add(videoMessage);
        }
        return messageList;
    }
}
