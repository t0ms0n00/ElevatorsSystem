package ElevatorSystem;

import Schedulers.Scheduler;
import Tuples.DoubleTuple;

public class Elevator {
    private final int id;
    private int position;
    private int direction;
    private int target;
    private Scheduler scheduler = new Scheduler();

    public Elevator(int id){
        this.id = id;
        this.position = 0;
        this.direction = 0;
        this.target = 0;
    }

    public int getId(){
        return this.id;
    }

    public int getPosition(){
        return this.position;
    }

    public int getDirection() {
        return this.direction;
    }

    public int getTarget(){
        return this.target;
    }

    public void setPosition(int newPosition){
        this.position = newPosition;
    }

    public void setDirection(int newDirection) {
        this.direction = newDirection;
    }

    public void setTarget(int newTarget){
        this.target = newTarget;
    }

    public void makeRequest(DoubleTuple request){
        this.scheduler.add(request);
    }

    public void finishTask(){
        this.scheduler.remove();
        this.direction = 0;
    }

    public void getTask(){ /// check actual task
        if(!this.scheduler.isEmpty()){
            int change = this.scheduler.get().getDirection();
            if(change != this.direction){
                /// condition to switch direction
                this.scheduler.switchComparator(change);
            }
            this.target = this.scheduler.get().getFloor();
        }
        /// set direction
        if(target < position) direction = -1;
        else if(target > position) direction = 1;
        else direction = 0;
    }
}
