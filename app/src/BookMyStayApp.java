import java.util.*;

// Reservation class
class Reservation {
    private String customerName;
    private String roomType;

    public Reservation(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Booking Service (Actor)
class BookingService {

    private Queue<Reservation> bookingQueue;
    private Map<String, Integer> inventory;

    public BookingService(Map<String, Integer> inventory) {
        this.inventory = inventory;
        this.bookingQueue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation r) {
        bookingQueue.add(r);
        System.out.println("📩 Request added: " + r.getCustomerName());
    }

    // Process all bookings safely
    public void processBookings() {
        System.out.println("\n==== Processing Bookings ====\n");

        while (!bookingQueue.isEmpty()) {
            Reservation r = bookingQueue.poll(); // FIFO
            String roomType = r.getRoomType();

            if (inventory.containsKey(roomType) && inventory.get(roomType) > 0) {
                // Allocate room safely
                inventory.put(roomType, inventory.get(roomType) - 1);

                System.out.println("✅ Booking CONFIRMED for "
                        + r.getCustomerName()
                        + " → " + roomType);
            } else {
                System.out.println("❌ Booking FAILED for "
                        + r.getCustomerName()
                        + " → " + roomType + " (Not Available)");
            }
        }
    }

    // Show remaining inventory
    public void showInventory() {
        System.out.println("\n==== Remaining Inventory ====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}

// Main class
public class BookMyStayAppUC6 {

    public static void main(String[] args) {

        // Step 1: Initialize inventory (Centralized)
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);

        // Step 2: Booking Service
        BookingService service = new BookingService(inventory);

        // Step 3: Add booking requests (Queue)
        service.addRequest(new Reservation("Alice", "Single Room"));
        service.addRequest(new Reservation("Bob", "Single Room"));
        service.addRequest(new Reservation("Charlie", "Single Room"));
        service.addRequest(new Reservation("David", "Double Room"));

        // Step 4: Process all bookings safely
        service.processBookings();

        // Step 5: Show final inventory
        service.showInventory();
    }
}