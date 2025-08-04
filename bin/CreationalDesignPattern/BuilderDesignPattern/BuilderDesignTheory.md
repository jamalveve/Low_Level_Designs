## 🏗️ Builder Design Pattern in Java

The **Builder Design Pattern** is a creational pattern that provides a flexible solution to construct complex objects step-by-step. Instead of using constructors with many parameters, the Builder pattern uses a separate builder object that allows you to set optional fields in a readable, chained manner and then build the final immutable object.

### ⚡ What’s Happening?

You create a **Builder** class inside or alongside the product class. This builder has methods to set optional attributes and builds the immutable product. Mandatory attributes are passed to the builder constructor. The client configures the builder through method chaining and calls `build()` to get the final object.

### 🛠️ Components

#### 1. Product Class

The class representing the complex object. It is immutable and constructed only via the builder.

```java
class Computer {
    private String cpu;            // mandatory
    private String ram;            // mandatory
    private String storage;        // optional
    private String graphicsCard;   // optional

    private Computer(ComputerBuilder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", RAM=" + ram + ", Storage=" + storage + ", GraphicsCard=" + graphicsCard + "]";
    }
}
```

#### 2. Builder Class

Nested or external class responsible for incremental construction. Provides setters for optional fields, returns itself for chaining, and a `build()` method to create the product.

```java
public static class ComputerBuilder {
    private final String cpu;
    private final String ram;
    private String storage;
    private String graphicsCard;

    public ComputerBuilder(String cpu, String ram) {
        this.cpu = cpu;
        this.ram = ram;
    }

    public ComputerBuilder storage(String storage) {
        this.storage = storage;
        return this;
    }

    public ComputerBuilder graphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
        return this;
    }

    public Computer build() {
        return new Computer(this);
    }
}
```

#### 3. Client Usage

Client constructs the object by creating the builder, chaining optional configurations, and finally calling `build()`.

```java
public class ComputerBuilderDemo {
    public static void main(String[] args) {
        Computer gamingPC = new Computer.ComputerBuilder("Intel i9", "32GB")
                .storage("1TB SSD")
                .graphicsCard("NVIDIA RTX 3090")
                .build();

        Computer officePC = new Computer.ComputerBuilder("Intel i5", "16GB").build();

        System.out.println(gamingPC);
        System.out.println(officePC);
    }
}
```

### 💡 How It Works (with User example):

```java
class User {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String phone;
    private final String address;

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    public static class UserBuilder {
        private final String firstName;
        private final String lastName;

        private int age;
        private String phone;
        private String address;

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

public class UserBuilderDemo {
    public static void main(String[] args) {
        User user1 = new User.UserBuilder("John", "Doe")
                .age(30)
                .phone("1234567890")
                .address("123 Main Street")
                .build();

        User user2 = new User.UserBuilder("Jane", "Smith").build();

        System.out.println(user1);
        System.out.println(user2);
    }
}
```

### 🌟 Why Builder Pattern?

- Manages complex object creation with many optional parameters.
- Avoids constructor overload or telescoping constructor anti-pattern.
- Supports immutability by building fully initialized objects.
- Provides readable, chainable APIs for object configuration.

### 🚗 Real-World Analogy

Ordering a custom pizza where you specify size and toppings step-by-step, then place your order once finalized — the builder accumulates choices and creates the pizza in the end.

This explanation mirrors your previous detailed style with clear roles, code examples, and usage for the Builder Design Pattern.