import java.util.*;

// Custom Exception for Invalid Booking
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Booking class
class Booking {
    private int bookingId;
    private String guestName;
    private int nights;
    private double pricePerNight;

    public Booking(int bookingId, String guestName, int nights, double pricePerNight) {
        this.bookingId = bookingId;
        this.guestName = guestName;
        this.nights = nights;
        this.pricePerNight = pricePerNight;
    }

    public double calculateTotal() {
        return nights * pricePerNight;
    }

    public void display() {
        System.out.println("Booking ID: " + bookingId +
                ", Guest: " + guestName +
                ", Nights: " + nights +
                ", Total: ₹" + calculateTotal());
    }
}

// Validator class
class BookingValidator {

    public static void validate(String guestName, int nights, double price) throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (nights <= 0) {
            throw new InvalidBookingException("Number of nights must be greater than 0.");
        }

        if (price <= 0) {
            throw new InvalidBookingException("Price must be positive.");
        }
    }
}

// Booking Service
class BookingService {

    private List<Booking> bookings = new ArrayList<>();

    public void createBooking(int id, String name, int nights, double price) {
        try {
            // Validate input
            BookingValidator.validate(name, nights, price);

            // Create booking if valid
            Booking booking = new Booking(id, name, nights, price);
            bookings.add(booking);

            System.out.println("Booking successful!");
            booking.display();

        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void showAllBookings() {
        System.out.println("\n--- Valid Bookings ---");
        for (Booking b : bookings) {
            b.display();
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        BookingService service = new BookingService();

        // Valid booking
        service.createBooking(1, "Thanveer", 3, 2000);

        // Invalid cases
        service.createBooking(2, "", 2, 1500);        // invalid name
        service.createBooking(3, "Ali", 0, 1500);     // invalid nights
        service.createBooking(4, "Rahul", 2, -500);   // invalid price

        // Display valid bookings
        service.showAllBookings();
    }
}