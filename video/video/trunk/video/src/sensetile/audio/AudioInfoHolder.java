/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.audio;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import sensetile.common.utils.Guard;

/**
 *
 * @author SenseTile
 */
public class AudioInfoHolder
{
    public static final AudioInfoHolder NONE = null;

    private boolean isFormatSigned = Boolean.TRUE;
    private AudioFormat audioFormat = null;
    private boolean isFormatFounded = Boolean.FALSE;
    private DataLine.Info dlinfo = null ;
   
    public static AudioInfoHolder createAudioInfoHolder(final AudioFormat auFormat)
    {
        Guard.ArgumentNotNull(auFormat, "AudioFormat cannot be a null.");
        return new AudioInfoHolder( auFormat);
    }
    private AudioInfoHolder( final AudioFormat auFormat)
    {
        audioFormat = auFormat;
        dlinfo = new DataLine.Info(TargetDataLine.class, auFormat);
    }

    public AudioFormat getAudioFormat()
    {
        return audioFormat;
    }

    public boolean isFormatSigned()
    {
        return isFormatSigned;
    }

    public void setFormatSigned(final boolean isSigned)
    {
        isFormatSigned = isSigned;
    }

    public boolean isFormatFounded()
    {
        return isFormatFounded;
    }

    public void setFormatFounded( final boolean isFounded)
    {
        isFormatFounded = isFounded;
    }

    public DataLine.Info getDataLineInfo()
    {
        return dlinfo;
    }

}
