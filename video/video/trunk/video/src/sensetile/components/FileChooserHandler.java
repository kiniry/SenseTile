/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.components;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.text.DecimalFormat;
import sensetile.common.sources.ISource;

import sensetile.video.exporter.VideoExporterAVI;
import sensetile.video.sources.IVideoSource;
/**
 *
 * @author SenseTile
 */
public class FileChooserHandler implements Runnable
{
    private FrameViewer _frameViewer = null;
    private FileChooser _fileChooser = null;
    private java.io.File _file = null;
    private boolean _flag = Boolean.FALSE;
    private VideoExporterAVI _avi = null;
    private ISource _source = ISource.NO_SOURCE;
   public FileChooserHandler(final FrameViewer frameViewer )
   {
       _frameViewer = frameViewer;
       _file = new java.io.File("video" +
            java.util.UUID.randomUUID().toString() + ".avi");
       _source = frameViewer.getSource();
       createFileChooser();
   }


   private FileChooser createFileChooser()
   {
       _fileChooser = new FileChooser( new JFrame(), true);
       _fileChooser.setFileChooserHandler(this);
       _fileChooser.setLocationRelativeTo(_frameViewer);
       _fileChooser.getTxtSelectedFileName().setText(_file.getAbsolutePath());
        _fileChooser.pack();
        _fileChooser.setVisible(Boolean.TRUE);
       return _fileChooser;
   }

   public void setAlive(boolean isAlive)
   {
       _flag = isAlive;
   }

  

   public FrameViewer getFrameViewer()
   {
       return _frameViewer;
   }

   public boolean isAlive()
   {
       return _flag;
   }

   public void stopExporting()
   {
       _flag = Boolean.TRUE;
       _frameViewer.getLblActualFileSize().setText("");
       _frameViewer.getLblActualFileSize().revalidate();
       _frameViewer.getLblActualRecordingTime().setText("");
       _frameViewer.getLblActualRecordingTime().revalidate();
       _avi.stopExport();
   }

   public void startExporting()
   {
      _avi = new VideoExporterAVI(new File(_file.getAbsolutePath()));
        _avi.setVideoSource((IVideoSource)_source);
        _avi.startExport();
       new Thread(this).start();
   }
   
   public void fileActionPerformed() {
       JFileChooser chooser = 
               new JFileChooser(_fileChooser.
               getTxtSelectedFileName().getText());

        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        chooser.setMultiSelectionEnabled(false);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("AVI", "AVI", "avi"));

        if (chooser.showSaveDialog(_fileChooser) == JFileChooser.APPROVE_OPTION) {
            _file = chooser.getSelectedFile();
            if (!_file.getAbsolutePath().toLowerCase().endsWith(".avi") ) {
                _file = new File(_file.getAbsolutePath() + ".avi");
            }
            _fileChooser.getTxtSelectedFileName().setText(_file.getAbsolutePath());
        }
    }

    public void run() {
        long  timeStamp = System.currentTimeMillis();
        if(_flag)
        {
            _avi.stopExport();
        }
         while (!_flag) {
            try {
                if (_file.exists()) {
                    _frameViewer.getLblActualFileSize().setText("File size: " + 
                            (new DecimalFormat().format((_file.length() / 1024) / 1024f)) + " Mb");
                } else {
                    _frameViewer.getLblActualFileSize().setText("");
                }
                if (timeStamp == 0) {
                    _frameViewer.getLblActualRecordingTime().setText("");
                } else {
                    int delta = (int) ((System.currentTimeMillis() - timeStamp) / 1000);
                    int h = (delta / 3600);
                    delta -= (h * 3600);
                    int m = delta / 60;
                    delta -= m * 60;
                    int s = delta;
                    String time = "";
                    if (h < 10) {
                        time += "0";
                    }
                    time += h + ":";
                    if (m < 10) {
                        time += "0";
                    }
                    time += m + ":";
                    if (s < 10) {
                        time += "0";
                    }
                    time += s;
                    _frameViewer.getLblActualRecordingTime().setText("Recording time: " + time);
                }

                _frameViewer.getLblActualRecordingTime().revalidate();
                _frameViewer.getLblActualFileSize().revalidate();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }

}
