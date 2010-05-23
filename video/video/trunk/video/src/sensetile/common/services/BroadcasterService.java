/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.common.services;

import sensetile.common.messages.IMessage;
import java.util.ArrayList;
import java.util.List;
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
        assert observer != null :"Observer cannot be a null";
        if (! _observableList.contains(observer))
        {
            _observableList.add(observer);
            assert _observableList.contains(observer);
        }
    }

    public void attachObservableTuple(final IObservable... observables)
    {
        assert observables != null : "Tuple of observers cannot be a null";
        for(IObservable currentObs : observables)
        {
            attachObserver(currentObs);
        }
    }

    public void detachObserver(final IObservable observer)
    {
        assert observer != null :"Observer cannot be a null";
        if ( _observableList.contains(observer))
        {
            _observableList.remove(observer);
             assert !_observableList.contains(observer);
        }
    }

     public void detachObservableTuple(final IObservable... observables)
     {
        assert observables != null : "Tuple of observers cannot be a null";
        for(IObservable currentObs : observables)
        {
            detachObserver(currentObs);

        }
     }
     public void broadcastMessage(final IMessage message)
     {
         assert message != null :"Message cannot be a null.";
        notifyObservers(message);
     }

      private void notifyObservers(final IMessage message)
      {
        assert message != null: "Message cannot be a null.";
        for (IObservable observable : _observableList)
        {
          observable.update(message);
        }
      }

      public void processMessageSequence(final List<IMessage> messages)
      {
          assert messages != null : "Message sequence cannot be a null.";
          assert messages.size() > 0: "Message's list  cannot be empty.";

          for(IMessage message : messages)
          {
              broadcastMessage(message);
          }
      }

       public void processMessageList(final List<IMessage> messages)
       {
         assert messages != null : "Message sequence cannot be a null.";
         assert messages.size() > 0: "Message's list  cannot be empty.";
          for (IObservable observable : _observableList)
         {
           observable.updateSequence(messages);
         }
       }
}
