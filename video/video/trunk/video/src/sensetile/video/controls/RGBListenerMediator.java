
package sensetile.video.controls;

import java.awt.image.BufferedImage;
import java.nio.IntBuffer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.gstreamer.elements.RGBDataSink;
import java.awt.image.DataBufferInt;
import org.gstreamer.Element;

/**
 *
 * @author SenseTile
 */
public class RGBListenerMediator implements RGBDataSink.Listener
{
    private BufferedImage _currentImage = null;
    private final Lock BUFFER_LOCK = new ReentrantLock();
    private volatile boolean _updatePending = false;
    private VideoGrabber _videoGrabber = null;
    private int[] _pixels = null;
    
    @Override
    public void rgbFrame(int width, int height, IntBuffer rgb)
    {
        
        // If the EDT is still copying data from the buffer, just drop this frame
        
        if (!BUFFER_LOCK.tryLock())
        {
            return;
        }

        // If there is already a swing update pending, also drop this frame.
        
        if (_updatePending)
        {
            BUFFER_LOCK.unlock();
            return;
        }
        try
        {
            final BufferedImage renderImage =
                    getBufferedImage(width, height);
            int[] pixels =
                    ((DataBufferInt) renderImage.getRaster().getDataBuffer()).getData();
            rgb.get(pixels, 0, width * height);
            _pixels = pixels;
            _updatePending = true;
        }
        finally
        {
            BUFFER_LOCK.unlock();
        }
        // Tell swing to use the new buffer
        _videoGrabber.update(_currentImage.getWidth(),
                _currentImage.getHeight());
    }

    private BufferedImage getBufferedImage(int width, int height)
    {
        if (_currentImage != null && _currentImage.getWidth() == width
            && _currentImage.getHeight() == height)
        {
            return _currentImage;
        }
        if (_currentImage != null)
        {
            _currentImage.flush();
        }
        _currentImage = 
                new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
        _currentImage.setAccelerationPriority(0.0f);
        return _currentImage;
    }
   

      public BufferedImage getCurrentImage()
      {
          return _currentImage;
      }

      public int[] getPixels()
      {
          return _pixels;
      }

      public Lock getBufferLock()
      {
          return BUFFER_LOCK;
      }

      public boolean getUpdatePending()
      {
          return _updatePending;
      }
      public void setUpdatePanding(final  boolean updatePanding )
      {
          _updatePending = updatePanding;
      }

      public void setVideoGrabber( VideoGrabber videoGrabber)
      {
          assert videoGrabber != null : "Video grabber cannot be a null.";
          _videoGrabber = videoGrabber;
      }

      public Element getVideoElement()
      {
          assert  _videoGrabber.getElement() != null :"Element os grabber cannot be a null.";
          return _videoGrabber.getElement();
      }
   }
