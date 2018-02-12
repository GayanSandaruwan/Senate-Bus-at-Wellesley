/**
 * Created by Gayan Sandaruwan on 09-Feb-18.
 */
public class Rider extends Thread{

//    private int id;
//
//    public Rider(int id){
//        this.id = id;
//    }
//    public int getRiderId() {
//        return id;
//    }
//
//    public void run(){
//
//        try {
//            sleep(300);
//            System.out.println("Rider : "+id+"Started waiting for a bus");
//            Halt.riderArrive(this);
//            System.out.println("Rider : "+id + "Got in");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }



    private String riderId;

    public Rider(String riderId){
        this.riderId = riderId;
        System.out.println("Creating Rider .. : "+riderId);
    }

    public void run() {
        try {
            Road.mutex.acquire();
            System.out.println("Rider : "+ riderId + " Entering to the Bus Halt..");
            Road.busCount++;
            Road.mutex.release();

            Road.bus.acquire();

            Road.multiplex.acquire();

            boardIn();

            Road.busCount-- ;
            Road .riders -- ;
            System.out.println("  ");

            if (Road.riders  == 0)
            {
                System.out.println("Notifying bus to depart...");
                Road.multiplex.release(50);
                Road.all_abord.release();

            }
            else
            {
                Road.bus.release();
            }
        }

        catch(Exception e) {
            System.out.println(e);
        }
    }

    private void boardIn()
    {
        System.out.println("Rider :" +this.riderId + " got in to the bus  : "+Road.readCurrentBus().getBusId());
    }

}
