package sensetile.common.services;
import sensetile.common.messages.MessageType;
import sensetile.common.messages.PipeMessage;
import sensetile.common.messages.IMessage;
import java.util.List;
import java.util.Vector;
import sensetile.common.sources.ISource;
import sensetile.common.utils.Guard;
/**
 * @author SenseTile
 */
public class LayerService implements IObservable
{
    public static LayerService NONE = null;
    private static Vector<ISource> _sources = null;
    private LayerService()
    {
        _sources = new Vector<ISource>();
        BroadcasterService.getInstance().attachObserver(this);
    }

    public static LayerService getInstance()
    {
        return Instance.sole_Instance;
    }

    public void update(final IMessage message) {
         Guard.ArgumentNotNull(message, "Message cannot be a null.");

        if (!message.getClass().isAssignableFrom(PipeMessage.class))
        {
            return;
        }
        assert message.getClass().isAssignableFrom(PipeMessage.class):
            "Message is not assignable from PipeMessage.class.";

        PipeMessage packetMessage = (PipeMessage)message;

        MessageType.PipeType type = packetMessage.getPacketType();

        ISource currentSource = (ISource)packetMessage.getMessage();

        boolean isError = type == MessageType.PipeType.PIPE_BUS_ERROR;
     
        ISource targetSource = ISource.NO_SOURCE;
        for(ISource source : _sources)
        {
            if(isError && source.isEqual(currentSource))
            {
                targetSource = source;
                break;
            }
        }

        if(targetSource != ISource.NO_SOURCE)
        {
         _sources.remove(targetSource);
        }
    }

    public void updateSequence(List<IMessage> message) {
        return;
    }

    private static class Instance
    {
        static final LayerService
                sole_Instance = new LayerService();
    }
    
    public  void add( final ISource source)
    {
        Guard.ArgumentNotNull(source, "Source cannot be a null.");
        if(!contains(source))
        {
          _sources.add(source);
        }
    }

    public  void remove(final ISource source)
    {
        Guard.ArgumentNotNull(source, "Source cannot be a null.");
        if(contains(source))
        {
          _sources.remove(source);
        }
    }

    public  void clear()
    {
        _sources.clear();
    }

    public  ISource get(final int index)
    {
        Guard.IndexOutOfBounds(_sources, index);
        return _sources.get(index);
    }
    public  int size()
    {
        return _sources.size();
    }
    public  void remove(final int index)
    {
        Guard.IndexOutOfBounds(_sources, index);
        _sources.remove(index);
    }

    public boolean contains(ISource source)
    {
        Guard.ArgumentNotNull(source, "Source cannot be a null.");
        return _sources.contains(source);
    }

    public int getIndex(final ISource source)
    {
        Guard.ArgumentNotNull(source, "Source cannot be a null.");
        int index = Integer.MIN_VALUE;
        if(contains(source))
        {
            index = _sources.indexOf(source);
        }
        return index;
    }

    public Vector<ISource> getSources()
    {
        return _sources;
    }

    public void moveDown( final ISource source )
    {
        Guard.ArgumentNotNull(source, "Source cannot be a null.");
        if(!contains(source))
        {
            return;
        }
        int index = _sources.indexOf(source);

        if (index < _sources.size() - 1)
        {
            _sources.remove(source);
            _sources.add(index + 1, source);
        }

    }

    public ISource getByUUID(final String uuid)
    {
        Guard.ArgumentNotNullOrEmptyString(uuid, "uuid cannot be " +
                "a null or empty string.");
        ISource source = null;
        for (ISource s : _sources)
        {
            if (s.getUUID().equals(uuid))
            {
                source=s;
                break;
            }
        }
        return source;
    }

    public void moveUp(final ISource source) {
        Guard.ArgumentNotNull(source, "Source cannot be a null.");
        if(!contains(source)) 
        {
            return;
        }
        int index = _sources.indexOf(source);
        if (index > 0)
        {
            _sources.remove(source);
            _sources.add(index - 1, source);
        }
    }
}
