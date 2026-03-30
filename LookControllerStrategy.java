import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LookControllerStrategy implements ControllerStrategy {

    @Override
    public void processRequests(List<ElevatorCar> cars, List<ElevatorRequest> requests) {
        if (cars.isEmpty() || requests.isEmpty()) {
            return;
        }

        // Track which requests are currently being handled this tick to prevent
        // multiple cars
        // going to the exact same request simultaneously.
        Set<ElevatorRequest> handledRequests = new HashSet<>();

        for (ElevatorCar car : cars) {
            if (!car.canMove())
                continue;

            ElevatorRequest bestMatch = null;
            int minDistance = Integer.MAX_VALUE;

            // Find the closest unhandled request for this car
            for (ElevatorRequest r : requests) {
                if (handledRequests.contains(r))
                    continue;

                int distance = Math.abs(car.getLevel() - r.getLevel());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestMatch = r;
                }
            }

            if (bestMatch != null) {
                handledRequests.add(bestMatch); // Claim it for this car

                if (car.getLevel() < bestMatch.getLevel()) {
                    car.moveOneFloor(Direction.UP);
                } else if (car.getLevel() > bestMatch.getLevel()) {
                    car.moveOneFloor(Direction.DOWN);
                } else {
                    // Arrived
                    car.stopAndIdle();
                    car.openDoor();
                    // Remove from global queue because it's fulfilled
                    requests.remove(bestMatch);
                }
            }
        }
    }
}