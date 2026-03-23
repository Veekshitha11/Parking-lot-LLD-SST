import java.util.HashMap;
import java.util.Map;

public class FareCalculator {

    private Map<SlotType, Integer> rates = new HashMap<>();

    public FareCalculator() {
        rates.put(SlotType.SMALL, 10);
        rates.put(SlotType.MEDIUM, 20);
        rates.put(SlotType.LARGE, 30);
    }

    public int calculate(Ticket ticket, long exitTime) {
        long hours = (exitTime - ticket.getEntryTime()) / (1000 * 60 * 60);
        hours = Math.max(1, hours);

        return (int) hours * rates.get(ticket.getSlot().getType());
    }
}