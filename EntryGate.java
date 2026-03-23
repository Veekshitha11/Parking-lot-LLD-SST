import java.util.Comparator;
import java.util.PriorityQueue;

public class EntryGate {

    private int gateId;
    private PriorityQueue<ParkingSlot> minHeap;

    public EntryGate(int gateId) {
        this.gateId = gateId;

        this.minHeap = new PriorityQueue<>(
            Comparator.comparingInt(slot -> slot.getDistance(gateId))
        );
    }

    public int getGateId() {
        return gateId;
    }

    public PriorityQueue<ParkingSlot> getHeap() {
        return minHeap;
    }

    public void addSlot(ParkingSlot slot) {
        minHeap.offer(slot);
    }
}
