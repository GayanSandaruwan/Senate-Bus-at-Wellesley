/**
 * Created by Gayan Sandaruwan on 09-Feb-18.
 */
public class Halt extends Thread{

//    private static Bus currentBus;
//    private ReentrantReadWriteLock currentBusLock = new ReentrantReadWriteLock(true);       //True for fairness property
//    private ArrayList<Rider> subscribedRiders = new ArrayList<Rider>();
//    private ReentrantReadWriteLock subscriptionLock = new ReentrantReadWriteLock(true);
//    private boolean boarding = false;
//    private ReentrantReadWriteLock boardingLock = new ReentrantReadWriteLock(true);

//    public static Semaphore boarding = new Semaphore(0);
//    public  static Semaphore passWait = new Semaphore(0);
//    public static Semaphore notBoarding = new Semaphore(1);
//    public static Semaphore busCame = new Semaphore(0);
//
////    public Bus checkCurrentBus(){
////            currentBusLock.readLock().lock();
////            Bus cb = currentBus;
////            currentBusLock.readLock().unlock();
////            return cb;
////    }
//
//    public static void BusArrived(Bus bus){
//        try {
//            System.out.println("acuring bus lock");
//            busCame.acquire();                      //Mutex
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        currentBus = bus;
//        bus.start();                                // Start the bus thread
//        busCame.release();                          // Update of bus finished
//    }
//
//    public static void riderArrive(Rider rider) throws InterruptedException {
//
//        notBoarding.acquire();              //Mutex
//        passWait.release();
//        notBoarding.release();
//
//        boarding.acquire();                 //Start boarding
//        busCame.acquire();                  // Mutex read write protection
//        currentBus.enterBus(rider);
//        busCame.release();
//        passWait.acquire();
//    }

//    public boolean subscribe(Rider rider){                  //Riders come and wait for the bus
//
//        boolean subscribed = false;
//        subscriptionLock.writeLock().lock();
//            boardingLock.readLock().lock();
//                if (!boarding) {
//                    subscribedRiders.add(rider);
//                    subscribed = true;
//                }
//            boardingLock.readLock().unlock();
//        subscriptionLock.writeLock().unlock();
//
//        return subscribed;
//    }
//
//    public void notifyRiders(){                             // Notify the Riders
//
//        synchronized (this){
//            for (Rider rider: subscribedRiders) {
//                rider.getNotified(currentBus);
//            }
//        }
//    }


    TimeMeter next_rider = new TimeMeter();
    private int Rider_counter  = 0;
    public static int time_scale = 1000;

    public void run() {
        while(true)
        {
            Rider rider = new Rider(Rider_counter+"");
            double Rider_create = next_rider.next_rider_gap();
            try {
                Thread.sleep((long)Rider_create*time_scale);
                rider.start();
                System.out.println("Rider thead is starts in : "+Rider_create+" seconds");
                Rider_counter++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

