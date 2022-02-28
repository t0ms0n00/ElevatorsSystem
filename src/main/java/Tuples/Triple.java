package Tuples;

public class Triple {
    private final int first;
    private final int second;
    private final int third;

    public Triple(int first, int second, int third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public String toString() {
        return "(" + first +
                ", " + second +
                ", " + third +
                ')';
    }

    public int getElevatorId(){
        return this.first;
    }

    public int getActualFloor(){
        return this.second;
    }

    public int getTargetFloor(){
        return this.third;
    }
}
