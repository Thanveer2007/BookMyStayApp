import java.util.*;

// Add-On Service class
class AddOnService {
    private String serviceName;
    private double price;

    public AddOnService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }
}

// Reservation class
class Reservation {
    private int reservationId;
    private String guestName;
    private List<AddOnService> services;

    public Reservation(int reservationId, String guestName) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.services = new ArrayList<>();
    }

    public int getReservationId() {
        return reservationId;
    }

    public void addService(AddOnService service) {
        services.add(service);
    }

    public void removeService(String serviceName) {
        services.removeIf(s -> s.getServiceName().equalsIgnoreCase(serviceName));
    }

    public double calculateTotalCost() {
        double total = 0;
        for (AddOnService s : services) {
            total += s.getPrice();
        }
        return total;
    }

    public void displayServices() {
        System.out.println("Add-On Services for " + guestName + ":");
        for (AddOnService s : services) {
            System.out.println("- " + s.getServiceName() + " : ₹" + s.getPrice());
        }
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    public void addServiceToReservation(Reservation reservation, AddOnService service) {
        reservation.addService(service);
        System.out.println(service.getServiceName() + " added successfully.");
    }

    public void removeServiceFromReservation(Reservation reservation, String serviceName) {
        reservation.removeService(serviceName);
        System.out.println(serviceName + " removed successfully.");
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {

        // Create reservation
        Reservation reservation = new Reservation(101, "Thanveer");

        // Create services
        AddOnService breakfast = new AddOnService("Breakfast", 250);
        AddOnService spa = new AddOnService("Spa", 1000);
        AddOnService pickup = new AddOnService("Airport Pickup", 800);

        // Manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services
        manager.addServiceToReservation(reservation, breakfast);
        manager.addServiceToReservation(reservation, spa);
        manager.addServiceToReservation(reservation, pickup);

        // Display
        reservation.displayServices();

        // Remove one service
        manager.removeServiceFromReservation(reservation, "Spa");

        // Display again
        reservation.displayServices();

        // Total cost
        System.out.println("Total Add-On Cost: ₹" + reservation.calculateTotalCost());
    }
}