

## 👁️ Observer Design Pattern in Java

The Observer Design Pattern defines a **one-to-many dependency** between objects so that when one object (subject) changes state, all its dependents (observers) are notified and updated automatically. This allows for a loosely coupled interaction between the subject and its observers.

### ⚡ What’s Happening?

In all these examples, an object (called the **Subject**) maintains a list of dependents called **Observers** that want to be notified about changes in the subject’s state. When the subject’s state changes, it **notifies all registered observers** by calling their `update()` methods. This lets observers react accordingly.

### 🛠️ Components

#### 1. Observer Interface

Defines the method(s) that observer classes must implement to receive updates from the subject.

```java
interface Observer {
    void update(...);  // Update with relevant data (e.g., availability, price, temperature)
}
```

#### 2. Subject (Observable)

Maintains a list of observers and provides methods to register, remove, and notify observers.

```java
class Subject {
    private List observers = new ArrayList<>();

    public void addObserver(Observer o) { observers.add(o); }
    public void removeObserver(Observer o) { observers.remove(o); }

    protected void notifyObservers() {
        for (Observer o : observers) {
            o.update(...);  // Pass updated state
        }
    }
}
```

#### 3. Concrete Subjects and Observers

Concrete subjects hold actual state (availability, price, temperature) and notify observers on state changes. Observers implement behavior on receiving updates.

### Example 1: Product Availability Notification

- **Subject:** `ProductNew` maintains availability status.
- **Observers:** `CustomerNew` objects get notified when availability changes.

```java
class ProductNew extends Subject {
    private String availability;
    public void setAvailability(String availability) {
        this.availability = availability;
        notifyObservers();
    }
}

class CustomerNew implements Observer {
    public void update(String availability) {
        System.out.println("Product is now " + availability);
    }
}
```

### Example 2: Product Price Updates

- **Subject:** `Product` contains price.
- **Observers:** `Customer` objects receive price update notifications.

```java
class Product extends Subject {
    private float price;
    public void setPrice(float price) {
         this.price = price;
         notifyObservers();
    }
}

class Customer implements Observer {
    public void update(float newPrice) {
         System.out.println("Product price updated to " + newPrice);
    }
}
```

### Example 3: Weather Station Temperature Updates

- **Subject:** `WeatherStation` with temperature data.
- **Observers:** `PhoneDisplay`, `TVDisplay`, `TabDisplay` that update their display based on temperature changes.

```java
class WeatherStation extends Subject {
    private float temperature;
    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
}

class PhoneDisplay implements Observer {
    public void update(float temperature) {
        System.out.println("Phone Display: Temperature updated - " + temperature);
    }
}
```

### 🌟 Why Observer Pattern?

- **Loose Coupling:** Subjects and observers know only their interfaces, minimizing dependencies.
- **Dynamic Relationships:** Observers can be added/removed at runtime.
- **Broadcast Communication:** One subject notifies several observers automatically.
- **Real-Time Updates:** Keeps multiple parts of an application synchronized.

### 🚗 Real-World Analogy

Just like subscribing to a newsletter: when the newsletter (subject) publishes a new issue (state change), all subscribers (observers) immediately get notified without the newsletter knowing the exact details of each subscriber.

**Source** :[https://github.com/jamalveve/LLD_HLD/tree/main/src/Observer/Design/Pattern]
This Observer pattern structure brings a clean, modular way to handle event-driven designs in Java, similar to your strategy pattern explanation style.
