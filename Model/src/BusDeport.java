/**
 * Created by Gayan Sandaruwan on 11-Feb-18.
 */
public class BusDeport extends Thread{


    TimeMeter next_bus = new TimeMeter();
    private int bus_counter = 0;
    public static int time_scale = 1000;
    public void run() {
        while(true)
        {
            Bus bus = new Bus(""+bus_counter);
            double bus_starts = next_bus.next_bus_gap();
            try {
                Thread.sleep((long)bus_starts*time_scale);
                bus.start();
                System.out.println("Bus is started in : "+ bus_starts+" seconds");
                bus_counter++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
