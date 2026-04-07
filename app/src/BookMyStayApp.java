import java.util.HashMap;
import java.util.Map;

// RoomInventory class (Actor)
class RoomInventory {

    // Centralized storage using HashMap
    private Map<String, Integer> inventory;

    // Constructor → initialize system
    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Register room types
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Display all room availability
    public void showAllRooms() {
        System.out.println("\n==== Room Availability ====\n");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey());
            System.out.println("Available: " + entry.getValue());
            System.out.println("--------------------------");
        }
    }

    // Get availability of a specific room
    public void getAvailability(String roomType) {
        if (inventory.containsKey(roomType)) {
            System.out.println(roomType + " Available: " + inventory.get(roomType));
        } else {
            System.out.println("Room type not found!");
        }
    }

    // Book a room
    public void bookRoom(String roomType) {
        if (inventory.containsKey(roomType)) {
            int count = inventory.get(roomType);

            if (count > 0) {
                inventory.put(roomType, count - 1);
                System.out.println("✅ " + roomType + " booked successfully!");
            } else {
                System.out.println("❌ No rooms available!");
            }
        } else {
            System.out.println("❌ Invalid room type!");
        }
    }
}

// Main class
public class BookMyStayApp {

    public static void main(String[] args) {

        // Step 1: Initialize inventory system
        RoomInventory inventory = new RoomInventory();

        // Step 2: Register room types
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 3);
        inventory.addRoomType("Deluxe Room", 2);

        // Step 3: Show all rooms
        inventory.showAllRooms();

        // Step 4: Check availability
        inventory.getAvailability("Double Room");

        // Step 5: Book a room
        inventory.bookRoom("Double Room");

        // Step 6: Show updated inventory
        inventory.showAllRooms();
    }
}