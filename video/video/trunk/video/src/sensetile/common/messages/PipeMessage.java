package sensetile.common.messages;

/**
 * @author SenseTile
 */
public class PipeMessage implements IMessage
{
    private Object _message = null;
    private MessageType.PipeType _messageType =
            MessageType.PipeType.PIPE_BUS_EOS;

    private PipeMessage( final Object messageObject,
            final MessageType.PipeType messageType)
    {
        _message = messageObject;
        _messageType = messageType;
    }

    public void setMessage(Object messageObject)
    {
        assert messageObject != null : "Message object cannot be a null.";
        assert messageObject.getClass().isAssignableFrom(PipeMessage.class) :
            "Invalid message object type.";
        _message = messageObject;
    }

    public Object getMessage()
    {
        return _message;
    }
    public MessageType.PipeType getPacketType()
    {
        return _messageType;
    }

    public void setMessageType (final MessageType.PipeType messageType )
    {
        _messageType = messageType;
    }

    public static final PipeMessage createMessage( final Object messageObject,
            final MessageType.PipeType messageType)
    {
       assert messageObject != null : "Message object cannot be a null.";
        return new PipeMessage(messageObject, messageType);
    }
}
