
package sensetile.audio;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;


/**
 * Class responsable for monitoring sound.
 * @author SenseTile
 */
public class AudioLevel implements Runnable
{
    private TargetDataLine tdl = null;
    private AudioFormat format = null;
    private int audioLevel = 0;
    private boolean stopMe = Boolean.FALSE;
    private boolean isFormatSigned = Boolean.TRUE;
    private AudioService audioService = null;
    private static String NO_AUDIO_MONITORING =
            "No format found. Monitoring of sound is not active.";

    public AudioLevel()
    {
        audioService = AudioService.getInstance();
    }

    public void initialize()
    {
        AudioInfoHolder holder = audioService.detect();
        format = holder.getAudioFormat();
        isFormatSigned = holder.isFormatSigned();

        if (holder.isFormatFounded() &&
                audioService.isPulseAudioExists())
        {
            try
            {
                tdl = AudioSystem.getTargetDataLine(holder.getAudioFormat());
                Thread t = new Thread(this);
                t.setPriority(Thread.MAX_PRIORITY);
                t.start();
            }
            catch (LineUnavailableException ex)
            {
                Logger.getLogger(AudioLevel.class.getName()).
                        log(Level.SEVERE, ex.getMessage(), ex);
            }
            
        }
        else
        {
            Logger.getLogger(AudioLevel.class.getName()).
                    log(Level.INFO, NO_AUDIO_MONITORING);
        }
    }

    public int getLevel()
    {
        return audioLevel;
    }

    public void stop()
    {
        stopMe = true;
    }

    @Override
    public void run()
    {
        Logger.getLogger(AudioLevel.class.getName()).log(Level.INFO,
                                    "Monitoring sound started.");
        byte lastHighByte = 0;
        byte lastLowByte = 0;
        while (!stopMe)
        {
            lastHighByte = 0;
            lastLowByte = 0;
            try {
                tdl.open(format);
                tdl.start();
                byte[] buffer = new byte[11025];
                tdl.read(buffer, 0, buffer.length);
                for (byte b : buffer)
                {
                    if (b > lastHighByte)
                    {
                        lastHighByte = b;
                    }

                    if (b < lastLowByte)
                    {
                        lastLowByte = b;
                    }
                }
                if (isFormatSigned)
                {
                    audioLevel = lastHighByte;
                } 
                else
                {
                    audioLevel = lastLowByte;
                }
                Thread.sleep(1);
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        tdl.stop();
        tdl.close();
    }
}
