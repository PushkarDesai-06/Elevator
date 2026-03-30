
public class ElevatorCar implements ElevatorObserver {
    private final String id;
    private int weight = 0;
    private Status status = Status.IDLE;
    private int level = 0;
    private boolean isDoorOpen = false;
    private final int MAX_WEIGHT_CAPACITY = 1000; // e.g., 1000 kg limit

    public ElevatorCar(String id) {
        this.id = id;
    }

    public void openDoor() {
        isDoorOpen = true;
        System.out.println("Car " + id + ": Door opened at floor " + level);
    }

    public void closeDoor() {
        if (weight > MAX_WEIGHT_CAPACITY) {
            System.out.println("Car " + id + ": OVERWEIGHT ALARM! Cannot close doors. Please reduce weight.");
            return;
        }
        isDoorOpen = false;
        System.out.println("Car " + id + ": Door closed at floor " + level);
    }

    public boolean canMove() {
        return !isDoorOpen && weight <= MAX_WEIGHT_CAPACITY && status != Status.UNDER_MAINTAINANCE;
    }

    // Dumb actuator method: just moves one step as instructed by the controller
    public void moveOneFloor(Direction direction) {
        if (!canMove()) {
            System.out.println("Car " + id + " cannot move. Check doors, weight, or maintenance status.");
            return;
        }

        if (direction == Direction.UP) {
            level++;
            status = Status.MOVING_UP;
        } else {
            level--;
            status = Status.MOVING_DOWN;
        }
        System.out.println("Car " + id + " reached floor " + level);
    }

    public void stopAndIdle() {
        this.status = Status.IDLE;
        System.out.println("Car " + id + " is now IDLE at floor " + level);
    }

    public void setUnderMaintenance() {
        this.status = Status.UNDER_MAINTAINANCE;
        openDoor();
        System.out.println("Car " + id + " is under maintenance.");
        // The controller will notice this status change and handle re-routing requests.
    }

    // Getters for the Controller to read the car's state
    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isDoorOpen() {
        return isDoorOpen;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public void listen(ElevatorEvent event) {
        if (event.getWeight() >= 0)
            this.weight = event.getWeight();
        // The car is dumb now, so level updates usually come from movement,
        // but we can allow sensors to forcibly update it via events.
        if (event.getLevel() != 0)
            this.level = event.getLevel();
        if (event.getStatus() != null)
            this.status = event.getStatus();
    }
}
