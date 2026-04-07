import java.util.*;

// Reservation class (Actor)
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

// Booking Queue Management
class BookingQueue {
    private Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("✅ Booking request added for " + reservation.getCustomerName());
    }

    // Process booking (FIFO)
    public void processBooking(Map<String, Integer> inventory) {
        if (queue.isEmpty()) {
            System.out.println("❌ No booking requests!");
            return;
        }

        Reservation r = queue.poll(); // FIFO

        String roomType = r.getRoomType();

        if (inventory.containsKey(roomType) && inventory.get(roomType) > 0) {
            inventory.put(roomType, inventory.get(roomType) - 1);
            System.out.println("🎉 Booking confirmed for " + r.getCustomerName() +
                    " (" + roomType + ")");
        } else {
            System.out.println("❌ Booking failed for " + r.getCustomerName() +
                    " (No " + roomType + " available)");
        }
    }

    // Display pending requests
    public void showQueue() {
        System.out.println("\n==== Pending Booking Requests ====");
        for (Reservation r : queue) {
            System.out.println(r.getCustomerName() + " → " + r.getRoomType());
        }
    }
}

// Main class
public class BookMyStayAppUC5 {

    public static void main(String[] args) {

        // Step 1: Inventory (from UC3)
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);

        // Step 2: Booking Queue
        BookingQueue bookingQueue = new BookingQueue();

        // Step 3: Add booking requests (FCFS order)
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Single Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Single Room"));
        bookingQueue.addRequest(new Reservation("David", "Double Room"));

        // Show queue
        bookingQueue.showQueue();

        // Step 4: Process bookings one by one (FIFO)
        System.out.println("\n==== Processing Bookings ====\n");

        bookingQueue.processBooking(inventory);
        bookingQueue.processBooking(inventory);
        bookingQueue.processBooking(inventory);
        bookingQueue.processBooking(inventory);

        // Final inventory
        System.out.println("\n==== Final Inventory ====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}