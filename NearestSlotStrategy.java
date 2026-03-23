import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class NearestSlotStrategy implements SlotStrategy {

    @Override
    public ParkingSlot findSlot(EntryGate gate, VehicleType type) {

        PriorityQueue<ParkingSlot> heap = gate.getHeap();
        List<ParkingSlot> temp = new ArrayList<>();

        ParkingSlot result = null;

        while (!heap.isEmpty()) {
            ParkingSlot slot = heap.poll();

            if (!slot.isOccupied() && isCompatible(slot.getType(), type)) {
                result = slot;
                break;
            }

            temp.add(slot);
        }

        heap.addAll(temp);

        return result;
    }

    private boolean isCompatible(SlotType slot, VehicleType vehicle) {
        if (vehicle == VehicleType.BIKE) return true;
        if (vehicle == VehicleType.CAR)
            return slot == SlotType.MEDIUM || slot == SlotType.LARGE;
        return slot == SlotType.LARGE;
    }
}