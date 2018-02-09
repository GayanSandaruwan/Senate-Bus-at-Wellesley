import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Gayan Sandaruwan on 09-Feb-18.
 */
public class Halt {

    private Bus currentBus;
    private ReentrantReadWriteLock currentBusLock = new ReentrantReadWriteLock(true);       //True for fairness property
    private ArrayList<Rider> subscribedRiders = new ArrayList<Rider>();
    private ReentrantReadWriteLock subscriptionLock = new ReentrantReadWriteLock(true);
    private boolean boarding = false;
    private ReentrantReadWriteLock boardingLock = new ReentrantReadWriteLock(true);

    public Bus checkCurrentBus(){
            currentBusLock.readLock().lock();
            Bus cb = currentBus;
            currentBusLock.readLock().unlock();
            return cb;
    }

    public void BusArrived(Bus bus){

        currentBusLock.writeLock().lock();
        currentBus = bus;
        currentBusLock.writeLock().unlock();
    }

    public boolean subscribe(Rider rider){                  //Riders come and wait for the bus

        boolean subscribed = false;
        subscriptionLock.writeLock().lock();
            boardingLock.readLock().lock();
                if (!boarding) {
                    subscribedRiders.add(rider);
                    subscribed = true;
                }
            boardingLock.readLock().unlock();
        subscriptionLock.writeLock().unlock();

        return subscribed;
    }

    public void notifyRiders(){                             // Notify the Riders

        synchronized (this){
            for (Rider rider: subscribedRiders) {
                rider.getNotified(currentBus);
            }
        }
    }
}

