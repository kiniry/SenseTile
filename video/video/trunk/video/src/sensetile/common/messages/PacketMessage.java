package sensetile.common.messages;

/**
 * @author dragan
 */
public class PacketMessage implements IMessage {
    private Object _message = null;
    private MessageType.PacketType _messageType =
            MessageType.PacketType.PACKET_IS_NOT_AVAILABLE;

    private PacketMessage( final Object messageObject,
            final MessageType.PacketType messageType)
    {
        _message = messageObject;
        _messageType = messageType;
    }
    public void setMessage(Object messageObject)
    {
        assert messageObject != null : "Message object cannot be a null.";
        assert messageObject.getClass().isAssignableFrom(PacketMessage.class) :
            "Invalid message object type.";
        _message = messageObject;
    }

    public Object getMessage()
    {
        return _message;
    }
    public MessageType.PacketType getPacketType()
    {
        return _messageType;
    }

    public void setMessageType (final MessageType.PacketType messageType )
    {
        _messageType = messageType;
    }

    public static final PacketMessage createMessage( final Object messageObject,
            final MessageType.PacketType messageType)
    {
        assert messageObject != null : "Message object cannot be a null.";
        return new PacketMessage(messageObject, messageType);
    }

}
