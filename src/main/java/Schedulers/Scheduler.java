package Schedulers;

import Comparators.ElevatorDownComparator;
import Comparators.ElevatorUpComparator;
import Tuples.DoubleTuple;

import java.util.PriorityQueue;

public class Scheduler {
    private PriorityQueue<DoubleTuple> requestsQueue = new PriorityQueue<DoubleTuple>(new ElevatorUpComparator());

    public void switchComparator(int compareDirection){     /// change compare strategy when direction changed
        PriorityQueue<DoubleTuple> newQueue = new PriorityQueue<>(requestsQueue);
        if(compareDirection == -1){
            requestsQueue = new PriorityQueue<>(new ElevatorDownComparator());
        }
        else if(compareDirection == 1){
            requestsQueue = new PriorityQueue<>(new ElevatorUpComparator());
        }
        requestsQueue.addAll(newQueue);
    }

    public void add(DoubleTuple tuple){
        this.requestsQueue.add(tuple);
    }

    public void remove(){
        this.requestsQueue.poll();
    }

    public DoubleTuple get(){
        return this.requestsQueue.peek();
    }

    public boolean isEmpty(){
        return this.requestsQueue.isEmpty();
    }

}
