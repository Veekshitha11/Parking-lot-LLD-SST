import java.util.*;

public class ParkingLot {

    private List<ParkingLevel> levels;   // NEW
    private Map<Integer, EntryGate> gates;
    private Map<Integer, Ticket> activeTickets;

    private SlotStrategy strategy;
    private FareCalculator calculator;

    private int ticketCounter = 1;

    public ParkingLot(List<ParkingLevel> levels, Map<Integer, EntryGate> gates) {
        this.levels = levels;
        this.gates = gates;
        this.activeTickets = new HashMap<>();
        this.strategy = new NearestSlotStrategy();
        this.calculator = new FareCalculator();

        initializeHeaps(); // IMPORTANT
    }

    // 🔥 push all slots from all levels into each gate heap
    private void initializeHeaps() {
        for (ParkingLevel level : levels) {
            for (ParkingSlot slot : level.getSlots()) {
                for (EntryGate gate : gates.values()) {
                    gate.addSlot(slot);
                }
            }
        }
    }

    public Ticket park(Vehicle vehicle, long entryTime, int gateId) {

        EntryGate gate = gates.get(gateId);

        ParkingSlot slot = strategy.findSlot(gate, vehicle.getType());

        if (slot == null) {
            System.out.println("No slot available");
            return null;
        }

        slot.occupy();

        Ticket ticket = new Ticket(ticketCounter++, vehicle, slot, entryTime);
        activeTickets.put(ticket.getId(), ticket);

        return ticket;
    }

    public int exit(int ticketId, long exitTime) {

        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) return -1;

        ParkingSlot slot = ticket.getSlot();
        slot.free();

        for (EntryGate gate : gates.values()) {
            gate.getHeap().offer(slot);
        }

        int fare = calculator.calculate(ticket, exitTime);

        activeTickets.remove(ticketId);

        return fare;
    }

    public void status() {

        Map<SlotType, Integer> freeCount = new HashMap<>();

        for (ParkingLevel level : levels) {
            for (ParkingSlot slot : level.getSlots()) {
                if (!slot.isOccupied()) {
                    freeCount.put(slot.getType(),
                        freeCount.getOrDefault(slot.getType(), 0) + 1);
                }
            }
        }

        System.out.println("Available slots: " + freeCount);
    }
}