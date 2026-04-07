import java.io.*;
import java.util.*;

// Serializable class
class Booking implements Serializable {
    String guestName;
    int rooms;

    public Booking(String guestName, int rooms) {
        this.guestName = guestName;
        this.rooms = rooms;
    }

    public String toString() {
        return guestName + " booked " + rooms + " room(s)";
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "bookings.dat";

    // Save data
    public static void saveData(List<Booking> bookings) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(bookings);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // Load data
    public static List<Booking> loadData() {
        List<Booking> bookings = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            bookings = (List<Booking>) ois.readObject();
            System.out.println("Data loaded successfully!");
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
        }

        return bookings;
    }
}

// Main System
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Load previous data
        List<Booking> bookings = PersistenceService.loadData();

        while (true) {
            System.out.println("\n--- Book My Stay ---");
            System.out.println("1. Add Booking");
            System.out.println("2. View Bookings");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter guest name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter number of rooms: ");
                    int rooms = sc.nextInt();

                    bookings.add(new Booking(name, rooms));
                    System.out.println("Booking added!");
                    break;

                case 2:
                    System.out.println("\nAll Bookings:");
                    for (Booking b : bookings) {
                        System.out.println(b);
                    }
                    break;

                case 3:
                    // Save before exit
                    PersistenceService.saveData(bookings);
                    System.out.println("Exiting... Data saved!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}