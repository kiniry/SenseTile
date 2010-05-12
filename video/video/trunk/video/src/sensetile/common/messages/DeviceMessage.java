/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.common.messages;
import sensetile.common.messages.MessageType.Validity;
import sensetile.common.utils.Guard;
import sensetile.devices.IDevice;

/**
 *
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
        Guard.ArgumentNotNull(device, "Device cannot be a null.");
        return new DeviceMessage(device, validity);
    }

    public void setMessage(Object messageObject) {
        Guard.ArgumentNotNull(messageObject, "Message object cannot be a null.");
        if(!messageObject.getClass().isAssignableFrom(IDevice.class))
        {
           throw new MessageException("Invalid message object type.");
        }
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
