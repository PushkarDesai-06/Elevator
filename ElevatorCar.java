
import java.util.ArrayList;
import java.util.List;

public class ElevatorCar implements ElevatorObserver {
    private final String id;
    private int weight = 0;
    private Status status = Status.IDLE;
    private int level = 0;
    private boolean isDoorOpen = false;
    private List<Integer> upCalls = new ArrayList<>();
    private List<Integer> downCalls = new ArrayList<>();

    public ElevatorCar(String id) {
        this.id = id;
    }

    public void openDoor() {
        isDoorOpen = true;
    }

    public void closeDoor() {
        isDoorOpen = false;
    }

    @Override
    public void listen(ElevatorEvent event) {

        if (event.getWeight() >= 0)
            this.weight = event.getWeight();
        if (event.getLevel() != 0)
            this.level = event.getLevel();
        if (event.getStatus() != null)
            this.status = event.getStatus();

    }

    public String getId() {
        return id;
    }

}
