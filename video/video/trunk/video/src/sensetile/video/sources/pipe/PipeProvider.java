/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.video.sources.pipe;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.gstreamer.Bus;
import org.gstreamer.Element;
import org.gstreamer.Format;
import org.gstreamer.GstObject;
import org.gstreamer.Pipeline;
import org.gstreamer.State;
import sensetile.common.services.BroadcasterService;
import sensetile.common.messages.IMessage;
import sensetile.common.messages.MessageType.PipeType;
import sensetile.common.messages.PipeMessage;
import sensetile.video.controls.RGBDataSinkExtended;
import sensetile.video.sources.IVideoSource;
/**
 *
 * @author SenseTile
 */
public class PipeProvider {

    public static final PipeProvider NONE = null;
    private Pipeline _pipeline = null;
    private boolean _isInitialized = Boolean.FALSE;
    private boolean _isStarted = Boolean.FALSE;
    private boolean _isPaused = Boolean.FALSE;
    private RGBDataSinkExtended _elementSink = null;
    public PipeProvider()
    {
        super();
    }
    
    public void  initializePipe(final IVideoSource videoSource)
    {
        assert videoSource != null : "Video source cannot be a null.";
        
       _elementSink = RGBDataSinkExtended.createRGBSink(videoSource);
       String rescaling = "! videoscale ! video/x-raw-yuv,width=" +
                    videoSource.getWidth() + ",height=" + videoSource.getHeight() +
                    " ! videorate ,framerate=\\(fraction\\)" + videoSource.getFrameRate() +
                    " " + "! ffmpegcolorspace ! identity";
       String launchCommand = videoSource.getSource() + 
               " device=" + videoSource.getLocation() +
               " " + rescaling + " " + " name=tosink";
       final Pipeline  pipe = Pipeline.launch(launchCommand);

        Logger.getLogger(PipeProvider.class.getName()).log(Level.INFO,
                                    "Pipeline.launch with: " + launchCommand);
        pipe.add(_elementSink);
        Element e = pipe.getElementByName("tosink");
        e.link(_elementSink);
        pipe.getBus().connect(new Bus.SEGMENT_START()
        {
            @Override
            public void segmentStart(GstObject arg0, Format arg1, long arg2)
            {
                 Logger.getLogger(PipeProvider.class.getName()).log(Level.INFO,
                                    "SEGMENT_START: " + arg0.toString() +
                                    " : " + arg1 + ", " + arg2);
            }
        });
        pipe.getBus().connect(new Bus.INFO()
        {
            @Override
            public void infoMessage(GstObject arg0, int arg1, String arg2)
            {
                Logger.getLogger(PipeProvider.class.getName()).log(Level.INFO,
                                    "INFO: " + arg1 + ", " + arg2);
            }
        });
        pipe.getBus().connect(new Bus.BUFFERING()
        {
            @Override
            public void bufferingData(GstObject arg0, int arg1)
            {

                 Logger.getLogger(PipeProvider.class.getName()).log(Level.INFO,
                                    "BUFFERING: " + arg1);
            }
        });
        pipe.getBus().connect(new Bus.EOS()
        {
            @Override
            public void endOfStream(GstObject arg0)
            {
                IMessage endOfStreamMessage =
                        PipeMessage.createMessage(videoSource, PipeType.PIPE_BUS_EOS);
                BroadcasterService.getInstance().broadcastMessage(endOfStreamMessage);
                pipe.stop();
            }
        });
        pipe.getBus().connect(new Bus.ERROR()
        {

            @Override
            public void errorMessage(GstObject arg0, int arg1, String arg2)
            {
                
                IMessage errorMessage =
                        PipeMessage.createMessage(videoSource, PipeType.PIPE_BUS_ERROR);
                 Logger.getLogger(PipeProvider.class.getName()).
                    log(Level.INFO, "Message has been broadcasted. " +
                    "REASON: [Error in pipe stream].");
                BroadcasterService.getInstance().broadcastMessage(errorMessage);
                Logger.getLogger(PipeProvider.class.getName()).log(Level.SEVERE,
                                    "Pipe Provider: " + videoSource.getDeviceName() +
                                    " Error: " + arg0 + "," + arg1 + ", " + arg2);    
            }
        });
         _isInitialized = Boolean.TRUE;
         _pipeline = pipe;
    }
    public void startPipe()
    {
        if(_isInitialized && !_isStarted )
        {
           _pipeline.setState(State.PLAYING);
           _isStarted = Boolean.TRUE;
           _isInitialized = Boolean.TRUE;
           _isPaused = Boolean.FALSE;
        }
    }
    public void stopPipe()
    {
       if(_isInitialized &&  _isStarted)
       {
        _pipeline.setState(State.NULL);
        _isStarted = Boolean.FALSE;
        _isInitialized = Boolean.FALSE;
        _isPaused = Boolean.FALSE;
        _elementSink = null;
        _pipeline = null;
       }
    }
    public void pausePipe()
    {
        if(_isInitialized &&  _isStarted)
        {
           _pipeline.setState(State.PAUSED);
           _isStarted = Boolean.TRUE;
           _isInitialized = Boolean.TRUE;
           _isPaused = Boolean.TRUE;
        }
    }
    public void play()
    {
        if(_isInitialized &&  _isStarted)
        {
           _pipeline.setState(State.PLAYING);
           _isStarted = Boolean.TRUE;
           _isPaused = Boolean.FALSE;
        }
    }
    public boolean isPaused()
    {
        return _isPaused;
    }
}
