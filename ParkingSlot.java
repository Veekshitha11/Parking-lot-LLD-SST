import java.util.Map;

public class ParkingSlot {
    private int id;
    private int levelId;   // NEW
    private SlotType type;
    private boolean occupied;
    private Map<Integer, Integer> distanceMap;

    public ParkingSlot(int id, int levelId, SlotType type, Map<Integer, Integer> distanceMap) {
        this.id = id;
        this.levelId = levelId;
        this.type = type;
        this.distanceMap = distanceMap;
        this.occupied = false;
    }

    public int getLevelId() {
        return levelId;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy() {
        this.occupied = true;
    }

    public void free() {
        this.occupied = false;
    }

    public SlotType getType() {
        return type;
    }

    public int getDistance(int gateId) {
        return distanceMap.get(gateId);
    }

    public int getId() {
        return id;
    }
}