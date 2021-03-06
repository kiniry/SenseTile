/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensetile.video.exporter;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.gstreamer.*;

/**
 *
 * @author SenseTile
 */
public class VideoExporterOGG extends VideoExporter
{
    private int port = 4888;

    @Override
    public void startExport() {

        //Video
        Element source = ElementFactory.make("fakesrc", "source");
        Element sink = null;
        if (output != null) {
            sink = ElementFactory.make("filesink", "filesink");
            sink.set("location", output.getAbsolutePath());
            vbitrate=1000;
        } else {
            sink = ElementFactory.make("tcpserversink", "tcpserversink");
            sink.set("port", port);
            sink.set("sync-method", 1);
            sink.set("recover-policy", 1);
        }
        Element videoRate = ElementFactory.make("videorate", "videorate");
        Element capsRate = ElementFactory.make("capsfilter", "capsrate");
        capsRate.setCaps(Caps.fromString("video/x-raw-rgb,framerate=" + rate + "/1"));

        Element capsSize = ElementFactory.make("capsfilter", "capsSize");
        Caps fltcaps = new Caps(new StringBuffer().append( "video/x-raw-rgb, framerate=").
                                    append(rate).append("/1").append(", width=").append(captureWidth).
                                    append(", height=").append(captureHeight).
                                    append(", bpp=32, depth=32,red_mask=0x0000FF00," +
                                    "         green_mask=0x00FF0000,blue_mask=0xFF000000").toString());
        capsSize.setCaps(fltcaps);

        Element ffmpegColorSpace = ElementFactory.make("ffmpegcolorspace", "ffmpegcolorspace");
        Element muxer = ElementFactory.make("oggmux", "oggmux");
        Element vEncoder = ElementFactory.make("theoraenc", "encoder");
        vEncoder.set("bitrate", vbitrate);
        Element queue = ElementFactory.make("queue", "queue");
        //Audio
        Element audioSource = ElementFactory.make("gconfaudiosrc", "gconfaudiosrc");
        Element audioCaps = ElementFactory.make("capsfilter", "audiocaps");
        audioCaps.setCaps(Caps.fromString("audio/x-raw-int,rate=44100,channels=2"));
        Element audioConvert = ElementFactory.make("audioconvert", "audioconvert");
        Element aEncoder = ElementFactory.make("vorbisenc", "vorbisenc");
        aEncoder.set("bitrate", abitrate);
        Element aqueue = ElementFactory.make("queue", "aqueue");

        pipe = new Pipeline();
        pipe.addMany(source, capsSize, videoRate, capsRate, ffmpegColorSpace, vEncoder, queue, muxer, sink);
        Element.linkMany(source, capsSize, videoRate, capsRate, ffmpegColorSpace, vEncoder, queue, muxer, sink);

        pipe.addMany(audioSource, audioCaps, audioConvert, aEncoder, aqueue);
        Element.linkMany(audioSource, audioCaps, audioConvert, aEncoder, aqueue, muxer);
        source.set("signal-handoffs", true);
        source.set("sizemax", captureWidth * captureHeight * 4);
        source.set("sizetype", 2);
        source.set("sync", true);
        source.set("is-live", true);
        source.set("filltype", 1); // Don't fill the buffer before handoff
        source.connect(new Element.HANDOFF() {

            @Override
            public void handoff(Element element, org.gstreamer.Buffer buffer, Pad pad) {
                data = ((java.awt.image.DataBufferInt) _videoSource.getImage().getRaster().getDataBuffer()).getData();
                ByteBuffer bytes = buffer.getByteBuffer();
                IntBuffer b = bytes.asIntBuffer();
                b.put(data);
            }
        });
        source.connect("handoff", new Closure() {

            @SuppressWarnings("unused")
            public void invoke(Element element, Buffer buffer, Pad pad) {
                        System.out.println("Closure: Element=" + element.getNativeAddress()
                                + " buffer=" + buffer.getNativeAddress()
                                + " pad=" + pad.getNativeAddress());
                }
        });

        pipe.getBus().connect(new Bus.ERROR() {

            public void errorMessage(GstObject arg0, int arg1, String arg2) {
              
                System.out.println("OGG Export Error: " + arg0 + "," + arg1 + ", " + arg2);
            }
        });
        pipe.getBus().connect(new Bus.INFO() {

            @Override
            public void infoMessage(GstObject arg0, int arg1, String arg2) {
                System.out.println("OGG Export Info: " + arg0 + "," + arg1 + ", " + arg2);
            }
        });
        pipe.play();

    }

    public VideoExporterOGG(java.io.File outputFile) {
        output = outputFile;
    }

    public VideoExporterOGG(int port) {
        output = null;
        this.port = port;
    }

//    public static void main(String[] args) {
//        Gst.init();
//        Mixer mixer = new Mixer();
//        VideoExporterOGG ogg = new VideoExporterOGG(new java.io.File("/home/dragan/JAD/test.ogg"));
//        ogg.setMixer(mixer);
//        ogg.startExport();
//        System.out.println("Recording for 10 sec");
//        try {
//            Thread.sleep(10000);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        ogg.stopExport();
//        ogg = null;
//        System.out.println("Done!");
//        System.exit(0);
//    }
    
}
