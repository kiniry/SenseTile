/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.common.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import sensetile.sensor.controls.telos.TelosViewer;
import sensetile.sensor.controls.telos.TelosViewerControler;
import sensetile.sensor.sources.telos.TelosSource;


/**
 *
 * @author SenseTile
 */
public class TelosItemCreator implements IActionListener
{

    private   TelosSource _telosSource = TelosSource.NONE;
    public TelosItemCreator()
    {
        super();
        _telosSource = TelosSource.createTelos();
    }


    public JMenuItem createTelosItem(final SenseTileView senseTileView)
    {
        JMenuItem menuItemTelos = null;


          if(_telosSource == TelosSource.NONE)
          {
              return menuItemTelos;
          }
          String telosName = TelosSource.createTelos().getDeviceName();
          menuItemTelos = new JMenuItem(telosName,1);
          menuItemTelos.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    actionPerform(senseTileView, ((JMenuItem) evt.getSource()).getMnemonic());
                }

            });
          return menuItemTelos;

    }

    public void actionPerform(Object obj, int index)
    {
        if(! obj.getClass().isAssignableFrom(SenseTileView.class))
        {
            return;
        }
        SenseTileView senseTileView = (SenseTileView)obj;

        TelosViewerControler controler = TelosViewerControler.getInstance();
        if(controler.containsFrameFrom(_telosSource))
        {
            return;
        }
        TelosViewer telosViewer =
                controler.createFrame(_telosSource, senseTileView.getDesktopWebcam().getWidth(),
                senseTileView.getDesktopWebcam().getHeight(), senseTileView.getDesktopTaskbarHeight());
        senseTileView.getDesktopWebcam().add(telosViewer, 0);

        telosViewer.requestFocus();
        try
        {
            telosViewer.setSelected(true);
        }
        catch (PropertyVetoException ex)
        {
            Logger.getLogger(SenseTileView.class.getName()).log(Level.SEVERE, "FIRED PROPERTY VETO.", ex);
        }
        senseTileView.getCboSelectedSource().revalidate();
        senseTileView.getCboSelectedSource().repaint();
        senseTileView.getCboSelectedSource().setSelectedItem(_telosSource);
        SenseTileViewControler st_controler = SenseTileViewControler.getInstance();
        st_controler.updateButtons(_telosSource);
    }

}
