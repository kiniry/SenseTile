/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import sensetile.common.messages.MessageType.Validity;
import sensetile.common.messages.PipeMessage;
import sensetile.common.messages.SourceMessage;
import sensetile.common.sources.ISource;
import sensetile.common.utils.CommonUtils;
import sensetile.common.utils.Guard;
import sensetile.devices.IDevice;
import sensetile.video.controls.MissingVideoGrabberException;
import sensetile.video.controls.VideoGrabber;
import sensetile.video.sources.IVideoSource;


/**
 *
 * @author dragan
 */
public class FrameViewerControler implements IObservable
{
    
    private List<FrameViewer> _frames = null;
    private List<FileChooserHandler> _chooserHandlers = null;
    private LayerService _layerService = LayerService.NONE;


    private FrameViewerControler()
    {
      _frames = new ArrayList<FrameViewer>();
      _layerService = LayerService.getInstance();
      _chooserHandlers = new ArrayList<FileChooserHandler>();
      
    }

    public static FrameViewerControler getInstance()
    {
        return Instance.sole_Instance;
    }

    public void update(IMessage message)
    {
         Guard.ArgumentNotNull(message, "Message cannot be a null.");
        if (!CommonUtils.isTypeOf(message,
                 "sensetile.common.messages.PipeMessage"))
        {
            return;
        }
        PipeMessage packetMessage = (PipeMessage)message;
        PipeType type = packetMessage.getPacketType();
        IVideoSource source = (IVideoSource)packetMessage.getMessage();
        FrameViewer frameViewer = findFrameViewerFrom(source);
        if(type == PipeType.PIPE_BUS_ERROR && source.isEqual(frameViewer.getSource()))
        {
            frameViewer.doDefaultCloseAction();
        }
    }

    /**
     * @TODO DESIGN SHOULD BE BETTER - NO TIME AT THIS MOMENT TO IMPROVE DESIGN!
     * @param source
     */
    public void openFileChooser(ISource source)
    {
        Guard.ArgumentNotNull(source, "Source cannot be a null.");
        FileChooserHandler chooserHandler = null;
        FrameViewer frameViewer = findFrameViewerFrom(source);
        if(!source.isPlaying() && !source.isPaused())
        {
          return ;
      
        }
        chooserHandler = findFileChooserHandlerBy(source);
        
        if(chooserHandler != null && chooserHandler.isRecording())
        {
            return;
        }
        else
        {
            chooserHandler =  FileChooserHandler.createHandler(frameViewer);
            if(!_chooserHandlers.contains(chooserHandler)) 
            {
             _chooserHandlers.add(chooserHandler);
            }
        }
    }


    private FileChooserHandler findFileChooserHandlerBy( ISource source)
    {
        FileChooserHandler chooserHandler = null;
        for(FileChooserHandler fch : _chooserHandlers)
        {
            ISource currentSource = fch.getFrameViewer().getSource();
            if(currentSource.equals(source))
            {
                chooserHandler = fch;
                break;
            }
        }
        return chooserHandler;
    }

    public void stopExporting(final ISource source)
    {
       Guard.ArgumentNotNull(source, "Source cannot be a null.");
        FrameViewer frameViewer = findFrameViewerFrom(source);
       FileChooserHandler fileChooserHandler =
               findFileChooserHandler(frameViewer);
       if(fileChooserHandler != null &&
               fileChooserHandler.isRecording())
       {
             fileChooserHandler.stopExporting();
             if(_chooserHandlers.contains(fileChooserHandler))
             {
                _chooserHandlers.remove(fileChooserHandler);
             }
       }
    }


    private FileChooserHandler findFileChooserHandler(final FrameViewer frameViewer)
    {
        FileChooserHandler fileChooser = null;
        Guard.ArgumentNotNull(frameViewer, "Frame Viewer cannot be a null.");
        for(FileChooserHandler chooserHandler : _chooserHandlers)
        {
            FrameViewer fv = chooserHandler.getFrameViewer();
            if( fv != null && fv.equals(frameViewer))
            {
                fileChooser = chooserHandler;
                break;
            }
        }
        return fileChooser;
    }

    public void updateSequence(List<IMessage> messages)
    {
       Guard.ArgumentNotNull(messages, "Message list cannot be a null.");
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
        boolean contains = Boolean.FALSE;
        Guard.ArgumentNotNull(frame, "Frame cannot be a null.");
        if(_frames.contains(frame))
        {
           contains = Boolean.TRUE;
        }
        return contains;
    }

    public void addFrame( final FrameViewer frame)
    {
        Guard.ArgumentNotNull(frame, "Frame cannot be a null.");
        if(!contains(frame))
        {
            _frames.add(frame);
        }
    }

    public void removeFrame(final FrameViewer frame)
    {
        Guard.ArgumentNotNull(frame, "Frame cannot be a null.");
        if(contains(frame))
        {
            _frames.remove(frame);
        }
    }
     
    public boolean containsFrameFrom(IVideoSource source) 
    {
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
        Guard.ArgumentNotNull(source, "Source cannot be a null.");
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
        Guard.ArgumentNotNull(source, "Video source cannot be a null.");
        FrameViewer frame = findFrameViewerFrom(source);
        stopExporting(frame);
        if (source.isPlaying())
        {
            source.stopSource();
        }
        _layerService.remove(source);
        source.reset();
        doNotification(source);
        removeFrame(frame);
    }

    private void stopExporting(FrameViewer frame)
    {
      Guard.ArgumentNotNull(frame, "FrameViewer cannot be a null.");
      FileChooserHandler chooserHandler = findFileChooserHandler(frame);
      if(chooserHandler != null && chooserHandler.isRecording())
      {
          chooserHandler.stopExporting();
      }
    }
    private void doNotification( final ISource source)
    {
        IMessage message = SourceMessage.createSourceMessage(source, Validity.INVALID);
        BroadcasterService broadcasterService = BroadcasterService.getInstance();
        broadcasterService.broadcastMessage(message);
         Logger.getLogger(FrameViewerControler.class.getName()).
                    log(Level.INFO, "Source message is broadcasted. " +
                    "REASON: [FrameView has been closed].");
    }
    
    public void startSource(IVideoSource source , boolean autoStart)
    {
        Guard.ArgumentNotNull(source, "Video source cannot be a null.");
        FrameViewer frame =findFrameViewerFrom(source);
        frame.setTitle(getTitle(source));
        if (autoStart)
        {
            source.startSource();
           
        }
        
    }

    public String getTitle(final IVideoSource source)
    {
        Guard.ArgumentNotNull(source, "Video source cannot be a null.");
        return source.getDeviceName()+
                " (" + (_layerService.size() - _layerService.getIndex(source)) + ")";
    }

   


}
