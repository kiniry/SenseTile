package sensetile.common.messages;
import sensetile.common.messages.MessageType.Validity;
import sensetile.devices.IDevice;
/**
 * @author SenseTile
 */
public class DeviceMessage implements IMessage
{
    
    private IDevice _device = null;
    private Validity _validity = Validity.VALID;

    private DeviceMessage( final IDevice device, Validity validity)
    {
        _device = device;
        _validity = validity;
    }

    public static final DeviceMessage
            createDeviceMessage(final IDevice device, Validity validity)
    {
        assert device != null : "Device cannot be a null.";
        
        return new DeviceMessage(device, validity);
    }

    public void setMessage(Object messageObject) {
        assert messageObject != null : "Message object cannot be a null.";
        assert messageObject.getClass().isAssignableFrom(IDevice.class) :
            "Invalid message object type.";
        _device = (IDevice)messageObject;
    }
    public Object getMessage()
    {
        return _device;
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
