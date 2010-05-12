/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.common.services;

import sensetile.common.messages.IMessage;
import java.util.ArrayList;
import java.util.List;
import sensetile.common.utils.Guard;

/**
 *
 * @author SenseTile
 */
public class BroadcasterService
{
    public static final BroadcasterService NO_SERVICE = null;
    private List<IObservable> _observableList = null;
    private BroadcasterService() 
    {
      _observableList = new ArrayList<IObservable>();
    }

    public static BroadcasterService getInstance()
    {
        return Instance.sole_Instance;
    }

    private static class Instance
    {
        static final BroadcasterService
                sole_Instance = new BroadcasterService();
    }

    public void attachObserver(final IObservable observer)
    {
        Guard.ArgumentNotNull(observer, "Observer cannot be a null");
        if (! _observableList.contains(observer))
        {
            _observableList.add(observer);
           
        }
    }

    public void attachObservableTuple(final IObservable... _observables)
    {
        Guard.ArgumentNotNull(_observables, "Tuple of observers cannot be a null");
        for(IObservable currentObs : _observables)
        {
            attachObserver(currentObs);
        }
    }

    public void detachObserver(final IObservable observer)
    {
        Guard.ArgumentNotNull(observer, "Observer cannot be a null");
        if ( _observableList.contains(observer))
        {
            _observableList.remove(observer);
        }
    }

     public void detachObservableTuple(final IObservable... _observables)
     {
        Guard.ArgumentNotNull(_observables, "Tuple of observers cannot be a null");
        for(IObservable currentObs : _observables)
        {
            detachObserver(currentObs);

        }
     }
     public void broadcastMessage(final IMessage message)
     {
        Guard.ArgumentNotNull(message, "Message cannot be a null.");
        notifyObservers(message);
     }

      private void notifyObservers(final IMessage message)
      {
        for (IObservable observable : _observableList)
        {
          observable.update(message);
        }
      }

      public void processMessageSequence(final List<IMessage> messages)
      {
          Guard.ArgumentNotNull(messages, "Message sequence cannot be a null.");
          for(IMessage message : messages)
          {
              broadcastMessage(message);
          }

      }

       public void processMessageList(final List<IMessage> messages)
       {
          Guard.ArgumentNotNull(messages, "Message list cannot be a null.");
         for (IObservable observable : _observableList)
         {
           observable.updateSequence(messages);
         }
       }
}
