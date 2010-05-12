/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.common.services;

import sensetile.common.messages.IMessage;
import java.util.List;

/**
 *
 * @author SenseTile
 */
public interface IObservable {

    void update(IMessage message);
    void updateSequence(List<IMessage> messages);
}
