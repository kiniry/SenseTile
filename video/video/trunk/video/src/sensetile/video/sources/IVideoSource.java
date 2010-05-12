package sensetile.video.sources;

import sensetile.common.sources.ISource;
import java.awt.image.BufferedImage;
import sensetile.video.controls.RGBListenerMediator;

/**
 * @author SenseTile
 */
public interface IVideoSource extends ISource 
{
    public static final int DEFAULT_BPP = 32;
    public static final int DEFAULT_DEPTH = 32;
    public static final int DEFAULT_FRAME_RATE = 15/1;
    public static final IVideoSource NO_VIDEO_SOURCE = null;

    int getBpp();
    void setBpp( final int bpp);

    int getDepth();
    void setDepth(final int depth);

    int getFrameRate();
    void setFrameRate(final int frameRate);

    String getSource();
    BufferedImage getImage();

    RGBListenerMediator getRGBMediatorListener();
    int[] getPixels();
    void reset();

    boolean isReadyToProcess();
    void enableProcess( final boolean isReadyToProcess);
    void setDeviceName( final String deviceName);
}
