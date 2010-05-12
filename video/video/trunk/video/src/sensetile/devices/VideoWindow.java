package sensetile.devices;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
/**
 *The capture area is described by a struct video_window.<p>
 * This defines a capture area and the clipping information if relevant.
 * The VIDIOCGWIN ioctl recovers the current settings and the 
 * VIDIOCSWIN sets new values. A successful call to VIDIOCSWIN indicates
 * that a suitable set of parameters have been chosen.
 * They do not indicate that exactly what was requested was granted.
 * The program should call VIDIOCGWIN to check if the nearest match was suitable.
 * The struct video_window contains the following fields:
 * <ul>
 * <li> x	The X co-ordinate specified in X windows format.
 * <li> y	The Y co-ordinate specified in X windows format.
 * <li> width	The width of the image capture.
 * <li> height	The height of the image capture.
 * <li> chromakey	A host order RGB32 value for the chroma key.
 * <li> flags	Additional capture flags.
 * <li> clips	A list of clipping rectangles. (Set only)
 * <li> clipcount	The number of clipping rectangles. (Set only)
 * </ul>
 * @author SenseTile
 */
public class VideoWindow extends Structure {

    public static class ByValue extends VideoWindow implements Structure.ByValue
    {
    }
    public static class ByReference extends VideoWindow implements Structure.ByReference
    {
    }
    public int x, y, width, height, chromakey, flags;

    public Pointer clip;

    public int clipcount;
}
