
package sensetile.video.exporter;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.gstreamer.*;
/**
 *
 * @author SenseTile
 */
public class VideoExporterAVI extends VideoExporter
{

    @Override
    public void startExport()
    {
        Element source = ElementFactory.make("fakesrc", "source");
        Element sink = ElementFactory.make("filesink", "filesink");
        sink.set("location", output.getAbsolutePath());
        Element videoRate = ElementFactory.make("videorate", "videorate");
        Element capsRate = ElementFactory.make("capsfilter", "capsrate");
        capsRate.setCaps(Caps.fromString((new StringBuilder()).
                append("video/x-raw-rgb,framerate=").append(rate).append("/1").toString()));
        Element capsSize = ElementFactory.make("capsfilter", "capsSize");
        Caps fltcaps = new Caps((new StringBuilder()).append("video/x-raw-rgb, framerate=")
                .append(rate).append("/1").append(", width=").append(captureWidth).append(", height=")
                .append(captureHeight).append(", bpp=32, depth=32,red_mask=0x0000FF00,green_mask=0x00FF0000,blue_mask=0xFF000000").toString());
        capsSize.setCaps(fltcaps);
        Element ffmpegColorSpace = ElementFactory.make("ffmpegcolorspace", "ffmpegcolorspace");
        Element muxer = ElementFactory.make("avimux", "avimux");
        Element vEncoder = ElementFactory.make("xvidenc", "xvidenc");
        Element queue = ElementFactory.make("queue", "queue");
        Element audioSource = ElementFactory.make("gconfaudiosrc", "gconfaudiosrc");
        Element audioCaps = ElementFactory.make("capsfilter", "audiocaps");
        audioCaps.setCaps(Caps.fromString("audio/x-raw-int,rate=22050,channels=2"));
        Element audioConvert = ElementFactory.make("audioconvert", "audioconvert");
        Element aEncoder = ElementFactory.make("lame", "lame");
        aEncoder.set("bitrate", Integer.valueOf(abitrate / 1000));
        Element aqueue = ElementFactory.make("queue", "aqueue");
        pipe = new Pipeline();
        pipe.addMany(new Element[] {
            source, capsSize, videoRate, capsRate, ffmpegColorSpace, vEncoder, queue, muxer, sink
        });
        Element.linkMany(new Element[] {
            source, capsSize, videoRate, capsRate, ffmpegColorSpace, vEncoder, queue, muxer, sink
        });
        pipe.addMany(new Element[] {
            audioSource, audioCaps, audioConvert, aEncoder, aqueue
        });
        Element.linkMany(new Element[] {
            audioSource, audioCaps, audioConvert, aEncoder, aqueue, muxer
        });
        source.set("signal-handoffs", Boolean.valueOf(true));
        source.set("sizemax", Integer.valueOf(captureWidth * captureHeight * 4));
        source.set("sizetype", Integer.valueOf(2));
        source.set("sync", Boolean.valueOf(true));
        source.set("is-live", Boolean.valueOf(true));
        source.set("filltype", Integer.valueOf(1));
        source.connect(new org.gstreamer.Element.HANDOFF() {

            public void handoff(Element element, Buffer buffer, Pad pad)
            {
                data = _videoSource.getPixels();
                ByteBuffer bytes = buffer.getByteBuffer();
                IntBuffer b = bytes.asIntBuffer();
                b.put(data);
            }

            final VideoExporterAVI VEAVI;
            {
                VEAVI = VideoExporterAVI.this;
             //   super();
            }
        });
        source.connect("handoff", new Closure()
        {

            public void invoke(Element element1, Buffer buffer1, Pad pad1)
            {

            }

            final VideoExporterAVI VEAVI;
            
            {
                VEAVI = VideoExporterAVI.this;
              //  super();
            }
        });
        pipe.getBus().connect(new org.gstreamer.Bus.ERROR() {

            public void errorMessage(GstObject arg0, int arg1, String arg2)
            {
                System.out.println((new StringBuilder()).append("AVI Export Error: ").append(arg0).append(",").append(arg1).append(", ").append(arg2).toString());
            }

            final VideoExporterAVI VEAVI;
            
            {
                VEAVI = VideoExporterAVI.this;
             //   super();
            }
        });
        pipe.play();
    }

    public VideoExporterAVI(File outputFile)
    {
        super();
        output = outputFile;
    }

//    public static void main(String args[])
//    {
//        Gst.init();
//
//        VideoExporterAVI avi = new VideoExporterAVI(new File("/home/dragan/JAD/test.avi"));
//        avi.setVideoSource(VideoSourceProvider.getInstance().getVideoSourceAt(0));
//        avi.startExport();
//        System.out.println("Recording for 10 sec");
//        try
//        {
//            Thread.sleep(10000);
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        avi.stopExport();
//        avi = null;
//        System.out.println("Done!");
//        System.exit(0);
//    }
}
