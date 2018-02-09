/**
 * Created by Gayan Sandaruwan on 09-Feb-18.
 */
public class Bus {

    private final int riderLimit = 50;
    private Rider[] seats = new Rider[riderLimit];
    private int currentRiders;
    private String busNo;

    public Bus(String busNo, int currentRiderCount){
        this.busNo=busNo;
        currentRiders =currentRiderCount;

        System.out.println("Bus created with bus No : "+busNo+" Already filled seats : "+currentRiderCount);
    }

    public boolean enterBus(Rider rider){
        System.out.println(" Rider : "+ rider.getRiderId() + " Is trying to get in to the bus : " +busNo );

        synchronized (this){

            if(currentRiders<riderLimit){
                seats[currentRiders] = rider;
                currentRiders++;
                return true;
            }
            else{
                return false;
            }
        }
    }
}
