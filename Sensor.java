import java.util.ArrayList;
import java.util.List;

public class Sensor {
    private final List<ElevatorObserver> observers = new ArrayList<>();

    public void attach(ElevatorObserver observer) {
        observers.add(observer);
    }

    public void detach(ElevatorObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(ElevatorEvent event) {
        for (ElevatorObserver observer : observers) {
            observer.listen(event);
        }
    }

    // Simulates the physical weight sensor detecting a change in the car
    public void changeWeight(int newWeight) {
        System.out.println("Sensor detected weight change: " + newWeight + " kg.");
        // Level=0 and Status=null are ignored by the ElevatorCar's listen() method
        ElevatorEvent event = new ElevatorEvent(0, newWeight, null);
        notifyObservers(event);
    }

    // Simulates a maintenance switch or mechanical status flag being tripped
    public void triggerStatusChange(Status newStatus) {
        System.out.println("Sensor detected mechanical status change: " + newStatus);
        // Level=0 and Weight=-1 are ignored by the ElevatorCar's listen() method
        ElevatorEvent event = new ElevatorEvent(0, -1, newStatus);
        notifyObservers(event);
    }
}
