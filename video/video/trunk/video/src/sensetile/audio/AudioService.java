
package sensetile.audio;

import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author SenseTile
 */
public class AudioService
{
    private static final float SAMPLE_RATE = 44100;
    private static final int SAMPLE_SIZE_INBITS = 8;
    private static final int CHANNELS = 1;

    private AudioService() { }
    public static AudioService getInstance()
    {
        return Instance.sole_Instance;
    }
    
    private static class Instance
    {
        static final AudioService sole_Instance = new AudioService();
    }

     public List<String> getAllMixers()
     {
        List<String> mixerList = new ArrayList<String>();
        for (Mixer.Info mixer : AudioSystem.getMixerInfo())
        {
            mixerList.add(mixer.getName());
            System.out.println(mixer.getName());
        }
        return mixerList;
     }
     /**
      * @TODO refactoring: detect() method should implement strategy pattern.
      * @return
      */
     public AudioInfoHolder detect()
     {
         AudioInfoHolder audioInfoHolder = AudioInfoHolder.NONE;
         audioInfoHolder = createInfo(Boolean.TRUE, Boolean.FALSE);

        if (AudioSystem.isLineSupported(audioInfoHolder.getDataLineInfo()))
        {
            audioInfoHolder.setFormatFounded(Boolean.TRUE);
        }
        else
        {
            audioInfoHolder = createInfo(Boolean.FALSE, Boolean.FALSE);
            if (AudioSystem.isLineSupported(audioInfoHolder.getDataLineInfo()))
            {
                audioInfoHolder.setFormatFounded(Boolean.TRUE);
                audioInfoHolder.setFormatSigned(Boolean.FALSE);
            }
            else
            {
                audioInfoHolder = createInfo(Boolean.TRUE, Boolean.FALSE);
                if (AudioSystem.isLineSupported(audioInfoHolder.getDataLineInfo()))
                {
                  audioInfoHolder.setFormatFounded(Boolean.TRUE);
                  audioInfoHolder.setFormatSigned(Boolean.TRUE);

                }
                else
                {
                    audioInfoHolder = createInfo(Boolean.FALSE, Boolean.FALSE);
                    if (AudioSystem.isLineSupported(audioInfoHolder.getDataLineInfo()))
                    {
                        audioInfoHolder.setFormatFounded(Boolean.TRUE);
                         audioInfoHolder.setFormatSigned(Boolean.FALSE);
                    }
                }
            }
        }
         
         return audioInfoHolder;
     }

     private AudioInfoHolder createInfo(final boolean signed, final boolean bigEndian )
     {
         return AudioInfoHolder.createAudioInfoHolder
                 (
                    new AudioFormat(SAMPLE_RATE,
                        SAMPLE_SIZE_INBITS, CHANNELS,
                        signed, bigEndian)
                 );
     }

     public boolean isPulseAudioExists()
     {
      boolean pulseAudioFound = false;
      for (Mixer.Info mixer : AudioSystem.getMixerInfo())
        {
            boolean isPulseAudio = mixer.getName().
                    toLowerCase().indexOf("pulseaudio")!=-1;
            if (isPulseAudio)
            {
                pulseAudioFound = true;
                break;
            }
        }
      return pulseAudioFound;
    }
}
