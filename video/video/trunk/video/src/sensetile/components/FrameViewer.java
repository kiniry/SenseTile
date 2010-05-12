
/*
 * FrameWebcam.java
 * Created on 2009-09-02, 18:27:52
 */
package sensetile.components;

import java.awt.Graphics;

import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import sensetile.common.components.SenseTileView;
import sensetile.common.sources.ISource;
import sensetile.common.utils.Guard;
import sensetile.video.controls.VideoGrabber;
import sensetile.video.sources.IVideoSource;
/**
 *
 * @author SenseTile
 */
public class FrameViewer extends JInternalFrame {

    IVideoSource _source = null;
    private int _width = ISource.DEFAULT_WIDTH;
    private int _height = ISource.DEFAULT_HEIGHT;
    private int _parentWidth = 640;
    private int _parentHeight =480;
   
    /** Creates new form FrameWebcam */
    public FrameViewer()
    {
        initComponents();
    }
    public void setParentSize(int width,int height){
        _parentWidth=width;
        _parentHeight=height;
    }

    public void setOutputSize(int width, int height) {
        _width = width;
        _height = height;
    }

    public void setSource(final IVideoSource source)
    {
        Guard.ArgumentNotNull(source, "Source cannot be a null.");
        _source = source;

    }

    public void setGrabberToPanViewer(final VideoGrabber grabber)
    {
        Guard.ArgumentNotNull(grabber, "VideoGrabber cannot be a null.");
        panViewer.add(grabber, BorderLayout.CENTER);
    }
  
    public void startSource(boolean autoStart)
    {
        FrameViewerControler.getInstance().startSource(_source, autoStart);
    }

    public IVideoSource getSource() {
        return _source;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpCaptureSize = new javax.swing.ButtonGroup();
        panViewer = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("sensetile/Languages"); // NOI18N
        setTitle(bundle.getString("IMAGE")); // NOI18N
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/image-x-generic.png"))); // NOI18N
        setOpaque(false);
        setVisible(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameDeiconified(evt);
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameIconified(evt);
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                formAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        panViewer.setBackground(java.awt.Color.red);
        panViewer.setDoubleBuffered(false);
        panViewer.setName("panViewer"); // NOI18N
        panViewer.setOpaque(false);
        panViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panViewerMousePressed(evt);
            }
        });
        panViewer.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panViewer, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panViewerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panViewerMousePressed
    }//GEN-LAST:event_panViewerMousePressed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed

        FrameViewerControler.getInstance().formInternalFrameClosed(_source);
    }//GEN-LAST:event_formInternalFrameClosed

    private void formInternalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeiconified
        _source.setSelected(true);
    }//GEN-LAST:event_formInternalFrameDeiconified

    private void formInternalFrameIconified(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameIconified
        _source.setSelected(false);
    }//GEN-LAST:event_formInternalFrameIconified

    private void formAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorMoved

    }//GEN-LAST:event_formAncestorMoved

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        if (_source != null) {
            int w = getWidth() * _width / _parentWidth;
            int h = (getHeight() + 6) * _height / (_parentHeight - SenseTileView.DesktopTaskbarHeight);
            _source.setWidth(IVideoSource.DEFAULT_WIDTH);
            _source.setHeight(IVideoSource.DEFAULT_HEIGHT);
        }
    }//GEN-LAST:event_formComponentResized

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (_source != IVideoSource.NO_VIDEO_SOURCE)
        {
            setTitle(FrameViewerControler.getInstance().getTitle(_source));
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup grpCaptureSize;
    private javax.swing.JPanel panViewer;
    // End of variables declaration//GEN-END:variables

}
