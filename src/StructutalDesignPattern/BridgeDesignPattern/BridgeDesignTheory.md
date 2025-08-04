## 🌉 Bridge Design Pattern in Java

The **Bridge Design Pattern** is a structural pattern that decouples an abstraction from its implementation so that the two can vary independently. It helps to separate the interface (abstraction) from the implementation details, allowing flexibility and extensibility on both sides.

### ⚡ What’s Happening?

Instead of a fixed inheritance relationship, the pattern uses composition: the abstraction holds a reference to an implementor interface, and concrete abstractions delegate the work to concrete implementors. This allows you to change implementations at runtime or extend abstraction and implementation hierarchies independently.

### 🛠️ Components

#### 1. Implementor Interface

Defines the interface for implementation classes.

```java
interface Device {
    void turnOn();
    void turnOff();
    void setVolume(int volume);
}
```

#### 2. Concrete Implementors

Provide different implementation details for the interface.

```java
class TV implements Device {
    private int volume = 20;
    public void turnOn() { System.out.println("TV is turned ON"); }
    public void turnOff() { System.out.println("TV is turned OFF"); }
    public void setVolume(int volume) { 
        this.volume = volume;
        System.out.println("TV volume set to " + volume);
    }
}

class Radio implements Device {
    private int volume = 30;
    public void turnOn() { System.out.println("Radio is turned ON"); }
    public void turnOff() { System.out.println("Radio is turned OFF"); }
    public void setVolume(int volume) { 
        this.volume = volume;
        System.out.println("Radio volume set to " + volume);
    }
}
```

#### 3. Abstraction

Defines the interface for clients and maintains a reference to an implementor.

```java
abstract class RemoteControl {
    protected Device device;
    protected RemoteControl(Device device) { this.device = device; }
    public abstract void turnOn();
    public abstract void turnOff();
    public abstract void setVolume(int volume);
}
```

#### 4. Refined Abstraction

Extends the abstraction and delegates work to the implementor. Can add extra features.

```java
class BasicRemoteControl extends RemoteControl {
    public BasicRemoteControl(Device device) {
        super(device);
    }
    public void turnOn() { device.turnOn(); }
    public void turnOff() { device.turnOff(); }
    public void setVolume(int volume) { device.setVolume(volume); }
    public void mute() {
        device.setVolume(0);
        System.out.println("Device muted");
    }
}
```

#### 5. Client Usage

Creates specific implementations and abstraction objects and uses them.

```java
public class RemoteControlSystem {
    public static void main(String[] args) {
        Device tv = new TV();
        RemoteControl remoteForTV = new BasicRemoteControl(tv);
        remoteForTV.turnOn();
        remoteForTV.setVolume(25);
        remoteForTV.mute();
        remoteForTV.turnOff();

        System.out.println();

        Device radio = new Radio();
        RemoteControl remoteForRadio = new BasicRemoteControl(radio);
        remoteForRadio.turnOn();
        remoteForRadio.setVolume(50);
        remoteForRadio.turnOff();
    }
}
```

## 🏭 Another Example: Workshop and Vehicle Bridge

Demonstrates separating vehicle types from their manufacturing processes as independent abstractions and implementations.

```java
interface Workshop {
    void work();
}

class Produce implements Workshop {
    public void work() {
        System.out.print("Produced");
    }
}

class Assemble implements Workshop {
    public void work() {
        System.out.print(" And ");
        System.out.println("Assembled.");
    }
}

abstract class Vehicle {
    protected Workshop workShop1;
    protected Workshop workShop2;

    protected Vehicle(Workshop workShop1, Workshop workShop2) {
        this.workShop1 = workShop1;
        this.workShop2 = workShop2;
    }

    abstract public void manufacture();
}

class Car extends Vehicle {
    public Car(Workshop workShop1, Workshop workShop2) {
        super(workShop1, workShop2);
    }

    public void manufacture() {
        System.out.print("Car ");
        workShop1.work();
        workShop2.work();
    }
}

class Bike extends Vehicle {
    public Bike(Workshop workShop1, Workshop workShop2) {
        super(workShop1, workShop2);
    }

    public void manufacture() {
        System.out.print("Bike ");
        workShop1.work();
        workShop2.work();
    }
}

public class WorkShopSystem {
    public static void main(String[] args) {
        Vehicle car = new Car(new Produce(), new Assemble());
        car.manufacture();

        Vehicle bike = new Bike(new Produce(), new Assemble());
        bike.manufacture();
    }
}
```

### 🌟 Why Bridge Pattern?

- Decouples abstraction and implementation for independent evolution.
- Improves flexibility and extensibility.
- Avoids combinatorial explosion of classes.
- Allows runtime switching of implementations.

### 🚗 Real-World Analogy

A universal remote (abstraction) that can control different devices (TV, Radio) with different protocols (implementations), both evolving independently.

This explanation demonstrates the Bridge pattern’s goal to separate abstraction and implementation with clear code and real-world analogies.