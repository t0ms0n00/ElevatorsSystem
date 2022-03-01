package ElevatorSystem;

import Tuples.DoubleTuple;
import Tuples.Triple;

public class ElevatorSystem {
    private final int elevatorsNumber;
    private final int floorsNumber;
    private Elevator[] elevators;

    public ElevatorSystem(int elevatorsNumber, int floorsNumber){
        this.elevatorsNumber = elevatorsNumber;
        this.floorsNumber = floorsNumber;
        this.elevators = new Elevator[elevatorsNumber];
        for(int i=0; i<this.elevatorsNumber; i++){
            this.elevators[i] = new Elevator(i); /// id is a number, but if it might be something else,
                                                 /// hashmap should be considered
        }
    }

    public void pickup(int requestFloorNumber, int direction){
        /// create a request to move to floor/from floor dependent by direction, add request to queue
        int elevatorID = -1;
        int minDistance = floorsNumber + 1;
        for(int i=0; i<elevatorsNumber; i++){
            Elevator elevator = elevators[i];
            if(elevator.getPosition() == requestFloorNumber){
                /// elevator is on that floor so request moving up/down to it is untypical, arbitrally skip
                continue;
            }
            if(elevator.getPosition() > requestFloorNumber && direction == 1){
                /// request is unreachable - elevator is above goal, and to goal we need move up
                continue;
            }
            if(elevator.getPosition() < requestFloorNumber && direction == -1){
                /// request is unreachable- elevator is below goal, and to goal we need move down
                continue;
            }
            int elevatorDistance = Math.abs(elevator.getPosition()-requestFloorNumber); // distance between actual position and requested target
            if(elevator.getDirection() == -1 * direction){
                /// elevator has a task in opposite direction, so we add a way to actual task twice (go and return),
                // to favour elevators with the same direction as request's because time is shorter
                elevatorDistance += 2 * Math.abs(elevator.getTarget()-elevator.getPosition());
            }
            if(elevatorDistance < minDistance) {
                minDistance = elevatorDistance;
                elevatorID = i;
            }
        }
        if(elevatorID != -1){
            /// request cannot be handled
            this.elevators[elevatorID].makeRequest(new DoubleTuple(requestFloorNumber, direction));
        }
        /*else{
            /// request can't be catched by any of elevators
            System.out.println("Request is unreachable, please try to fix parameters");
        }*/

    }

    public void update(int elevatorID, int floor, int targetFloor){
        /// change elevator state
        this.elevators[elevatorID].setPosition(floor); // now elevator is on that floor
        this.elevators[elevatorID].setTarget(targetFloor); // update elevator's target
    }

    public void step(){
        /// make one move for each elevator
        for(int i=0; i<elevatorsNumber; i++){
            Elevator elevator = this.elevators[i];
            elevator.getTask();
            this.update(i, elevator.getPosition() + elevator.getDirection(), elevator.getTarget());
            if(elevator.getPosition() == elevator.getTarget()){ /// when reached target
                elevator.finishTask(); /// remove finished task
            }
        }
    }

    public Triple[] status(){
        /// return states for all elevators
        Triple[] array = new Triple[this.elevatorsNumber];
        for(int i=0; i<this.elevatorsNumber; i++){
            array[i] = new Triple(this.elevators[i].getId(), this.elevators[i].getPosition(), this.elevators[i].getTarget());
        }
        return array;
    }

}
