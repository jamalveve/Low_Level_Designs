## 🧩 Visitor Design Pattern in Java 

The **Visitor Design Pattern** is a behavioral pattern that separates an algorithm from the objects on which it operates. It allows adding new operations to existing object structures without modifying those structures.

### ⚡ What’s Happening?

Instead of embedding operational logic inside booking classes, the Visitor pattern defines *visitor* objects that visit each booking type and perform specific operations. Bookings accept visitors, which determine the exact action based on the booking type.

### 🛠️ Components

#### 1. Visitor Interface

Defines visit methods for each type of booking element.

```java
interface BookingVisitor {
    void visit(RoomBooking roomBooking);
    void visit(ServiceBooking serviceBooking);
}
```

#### 2. Element Interface

Declares an `accept` method to receive visitors.

```java
interface BookingElement {
    void accept(BookingVisitor visitor);
}
```

#### 3. Concrete Elements

Implement `accept` method to call the visitor’s matching visit method.

```java
class RoomBooking implements BookingElement {
    private String roomType;
    private int nights;
    private double pricePerNight;

    public RoomBooking(String roomType, int nights, double pricePerNight) {
        this.roomType = roomType;
        this.nights = nights;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType() { return roomType; }
    public int getNights() { return nights; }
    public double getPricePerNight() { return pricePerNight; }
    public double getTotalPrice() { return pricePerNight * nights; }

    public void accept(BookingVisitor visitor) {
        visitor.visit(this);
    }
}

class ServiceBooking implements BookingElement {
    private String serviceName;
    private double price;

    public ServiceBooking(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() { return serviceName; }
    public double getPrice() { return price; }

    public void accept(BookingVisitor visitor) {
        visitor.visit(this);
    }
}
```

#### 4. Concrete Visitors

Implement operations for each booking type.

```java
class BookingCostVisitor implements BookingVisitor {
    private double totalCost = 0;

    public void visit(RoomBooking roomBooking) {
        System.out.println("Calculating cost for room: " + roomBooking.getRoomType() +
            ", Nights: " + roomBooking.getNights() +
            ", Per night: " + roomBooking.getPricePerNight() +
            ", Total: " + roomBooking.getTotalPrice());
        totalCost += roomBooking.getTotalPrice();
    }

    public void visit(ServiceBooking serviceBooking) {
        System.out.println("Calculating cost for service: " + serviceBooking.getServiceName() +
            ", Price: " + serviceBooking.getPrice());
        totalCost += serviceBooking.getPrice();
    }

    public double getTotalCost() { return totalCost; }
}

class BookingPrintVisitor implements BookingVisitor {
    public void visit(RoomBooking roomBooking) {
        System.out.println("Room booked: " + roomBooking.getRoomType() + " for " + roomBooking.getNights() + " nights.");
    }

    public void visit(ServiceBooking serviceBooking) {
        System.out.println("Additional service booked: " + serviceBooking.getServiceName() +
            ", Price: " + serviceBooking.getPrice());
    }
}
```

#### 5. Client

Creates bookings and applies visitors for separate concerns like printing and cost calculation.

```java
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

        System.out.println("Booking Details:");
        for (BookingElement booking : bookings) {
            booking.accept(printVisitor);
        }

        for (BookingElement booking : bookings) {
            booking.accept(costVisitor);
        }

        System.out.println("Total booking cost: $" + costVisitor.getTotalCost());
    }
}
```

### 🌟 Why Visitor Pattern?

- Separates unrelated operations from object structure.
- Adds new operations without changing existing classes.
- Supports double dispatch — operation varies by visitor and element types.
- Facilitates adding features like cost calculation, printing, logging easily.

### 🚗 Real-World Analogy

A hotel manager (visitor) checks each booking type and performs distinct operations such as printing details or calculating costs without modifying the bookings themselves.

This explanation uses your hotel booking example to clearly illustrate the Visitor pattern’s roles, responsibilities, and usage.