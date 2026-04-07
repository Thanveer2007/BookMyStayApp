import java.util.HashMap;
import java.util.Map;

// Room class (Basic structure)
class Room {
    private String roomType;
    private double price;

    public Room(String roomType, double price) {
        this.roomType = roomType;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }
}

// Centralized Inventory Management
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    // Add room type with availability
    public void addRoom(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Display all rooms
    public void displayInventory() {
        System.out.println("==== Room Inventory ====\n");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey());
            System.out.println("Available Rooms: " + entry.getValue());
            System.out.println("-------------------------");
        }
    }

    // Check availability
    public void checkAvailability(String roomType) {
        if (inventory.containsKey(roomType)) {
            System.out.println(roomType + " Available: " + inventory.get(roomType));
        } else {
            System.out.println("Room type not found!");
        }
    }

    // Book a room (reduce count)
    public void bookRoom(String roomType) {
        if (inventory.containsKey(roomType) && inventory.get(roomType) > 0) {
            inventory.put(roomType, inventory.get(roomType) - 1);
            System.out.println(roomType + " booked successfully!");
        } else {
            System.out.println("Room not available!");
        }
    }
}

// Main class
public class BookMyStayAppUC3 {

    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Add room types
        inventory.addRoom("Single Room", 5);
        inventory.addRoom("Double Room", 3);
        inventory.addRoom("Deluxe Room", 2);

        // Display inventory
        inventory.displayInventory();

        // Check availability
        inventory.checkAvailability("Single Room");

        // Book a room
        inventory.bookRoom("Single Room");

        // Display updated inventory
        inventory.displayInventory();
    }
}