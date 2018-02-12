import java.util.Random;

/**
 * Created by Gayan Sandaruwan on 10-Feb-18.
 */
public class TimeMeter {

    private int bus_mean_arrival_time = 60*20;  // Mean time gap between two busses
    private int rider_mean = 30;  // mean time gap between two riders

    public double next_bus_gap() {
        return next_time(bus_mean_arrival_time);
    }

    public double next_rider_gap() {
        return next_time(rider_mean);
    }

    private double next_time(double lambda)
    {
        Random rand = new Random();
        return Math.log(1-rand.nextDouble())*(-lambda);
    }
}
