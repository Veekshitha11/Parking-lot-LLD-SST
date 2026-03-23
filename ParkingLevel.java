import java.util.List;

public class ParkingLevel {

    private int levelId;
    private List<ParkingSlot> slots;

    public ParkingLevel(int levelId, List<ParkingSlot> slots) {
        this.levelId = levelId;
        this.slots = slots;
    }

    public int getLevelId() {
        return levelId;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }
}