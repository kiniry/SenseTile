package sensetile.devices;
/**
 *Provides access to portions of the Video4Linux API.
 * The VIDIOCGCAP ioctl call is used to obtain the capability information for a video device.<p>
 * The struct video_capability object passed to the ioctl is completed and returned.
 * It contains the following information:
 * <ul>
 * <li> name[32]	Canonical name for this interface.
 * <li> type	Type of interface.
 * <li> channels	Number of radio/tv channels if appropriate.
 * <li> audios	Number of audio devices if appropriate.
 * <li> maxwidth	Maximum capture width in pixels.
 * <li> maxheight	Maximum capture height in pixels.
 * <li> minwidth	Minimum capture width in pixels.
 * <li> minheight	Minimum capture height in pixels.
 * </ul>
 * @author SenseTile
 */
public class VideoCapability extends com.sun.jna.Structure
{

    public byte[] name = new byte[32];
    public int type,  channels,  audios,  maxwidth,  maxheight,  minwidth,  minheight;
    
    public static class ByValue extends VideoCapability implements com.sun.jna.Structure.ByValue {}

    public static class ByReference extends VideoCapability implements com.sun.jna.Structure.ByReference {}
    @Override
     public String toString()
    {
        StringBuffer buf = new StringBuffer();

        buf.append("name: ");
        buf.append(name);

        buf.append(" type: ");
        buf.append(type);

        buf.append(" channels: ");
        buf.append(channels);

        buf.append(" audios: ");
        buf.append(audios);

        buf.append(" minWidth: ");
        buf.append(minwidth);

        buf.append(" minHeight: ");
        buf.append(minheight);

        buf.append(" maxWidth: ");
        buf.append(maxwidth);

        buf.append(" maxHeight: ");
        buf.append(maxheight);

        return buf.toString();
    }
}
