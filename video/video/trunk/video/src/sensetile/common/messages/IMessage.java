/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.common.messages;

/**
 *
 * @author SenseTile
 */
public interface IMessage
{
    public static final IMessage NO_MESSAGE = null;
    void setMessage( final Object messageObject);
    Object getMessage();

}
