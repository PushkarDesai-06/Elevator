import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private List<ElevatorCar> cars;
    private ControllerStrategy strategy;
    private List<ElevatorRequest> requests;

    public Elevator(List<ElevatorCar> cars, ControllerStrategy strategy) {
        this.cars = cars;
        this.strategy = strategy;
        requests = new ArrayList<>();
    }

    public void callElevator(ElevatorRequest request) {
        requests.add(request);
        strategy.notifyChange();
    }

}
