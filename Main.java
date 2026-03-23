import java.util.*;

public class Main {

    public static void main(String[] args) {

        EntryGate gate1 = new EntryGate(1);
        EntryGate gate2 = new EntryGate(2);

        Map<Integer, EntryGate> gates = new HashMap<>();
        gates.put(1, gate1);
        gates.put(2, gate2);

        List<ParkingLevel> levels = new ArrayList<>();

        // create 2 levels
        for (int l = 1; l <= 2; l++) {

            List<ParkingSlot> slots = new ArrayList<>();

            for (int i = 1; i <= 5; i++) {

                Map<Integer, Integer> dist = new HashMap<>();
                dist.put(1, i + l * 2);
                dist.put(2, i + l * 3);

                ParkingSlot slot = new ParkingSlot(i, l, SlotType.MEDIUM, dist);
                slots.add(slot);
            }

            levels.add(new ParkingLevel(l, slots));
        }

        ParkingLot lot = new ParkingLot(levels, gates);

        Vehicle car = new Vehicle("KA01AB1234", VehicleType.CAR);

        Ticket t = lot.park(car, System.currentTimeMillis(), 1);
        System.out.println("Ticket ID: " + t.getId());

        int fare = lot.exit(t.getId(), System.currentTimeMillis() + 3600000);
        System.out.println("Fare: " + fare);

        lot.status();
    }
}