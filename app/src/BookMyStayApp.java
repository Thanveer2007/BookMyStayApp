import java.util.*;

// Room Inventory Class
class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Standard", 5);
        rooms.put("Deluxe", 3);
        rooms.put("Suite", 2);
    }

    public boolean bookRoom(String type) {
        if (rooms.getOrDefault(type, 0) > 0) {
            rooms.put(type, rooms.get(type) - 1);
            return true;
        }
        return false;
    }

    public void rollbackRoom(String type) {
        rooms.put(type, rooms.getOrDefault(type, 0) + 1);
    }

    public void displayInventory() {
        System.out.println("\n--- Room Inventory ---");
        for (String type : rooms.keySet()) {
            System.out.println(type + " : " + rooms.get(type));
        }
    }
}

// Booking Class
class Booking {
    int id;
    String guestName;
    String roomType;
    boolean isActive;

    public Booking(int id, String guestName, String roomType) {
        this.id = id;
        this.guestName = guestName;
        this.roomType = roomType;
        this.isActive = true;
    }
}

// Cancellation Service
class CancellationService {

    public void cancelBooking(Booking booking, RoomInventory inventory) {
        if (booking == null || !booking.isActive) {
            System.out.println("Invalid or already cancelled booking.");
            return;
        }

        // Rollback inventory
        inventory.rollbackRoom(booking.roomType);

        // Update booking status
        booking.isActive = false;

        System.out.println("Booking ID " + booking.id + " cancelled successfully.");
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        List<Booking> bookings = new ArrayList<>();

        // Create bookings
        if (inventory.bookRoom("Deluxe")) {
            bookings.add(new Booking(1, "Thanveer", "Deluxe"));
        }

        if (inventory.bookRoom("Suite")) {
            bookings.add(new Booking(2, "Ali", "Suite"));
        }

        inventory.displayInventory();

        // Cancel booking
        CancellationService cancelService = new CancellationService();
        cancelService.cancelBooking(bookings.get(0), inventory);

        // After rollback
        inventory.displayInventory();
    }
}