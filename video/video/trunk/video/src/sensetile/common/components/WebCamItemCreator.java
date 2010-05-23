/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.common.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import sensetile.common.services.LayerService;
import sensetile.components.FrameViewer;
import sensetile.components.FrameViewerControler;
import sensetile.video.sources.IVideoSource;
import sensetile.video.sources.VideoSourceProvider;

/**
 *
 * @author SenseTile
 */
public class WebCamItemCreator implements IActionListener
{

    private VideoSourceProvider _videoProvider = VideoSourceProvider.NONE;
    private  LayerService _layerService = LayerService.NONE;
    private SenseTileView _senseTileView = null;
    public WebCamItemCreator(final SenseTileView senseTileView)
    {
        super();
        _videoProvider = VideoSourceProvider.getInstance();
        _layerService = LayerService.getInstance();
        _senseTileView = senseTileView;
    }

    public List<JMenuItem> createVideoItemList()
    {
          List<JMenuItem> videoItemList = new ArrayList<JMenuItem>();
         List<IVideoSource> sources =  _videoProvider.getSourcesWebcams();
         for(int i=0 ; i<sources.size(); i++)
         {
            IVideoSource source = sources.get(i);
            if( !source.isReadyToProcess())
            {
                _layerService.remove(source);
                continue;
            }

            JMenuItem menuItemV4L = new JMenuItem(source.getDeviceName(), i);

            menuItemV4L.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    actionPerform(_senseTileView, ((JMenuItem) evt.getSource()).getMnemonic());
                }
            });
            videoItemList.add(menuItemV4L);
        }
         return videoItemList;
    }
    public void actionPerform(Object obj , final int index)
    {
        if(! obj.getClass().isAssignableFrom(SenseTileView.class))
        {
            return;
        }
        SenseTileView senseTileView = (SenseTileView)obj;
        IVideoSource videoSource = _videoProvider.getVideoSourceAt(index);
       
        if(_layerService.contains(videoSource))
        {
            return;
        }

        FrameViewerControler controler = FrameViewerControler.getInstance();
        if(controler.containsFrameFrom(videoSource))
        {
            return;
        }
        FrameViewer frameViewer =
                controler.createFrame(videoSource,
                senseTileView.getDesktopWebcam().getWidth(),
                senseTileView.getDesktopWebcam().getHeight(), 
                senseTileView.getDesktopTaskbarHeight());

        senseTileView.getDesktopWebcam().add(frameViewer, 0);

        frameViewer.requestFocus();
        try
        {
            frameViewer.setSelected(true);
        }
        catch (PropertyVetoException ex)
        {
            Logger.getLogger(SenseTileView.class.getName()).log(Level.SEVERE, "FIRED PROPERTY VETO.", ex);
        }
        senseTileView.getCboSelectedSource().revalidate();
        senseTileView.getCboSelectedSource().repaint();
        senseTileView.getCboSelectedSource().setSelectedItem(videoSource);
        SenseTileViewControler st_controler = SenseTileViewControler.getInstance();
        st_controler.updateButtons(videoSource);
    }




}
