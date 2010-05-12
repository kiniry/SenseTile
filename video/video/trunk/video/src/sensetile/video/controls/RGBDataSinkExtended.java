package sensetile.video.controls;
import java.nio.IntBuffer;
import org.gstreamer.Bin;
import org.gstreamer.Buffer;
import org.gstreamer.Caps;
import org.gstreamer.Element;
import org.gstreamer.ElementFactory;
import org.gstreamer.GhostPad;
import org.gstreamer.Pad;
import org.gstreamer.Structure;
import org.gstreamer.elements.BaseSink;
import org.gstreamer.lowlevel.GstBinAPI;
import org.gstreamer.lowlevel.GstNative;
import sensetile.common.utils.Guard;
import sensetile.video.sources.IVideoSource;

/**
 *
 * @author SenseTile
 */
public class RGBDataSinkExtended extends Bin
{
    private static final GstBinAPI gst = GstNative.load(GstBinAPI.class);
    private boolean _passDirectBuffer = false;
    private final RGBListenerMediator _listener;
    private final BaseSink _videosink;

    public static RGBDataSinkExtended createRGBSink( final IVideoSource source)
    {
        Guard.ArgumentNotNull(source, "IVideo source cannot be a null.");
        return new RGBDataSinkExtended(source);
    }
    private RGBDataSinkExtended(IVideoSource source)
    {
        super(initializer(gst.ptr_gst_bin_new("RGBDataSink")));
        _listener = source.getRGBMediatorListener();
        _videosink = (BaseSink) ElementFactory.make("fakesink", "VideoSink");
        _videosink.set("signal-handoffs", true);
        _videosink.set("sync", true);
        _videosink.connect(new VideoHandoffListener());

      
        // Convert the input into 32bit RGB so it can be fed directly to a BufferedImage
        

        Element conv = ElementFactory.make("ffmpegcolorspace", "ColorConverter");
        Element videofilter = ElementFactory.make("capsfilter", "filter");
        videofilter.setCaps(Caps.fromString("video/x-raw-rgb,framerate=(fraction)" + source.getFrameRate() +
                                            ", bpp="+ source.getBpp() +
                                            ", depth=" + source.getDepth()));
        addMany(conv, videofilter, _videosink);
        Element.linkMany(conv, videofilter, _videosink);

        
        // Link the ghost pads on the bin to the sink pad on the convertor
        
        addPad(new GhostPad("sink", conv.getStaticPad("sink")));
    }
    
    public void setPassDirectBuffer(boolean passThru)
    {
        _passDirectBuffer = passThru;
    }

    /**
     * Gets the actual gstreamer sink element.
     *
     * @return a BaseSink
     */
    public BaseSink getSinkElement()
    {
        return _videosink;
    }

    class VideoHandoffListener implements Element.HANDOFF
    {
        @Override
        public void handoff(Element element, Buffer buffer, Pad pad) {
            Caps caps = buffer.getCaps();
            Structure struct = caps.getStructure(0);

            int width = struct.getInteger("width");
            int height = struct.getInteger("height");
            if (width < 1 || height < 1) {
                return;
            }
            IntBuffer rgb;
            if (_passDirectBuffer) {
                rgb = buffer.getByteBuffer().asIntBuffer();
            } else {
                rgb = IntBuffer.allocate(width * height);
                rgb.put(buffer.getByteBuffer().asIntBuffer()).flip();
            }
            _listener.rgbFrame(width, height, rgb);

            //
            // Dispose of the gstreamer buffer immediately to avoid more being
            // allocated before the java GC kicks in
            //
            buffer.dispose();
        }
    }
}
