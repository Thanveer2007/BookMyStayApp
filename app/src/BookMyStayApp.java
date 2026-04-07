class Hotel {
    private int availableRooms;

    public Hotel(int rooms) {
        this.availableRooms = rooms;
    }

    // synchronized method to ensure thread safety
    public synchronized void bookRoom(String guestName, int roomsRequested) {
        System.out.println(guestName + " is trying to book " + roomsRequested + " room(s)...");

        if (roomsRequested <= availableRooms) {
            System.out.println("Booking successful for " + guestName);
            availableRooms -= roomsRequested;
            System.out.println("Rooms left: " + availableRooms);
        } else {
            System.out.println("Booking failed for " + guestName + " (Not enough rooms)");
        }

        System.out.println("-----------------------------------");
    }
}

// Thread class
class BookingThread extends Thread {
    private Hotel hotel;
    private String guestName;
    private int rooms;

    public BookingThread(Hotel hotel, String guestName, int rooms) {
        this.hotel = hotel;
        this.guestName = guestName;
        this.rooms = rooms;
    }

    public void run() {
        hotel.bookRoom(guestName, rooms);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        Hotel hotel = new Hotel(5); // Total rooms = 5

        // Multiple users (threads)
        BookingThread t1 = new BookingThread(hotel, "Guest A", 2);
        BookingThread t2 = new BookingThread(hotel, "Guest B", 3);
        BookingThread t3 = new BookingThread(hotel, "Guest C", 2);

        // Start threads
        t1.start();
        t2.start();
        t3.start();
    }
}