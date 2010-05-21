/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.components;

import com.google.gdata.util.NotImplementedException;
import javax.swing.JFrame;
import sensetile.common.sources.ISource;
import sensetile.common.utils.Guard;

/**
 *
 * @author SenseTile
 */
public class VideoBroadcastHandler
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
        return _frameViewer;
    }
    
    public static VideoBroadcastHandler createHandler(final FrameViewer frameViewer)
    {
       Guard.ArgumentNotNull(frameViewer, "Frame Viewer cannot be a null.");
       return new VideoBroadcastHandler(frameViewer);
    }
    private BroadcasterFrame createBroadcaster()
   {
       _broadcasterFrame = new BroadcasterFrame( new JFrame(), true);
      
       _broadcasterFrame.setLocationRelativeTo(_frameViewer);

        _broadcasterFrame.pack();
       return _broadcasterFrame;
   }

   public BroadcasterFrame getBroadcasterFrame()
   {
       return _broadcasterFrame;
   }

   public void stopBroadcasting()
   {
       assert false;
   }

}
