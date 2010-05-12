package sensetile.devices;
import com.sun.jna.Structure;

/**
 * This class represents Driver capabilities. Appropriate
 * linux v4l2_capability looks as fallow:
 *  The struct struct v4l2_capability consists of the following fields:<p>
 * <ul>
 * <li> <b>__u8 driver[16]</b>;  Name of the driver, a unique NUL-terminated ASCII string.<p>
 *                        For example: "bttv". Driver specific applications can use this information to verify the driver identity.<p>
 *                        It is also useful to work around known bugs, or to identify drivers in error reports.<p>
 *                        The driver version is stored in the version field.
 *
 * <li> <b> __u8    card[32]</b>;Name of the device, a NUL-terminated ASCII string.<p>
 *                       For example: "Yoyodyne TV/FM". One driver may support different
 *                       brands or models of video hardware.<p>
 *                       This information is intended for users, for example in a menu of
 *                       available devices. <p>
 *                       Since multiple TV cards of the same brand may be installed which
 *                       are supported by the same driver,<p>
 *                       this name should be combined with the character device file name
 *                       (e. g. /dev/video2) or<p>
 *                       the bus_info string to avoid ambiguities.
 * <li> <b> __u8    bus_info[32]</b>;  Location of the device in the system, a NUL-terminated
 *                      ASCII string.<p>
 *                      For example: "PCI Slot 4". This information is intended for users, to
 *                      distinguish multiple identical devices.<p>
 *                      If no such information is available the field may simply count the devices
 *                      controlled by the driver, or <p>
 *                      contain the empty string (bus_info[0] = 0).
 * <li> <b> __u32   version</b>;Version number of the driver. Together with the driver field this
 *                      identifies a particular driver.
 * <li> <b> __u32    capabilities</b>;    Device capabilities:
 * <ul>
 * <li>V4L2_CAP_VIDEO_CAPTURE	0x00000001	The device supports the Video Capture interface.
 * <li>V4L2_CAP_VIDEO_OUTPUT	0x00000002	The device supports the Video Output interface.
 * <li>V4L2_CAP_VIDEO_OVERLAY	0x00000004	The device supports the Video Overlay interface. A video overlay device 
 *                                              typically stores captured images directly in the video memory of a graphics card,
 *                                              with hardware clipping and scaling.
 * <li>V4L2_CAP_VBI_CAPTURE	0x00000010	The device supports the Raw VBI Capture interface, providing Teletext and Closed Caption data.
 * <li>V4L2_CAP_VBI_OUTPUT	0x00000020	The device supports the Raw VBI Output interface.
 * <li>V4L2_CAP_SLICED_VBI_CAPTURE	0x00000040	The device supports the Sliced VBI Capture interface.
 * <li>V4L2_CAP_SLICED_VBI_OUTPUT	0x00000080	The device supports the Sliced VBI Output interface.
 * <li>V4L2_CAP_RDS_CAPTURE	0x00000100	[to be defined]
 * <li>V4L2_CAP_VIDEO_OUTPUT_OVERLAY	0x00000200	The device supports the Video Output Overlay (OSD) interface. 
 *                                                      Unlike the Video Overlay interface, this is a secondary function
 *                                                      of video output devices and overlays an image onto an outgoing
 *                                                      video signal. When the driver sets this flag, it must clear the
 *                                                      V4L2_CAP_VIDEO_OVERLAY flag and vice versa.[a]
 * <li>V4L2_CAP_TUNER	0x00010000	The device has some sort of tuner or modulator to receive or emit RF-modulated
 *                                      video signals. For more information about tuner and modulator programming see Section 1.6.
 * <li>V4L2_CAP_AUDIO	0x00020000	The device has audio inputs or outputs. It may or may not support audio recording or playback, 
 *                                      in PCM or compressed formats. PCM audio support must be implemented as ALSA or OSS interface.
 *                                      For more information on audio inputs and outputs see Section 1.5.
 * <li>V4L2_CAP_RADIO	0x00040000	This is a radio receiver.
 * <li>V4L2_CAP_READWRITE	0x01000000	The device supports the read() and/or write() I/O methods.
 * <li>V4L2_CAP_ASYNCIO	0x02000000	The device supports the asynchronous I/O methods.
 * <li>V4L2_CAP_STREAMING	0x04000000	The device supports the streaming I/O method.
 * </ul>
 * <li> <b> __u32 </b>   reserved[4];
 * </ul>
 * @author SenseTile
 */
public class V4l2Capability extends Structure
{
    public static class ByValue extends V4l2Capability implements Structure.ByValue {}

    public static class ByReference extends V4l2Capability implements Structure.ByReference {}
    
    public byte[] driver = new byte[16];

    public byte[] card = new byte[32];

    public byte[] bus_info = new byte[32];

    public int version = Integer.MIN_VALUE;

    public int capabilities = Integer.MIN_VALUE;

    public int[] reserved = new int[4];
}
