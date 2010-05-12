
package sensetile.video.exporter;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensetile.common.services.LayerService;
import sensetile.video.sources.IVideoSource;
import sensetile.devices.Vloopback;

/**
 *
 * @author SenseTile
 */
public class Mixer implements Runnable
{
    public static final Mixer NONE = null;
    private LayerService _layerService = LayerService.NONE;

    protected int outputWidth = IVideoSource.DEFAULT_WIDTH;
    protected int outputHeight = IVideoSource.DEFAULT_HEIGHT;

    private Vloopback outputDevice = Vloopback.NONE;

    private BufferedImage outputImage = GraphicsEnvironment.getLocalGraphicsEnvironment().
            getDefaultScreenDevice().getDefaultConfiguration().
            createCompatibleImage(outputWidth, outputHeight);

    protected BufferedImage image = GraphicsEnvironment.getLocalGraphicsEnvironment().
            getDefaultScreenDevice().getDefaultConfiguration().
            createCompatibleImage(outputWidth, outputHeight);

    protected static GraphicsConfiguration graphicConfiguration = null;

    private boolean isDrawing = Boolean.FALSE;
    private int frameRate = IVideoSource.DEFAULT_FRAME_RATE;
    // TODO think abouth that !
    private int[] workingPixels = null;
  
   public Mixer()
   {
       _layerService = LayerService.getInstance();
        new Thread(this).start();
   }

    public BufferedImage getImage()
    {
        return outputImage;
    }


    public void setSize(final int width, final int height)
    {
        outputWidth = width;
        outputHeight = height;
    }

    public void setOutput( Vloopback vLoopback)
    {
        outputDevice = vLoopback;
    }

    public void setFramerate(int fps)
    {
        frameRate = fps;
    }

    public int getFramerate()
    {
        return frameRate;
    }

    private void drawImage()
    {
        isDrawing = true;
        if (image == null || (outputWidth != image.getWidth())) {
            image = graphicConfiguration.createCompatibleImage(outputWidth, outputHeight, java.awt.image.BufferedImage.TRANSLUCENT);
            outputImage = graphicConfiguration.createCompatibleImage(outputWidth, outputHeight, java.awt.image.BufferedImage.TRANSLUCENT);
        }
        java.awt.Graphics2D buffer = image.createGraphics();
        int x1, x2, x3, x4;
        int y1, y2, y3, y4;

        buffer.setStroke(new java.awt.BasicStroke(3));

        for (int i = _layerService.size()-1;i>=0;i--)
        {
            IVideoSource source = (IVideoSource)_layerService.get(i);
//            BufferedImage img = source.getImage();

//            if (img != null) {
                    //Don't do anything if there is no rotation to do...
//                    x1 = source.getShowAtX();
//                    y1 = source.getShowAtY();
//                    x2 = x1 + source.getWidth();
//                    y2 = y1 + source.getHeight();
//                    x3 = 0;
//                    y3 = 0;
                    //x4 = source.getCaptureWidth();
                    //y4 = source.getCaptureHeight();
                    //buffer.drawImage(img, x1, y1, x2, y2, x3, y3, x4, y4, null);
//                }
        }


        buffer.dispose();
        outputImage.getGraphics().drawImage(image, 0, 0, null);
        isDrawing = false;
    }

    
  

    @Override
    public void run() {
        java.awt.Graphics g = image.getGraphics();
        g.setColor(java.awt.Color.BLACK);
        g.fillRect(0, 0, outputWidth, outputHeight);
        long lastDraw = 0;
        long frameRateLength = 1000 / frameRate;
        while (true) {
            try
            {
                Thread.sleep(10);
                frameRateLength = 1000 / frameRate;
                boolean  isNonNullTime = System.currentTimeMillis() - lastDraw >= frameRateLength;
                if (isNonNullTime && !isDrawing)
                {
                    drawImage();
                    if (outputDevice != null)
                    {
                        if (workingPixels == null)
                        {
                   //         outputDevice.writeImage(outputImage);
                        }
                        else
                        {
                    //        outputDevice.writeImage(workingPixels);
                        }
                    }
                    lastDraw = System.currentTimeMillis();
                }
            }
            catch (InterruptedException ex) {
                Logger.getLogger(Mixer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
