public class ElevatorEvent {
    private int level;
    private int weight;
    private Status status;

    public ElevatorEvent(int level, int weight, Status status) {
        this.level = level;
        this.weight = weight;
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
