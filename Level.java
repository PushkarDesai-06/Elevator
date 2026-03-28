public class Level {
    int level;
    Button upButton;
    Button downButton;
    Elevator elevator;

    public void callElevator(Direction direction) {
        elevator.callElevator(new ElevatorRequest(level, direction));
    }

}
