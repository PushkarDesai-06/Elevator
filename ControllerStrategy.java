import java.util.List;

public interface ControllerStrategy {
    // Process the requests and orchestrate movement of the given elevator cars
    void processRequests(List<ElevatorCar> cars, List<ElevatorRequest> requests);
}