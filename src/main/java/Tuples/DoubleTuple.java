package Tuples;

public class DoubleTuple {
    private final int floor;
    private final int direction;

    public DoubleTuple(int floor, int direction){
        this.floor = floor;
        this.direction = direction;
    }

    public int getFloor() {
        return floor;
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "(" + floor +
                ", " + direction +
                ')';
    }
}
