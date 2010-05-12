/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.devices;


import java.io.PipedInputStream;
import com.sun.jna.Native;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.nio.ByteBuffer;


/**
 *
 * @author SenseTile
 */
public class Vloopback
{
    public static final Vloopback NONE = null;
//    private final static int VIDEO_PALETTE_YUV420P = 15;
//    private final static int VIDEO_PALETTE_RGB24 = 4;
//   // private final static int VIDEO_PALETTE_RGB32 = 5;
//    private final static int O_RDWR = 2;
//    private final static int VIDIOCGCAP = -2143521279;
//    private final static int VIDIOCGPICT = -2146535930;
//    private static int VIDIOCSPICT = 1074689543;
//    //private static int VIDIOCGWIN = -2144831991;        //64bits
//    private static int VIDIOCGWIN = -2145356279;          //32bits
//    //private static int VIDIOCSWIN = 1076393482;         //64bits
//    private static int VIDIOCSWIN = 1075869194;           //32bits
//    private int fmt = VIDEO_PALETTE_YUV420P;
//    private PipedInputStream pin = null;
//    private int w, h;
//    private String deviceName = "";
//    private int deviceFD = 0;
//    private boolean reverseRedAndBlue = false;
//    private byte[] buffer = null;
//    private InfoListener listener = null;
//
//    public Vloopback( final InfoListener infoListener)
//    {
//
//        //We have to detect if we are in 32bits or 64bits
//        listener = infoListener;
//        System.out.println("Pointer Size: " + Native.POINTER_SIZE);
//        switch (Native.POINTER_SIZE)
//        {
//            case 4:
//                VIDIOCGWIN = -2145356279;
//                VIDIOCSWIN = 1075869194;
//                break;
//            case 8:
//                VIDIOCGWIN = -2144831991;
//                VIDIOCSWIN = 1076393482;
//                break;
//        }
//    }
//
//    public void setReverseRedAndBlue(final boolean v)
//    {
//        reverseRedAndBlue = v;
//    }
//
//    private void openDevice(final String name, final int width, final int height)
//            throws VloopbackException
//    {
//        w = width;
//        h = height;
//        deviceName = name;
//        deviceFD = CLibrary.INSTANCE.open(deviceName, 4002);
//        if (deviceFD <= 0)
//        {
//            if (listener != null)
//            {
//                listener.error("Unable to open device: " + deviceName);
//            }
//            throw new VloopbackException("Unable to open device: " + deviceName);
//        }
//        else
//        {
//            initDevice(deviceFD, width, height);
//        }
//    }
//
//    private void closeDevice() {
//        if (listener != null)
//        {
//            listener.info("Closing device : " + deviceName);
//        }
//
//        int status = CLibrary.INSTANCE.close(deviceFD);
//        if (status != 0)
//        {
//            System.out.println("Error closing device : " + deviceFD);
//            if (listener != null)
//            {
//                listener.error("Error closing device : " + deviceName);
//            }
//        }
//        if (listener != null)
//        {
//            listener.info("Virtual Webcam Stopped");
//        }
//
//    }
//
//    private void initDevice(final int dev, final int width, final int height)
//            throws VloopbackException
//    {
//        VideoCapability.ByReference vidCaps = new VideoCapability.ByReference();
//        VideoWindow.ByReference vidWin = new VideoWindow.ByReference();
//        VideoPicture.ByReference vidPic = new VideoPicture.ByReference();
//
//        if (CLibrary.INSTANCE.ioctl(dev, VIDIOCGCAP, vidCaps) == -1)
//        {
//            throw new VloopbackException("Unable to get video capacity");
//        }
//        if (CLibrary.INSTANCE.ioctl(dev, VIDIOCGWIN, vidWin) == -1)
//        {
//            throw new VloopbackException("Unable to get video window");
//        }
//        vidWin.width = width;
//        vidWin.height = height;
//        if (CLibrary.INSTANCE.ioctl(dev, VIDIOCSWIN, vidWin) == -1)
//        {
//            throw new VloopbackException("Unable to set video window size");
//        }
//
//        if (CLibrary.INSTANCE.ioctl(dev, VIDIOCGPICT, vidPic) == -1)
//        {
//            throw new VloopbackException("Unable to get video picture");
//        }
//        vidPic.palette = (short) fmt;
//        if (CLibrary.INSTANCE.ioctl(dev, VIDIOCSPICT, vidPic) == -1) {
//            throw new VloopbackException("Unable to set video palette");
//        }
//    }
//
//    public void setVideoRGB(final String device, final int w, final int h)
//            throws VloopbackException
//    {
//        fmt = VIDEO_PALETTE_RGB24;
//        openDevice(device, w, h);
//    }
//
    public void writeImage(byte[] data)
    {
//        int countWritten = 0;
//        if (deviceFD != 0)
//        {
//            countWritten = CLibrary.INSTANCE.write(deviceFD, data, data.length);
//            if (countWritten != buffer.length)
//            {
//                if (listener != null)
//                {
//                    listener.error("WebcamStudio: Error while writing image...");
//                }
//            }
//        }
    }
//
//    public void writeImage(final int[] data)
//    {
//        if (deviceFD != 0)
//        {
//            ByteBuffer bytes = ByteBuffer.allocate(data.length * 3);
//            if (reverseRedAndBlue)
//            {
//                for (int color : data)
//                {
//                    bytes.putInt(((color & 0x0000FF) << 16) + (color & 0x00FF00) + ((color & 0xFF0000) >> 16));
//                }
//            }
//            else
//            {
//                for (int color : data)
//                {
//                    bytes.putInt(color);
//                }
//            }
//
//            int countWritten = 0;
//            countWritten = CLibrary.INSTANCE.write(deviceFD, bytes.array(), data.length * 3);
//            if (countWritten != data.length * 3)
//            {
//                if (listener != null)
//                {
//                    listener.error("WebcamStudio: Error while writing image...");
//                }
//            }
//         }
//    }
//
//    public void writeImage(BufferedImage img)
//    {
//        if (deviceFD != 0)
//        {
//            int index = 0;
//            int c;
//            int x = 0;
//            int y = 0;
//            int[] data = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
//            buffer = new byte[data.length * 3];
//            for (int i = 0; i < data.length; i++)
//            {
//                if (reverseRedAndBlue)
//                {
//                    buffer[index++] = (byte) (data[i] >> 16 & 0xFF);
//                    buffer[index++] = (byte) (data[i] >> 8 & 0xFF);
//                    buffer[index++] = (byte) (data[i] >> 0 & 0xFF);
//                }
//                else
//                {
//                    buffer[index++] = (byte) (data[i] >> 0 & 0xFF);
//                    buffer[index++] = (byte) (data[i] >> 8 & 0xFF);
//                    buffer[index++] = (byte) (data[i] >> 16 & 0xFF);
//                }
//            }
//
//            int countWritten = 0;
//            countWritten = CLibrary.INSTANCE.write(deviceFD, buffer, buffer.length);
//            if (countWritten != buffer.length)
//            {
//                if (listener != null)
//                {
//                    listener.error("WebcamStudio: Error while writing image...");
//                }
//            }
//        }
//    }
//    public void closeVideoRGB() {
//        if (deviceFD != 0)
//        {
//            closeDevice();
//        }
//    }
}
