package sensetile.components;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import sensetile.common.messages.IMessage;
import sensetile.common.messages.MessageType.TransmissionType;
import sensetile.common.messages.TransmissionMessage;
import sensetile.common.services.BroadcasterService;
import sensetile.common.sources.ISource;

/**
 *
 * @author SenseTile
 */
public class VideoBroadcastHandler implements Runnable
{
    private FrameViewer _frameViewer = null;
    private boolean _isBroadcasting = Boolean.FALSE;
    private ISource _source = ISource.NO_SOURCE;
    private BroadcasterFrame _broadcasterFrame = null;

    private VideoBroadcastHandler(final FrameViewer frameViewer )
    {
       _frameViewer = frameViewer;
       _source = frameViewer.getSource();
       createBroadcaster();
    }
    public boolean isBroadcasting()
    {
       return _isBroadcasting;
    }

    public  FrameViewer getFrameViewer()
    {
        assert _frameViewer != null: "Frame viewer cannot be a null.";
        return _frameViewer;
    }
    
    public static VideoBroadcastHandler createHandler(final FrameViewer frameViewer)
    {
       assert frameViewer != null : "FrameViewer cannot be a null.";
       return new VideoBroadcastHandler(frameViewer);
    }
    private BroadcasterFrame createBroadcaster()
    {
       _broadcasterFrame = new BroadcasterFrame( new JFrame(), true);
       _broadcasterFrame.setVideoBroadcastHandler(this);
       _broadcasterFrame.setLocationRelativeTo(_frameViewer);
       _broadcasterFrame.pack();
       return _broadcasterFrame;
    }

   public BroadcasterFrame getBroadcasterFrame()
   {
        assert _broadcasterFrame != null: "Broadcaster Frame  cannot be a null.";
       return _broadcasterFrame;
   }

    public void startBroadcasting()
   {
        doNotification(TransmissionType.BROADCASTING_PROCESS_STARTED);
       Logger.getLogger(VideoRecorderHandler.class.getName()).
                    log(Level.INFO, "Start broadcasting video stream.");
      /*
       * @TODO add appropriate code.
       */
        _isBroadcasting = Boolean.TRUE;

       new Thread(this).start();
   }
   public void stopBroadcasting()
   {
       Logger.getLogger(VideoRecorderHandler.class.getName()).
                    log(Level.INFO, "Stop broadcasting video stream.");
       /*
       * @TODO add appropriate code.
       */
       _isBroadcasting = Boolean.FALSE;
       doNotification(TransmissionType.BROADCASTING_PROCESS_FINISHED);
       assert false;
   }

   public void doNotification(TransmissionType aType)
    {
        IMessage message = TransmissionMessage.
                createTransmissionMessage(_source,aType);
        BroadcasterService broadcasterService = BroadcasterService.getInstance();
        broadcasterService.broadcastMessage(message);
         Logger.getLogger(VideoRecorderHandler.class.getName()).
                    log(Level.INFO, "Transmission message is broadcasted. " +
                    "REASON: [" + aType.toString() + "; Broadcaster frame has been closed].");
    }

    public void run() {
        assert false;
        /*
       * @TODO add appropriate code.
       */
    }

}
