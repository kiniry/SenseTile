package sensetile.video.exporter;


import org.gstreamer.*;

import java.util.List;
import java.io.File;

import sensetile.video.sources.IVideoSource;
/**
 *
 * @author SenseTile
 */
public abstract class VideoExporter
{
    protected File output = null;
    protected Pipeline pipe = null;
    protected int captureWidth = IVideoSource.DEFAULT_WIDTH;
    protected int captureHeight = IVideoSource.DEFAULT_HEIGHT;
    protected int width = IVideoSource.DEFAULT_WIDTH;
    protected int height = IVideoSource.DEFAULT_HEIGHT;
    protected int rate = 70;
    
    protected int quality = 85;
    protected Mixer mixer = null;
    protected boolean stopMixer = true;
    protected int[] data = null;
    protected byte[] bdata = null;
    protected int vbitrate = 150;
    protected int abitrate = 128000;

    protected VideoExporter() {}

    public abstract void startExport();

    public void stopExport()
    {
        if (pipe != null)
        {
            pipe.stop();
            pipe.getState();
            List<Element> list = pipe.getElements();    
            for (int i = 0; i < list.size(); i++)
            {
                list.get(i).disown();
                pipe.remove(list.get(i));
            }
            pipe = null;
            stopMixer = true;
        }
    }

    

    public void setQuality(final int q)
    {
        quality = q;
    }

    public void setCaptureWidth(final int w)
    {
        captureWidth = w;
    }

    public void setCaptureHeight(final int h)
    {
        captureHeight = h;
    }

    public void setWidth(final int w)
    {
        width = w;
    }

    public void setHeight(final int h)
    {
        height = h;
    }

    public void setRate(final int r)
    {
        rate = r;
    }

    public void setMixer(final Mixer theMixer)
    {
        mixer = theMixer;
        captureWidth = mixer.getImage().getWidth();
        captureHeight = mixer.getImage().getHeight();
    }

    public int getVideoBitrate()
    {
        return vbitrate;
    }

    public void setAudioBitrate(final int r)
    {
        abitrate = r;
    }

    public void setVideoBitrate(int videoBitRate)
    {
        vbitrate = videoBitRate;
    }
}
