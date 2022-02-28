package Comparators;

import Tuples.DoubleTuple;

import java.util.Comparator;

public class ElevatorDownComparator implements Comparator<DoubleTuple> {
    @Override
    public int compare(DoubleTuple o1, DoubleTuple o2) {
        if(o1.getDirection() == -1 && o2.getDirection() == -1){ /// both requests down
            if(o1.getFloor() > o2.getFloor()) return -1;        /// o1 has lower priority, should be faster
            else if(o1.getFloor() == o2.getFloor()) return 0;
            else return 1;
        }
        else if(o1.getDirection() == -1){   /// one direction up, another down
            return -1;
        }
        else if (o2.getDirection() == -1){
            return 1;
        }
        return 1;   /// both up, schedule doesn't matter for now
    }
}
