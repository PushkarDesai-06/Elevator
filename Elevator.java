import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private List<ElevatorCar> cars;
    private ControllerStrategy strategy;
    private List<ElevatorRequest> requests;

    public Elevator(List<ElevatorCar> cars, ControllerStrategy strategy) {
        this.cars = cars;
        this.strategy = strategy;
        this.requests = new ArrayList<>();
    }

    // Single central method to request an elevator.
    public void callElevator(ElevatorRequest request) {
        requests.add(request);
    }

    // Called by a central clock/tick or manually to process movement step
    public void step() {
        strategy.processRequests(cars, requests);
    }

}
