package sensetile.common.components;

import java.awt.Dimension;
import org.gstreamer.Gst;

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;
import sensetile.audio.AudioLevel;
import sensetile.common.services.LayerService;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
/**
 *
 * @author SenseTile
 */
public class SenseTileView extends JFrame implements Runnable
{
     private java.awt.Image desktopBackground = null;
     public static int DesktopTaskbarHeight = 0;
     private AudioLevel _audioLevel = null;

     
    /** Creates new form CamView */
    public SenseTileView()
    {
    }
    void initialize()
    {
        
        _audioLevel = new AudioLevel();
        _audioLevel.initialize();
        Gst.init(java.util.ResourceBundle.getBundle("sensetile/Languages").getString("CAM"), new String[0]);
        desktopBackground = getToolkit().getImage(java.net.URLClassLoader.
                getSystemResource("sensetile/resources/tango/sensetile.png"));
        initComponents();
        for (java.awt.Component c : desktopWebcam.getComponents())
        {
            if (c.getClass().getName().toLowerCase().indexOf("taskbar") != -1) {
                DesktopTaskbarHeight = c.getHeight() + 6;
            }
        }
          panDesktop.setPreferredSize(new Dimension(640 + 12, 480 + DesktopTaskbarHeight + 12));
          detectWebcams();
          detectTelosSensor();
          new Thread(this).start();
           java.awt.Image img = getToolkit().getImage(java.net.URLClassLoader.
                   getSystemResource("sensetile/resources/tango/infocam.png"));
           setIconImage(img);

          javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel(LayerService.getInstance().getSources());
          cboSelectedSource.setModel(model);
          javax.swing.DefaultListCellRenderer renderer = new javax.swing.DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus)
            {
                Component component =
                        super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
                if(value == null)
                {
                    return component;
                }
                SenseTileViewControler senseTileViewControler =
                        SenseTileViewControler.getInstance();
                return senseTileViewControler.getListCellRendererComponent(component, value);
            }
        };
        cboSelectedSource.setRenderer(renderer);
        pack();
     }
      private void detectTelosSensor()
      {
          SenseTileViewControler st_conControler = SenseTileViewControler.getInstance();
          st_conControler.createTelosItem();

      }
      private void detectWebcams() 
      {
          SenseTileViewControler st_conControler = SenseTileViewControler.getInstance();
          st_conControler.createVideoItemList();
      }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelStatus = new javax.swing.JPanel();
        pgAudioLevel = new javax.swing.JProgressBar();
        lblOutputDevice = new javax.swing.JLabel();
        tabControls = new javax.swing.JTabbedPane();
        panDesktop = new javax.swing.JPanel(){
            public void paint(java.awt.Graphics g){
                if (mnuchkShowBackground != null && mnuchkShowBackground.isSelected()){
                    g.drawImage(desktopBackground, 0, 0, this.getWidth(),this.getHeight()-SenseTileView.DesktopTaskbarHeight,0,0,desktopBackground.getWidth(null),desktopBackground.getHeight(null),null);
                }
                super.paint(g);
            }
        }
        ;
        desktopWebcam = new javax.swing.JDesktopPane();
        panControls = new javax.swing.JPanel();
        lblSelectedSource = new javax.swing.JLabel();
        cboSelectedSource = new javax.swing.JComboBox();
        lblLayer = new javax.swing.JLabel();
        lblCurrentLayer = new javax.swing.JLabel();
        btnFoward = new javax.swing.JButton();
        btnBackward = new javax.swing.JButton();
        btnStartSource = new javax.swing.JButton();
        btnPauseSource = new javax.swing.JButton();
        btnStopSource = new javax.swing.JButton();
        btnRemoveSource = new javax.swing.JButton();
        lblSourceLocation = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        mnuSources = new javax.swing.JMenu();
        mnuSourcesWebcams = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuSourcesSensors = new javax.swing.JMenu();
        mnuOutput = new javax.swing.JMenu();
        mnuOutputWebcams = new javax.swing.JMenu();
        mnuVideoRecorder = new javax.swing.JMenuItem();
        mnuAbout = new javax.swing.JMenu();
        mnuAboutItem = new javax.swing.JMenuItem();
        mnuGoToWebsite = new javax.swing.JMenuItem();
        mnuVideoInfo = new javax.swing.JMenuItem();
        mnuchkShowBackground = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("sensetile/Languages"); // NOI18N
        setTitle(bundle.getString("SENSETILE/LINUX")); // NOI18N
        setMinimumSize(new java.awt.Dimension(650, 546));
        setResizable(false);

        panelStatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelStatus.setMinimumSize(new java.awt.Dimension(202, 23));
        panelStatus.setName("panelStatus"); // NOI18N
        panelStatus.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        pgAudioLevel.setMaximum(128);
        pgAudioLevel.setToolTipText(bundle.getString("AUDIOMETER")); // NOI18N
        pgAudioLevel.setValue(50);
        pgAudioLevel.setName("pgAudioLevel"); // NOI18N
        panelStatus.add(pgAudioLevel);

        lblOutputDevice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/aboutCam.png"))); // NOI18N
        lblOutputDevice.setText(bundle.getString("NO_OUTPUT!")); // NOI18N
        lblOutputDevice.setName("lblOutputDevice"); // NOI18N
        panelStatus.add(lblOutputDevice);

        getContentPane().add(panelStatus, java.awt.BorderLayout.SOUTH);

        tabControls.setName("tabControls"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(SenseTileView.class);
        panDesktop.setBackground(resourceMap.getColor("panDesktop.background")); // NOI18N
        panDesktop.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panDesktop.setName("panDesktop"); // NOI18N
        panDesktop.setOpaque(false);
        panDesktop.setLayout(new java.awt.BorderLayout());

        desktopWebcam.setBackground(resourceMap.getColor("desktopWebcam.background")); // NOI18N
        desktopWebcam.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        desktopWebcam.setAutoscrolls(true);
        desktopWebcam.setMaximumSize(new java.awt.Dimension(640, 480));
        desktopWebcam.setMinimumSize(new java.awt.Dimension(640, 480));
        desktopWebcam.setName("desktopWebcam"); // NOI18N
        desktopWebcam.setOpaque(false);
        desktopWebcam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                desktopWebcamMousePressed(evt);
            }
        });
        panDesktop.add(desktopWebcam, java.awt.BorderLayout.CENTER);

        tabControls.addTab(bundle.getString("output"), new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/image-x-generic.png")), panDesktop); // NOI18N

        panControls.setName("panControls"); // NOI18N

        lblSelectedSource.setText("Source"); // NOI18N
        lblSelectedSource.setName("lblSelectedSource"); // NOI18N

        cboSelectedSource.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSelectedSource.setName("cboSelectedSource"); // NOI18N
        cboSelectedSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSelectedSourceActionPerformed(evt);
            }
        });

        lblLayer.setText(bundle.getString("LAYER")); // NOI18N
        lblLayer.setName("lblLayer"); // NOI18N

        lblCurrentLayer.setName("lblCurrentLayer"); // NOI18N

        btnFoward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/go-up.png"))); // NOI18N
        btnFoward.setToolTipText(bundle.getString("MOVEUP")); // NOI18N
        btnFoward.setEnabled(false);
        btnFoward.setName("btnFoward"); // NOI18N
        btnFoward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFowardActionPerformed(evt);
            }
        });

        btnBackward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/go-down.png"))); // NOI18N
        btnBackward.setToolTipText(bundle.getString("MOVE_DOWN")); // NOI18N
        btnBackward.setEnabled(false);
        btnBackward.setName("btnBackward"); // NOI18N
        btnBackward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackwardActionPerformed(evt);
            }
        });

        btnStartSource.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/control_play_blue.png"))); // NOI18N
        btnStartSource.setToolTipText(bundle.getString("PLAY")); // NOI18N
        btnStartSource.setEnabled(false);
        btnStartSource.setName("btnStartSource"); // NOI18N
        btnStartSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartSourceActionPerformed(evt);
            }
        });

        btnPauseSource.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/control_pause_blue.png"))); // NOI18N
        btnPauseSource.setToolTipText(bundle.getString("PAUSE")); // NOI18N
        btnPauseSource.setEnabled(false);
        btnPauseSource.setName("btnPauseSource"); // NOI18N
        btnPauseSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseSourceActionPerformed(evt);
            }
        });

        btnStopSource.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/control_stop_blue.png"))); // NOI18N
        btnStopSource.setToolTipText(bundle.getString("STOP")); // NOI18N
        btnStopSource.setEnabled(false);
        btnStopSource.setName("btnStopSource"); // NOI18N
        btnStopSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopSourceActionPerformed(evt);
            }
        });

        btnRemoveSource.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/cancel.png"))); // NOI18N
        btnRemoveSource.setToolTipText(bundle.getString("REMOVE")); // NOI18N
        btnRemoveSource.setEnabled(false);
        btnRemoveSource.setName("btnRemoveSource"); // NOI18N
        btnRemoveSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveSourceActionPerformed(evt);
            }
        });

        lblSourceLocation.setText(null);
        lblSourceLocation.setName("lblSourceLocation"); // NOI18N

        javax.swing.GroupLayout panControlsLayout = new javax.swing.GroupLayout(panControls);
        panControls.setLayout(panControlsLayout);
        panControlsLayout.setHorizontalGroup(
            panControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panControlsLayout.createSequentialGroup()
                        .addComponent(lblSelectedSource)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLayer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCurrentLayer, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboSelectedSource, 0, 556, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panControlsLayout.createSequentialGroup()
                        .addComponent(lblSourceLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStartSource)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPauseSource)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStopSource)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveSource)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFoward)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBackward)))
                .addContainerGap())
        );
        panControlsLayout.setVerticalGroup(
            panControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectedSource)
                    .addComponent(lblLayer)
                    .addComponent(lblCurrentLayer)
                    .addComponent(cboSelectedSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFoward)
                    .addGroup(panControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnStartSource)
                        .addComponent(btnPauseSource)
                        .addComponent(btnStopSource))
                    .addComponent(btnRemoveSource)
                    .addComponent(btnBackward)
                    .addComponent(lblSourceLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(407, Short.MAX_VALUE))
        );

        tabControls.addTab(resourceMap.getString("panControls.TabConstraints.tabTitle"), resourceMap.getIcon("panControls.TabConstraints.tabIcon"), panControls); // NOI18N

        getContentPane().add(tabControls, java.awt.BorderLayout.CENTER);
        tabControls.getAccessibleContext().setAccessibleName(resourceMap.getString("tabControls.AccessibleContext.accessibleName")); // NOI18N

        menuBar.setMaximumSize(new java.awt.Dimension(402, 32769));
        menuBar.setName("menuBar"); // NOI18N
        menuBar.setPreferredSize(new java.awt.Dimension(402, 25));

        mnuSources.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/video-display.png"))); // NOI18N
        mnuSources.setText(bundle.getString("SOURCES")); // NOI18N
        mnuSources.setName("mnuSources"); // NOI18N
        mnuSources.setPreferredSize(new java.awt.Dimension(100, 23));

        mnuSourcesWebcams.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/camera-video.png"))); // NOI18N
        mnuSourcesWebcams.setText(bundle.getString("WEBCAMS")); // NOI18N
        mnuSourcesWebcams.setName("mnuSourcesWebcams"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N
        mnuSourcesWebcams.add(jSeparator1);

        mnuSources.add(mnuSourcesWebcams);

        mnuSourcesSensors.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/transmit_blue.png"))); // NOI18N
        mnuSourcesSensors.setText(bundle.getString("SENSORS")); // NOI18N
        mnuSourcesSensors.setName("mnuSourcesSensors"); // NOI18N
        mnuSources.add(mnuSourcesSensors);

        menuBar.add(mnuSources);

        mnuOutput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/image-x-generic.png"))); // NOI18N
        mnuOutput.setText(bundle.getString("OUTPUT")); // NOI18N
        mnuOutput.setName("mnuOutput"); // NOI18N
        mnuOutput.setPreferredSize(new java.awt.Dimension(100, 23));

        mnuOutputWebcams.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/camera-video.png"))); // NOI18N
        mnuOutputWebcams.setText(bundle.getString("WEBCAMS")); // NOI18N
        mnuOutputWebcams.setName("mnuOutputWebcams"); // NOI18N

        mnuVideoRecorder.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        mnuVideoRecorder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/media-record.png"))); // NOI18N
        mnuVideoRecorder.setText(bundle.getString("VIDEO_RECORDER")); // NOI18N
        mnuVideoRecorder.setName("mnuVideoRecorder"); // NOI18N
        mnuVideoRecorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuVideoRecorderActionPerformed(evt);
            }
        });
        mnuOutputWebcams.add(mnuVideoRecorder);

        mnuOutput.add(mnuOutputWebcams);

        menuBar.add(mnuOutput);

        mnuAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/aboutst.png"))); // NOI18N
        mnuAbout.setText(bundle.getString("ABOUT")); // NOI18N
        mnuAbout.setActionCommand(resourceMap.getString("mnuAbout.actionCommand")); // NOI18N
        mnuAbout.setLabel(bundle.getString("ABOUT")); // NOI18N
        mnuAbout.setMaximumSize(new java.awt.Dimension(100, 32767));
        mnuAbout.setName("mnuAbout"); // NOI18N
        mnuAbout.setPreferredSize(new java.awt.Dimension(100, 43));

        mnuAboutItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        mnuAboutItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/aboutst.png"))); // NOI18N
        mnuAboutItem.setText(bundle.getString("ABOUT")); // NOI18N
        mnuAboutItem.setName("mnuAboutItem"); // NOI18N
        mnuAboutItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAboutItemActionPerformed(evt);
            }
        });
        mnuAbout.add(mnuAboutItem);

        mnuGoToWebsite.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        mnuGoToWebsite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/goto.png"))); // NOI18N
        mnuGoToWebsite.setText(bundle.getString("GOTOWEBSITE")); // NOI18N
        mnuGoToWebsite.setName("mnuGoToWebsite"); // NOI18N
        mnuGoToWebsite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGoToWebsiteActionPerformed(evt);
            }
        });
        mnuAbout.add(mnuGoToWebsite);

        mnuVideoInfo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        mnuVideoInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sensetile/resources/tango/aboutCam.png"))); // NOI18N
        mnuVideoInfo.setText(bundle.getString("DEVICEINFO")); // NOI18N
        mnuVideoInfo.setName("mnuVideoInfo"); // NOI18N
        mnuVideoInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuVideoInfoActionPerformed(evt);
            }
        });
        mnuAbout.add(mnuVideoInfo);

        mnuchkShowBackground.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mnuchkShowBackground.setSelected(true);
        mnuchkShowBackground.setText(bundle.getString("MAIN_SHOW_SPLASH")); // NOI18N
        mnuchkShowBackground.setActionCommand(resourceMap.getString("mnuchkShowBackground.actionCommand")); // NOI18N
        mnuchkShowBackground.setLabel(bundle.getString("MAIN_SHOW_SPLASH")); // NOI18N
        mnuchkShowBackground.setName("mnuchkShowBackground"); // NOI18N
        mnuchkShowBackground.setPreferredSize(new java.awt.Dimension(243, 23));
        mnuAbout.add(mnuchkShowBackground);

        menuBar.add(mnuAbout);

        setJMenuBar(menuBar);

        getAccessibleContext().setAccessibleName(resourceMap.getString("frame1.AccessibleContext.accessibleName")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuGoToWebsiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGoToWebsiteActionPerformed
         SenseTileViewControler controler = SenseTileViewControler.getInstance();
         controler.goToWebsiteActionPerformed();
}//GEN-LAST:event_mnuGoToWebsiteActionPerformed

    private void mnuAboutItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAboutItemActionPerformed
       SenseTileViewControler controler = SenseTileViewControler.getInstance();
       controler.aboutItemActionPerformed();
}//GEN-LAST:event_mnuAboutItemActionPerformed

    private void mnuVideoInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVideoInfoActionPerformed
        SenseTileViewControler controler = SenseTileViewControler.getInstance();
        controler.videoInfoActionPerformed();
}//GEN-LAST:event_mnuVideoInfoActionPerformed

    private void desktopWebcamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desktopWebcamMousePressed

}//GEN-LAST:event_desktopWebcamMousePressed

    private void cboSelectedSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSelectedSourceActionPerformed
        SenseTileViewControler st_controler = SenseTileViewControler.getInstance();
        st_controler.selectedSourceActionPerformed();
}//GEN-LAST:event_cboSelectedSourceActionPerformed

    private void btnFowardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFowardActionPerformed
        SenseTileViewControler st_controler = SenseTileViewControler.getInstance();
        st_controler.fowardActionPerformed();
}//GEN-LAST:event_btnFowardActionPerformed

    private void btnBackwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackwardActionPerformed
        SenseTileViewControler st_controler = SenseTileViewControler.getInstance();
        st_controler.backwardActionPerformed();
}//GEN-LAST:event_btnBackwardActionPerformed

    private void btnStartSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartSourceActionPerformed
        SenseTileViewControler st_controler = SenseTileViewControler.getInstance();
        st_controler.startSourceActionPerformed();
        
}//GEN-LAST:event_btnStartSourceActionPerformed

    private void btnPauseSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseSourceActionPerformed
        SenseTileViewControler st_controler = SenseTileViewControler.getInstance();
        st_controler.pauseSourceActionPerformed();
}//GEN-LAST:event_btnPauseSourceActionPerformed

    private void btnStopSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopSourceActionPerformed
        SenseTileViewControler st_controler = SenseTileViewControler.getInstance();
        st_controler.stopSourceActionPerformed();
}//GEN-LAST:event_btnStopSourceActionPerformed

    private void btnRemoveSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveSourceActionPerformed
        SenseTileViewControler st_controler = SenseTileViewControler.getInstance();
        st_controler.removeSourceActionPerformed();
}//GEN-LAST:event_btnRemoveSourceActionPerformed

    private void mnuVideoRecorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVideoRecorderActionPerformed

}//GEN-LAST:event_mnuVideoRecorderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackward;
    private javax.swing.JButton btnFoward;
    private javax.swing.JButton btnPauseSource;
    private javax.swing.JButton btnRemoveSource;
    private javax.swing.JButton btnStartSource;
    private javax.swing.JButton btnStopSource;
    private javax.swing.JComboBox cboSelectedSource;
    private javax.swing.JDesktopPane desktopWebcam;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblCurrentLayer;
    private javax.swing.JLabel lblLayer;
    private javax.swing.JLabel lblOutputDevice;
    private javax.swing.JLabel lblSelectedSource;
    private javax.swing.JLabel lblSourceLocation;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mnuAbout;
    private javax.swing.JMenuItem mnuAboutItem;
    private javax.swing.JMenuItem mnuGoToWebsite;
    private javax.swing.JMenu mnuOutput;
    private javax.swing.JMenu mnuOutputWebcams;
    private javax.swing.JMenu mnuSources;
    private javax.swing.JMenu mnuSourcesSensors;
    private javax.swing.JMenu mnuSourcesWebcams;
    private javax.swing.JMenuItem mnuVideoInfo;
    private javax.swing.JMenuItem mnuVideoRecorder;
    private javax.swing.JCheckBoxMenuItem mnuchkShowBackground;
    private javax.swing.JPanel panControls;
    private javax.swing.JPanel panDesktop;
    private javax.swing.JPanel panelStatus;
    private javax.swing.JProgressBar pgAudioLevel;
    private javax.swing.JTabbedPane tabControls;
    // End of variables declaration//GEN-END:variables

    public JComboBox getCboSelectedSource()
    {
        return cboSelectedSource;
    }

    public JButton getBtnStopSource()
    {
        return btnStopSource;
    }

    public JButton getBtnStartSource()
    {
        return btnStartSource;
    }

    public JButton getBtnPauseSource()
    {
        return btnPauseSource;
    }

    public JButton getBtnRemoveSource()
    {
        return btnRemoveSource;
    }

    public JButton getBtnBackward()
    {
        return btnBackward;
    }

    public JButton getBtnFoward()
    {
        return btnFoward;
    }

    public JLabel getLblSourceLocation()
    {
        return lblSourceLocation;
    }

    public JLabel getLblCurrentLayer()
    {
        return lblCurrentLayer;
    }

    public JDesktopPane getDesktopWebcam()
    {
        return desktopWebcam;
    }

    public JMenu getMnuSourcesSensors()
    {
        return mnuSourcesSensors;
    }

    public JMenu getMnuSourcesWebcams()
    {
        return mnuSourcesWebcams;
    }

    public int getDesktopTaskbarHeight()
    {
        return DesktopTaskbarHeight;
    }
  
    public static void main(String args[])
    {
        try
        {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e)
        {
            Logger.getLogger(AudioLevel.class.getName()).
                    log(Level.SEVERE,e.getMessage(), e);
        }

        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {

              SenseTileViewControler.
                      getInstance().initialize();
            }
        });
    }

     @Override
    public void run()
     {
        while (true)
        {
            try{
                Thread.sleep(200);
                pgAudioLevel.setValue(_audioLevel.getLevel());
                cboSelectedSource.revalidate();
                cboSelectedSource.repaint();
                desktopWebcam.repaint();
               }
                catch (InterruptedException ex)
                {
                     Logger.getLogger(SenseTileView.class.getName()).
                             log(Level.SEVERE, ex.getMessage(), ex);
                }
            } 
        }
}
