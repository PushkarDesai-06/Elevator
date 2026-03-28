public class ElevatorRequest {
    private final int level;
    private final Direction direction;

    public ElevatorRequest(int level, Direction direction) {
        this.level = level;
        this.direction = direction;
    }

    public int getLevel() {
        return level;
    }

    public Direction getDirection() {
        return direction;
    }

}
