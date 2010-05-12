
package sensetile.common.messages;

import sensetile.common.sources.ISource;
import sensetile.common.utils.Guard;
import sensetile.common.messages.MessageType.Validity;

/**
 *
 * @author SenseTile
 */
public class SourceMessage implements IMessage {
    private ISource _source = ISource.NO_SOURCE;
    private Validity _validity = Validity.VALID;

    private SourceMessage( final ISource messageObject,
            final Validity messageType)
    {
        _source = messageObject;
        _validity = messageType;
    }

    public static final SourceMessage createSourceMessage(final ISource source,
            final Validity validity)
    {
        Guard.ArgumentNotNull(source, "Device cannot be a null.");
        return new SourceMessage(source, validity);
    }

    public void setMessage(Object messageObject)
    {
        Guard.ArgumentNotNull(messageObject, "Message object cannot be a null.");
        if(!messageObject.getClass().isAssignableFrom(ISource.class))
        {
           throw new MessageException("Invalid message object type.");
        }
        _source = (ISource)messageObject;
        
    }

    public Object getMessage()
    {
        return _source;
    }

    public void setValidity( final Validity validity)
    {
        _validity = validity;
    }

    public Validity getValidity()
    {
        return _validity;
    }

}
