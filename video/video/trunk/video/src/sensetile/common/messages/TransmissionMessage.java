/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.common.messages;

import sensetile.common.messages.MessageType.TransmissionType;
import sensetile.common.sources.ISource;
import sensetile.common.utils.Guard;

/**
 *
 * @author SenseTile
 */
public class TransmissionMessage implements IMessage {
    private ISource _source = ISource.NO_SOURCE;
    private MessageType.TransmissionType _transmissionType = TransmissionType.NO_TRAMSMISSION;

    private TransmissionMessage( final ISource messageObject,
            final TransmissionType transmissionType)
    {
        _source = messageObject;
        _transmissionType = transmissionType;
    }

    public static final TransmissionMessage createTransmissionMessage(final ISource source,
            final TransmissionType transmissionType)
    {
        Guard.ArgumentNotNull(source, "Source cannot be a null.");
        return new TransmissionMessage(source, transmissionType);
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

    public void setTransmissionType( final TransmissionType transmissionType)
    {
        _transmissionType = transmissionType;
    }

    public TransmissionType getTransmissionType()
    {
        return _transmissionType;
    }

}
