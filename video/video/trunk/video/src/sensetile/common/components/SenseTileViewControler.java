package sensetile.common.components;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import sensetile.common.messages.IMessage;
import sensetile.common.services.LayerService;
import sensetile.common.sources.ISource;
import sensetile.common.utils.Guard;
import sensetile.components.ButtonControler;
import sensetile.components.DeviceInfo;
import sensetile.video.sources.IVideoSource;
import javax.swing.JInternalFrame.JDesktopIcon;
import javax.swing.JMenuItem;
import sensetile.common.messages.DeviceMessage;
import sensetile.common.messages.MessageType.PipeType;
import sensetile.common.messages.MessageType.Validity;
import sensetile.common.messages.PipeMessage;
import sensetile.common.messages.SourceMessage;
import sensetile.common.services.BroadcasterService;
import sensetile.common.services.IObservable;
import sensetile.components.FrameViewer;
import sensetile.devices.IDevice;
import sensetile.sensor.controls.telos.TelosViewer;
import sensetile.video.sources.VideoSource;


/**
 * @author SenseTile
 */
public class SenseTileViewControler implements IObservable
{
    private LayerService _layerService = LayerService.NONE;
    private static final String KIND_SENSE_TILE =
            "http://kind.ucd.ie/products/opensource/SenseTile/";
    private ButtonControler _buttonControler = null;
    private SenseTileView _senseTileView = null;
    public  SenseTileViewControler()
    {
      _layerService = LayerService.getInstance();
      _buttonControler = new ButtonControler();
     _senseTileView = new SenseTileView();
     BroadcasterService _broadcasterService = BroadcasterService.getInstance();
     _broadcasterService.attachObserver(this);
    }


    public void initialize()
    {
        _senseTileView.initialize();
        _senseTileView.setVisible(Boolean.TRUE);
    }

    public static SenseTileViewControler getInstance()
    {
        return Instance.sole_Instance;
    }

    public void update(IMessage message)
    {
        Guard.ArgumentNotNull(message, "Message cannot be a null.");
        if (message.getClass().
                isAssignableFrom(PipeMessage.class))
        {
            realizePipeMessageFromNotification(message);
        }
        else if(message.getClass().
                isAssignableFrom(DeviceMessage.class))
        {
            realizeDeviceMessageFromNotification(message);

        }
        else if(message.getClass().
                isAssignableFrom(SourceMessage.class))
        {
            realizeSourceMessageFromNotification(message);
        }else
        {
            Logger.getLogger(SenseTileViewControler.class.getName()).
                    log(Level.INFO, "Unknown message object: " + message.getClass().getName() +
                    "REASON: [Message is not supported in this implementation].");
        }
    }
    private void realizePipeMessageFromNotification(final IMessage message)
    {
        PipeMessage pipeMessage = (PipeMessage)message;
        Object obj = pipeMessage.getMessage();
        if(obj instanceof VideoSource &&
                pipeMessage.getPacketType() == PipeType.PIPE_BUS_ERROR)
        {
            VideoSource vSource = (VideoSource)obj;
            String name = vSource.getDeviceName();
            removeJManuItemVideoFor(name);
        }
    }
     private void realizeDeviceMessageFromNotification(final IMessage message)
     {
        DeviceMessage devMessage = (DeviceMessage)message;
        if(devMessage.getValidity()== Validity.INVALID)
        {
            IDevice telosDevice = (IDevice)devMessage.getMessage();
            removeJMenuTelosFor(telosDevice.getName());
        }
     }

     private void realizeSourceMessageFromNotification(final IMessage message)
     {
         SourceMessage sourceMessage =(SourceMessage)message;
         ISource source = (ISource)sourceMessage.getMessage();
         removeComboItem(source.getDeviceName());
     }
    private void removeJMenuTelosFor(String name)
    {
        Component[] components = _senseTileView.
                 getMnuSourcesSensors().getMenuComponents();
        _senseTileView.getMnuSourcesSensors().removeAll();

        for(JMenuItem item : createItemList(components, name))
        {
          _senseTileView.getMnuSourcesSensors().add(item);
        }
        _senseTileView.getMnuSourcesSensors().revalidate();
    }
    void removeJManuItemVideoFor( String name)
    {
        Component[] components = _senseTileView.
                 getMnuSourcesWebcams().getMenuComponents();
        _senseTileView.getMnuSourcesWebcams().removeAll();

        for(JMenuItem item : createItemList(components, name))
        {
          _senseTileView.getMnuSourcesWebcams().add(item);
        }
        _senseTileView.getMnuSourcesWebcams().revalidate();
    }

    List<JMenuItem> createItemList(final Component[] components,
            String name )
    {
        List<JMenuItem> itemList = new ArrayList<JMenuItem>();
        for(int i=0; i< components.length; i++)
         {
             Component comp = components[i];
             if(comp instanceof JMenuItem)
             {
                 JMenuItem item = (JMenuItem) comp;
                 if(item.getText().equalsIgnoreCase(name))
                 {

                     removeComboItem(name);
                     continue;
                 }
                itemList.add(item);
             }
         }
        return itemList;
    }

    public void updateSequence(List<IMessage> messages)
    {
        Guard.ArgumentNotNull(messages, "Message list cannot be a null.");
       
           for(IMessage message :messages )
           {

             if (message.getClass().
                     isAssignableFrom(SourceMessage.class))
              {
                realizeSourceMessageFromSequenceNotification(message);
              }
              else if(message.getClass().
                      isAssignableFrom(DeviceMessage.class))
              {
                realizeDeviceMessageFromSequenceNotification(message);
              }
              else
              {
                  Logger.getLogger(SenseTileViewControler.class.getName()).
                    log(Level.INFO, "Unknown message object: " + message.getClass().getName() +
                    "REASON: [Message is not supported in this implementation].");
              }
           }
        
        createVideoItemList();
        createTelosItem();

        
    }
    private void realizeDeviceMessageFromSequenceNotification(final IMessage message)
    {
        DeviceMessage devMessage = (DeviceMessage)message;
        if(devMessage.getValidity()== Validity.INVALID)
        {
            IDevice device = (IDevice)devMessage.getMessage();
            removeComboItem(device.getName());
        }
    }
    private void realizeSourceMessageFromSequenceNotification(final IMessage message)
    {
        SourceMessage sourceMessage = (SourceMessage) message;
        ISource source = (ISource) sourceMessage.getMessage();
        final boolean isNotValid =
            sourceMessage.getValidity() ==Validity.INVALID &&
              !source.isPlaying() && !source.isPaused();
        if(isNotValid)
        {
           removeComboItem(source.getDeviceName());
        }
    }

    public void createVideoItemList()
    {
        _senseTileView.getMnuSourcesWebcams().removeAll();
        WebCamItemCreator camItemCreator = new WebCamItemCreator(_senseTileView);
        List<JMenuItem> videoItemList = camItemCreator.createVideoItemList();
        for(JMenuItem item : videoItemList)
        {
          _senseTileView.getMnuSourcesWebcams().add(item);
        }
        _senseTileView.getMnuSourcesWebcams().repaint();
        _senseTileView.getMnuSourcesWebcams().revalidate();
    }

    private static class Instance
    {
        static final SenseTileViewControler
                sole_Instance = new SenseTileViewControler();
    }

    public Component getListCellRendererComponent(
            final Component component, final Object value)
    {
        Guard.ArgumentNotNull(component, "Component cannot be a null.");
        Guard.ArgumentNotNull(value, "Value cannot be a null.");
        JLabel label = (JLabel) component;
        if (value instanceof IVideoSource)
        {
            IVideoSource source = (IVideoSource) value;
            label.setText(source.getDeviceName());
            label.setToolTipText(source.getDeviceName());
            if (source.getImage() != null)
            {
                label.setIcon(new ImageIcon(source.getImage().
                        getScaledInstance(16, 16, BufferedImage.SCALE_FAST)));
            }
        }
        return component;
    }

    public void aboutItemActionPerformed()
    {
        About about = new About(_senseTileView, Boolean.TRUE);
        about.pack();
        about.setLocationRelativeTo(_senseTileView);
        about.setVisible(Boolean.TRUE);
    }

    public void videoInfoActionPerformed()
    {
         DeviceInfo d = new DeviceInfo(_senseTileView, Boolean.TRUE);
        d.setLocationRelativeTo(_senseTileView);
        d.pack();
        d.setVisible(Boolean.TRUE);
    }

    public void goToWebsiteActionPerformed()
    {
        try
        {
            java.awt.Desktop.getDesktop().browse(
                    URI.create(KIND_SENSE_TILE));
        }
        catch ( Exception ex)
        {
            Message msg = new Message(_senseTileView, Boolean.TRUE);
            msg.pack();
            msg.setLocationRelativeTo(_senseTileView);
            msg.setMessage(ex.getMessage());
            msg.setVisible(Boolean.TRUE);
        }
    }

    public void updateButtons(final ISource source)
    {
        // SOURCE CAN BE A NULL ! NO GUARD CONDITION.
        
        _buttonControler.doUpdateButtonsState(_senseTileView, source);
        updateLbLSourceLocation(source);
    }

    private void updateLbLSourceLocation( final ISource source)
    {
        _senseTileView.getLblSourceLocation().setText(" ");
        if (source != ISource.NO_SOURCE)
        {
            _senseTileView.getLblSourceLocation().
                    setText(source.getLocation());

        }
    }

    public void backwardActionPerformed()
    {
        JComboBox combo =  _senseTileView.getCboSelectedSource();
        if (combo.getSelectedItem() != null)
        {
            ISource source = (ISource) combo.getSelectedItem();
            _layerService.moveDown(source);
            setText();
        }
    }

    public void fowardActionPerformed()
    {
        JComboBox combo =  _senseTileView.getCboSelectedSource();
        if (combo.getSelectedItem() != null) {
            ISource source = (ISource) combo.getSelectedItem();
            _layerService.moveUp(source);
            setText();
        }
    }


    public void selectedSourceActionPerformed()
    {
        JComboBox combo =  _senseTileView.getCboSelectedSource();
        if (combo.getSelectedItem() != null) {
            ISource source = (ISource) combo.getSelectedItem();
            requestFocus(source);
            setText();
            updateButtons(source);
        }
    }

    public void removeSourceActionPerformed()
    {
        JComboBox combo =  _senseTileView.getCboSelectedSource();
        JDesktopPane desktopWebcam = _senseTileView.getDesktopWebcam();
        if (combo.getSelectedItem() != null)
        {
            ISource source = (ISource) combo.getSelectedItem();
            requestFocus(source);
           
            for (JInternalFrame frame : desktopWebcam.getAllFrames())
            {
                if(removeFrame(frame, source))
                {
                   break;
                }

            }
        }
        System.gc();
       _senseTileView.getCboSelectedSource().setSelectedIndex(-1);
        updateButtons(ISource.NO_SOURCE);
    }


    private void removeComboItem( final String  name)
    {
        JComboBox combo =  _senseTileView.getCboSelectedSource();
        int count = combo.getItemCount();
        for(int i=0; i< count; i++)
        {
            ISource source = (ISource)combo.getItemAt(i);
            if(source.getDeviceName().equalsIgnoreCase(name))
            {
                combo.removeItem(source);
                break;
            }
        }
        
        combo.revalidate();
        combo.repaint();
        combo.setSelectedIndex(-1);
        updateButtons(ISource.NO_SOURCE);
    }

    private boolean removeFrame( final JInternalFrame frame, final ISource source)
    {
        boolean frameRemoved = Boolean.FALSE;
        if(frame.getClass().isAssignableFrom(FrameViewer.class))
        {
          FrameViewer viewer = (FrameViewer) frame;
          if (viewer.getSource().equals(source))
          {
            frameRemoved = Boolean.TRUE;
             removeComboItem(source.getDeviceName());
            viewer.doDefaultCloseAction();
          }
        }
        else
        {
          TelosViewer viewer = (TelosViewer) frame;
          if (viewer.getSource().equals(source))
          {
            frameRemoved = Boolean.TRUE;
            removeComboItem(source.getDeviceName());
            viewer.doDefaultCloseAction();
          }
        }
         updateButtons(ISource.NO_SOURCE);
        return frameRemoved;

    }
    // @TODO DO REFACTORING...
    private void requestFocus(final  ISource source)
    {
        JInternalFrame frame = null;
        JDesktopPane desktopWebcam = _senseTileView.getDesktopWebcam();
        for (Component c : desktopWebcam.getComponents())
        {

            if (c.getClass().getName().indexOf("TaskBar") != -1)
            {
                JComponent jc = (JComponent) c;
                for (Component tc : jc.getComponents())
                {
                    JDesktopIcon jd_icon = (JDesktopIcon) tc;
                    frame = jd_icon.getInternalFrame();
                    if (frame.getTitle().indexOf("(" + _layerService.getIndex(source) + ")") != -1)
                    {
                        try
                        {
                            frame.setIcon(false);
                        }
                        catch (PropertyVetoException ex)
                        {
                            Logger.getLogger(SenseTileView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        frame.requestFocus();
                        try
                        {
                            frame.setSelected(true);
                        }
                        catch (PropertyVetoException ex)
                        {
                            Logger.getLogger(SenseTileView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        frame.moveToFront();
                        frame.revalidate();
                        desktopWebcam.revalidate();
                        break;
                    }
                }
            }
            if (frame != null)
            {
                break;
            }
            else if (c instanceof JInternalFrame)
            {
                frame = (JInternalFrame) c;
                if (frame.getTitle().indexOf("(" + _layerService.getIndex(source) + ")") != -1)
                {
                    try
                    {
                        frame.setIcon(false);
                    }
                    catch (PropertyVetoException ex)
                    {
                        Logger.getLogger(SenseTileView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    frame.requestFocus();
                    frame.moveToFront();
                    frame.revalidate();
                    desktopWebcam.revalidate();
                    break;
                }
            }
        }
    }


    public void startSourceActionPerformed()
    {
        JComboBox combo =  _senseTileView.getCboSelectedSource();
        if (combo.getSelectedItem() != null)
        {
            ISource source = (ISource) combo.getSelectedItem();
            if (source.isPaused())
            {
                source.play();
            }
            else if (!source.isPlaying())
            {
                source.startSource();
            }
            updateButtons(source);
        }
    }

    public void pauseSourceActionPerformed()
    {
        JComboBox combo =  _senseTileView.getCboSelectedSource();
        if (combo.getSelectedItem() != null)
        {
            ISource source = (ISource) combo.getSelectedItem();
            if (source.isPlaying())
            {
                source.pauseSource();
            }
            updateButtons(source);
        }

    }
    
    public void stopSourceActionPerformed()
    {
        JComboBox combo =  _senseTileView.getCboSelectedSource();
        if (combo.getSelectedItem() != null)
        {
            ISource source = (ISource) combo.getSelectedItem();
            if (source.isPlaying())
            {
                source.stopSource();
            }
            updateButtons(source);
        }
    }

    


    public void createTelosItem()
    {
        _senseTileView.getMnuSourcesSensors().removeAll();
        TelosItemCreator telosItemCreator = new TelosItemCreator();
        JMenuItem telosItem = telosItemCreator.createTelosItem(_senseTileView);
        if(telosItem != null)
        {
          _senseTileView.getMnuSourcesSensors().add(telosItem);
          _senseTileView.getMnuSourcesSensors().repaint();
          _senseTileView.getMnuSourcesSensors().revalidate();
        }
    }


    private void setText()
    {
        _senseTileView.getLblCurrentLayer().setText("" + (_layerService.size()-
                _senseTileView.getCboSelectedSource().getSelectedIndex()));
    }

}
