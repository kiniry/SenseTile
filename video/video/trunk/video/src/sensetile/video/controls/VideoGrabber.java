
package sensetile.video.controls;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import org.gstreamer.Element;
import org.gstreamer.Pad;
import org.gstreamer.event.NavigationEvent;
import com.sun.jna.Platform;
import sensetile.common.utils.Guard;
import sensetile.video.sources.IVideoSource;

/**
 * SenseTile
 */
public class VideoGrabber extends javax.swing.JComponent
{
    private RGBDataSinkExtended videosink;
    private Pad videoPad;
    private RenderingHints renderingHints = null;
    private RenderComponent renderComponent = new RenderComponent();
    private static boolean quartzEnabled = false;
    private static boolean ddscaleEnabled = false;
    private boolean keepAspect = true;
    private float alpha = 1.0f;
    private Timer resourceTimer;
    private VolatileImage volatileImage;
    private Class<?> graphicsConfigClass = null;
    private boolean frameRendered = false;
    
    private final boolean useVolatile;
    private RGBListenerMediator _listener;

    private final static Class<?> oglGraphicsConfigClass;
    
    private final static Map<RenderingHints.Key, Object> openglHints = new HashMap<RenderingHints.Key, Object>()
    {

     {
        // Bilinear interpolation can be accelerated by the OpenGL pipeline
        put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
     }
    };
    
    private final static Map<RenderingHints.Key, Object> defaultHints = new HashMap<RenderingHints.Key, Object>()
    {
        
     {
        // Bilinear interpolation can be accelerated by the D3D pipeline
        put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
     }
    };
    static
    {
        quartzEnabled = Boolean.getBoolean("apple.awt.graphics.UseQuartz");
        try
        {
            ddscaleEnabled = Boolean.getBoolean("sun.java2d.ddscale")
                    && Boolean.getBoolean("sun.java2d.d3d");
        } catch (Exception ex) { }
        Class<?> cls;
        
        try
        {
            cls = Class.forName("sun.java2d.opengl.OGLGraphicsConfig");
        } 
        catch (Exception ex)
        {
            cls = null;
        }
        oglGraphicsConfigClass = cls;
    }


    /** Creates a new instance of GstVideoGrabber */

    public static VideoGrabber createVideGrabber( final IVideoSource videoSource)
    {
        Guard.ArgumentNotNull(videoSource, "IVideoSource cannot be a null.");
        return new VideoGrabber( videoSource );
    }

    public VideoGrabber( IVideoSource videoSource)
    {
        this._listener = videoSource.getRGBMediatorListener();
        _listener.setVideoGrabber(this);
        videosink = RGBDataSinkExtended.createRGBSink(videoSource);
        videosink.setPassDirectBuffer(true);
        // Limit the lateness of frames to no more than 20ms (half a frame at 25fps)
        videosink.getSinkElement().setMaximumLateness(20, TimeUnit.MILLISECONDS);
        videosink.getSinkElement().setQOSEnabled(true);

        // On MacOS, swing is un-accelerated, so using volatile actually slows it down a bit
        useVolatile = !Platform.isMac();

        // Get the pad to use to send events on
        videoPad = videosink.getSinkPads().get(0);

        //
        // Kick off a timer to free up the volatile image if there have been no recent updates
        // (e.g. the player is paused)
        //
        resourceTimer = new Timer(250, resourceReaper);

        //
        // Don't use a layout manager - the output component will positioned within this
        // component according to the aspect ratio and scaling mode
        //
        setLayout(null);
        add(renderComponent);

        //
        // Listen for the child changing its preferred size to the size of the
        // video stream.
        //
        renderComponent.addPropertyChangeListener("preferredSize", new PropertyChangeListener()
        {

            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                setPreferredSize(renderComponent.getPreferredSize());
                scaleVideoOutput();
            }
        });
        renderComponent.addMouseListener(mouseListener);
        renderComponent.addMouseMotionListener(mouseListener);
        renderComponent.addKeyListener(keyListener);
        addKeyListener(keyListener);
        //
        // Scale the video output in response to this component being resized
        //
        addComponentListener(new ComponentAdapter()
        {

            @Override
            public void componentResized(ComponentEvent arg0) {
                scaleVideoOutput();
            }

        });
        renderComponent.setBounds(getBounds());
        setOpaque(true);
        setBackground(Color.BLACK);
    }

    private final MouseInputListener mouseListener = new MouseInputAdapter()
    {
        /**
         * Gets the scaled X position of the mouse event.
         */
        private double getX(MouseEvent evt)
        {
            double scaleX = (double) imgWidth / (double) renderComponent.getWidth();
            return (double) evt.getX() * scaleX;
        }
        /**
         * Gets the scaled Y position of the mouse event.
         */
        private double getY(MouseEvent evt)
        {
            double scaleY = (double) imgHeight / (double) renderComponent.getHeight();
            return (double) evt.getY() * scaleY;
        }
        /**
         * Generate and send a mouse event to the peer pad of the video component.
         */
        private void mouse(String event, MouseEvent evt) {
          //  System.out.printf("%s (%d, %d) button=%x\n", event, evt.getX(), evt.getY(), evt.getButton());
            NavigationEvent nav = NavigationEvent.createMouseEvent(event, getX(evt), getY(evt),
                    evt.getButton());
            videoPad.pushEvent(nav);
        }

        @Override
        public void mousePressed(MouseEvent evt)
        {
            mouse("mouse-button-press", evt);
        }
        @Override
        public void mouseReleased(MouseEvent evt)
        {
            mouse("mouse-button-release", evt);
        }
        @Override
        public void mouseMoved(MouseEvent evt)
        {
            mouse("mouse-move", evt);
        }
        @Override
        public void mouseDragged(MouseEvent evt)
        {
            mouse("mouse-move", evt);
        }
    };
    // Map from some java key codes to X11 keysym names.
    private static final Map<Integer, String> keyMap = new HashMap<Integer, String>()
    {
     {
        put(KeyEvent.VK_BACK_SPACE, "BackSpace");
        put(KeyEvent.VK_ENTER, "Return");
        put(KeyEvent.VK_LEFT, "Left");
        put(KeyEvent.VK_RIGHT, "Right");
        put(KeyEvent.VK_UP, "Up");
        put(KeyEvent.VK_DOWN, "Down");
        put(KeyEvent.VK_ESCAPE, "Escape");
        put(KeyEvent.VK_COMMA, "comma");
        put(KeyEvent.VK_PERIOD, "period");
        put(KeyEvent.VK_SPACE, "space");
     }
    };
    private final KeyListener keyListener = new KeyAdapter()
    {
        private String getKey(KeyEvent evt)
        {
            if (keyMap.containsKey(evt.getKeyCode())) {
                return keyMap.get(evt.getKeyCode());
            }
            return String.valueOf(evt.getKeyChar());
        }
        private void key(String name, KeyEvent evt)
        {
        // System.out.println(name + " " + getKey(evt));
            videoPad.pushEvent(NavigationEvent.createKeyEvent(name, getKey(evt)));
            evt.consume();
        }
        @Override
        public void keyPressed(KeyEvent evt)
        {
            key("key-press", evt);
        }

        @Override
        public void keyReleased(KeyEvent evt)
        {
            key("key-release", evt);
        }
    };

    private void updateRenderingHints(Class<?> gcClass) {
        Map<RenderingHints.Key, Object> hints = new HashMap<RenderingHints.Key, Object>();
        if (gcClass != null && oglGraphicsConfigClass != null &&
                oglGraphicsConfigClass.isAssignableFrom(gcClass))
        {
            hints.putAll(openglHints);
        } 
        else if (quartzEnabled)
        {
            //hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        } 
        else if (ddscaleEnabled)
        {
            hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        } 
        else
        {
            hints.putAll(defaultHints);
        }
        renderingHints = new RenderingHints(hints);
    }

    /**
     * Scales the video output component according to its aspect ratio
     */
    private void scaleVideoOutput() {
        final Component child = renderComponent;
        final Dimension childSize = child.getPreferredSize();
        final int width = getWidth(), height = getHeight();
        // Figure out the aspect ratio
        double aspect = keepAspect ? (double) childSize.width / (double) childSize.height : 1.0f;

        //
        // Now scale and position the videoChild component to be in the correct position
        // to keep the aspect ratio correct.
        //
        int scaledHeight = (int)((double) width / aspect);
        if (!keepAspect)
        {
            //
            // Just make the child match the parent
            //
            child.setBounds(0, 0, width, height);
        } 
        else if (scaledHeight < height)
        {
            //
            // Output window is taller than the image is when scaled, so move the
            // video component to sit vertically in the centre of the VideoGrabber.
            //
            final int y = (height - scaledHeight) / 2;
            child.setBounds(0, y, width, scaledHeight);
        } 
        else
        {
            final int scaledWidth = (int)((double) height * aspect);
            final int x = (width - scaledWidth) / 2;
            child.setBounds(x, 0, scaledWidth, height);
        }
    }
    private ActionListener resourceReaper = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (!frameRendered)
            {
                if (volatileImage != null)
                {
                    volatileImage.flush();
                    volatileImage = null;
                }
                frameRendered = false;

                // Stop the timer so we don't wakeup needlessly
                resourceTimer.stop();
            }
        }
    };
    public Element getElement()
    {
        return videosink;
    }

    public void setKeepAspect(boolean keepAspect)
    {
        this.keepAspect = keepAspect;
    }

    @Override
    public boolean isLightweight()
    {
        return true;
    }

    @Override
    public boolean isOpaque()
    {
        return super.isOpaque() && alpha >= 1.0f;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isOpaque())
        {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(getBackground());
            if (alpha < 1.0f)
            {
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            }
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.dispose();
        }
    }
    private class RenderComponent extends javax.swing.JComponent
    {
        @Override
        protected void paintComponent(Graphics g) {
            int width = getWidth(), height = getHeight();
            Graphics2D g2d = (Graphics2D) g.create();
            if (alpha < 1.0f) {
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            }

            if (_listener.getCurrentImage() != null)
            {
                GraphicsConfiguration gc = getGraphicsConfiguration();
                // If the GraphicsConfig changed, then change the hints being used
                if (gc.getClass() != graphicsConfigClass)
                {
                    graphicsConfigClass = gc.getClass();
                    updateRenderingHints(graphicsConfigClass);
                }
                if (!renderingHints.isEmpty())
                {
                    g2d.setRenderingHints(renderingHints);
                }
                render(g2d, 0, 0, width, height);
            } 
            else if (alpha >= 1.0f)
            {
                g2d.setColor(getBackground());
                g2d.fillRect(0, 0, width, height);
            }
            g2d.dispose();
        }
        @Override
        public boolean isOpaque() {
            return VideoGrabber.this.isOpaque();
        }
        @Override
        public boolean isLightweight()
        {
            return true;
        }
    }
    @Deprecated
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
    @Deprecated
    public float getAlpha() {
        return alpha;
    }
    public void setOpacity(float opacity) {
        this.alpha = opacity;
    }
    public float getOpacity() {
        return alpha;
    }

    private void renderVolatileImage(BufferedImage bufferedImage) {
        do
        {
            
            int w = bufferedImage.getWidth(), h = bufferedImage.getHeight();
            GraphicsConfiguration gc = getGraphicsConfiguration();
            if (volatileImage == null || volatileImage.getWidth() != w
                    || volatileImage.getHeight() != h
                    || volatileImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
                if (volatileImage != null) {
                    volatileImage.flush();
                }
                volatileImage = gc.createCompatibleVolatileImage(w, h);
                volatileImage.setAccelerationPriority(1.0f);
            }
            //
            // Now paint the BufferedImage into the accelerated image
            //
            Graphics2D g = volatileImage.createGraphics();
            g.drawImage(bufferedImage, 0, 0, null);
            g.dispose();
        } while(volatileImage.contentsLost());
    }

    /**
     * Renders to a volatile image, and then paints that to the screen.
     * This helps with scaling performance on accelerated surfaces (e.g. OpenGL)
     *
     * @param g the graphics to paint the image to
     * @param x the left coordinate to start painting at.
     * @param y the top coordinate to start painting at.
     * @param w the width of the paint area
     * @param h the height of the paint area
     */
    private void volatileRender(Graphics g, int x, int y, int w, int h) {
        do {
            if (_listener.getUpdatePending() || volatileImage == null
                || volatileImage.validate(getGraphicsConfiguration()) != VolatileImage.IMAGE_OK)
            {
                _listener.getBufferLock().lock();
                try {
                    _listener.setUpdatePanding(false);
                    renderVolatileImage(_listener.getCurrentImage());
                } finally {
                    _listener.getBufferLock().unlock();
                }
            }
            g.drawImage(volatileImage, x, y, w, h, null);
        } while(volatileImage.contentsLost());
    }

    /**
     * Renders directly to the given <tt>Graphics</tt>.
     * This is only really useful on MacOS where swing graphics are unaccelerated
     * so using a volatile just incurs an extra memcpy().
     *
     * @param g the graphics to paint the image to
     * @param x the left coordinate to start painting at.
     * @param y the top coordinate to start painting at.
     * @param w the width of the paint area
     * @param h the height of the paint area
     */
    private void heapRender(Graphics g, int x, int y, int w, int h) {
        _listener.getBufferLock().lock();
        try {
            _listener.setUpdatePanding(false);
            g.drawImage(_listener.getCurrentImage(), x, y, w, h, null);
        } finally {
            _listener.getBufferLock().unlock();
        }
    }

    /**
     * Renders the current frame to the given <tt>Graphics</tt>.
     *
     * @param g the graphics to paint the image to
     * @param x the left coordinate to start painting at.
     * @param y the top coordinate to start painting at.
     * @param w the width of the paint area
     * @param h the height of the paint area
     */
    private void render(Graphics g, int x, int y, int w, int h) {
        if (useVolatile) {
            volatileRender(g, x, y, w, h);
        } else {
            heapRender(g, x, y, w, h);
        }
        //
        // Restart the resource reaper timer if neccessary
        //
        if (!frameRendered) {
            frameRendered = true;
            if (!resourceTimer.isRunning()) {
                resourceTimer.restart();
            }
        }
    }

    private int imgWidth = 0, imgHeight = 0;

    public  void update(final int width, final int height) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                //
                // If the image changed size, resize the component to fit
                //
                if (width != imgWidth || height != imgHeight) {
                    renderComponent.setPreferredSize(new Dimension(width, height));
                    imgWidth = width;
                    imgHeight = height;
                }
                if (renderComponent.isVisible()) {
                    renderComponent.paintImmediately(0, 0,
                        renderComponent.getWidth(), renderComponent.getHeight());
                }
            }
        });
    }

}

