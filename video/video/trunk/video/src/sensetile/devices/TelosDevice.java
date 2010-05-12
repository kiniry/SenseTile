/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.devices;

import java.io.File;
import sensetile.common.utils.Guard;

/**
 *
 * @author dragan
 */
public class TelosDevice implements IDevice{

    private File _file = null;
    private boolean _isReady = Boolean.FALSE;


    public TelosDevice(final String fileName)
    {
        Guard.ArgumentNotNullOrEmptyString(fileName, "Device name cannot be a null or empty string.");
        _file = new java.io.File(fileName);
        _isReady = Boolean.TRUE;
    }

    public Version getVersion()
    {
        return Version.TELOS;
    }

    public Type getType()
    {
        return Type.OUTPUT;
    }

    public String getName()
    {
        return "TELOS";
    }

    public File getFile()
    {
        return _file;
    }

    public boolean isReady() {
        return _isReady;
    }

    public void setReady(boolean isReady) {
        _isReady = isReady;
    }

    public void setName(final String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
