public interface SlotStrategy {
    ParkingSlot findSlot(EntryGate gate, VehicleType vehicleType);
}