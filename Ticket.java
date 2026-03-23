public class Ticket {
    private int id;
    private Vehicle vehicle;
    private ParkingSlot slot;
    private long entryTime;

    public Ticket(int id, Vehicle vehicle, ParkingSlot slot, long entryTime) {
        this.id = id;
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = entryTime;
    }

    public int getId() {
        return id;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}