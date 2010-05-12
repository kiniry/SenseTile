package sensetile.devices;
import com.sun.jna.Structure;
/**
 *The image properties of the picture can be queried with the VIDIOCGPICT
 * ioctl which fills in a struct video_picture.<p>
 * The VIDIOCSPICT ioctl allows values to be changed.
 * All values except for the palette type are scaled between 0-65535.<p>
 * The struct video_picture consists of the following fields:
 * <ul>
 * <li> brightness	Picture brightness.
 * <li> hue	        Picture hue (colour only).
 *  <li>colour	Picture colour (colour only).
 *  <li>contrast	Picture contrast.
 *  <li>whiteness	The whiteness (greyscale only).
 *  <li>depth	The capture depth (may need to match the frame buffer depth).
 *  <li>palette	Reports the palette that should be used for this image.
 * </ul>
 * @author SenseTile
 */
public class VideoPicture extends Structure {

    public static class ByValue extends VideoPicture implements Structure.ByValue
    {
    }
    public static class ByReference extends VideoPicture implements Structure.ByReference
    {
    }
    public short brightness, hue, color, contrast, whiteness, depth, palette;
   
}