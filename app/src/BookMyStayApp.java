import java.util.*;

// Booking class
class Booking {
    private int bookingId;
    private String guestName;
    private String roomType;
    private double amount;

    public Booking(int bookingId, String guestName, String roomType, double amount) {
        this.bookingId = bookingId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.amount = amount;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getAmount() {
        return amount;
    }

    public void display() {
        System.out.println("ID: " + bookingId +
                ", Guest: " + guestName +
                ", Room: " + roomType +
                ", Amount: ₹" + amount);
    }
}

// Booking History class
class BookingHistory {
    private List<Booking> bookings;

    public BookingHistory() {
        bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }

    public void displayAllBookings() {
        System.out.println("\n--- Booking History ---");
        for (Booking b : bookings) {
            b.display();
        }
    }
}

// Report Service class
class BookingReportService {

    public void generateTotalRevenue(List<Booking> bookings) {
        double total = 0;
        for (Booking b : bookings) {
            total += b.getAmount();
        }
        System.out.println("Total Revenue: ₹" + total);
    }

    public void generateBookingCount(List<Booking> bookings) {
        System.out.println("Total Bookings: " + bookings.size());
    }

    public void generateRoomTypeReport(List<Booking> bookings) {
        Map<String, Integer> roomCount = new HashMap<>();

        for (Booking b : bookings) {
            roomCount.put(b.getRoomType(),
                    roomCount.getOrDefault(b.getRoomType(), 0) + 1);
        }

        System.out.println("\nRoom Type Report:");
        for (String room : roomCount.keySet()) {
            System.out.println(room + " : " + roomCount.get(room));
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        // Add bookings
        history.addBooking(new Booking(1, "Thanveer", "Deluxe", 3000));
        history.addBooking(new Booking(2, "Ali", "Standard", 2000));
        history.addBooking(new Booking(3, "Rahul", "Deluxe", 3500));
        history.addBooking(new Booking(4, "John", "Suite", 5000));

        // Display history
        history.displayAllBookings();

        // Generate reports
        BookingReportService report = new BookingReportService();

        System.out.println("\n--- Reports ---");
        report.generateTotalRevenue(history.getAllBookings());
        report.generateBookingCount(history.getAllBookings());
        report.generateRoomTypeReport(history.getAllBookings());
    }
}