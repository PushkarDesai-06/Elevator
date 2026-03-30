import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Initializing Elevator System ===");

        // 1. Create Elevator Cars
        ElevatorCar car1 = new ElevatorCar("CAR-A");
        ElevatorCar car2 = new ElevatorCar("CAR-B");

        List<ElevatorCar> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);

        // 2. Create Sensors and Attach Observers (Cars)
        Sensor sensor1 = new Sensor();
        sensor1.attach(car1);

        Sensor sensor2 = new Sensor();
        sensor2.attach(car2);

        // 3. Initialize Controller Strategy and Central Elevator System
        ControllerStrategy strategy = new LookControllerStrategy();
        Elevator elevatorController = new Elevator(cars, strategy);

        // 4. Simulate a Hall Request
        System.out.println("\n--- [Event] Person at Floor 3 presses UP button ---");
        elevatorController.callElevator(new ElevatorRequest(3, Direction.UP));

        // 5. Real-world simulation relies on "ticks" or a clock running continuously.
        // We will simulate physical time passing using manual steps.
        System.out.println("\n>>> Clock Tick 1:");
        elevatorController.step(); // System evaluates request, Car A moves to Floor 1

        System.out.println("\n>>> Clock Tick 2:");
        elevatorController.step(); // Car A moves to Floor 2

        System.out.println("\n>>> Clock Tick 3:");
        elevatorController.step(); // Car A arrives at Floor 3, doors open!

        // 6. Simulate getting into the elevator, causing a weight violation
        System.out.println("\n--- [Event] Crowd enters CAR-A at Floor 3 ---");

        // Sensor detects weight spike to 1200kg (Limit is 1000kg)
        sensor1.changeWeight(1200);

        // The user inside presses Floor 5
        System.out.println("\n--- [Event] User inside CAR-A presses Floor 5 ---");
        elevatorController.callElevator(new ElevatorRequest(5, Direction.UP));

        // Let's try to step. The controller will try to move the car, but it's
        // physically blocked by its own logic.
        System.out.println("\n>>> Clock Tick 4 (Attempting to leave Floor 3):");
        car1.closeDoor(); // Physical mechanism tries to close door but alarm sounds
        elevatorController.step(); // The controller strategy tells it to move, but "dumb box" car rejects it.

        System.out.println("\n--- [Event] 2 people step out of CAR-A ---");
        sensor1.changeWeight(850); // Weight is back under limit
        car1.closeDoor(); // Doors successfully close

        System.out.println("\n>>> Clock Tick 5:");
        elevatorController.step(); // Controller strategy tells it to move, Car A moves to Floor 4

        System.out.println("\n>>> Clock Tick 6:");
        elevatorController.step(); // Car A arrives at Floor 5, doors open, request cleared!

        System.out.println("\n=== End ===");
    }
}