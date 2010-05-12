/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.devices;

import java.io.File;

/**
 *
 * @author SenseTile
 */
public interface IDevice
{
    public enum Type
    {
        INPUT,
        OUTPUT,
        UNKNOWN;
    }

    public enum Version
    {
        V4L,
        V4L2,
        TELOS,
        UNKNOWN;
    }
    Version getVersion();
    
    Type getType();

    String getName();
    void setName(final String name);

    File getFile();

    boolean isReady();
    void setReady( final boolean isReady);
}
