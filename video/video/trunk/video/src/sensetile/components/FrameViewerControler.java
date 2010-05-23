

package sensetile.components;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensetile.common.messages.DeviceMessage;
import sensetile.common.services.BroadcasterService;
import sensetile.common.messages.IMessage;
import sensetile.common.services.IObservable;
import sensetile.common.services.LayerService;
import sensetile.common.messages.MessageType.PipeType;
import sensetile.common.messages.MessageType.TransmissionType;
import sensetile.common.messages.MessageType.Validity;
import sensetile.common.messages.PipeMessage;
import sensetile.common.messages.SourceMessage;
import sensetile.common.messages.TransmissionMessage;
import sensetile.common.sources.ISource;
import sensetile.common.utils.CommonUtils;
import sensetile.devices.IDevice;
import sensetile.video.controls.MissingVideoGrabberException;
import sensetile.video.controls.VideoGrabber;
import sensetile.video.sources.IVideoSource;


/**
 *
 * @author SenseTile
 */
public class FrameViewerControler implements IObservable
{
    
    private List<FrameViewer> _frames = null;
    private List<VideoRecorderHandler> _recorderHandlers = null;
    private List<VideoBroadcastHandler> _broadcastHandlers = null;
    private LayerService _layerService = LayerService.NONE;


    private FrameViewerControler()
    {
      _frames = new ArrayList<FrameViewer>();
      _layerService = LayerService.getInstance();
      _recorderHandlers = new ArrayList<VideoRecorderHandler>();
      _broadcastHandlers = new ArrayList<VideoBroadcastHandler>();
    }

    public static FrameViewerControler getInstance()
    {
        return Instance.sole_Instance;
    }

    public void update(IMessage message)
    {
        assert message != null : "Message object cannot be a null.";
         
        if (CommonUtils.isTypeOf(message,
                 "sensetile.common.messages.PipeMessage"))
         {
            PipeMessage pipeMessage = (PipeMessage)message;
            resolvePipeMessage(pipeMessage);
         }
        else if(message.getClass().isAssignableFrom(TransmissionMessage.class))
         {
             TransmissionMessage transmissionMessage =
                     (TransmissionMessage)message;
             resolveTransmissionMessage(transmissionMessage);
         }else
         {
              Logger.getLogger(FrameViewerControler.class.getName()).
                    log(Level.INFO, "This type of message: " +
                    message.getClass().getName() + "is not supported.");
         }

        
    }

    private void resolvePipeMessage(final PipeMessage pipeMessage)
    {
        assert pipeMessage != null : "pipeMessage cannot be a null.";
        PipeType type = pipeMessage.getPacketType();
        IVideoSource source = (IVideoSource)pipeMessage.getMessage();
        FrameViewer frameViewer = findFrameViewerFrom(source);
        if(type == PipeType.PIPE_BUS_ERROR && source.isEqual(frameViewer.getSource()))
        {
          frameViewer.doDefaultCloseAction();
        }
    }

    private void resolveTransmissionMessage(final TransmissionMessage transmissionMessage)
    {
        assert transmissionMessage != null : "transmissionMessage cannot be a null.";
        IVideoSource source = (IVideoSource)transmissionMessage.getMessage();
         if(transmissionMessage.getTransmissionType() ==
         TransmissionType.RECORDING_PROCESS_STOPPED)
         {
           VideoRecorderHandler vrh = findVideoRecorderHandlerBy(source);
           if(vrh != null)
           {
                _recorderHandlers.remove(vrh);
           }
         }else if(transmissionMessage.getTransmissionType() ==
         TransmissionType.BROADCASTING_PROCESS_STOPPED)
         {
            VideoBroadcastHandler vbh = findVideoBroadcastHandlerBy(source);
            if(vbh != null)
            {
                _broadcastHandlers.remove(vbh);
            }
         }
    }

    /**
     * @TODO DESIGN SHOULD BE BETTER - NO TIME AT THIS MOMENT TO IMPROVE DESIGN!
     * @param source
     */
    public void openFileChooser(ISource source)
    {
        assert source != null : "Source cannot be a null.";

        VideoRecorderHandler recorderHandler = null;
        FrameViewer frameViewer = findFrameViewerFrom(source);
        if(!source.isPlaying() && !source.isPaused())
        {
          return ;
      
        }
        recorderHandler = findVideoRecorderHandlerBy(source);
        
        
        if(recorderHandler != null && recorderHandler.isRecording())
        {
            return;
        }
        else
        {
            recorderHandler =  VideoRecorderHandler.createHandler(frameViewer);
            if(!_recorderHandlers.contains(recorderHandler))
            {
             _recorderHandlers.add(recorderHandler);
             recorderHandler.getFileChooser().setVisible(Boolean.TRUE);
            }
        }
    }


    public void openBroadcasterFrame( ISource source)
    {
        assert source != null : "Source cannot be a null.";
        VideoBroadcastHandler videoBroadcastHandler = null;
        FrameViewer frameViewer = findFrameViewerFrom(source);
        if(!source.isPlaying() && !source.isPaused())
        {
          return;

        }

        videoBroadcastHandler = findVideoBroadcastHandlerBy(source);
        
        if(videoBroadcastHandler != null &&
                videoBroadcastHandler.isBroadcasting())
        {
            return;
        }
        else
        {
           videoBroadcastHandler=
                   VideoBroadcastHandler.createHandler(frameViewer);
           if(!_broadcastHandlers.contains(videoBroadcastHandler))
           {
               _broadcastHandlers.add(videoBroadcastHandler);
               videoBroadcastHandler.getBroadcasterFrame().setVisible(true);
           }
           
        }             
    }

    private VideoBroadcastHandler findVideoBroadcastHandlerBy( ISource source)
    {
        assert source != null : "Source cannot be a null.";
        VideoBroadcastHandler broadcastHandler = null;
        for(VideoBroadcastHandler bch : _broadcastHandlers)
        {
            FrameViewer fw = bch.getFrameViewer();
            if(fw != null)
            {
                ISource currentSource = fw.getSource();
                if(currentSource.equals(source))
                {
                    broadcastHandler = bch;
                    break;
                }
            }
        }
        return broadcastHandler;
    }
    private VideoRecorderHandler findVideoRecorderHandlerBy( ISource source)
    {
        assert source != null : "Source cannot be a null.";
        VideoRecorderHandler recorderHandler = null;
        for(VideoRecorderHandler fch : _recorderHandlers)
        {
            FrameViewer fw = fch.getFrameViewer();
            if(fw != null)
            {
                ISource currentSource = fw.getSource();
                if(currentSource.equals(source))
                {
                    recorderHandler = fch;
                    break;
                }
            }
        }
        return recorderHandler;
    }

    public void stopRecording(final ISource source)
    {
       assert source != null : "Source cannot be a null.";
       VideoRecorderHandler recorderHandler =
               findVideoRecorderHandlerBy(source);
       if(recorderHandler != null &&
               recorderHandler.isRecording())
       {
             recorderHandler.stopRecording();
             if(_recorderHandlers.contains(recorderHandler))
             {
                _recorderHandlers.remove(recorderHandler);
             }
       }
    }

    public void stopBroadcasting(final ISource source)
    {
        assert source != null : "Source cannot be a null.";
       VideoBroadcastHandler broadcastHandler =
               findVideoBroadcastHandlerBy(source);
       if(broadcastHandler != null &&
               broadcastHandler.isBroadcasting())
       {
             broadcastHandler.stopBroadcasting();
             if(_broadcastHandlers.contains(broadcastHandler))
             {
                _broadcastHandlers.remove(broadcastHandler);
             }
       }
    }
    
    public void updateSequence(List<IMessage> messages)
    {
       assert messages != null : "messages  cannot be a null.";
       for (IMessage iMessage :messages)
       {
            if (! CommonUtils.isTypeOf(iMessage, "sensetile.common.messages.DeviceMessage"))
            {
                continue;
            }
            DeviceMessage deviceMessage = (DeviceMessage) iMessage;
            if (! (deviceMessage.getValidity() == Validity.INVALID))
            {
                continue;
            }
            IDevice device = (IDevice) deviceMessage.getMessage();
            String invalidPath = device.getFile().getAbsolutePath();
            for(FrameViewer viewer : _frames)
            {
                if (viewer.getSource().hasPathAs(invalidPath))
                {
                    _layerService.remove(viewer.getSource());
                    viewer.doDefaultCloseAction();
                    break;
                }
            }
       }
       // FIND FRAME_VIEWER BY SOURCE LOCATION (PATH) AND CLOSE FRAME_VIEWER 
    }



    private static class Instance
    {
        static final FrameViewerControler
                sole_Instance = new FrameViewerControler();
    }

    public boolean contains(final FrameViewer frame )
    {
        assert frame != null : "FrameViewer cannot be a null.";
        boolean contains = Boolean.FALSE;
        if(_frames.contains(frame))
        {
           contains = Boolean.TRUE;
        }
        return contains;
    }

    public void addFrame( final FrameViewer frame)
    {
         assert frame != null : "FrameViewer cannot be a null.";
        if(!contains(frame))
        {
            _frames.add(frame);
            assert _frames.contains(frame);
        }
    }

    public void removeFrame(final FrameViewer frame)
    {
         assert frame != null : "FrameViewer cannot be a null.";
        if(contains(frame))
        {
            _frames.remove(frame);
            assert !_frames.contains(frame);
        }
    }
     
    public boolean containsFrameFrom(IVideoSource source) 
    {
        assert source != null : "Source cannot be a null.";
        boolean contains = Boolean.FALSE;
        if(findFrameViewerFrom(source) != null)
        {
            contains = Boolean.TRUE;
        }
        return contains;
    }

    public FrameViewer createFrame(ISource source, final int width,
            final int height, int desktopTaskbarHeight)
    {
        assert source != null : "Source cannot be a null.";
        FrameViewer frame = new FrameViewer();
        initialize(source,width,height,desktopTaskbarHeight, frame);
        BroadcasterService.getInstance().attachObserver(this);
        addFrame(frame);
        if (!_layerService.contains(source))
        {
            _layerService.add(source);
        }
        return frame;
    }

    private void initialize(ISource source, final int width,
            final int height, int desktopTaskbarHeight ,FrameViewer frame )
    {
        assert source != null : "Source cannot be a null.";
        frame.setParentSize(width, height);
        frame.setSource((IVideoSource)source);
        frame.setGrabberToPanViewer( createGrabber(frame));
        frame.setOutputSize(ISource.DEFAULT_WIDTH,
                            ISource.DEFAULT_HEIGHT);

        frame.setLocation(width /
                ISource.DEFAULT_WIDTH, (height - desktopTaskbarHeight) /
                ISource.DEFAULT_HEIGHT);
        frame.setSize(source.getWidth() * width /
                ISource.DEFAULT_WIDTH, (source.getHeight() * (height - desktopTaskbarHeight) /
                ISource.DEFAULT_HEIGHT));
    }

    private VideoGrabber createGrabber(FrameViewer frame)
    {
        assert frame != null : "FrameViewer cannot be a null.";
        if(frame.getSource() == IVideoSource.NO_VIDEO_SOURCE)
        {
          throw new MissingVideoGrabberException("VideoGrabber instance cannot be a null.");
        }
        VideoGrabber videoGrabber = new VideoGrabber(frame.getSource());
        videoGrabber.setPreferredSize(frame.getPreferredSize());
       return  videoGrabber;
    }
    private FrameViewer findFrameViewerFrom(final ISource source)
    {
        assert source != null : "Source cannot be a null.";
        FrameViewer frameViewer = null;
        for(FrameViewer current : _frames )
        {
            if(current.getSource().isEqual(source))
            {
                frameViewer = current;
                break;
            }
        }
        return frameViewer;
    }

    public void formInternalFrameClosed(final IVideoSource source)
    {
        assert source != null : "Source cannot be a null.";
        FrameViewer frame = findFrameViewerFrom(source);
        stopRecording(source);
        stopBroadcasting(source);
        if (source.isPlaying())
        {
            source.stopSource();
        }
        _layerService.remove(source);
        source.reset();
        doNotification(source);
        removeFrame(frame);
    }

    
    private void doNotification( final ISource source)
    {
        assert source != null : "Source cannot be a null.";
        IMessage message = SourceMessage.createSourceMessage(source, Validity.INVALID);
        BroadcasterService broadcasterService = BroadcasterService.getInstance();
        broadcasterService.broadcastMessage(message);
         Logger.getLogger(FrameViewerControler.class.getName()).
                    log(Level.INFO, "Source message is broadcasted. " +
                    "REASON: [FrameView has been closed].");
    }
    
    public void startSource(IVideoSource source , boolean autoStart)
    {
        assert source != null : "Source cannot be a null.";
        FrameViewer frame =findFrameViewerFrom(source);
        frame.setTitle(getTitle(source));
        if (autoStart)
        {
            source.startSource();
           
        }
        
    }

    public String getTitle(final IVideoSource source)
    {
        assert source != null : "Source cannot be a null.";
        return source.getDeviceName()+
                " (" + (_layerService.size() - _layerService.getIndex(source)) + ")";
    }

   


}
