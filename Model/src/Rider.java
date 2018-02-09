/**
 * Created by Gayan Sandaruwan on 09-Feb-18.
 */
public class Rider extends Thread{

    private int id;

    public Rider(int id){
        this.id = id;
    }
    public int getRiderId() {
        return id;
    }

    public void getNotified(Bus bus){}
    public void run(){

    }
}
