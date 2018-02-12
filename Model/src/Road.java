import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Gayan Sandaruwan on 11-Feb-18.
 */
public class Road {


//    public static void main(String[] args) {
//        System.out.println("Creating Bus");
//        System.out.println(Halt.passWait.availablePermits() + "Available passwait before Bus");
//        Bus bus= new Bus(1,5);
//        Rider r = new Rider(-5);
//        r.start();
//        System.out.println(Halt.passWait.availablePermits() + "Available passwait after Bus");
//        Halt.busCame.release();
//        Halt.BusArrived(bus);
//        for(int i=0;i<50-5;i++){
//            Rider rider = new Rider(i);
//            rider.start();
//        }
//        Halt.busCame.release();
//        System.out.println("Creating Bus");
//        Bus bus2= new Bus(1,5);
//        Halt.busCame.release();
//        Halt.BusArrived(bus2);
//    }


    public static int busCount = 0;
    public static int riders = 0;
    public static Semaphore mutex = new Semaphore(1);
    public static Semaphore multiplex = new Semaphore(50);
    public static Semaphore bus = new Semaphore(0);
    public static Semaphore all_abord = new Semaphore(0);
    private static Bus currentBus;
    private static ReentrantReadWriteLock busSetting = new ReentrantReadWriteLock(true);

    public static void main(String[] args)
    {
        System.out.println("*********************************************************************");
        System.out.println("************* Senate Bus at Wellesley on Your service ***************");
        System.out.println("*********************************************************************");

        System.out.println("Enter Time Scale as per hundred ");
        System.out.println("(eg : 100 ->20 min , 10->2 min , 1->12 seconds avg. bus gap/ avg rider gap :");

        Scanner timeScale = new Scanner(System.in);
        int timeS = timeScale.nextInt();
        Halt.time_scale = timeS*10;
        BusDeport.time_scale = timeS*10;
        new BusDeport().start();
        new Halt().start();



    }


    public static void setBus(Bus bus){

        busSetting.writeLock().lock();
        currentBus = bus;
        busSetting.writeLock().unlock();
    }

    public static Bus readCurrentBus(){
        Bus bus;
        busSetting.readLock().lock();
        bus = currentBus;
        busSetting.readLock().unlock();

        return bus;
    }
}
