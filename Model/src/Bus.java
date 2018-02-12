/**
 * Created by Gayan Sandaruwan on 09-Feb-18.
 */
public class Bus extends Thread{

//    private final int riderLimit = 50;
//    private Rider[] seats = new Rider[riderLimit];
//    private int currentRiders;
//    private int busNo;
//    private Semaphore busEnter = new Semaphore(1);
//
//    public Bus(int busNo, int currentRiderCount){
//        this.busNo=busNo;
//        currentRiders =currentRiderCount;
//        System.out.println("Bus created with bus No : "+busNo+" Already filled seats : "+currentRiderCount);
//    }
//
//    public void enterBus(Rider rider){
//        try {
//            busEnter.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//            System.out.println(" Rider : "+ rider.getRiderId() + " Is trying to get in to the bus : " +busNo );
//            seats[currentRiders] = rider;
//            currentRiders++;
//        busEnter.release();
//
//    }
//
//    public void run(){
//
//        try {
//            System.out.println(" Bus :" + this.busNo + "Started with :" + this.currentRiders +" Number of riders");
//            sleep(1000);
//            Halt.notBoarding.acquire();
//            System.out.println("Bus :"+this.busNo+ " Is boarding..");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Halt.boarding.release(50-currentRiders);
//        boolean allIn = false;
//        while (!allIn){
//            if(Halt.passWait.availablePermits() ==0){
//                allIn= true;
//            }
//            else{
//                try {
//                    sleep(100);
//                    System.out.println("Waiting for all to get in"+Halt.passWait.availablePermits());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        Halt.notBoarding.release();
//        try {
//            Halt.busCame.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    private String busId;
    private long start_time;
    public Bus(String busId){
        this.busId = busId;
        System.out.println("Creating the Bus  : "+busId);
    }

    public void run() {
        try {
            Thread.sleep(start_time);
            Road.mutex.acquire();
            if (Road.busCount > 0)
            {
                Road.riders = Math.min(Road.busCount,50);
                System.out.println("Bus : "+this.busId + "Has arrived");
                System.out.println(Road.riders + " riders were waiting for the bus..");
                Road.setBus(this);
                Road.bus.release();
                System.out.println("Bus is waiting for all riders to get in...");
                Road.all_abord.acquire();

            }

            Road.mutex.release();

            System.out.println("All waited riders are in. Bus : "+ this.busId+ " Is departing..");


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getBusId(){
        return busId;
    }

}
