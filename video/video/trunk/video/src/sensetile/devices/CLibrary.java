
package sensetile.devices;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * @author SenseTile
 */
public interface CLibrary extends Library
{

        CLibrary INSTANCE =
                (CLibrary) Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"),
                CLibrary.class);

        void printf(String format, Object... args);

        int open(String device, int mode);

        int close(int device);

        int ioctl(int device, int command, Object struct);

        String strerr(int no);

        int write(int device, byte[] buffer, int count);
    }
