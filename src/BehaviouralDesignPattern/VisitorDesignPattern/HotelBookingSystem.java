package BehaviouralDesignPattern.VisitorDesignPattern;

interface BookingVisitor {
    void visit(RoomBooking roomBooking);

    void visit(ServiceBooking serviceBooking);
}

interface BookingElement {
    void accept(BookingVisitor visitor);
}

// Room booking
class RoomBooking implements BookingElement {
    private String roomType;
    private int nights;
    private double pricePerNight;

    public RoomBooking(String roomType, int nights, double pricePerNight) {
        this.roomType = roomType;
        this.nights = nights;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNights() {
        return nights;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public double getTotalPrice() {
        return pricePerNight * nights;
    }

    @Override
    public void accept(BookingVisitor visitor) {
        visitor.visit(this);
    }
}

// Service booking (e.g., Spa, Breakfast)
class ServiceBooking implements BookingElement {
    private String serviceName;
    private double price;

    public ServiceBooking(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(BookingVisitor visitor) {
        visitor.visit(this);
    }
}

class BookingCostVisitor implements BookingVisitor {
    private double totalCost = 0;

    @Override
    public void visit(RoomBooking roomBooking) {
        System.out.println("Calculating cost for room: " + roomBooking.getRoomType() +
                ", Nights: " + roomBooking.getNights() +
                ", Per night: " + roomBooking.getPricePerNight() +
                ", Total: " + roomBooking.getTotalPrice());
        totalCost += roomBooking.getTotalPrice();
    }

    @Override
    public void visit(ServiceBooking serviceBooking) {
        System.out.println("Calculating cost for service: " + serviceBooking.getServiceName() +
                ", Price: " + serviceBooking.getPrice());
        totalCost += serviceBooking.getPrice();
    }

    public double getTotalCost() {
        return totalCost;
    }
}

class BookingPrintVisitor implements BookingVisitor {
    @Override
    public void visit(RoomBooking roomBooking) {
        System.out.println("Room booked: " + roomBooking.getRoomType() +
                " for " + roomBooking.getNights() + " nights.");
    }

    @Override
    public void visit(ServiceBooking serviceBooking) {
        System.out.println("Additional service booked: " + serviceBooking.getServiceName() +
                ", Price: " + serviceBooking.getPrice());
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        BookingElement[] bookings = {
                new RoomBooking("Deluxe", 3, 200.0),
                new ServiceBooking("Spa", 80.0),
                new RoomBooking("Standard", 2, 120.0),
                new ServiceBooking("Breakfast", 30.0)
        };

        BookingCostVisitor costVisitor = new BookingCostVisitor();
        BookingPrintVisitor printVisitor = new BookingPrintVisitor();

        // Print booking details
        System.out.println("Booking Details:");
        for (BookingElement booking : bookings) {
            booking.accept(printVisitor);
        }

        // Calculate total cost
        for (BookingElement booking : bookings) {
            booking.accept(costVisitor);
        }

        System.out.println("Total booking cost: $" + costVisitor.getTotalCost());
    }
}
