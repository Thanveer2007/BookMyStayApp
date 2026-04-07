public class BookMyStayApp {
    // Abstract class (Abstraction)
    abstract class Room {
        protected String roomType;
        protected double price;
        protected int availableRooms;

        // Constructor
        public Room(String roomType, double price, int availableRooms) {
            this.roomType = roomType;
            this.price = price;
            this.availableRooms = availableRooms;
        }

        // Abstract method
        public abstract void displayDetails();
    }

    // Single Room class (Inheritance)
    class SingleRoom extends Room {

        public SingleRoom(int availableRooms) {
            super("Single Room", 1000, availableRooms);
        }

        @Override
        public void displayDetails() {
            System.out.println("Room Type: " + roomType);
            System.out.println("Price: ₹" + price);
            System.out.println("Available Rooms: " + availableRooms);
            System.out.println("-----------------------------");
        }
    }

    // Double Room class
    class DoubleRoom extends Room {

        public DoubleRoom(int availableRooms) {
            super("Double Room", 2000, availableRooms);
        }

        @Override
        public void displayDetails() {
            System.out.println("Room Type: " + roomType);
            System.out.println("Price: ₹" + price);
            System.out.println("Available Rooms: " + availableRooms);
            System.out.println("-----------------------------");
        }
    }

    // Deluxe Room class
    class DeluxeRoom extends Room {

        public DeluxeRoom(int availableRooms) {
            super("Deluxe Room", 3000, availableRooms);
        }

        @Override
        public void displayDetails() {
            System.out.println("Room Type: " + roomType);
            System.out.println("Price: ₹" + price);
            System.out.println("Available Rooms: " + availableRooms);
            System.out.println("-----------------------------");
        }
    }

    // Main class

        public static void main(String[] args) {

            // Creating room objects (Static Availability)
            Room single = new SingleRoom(5);
            Room doubleRoom = new DoubleRoom(3);
            Room deluxe = new DeluxeRoom(2);

            // Display details
            System.out.println("==== Available Room Types ====\n");

            single.displayDetails();
            doubleRoom.displayDetails();
            deluxe.displayDetails();
        }

}
