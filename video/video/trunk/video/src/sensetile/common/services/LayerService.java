package sensetile.common.services;
import sensetile.common.messages.MessageType;
import sensetile.common.messages.PipeMessage;
import sensetile.common.messages.IMessage;
import java.util.List;
import java.util.Vector;
import sensetile.common.sources.ISource;
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
         assert message != null: "Message object cannot be a null.";

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
        assert source != null :"Source cannot be a null.";
        
        if(!contains(source))
        {
          _sources.add(source);
          assert _sources.contains(source);
        }
    }

    public  void remove(final ISource source)
    {
        assert source != null : "Source cannot be a null.";
        if(contains(source))
        {
          _sources.remove(source);
          assert !_sources.contains(source);
        }
    }

    public  void clear()
    {
        _sources.clear();
    }

    public  ISource get(final int index)
    {
         assert index >=0 : "Index cannot be a less than 0";
         assert index < _sources.size() : "Index cannot be a greater than list size.";
        return _sources.get(index);
    }
    public  int size()
    {
        return _sources.size();
    }
    public  void remove(final int index)
    {
        assert index >=0 : "Index cannot be a less than 0";
        assert index < _sources.size() : "Index cannot be a greater than list size.";
        _sources.remove(index);
    }

    public boolean contains(ISource source)
    {
        assert source != null : "Source cannot be a null.";
        return _sources.contains(source);
    }

    public int getIndex(final ISource source)
    {
        assert source != null : "Source cannot be a null.";
        
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
        assert source != null : "Source cannot be a null.";
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
        assert uuid != null  && !uuid.equalsIgnoreCase(""):
            "Source cannot be a null.or empty string.";
               
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
        assert source != null : "Source cannot be a null.";
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
