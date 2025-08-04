

<h2>🚦 Strategy Design Pattern in Java <h2>


### ⚡ What’s Happening?
The **Strategy Pattern** lets you define a family of algorithms, put each in its own class, and make them interchangeable at runtime. 
This allows a client (or context) object to change its behavior dynamically, without altering its own code
In this code, the “driving” behavior is separated, so each vehicle can have its own driving logic—without changing the core vehicle code.

### 🛠️ Components

- **Strategy Interface (`Drive`)**  
  Common interface — every driving behavior implements this:
  ```java
  public interface Drive {
      void drive();
  }
  ```

- **Concrete Strategies**  
  Algorithms/behaviors as standalone classes:
  ```java
  class SportsDriveStrategy implements Drive {
      public void drive() {
          System.out.println("own sports driving capability");
      }
  }
  class LoadDriveStrategy implements Drive {
      public void drive() {
          System.out.println("own Load driving capability");
      }
  }
  class PassengerDriveStrategy implements Drive {
      public void drive() {
          System.out.println("normal drive capability");
      }
  }
  ```

- **Context (`Vehicle1`)**  
  The object that uses a strategy:
  ```java
  class Vehicle1 {
      Drive driveObj;
      Vehicle1(Drive driveObj) {
          this.driveObj = driveObj;
      }
      public void drive() {
          driveObj.drive();
      }
  }
  ```

- **Vehicle Types (Concrete Contexts)**
  ```java
  class Goodsvehicle1 extends Vehicle1 {
      Goodsvehicle1() { super(new LoadDriveStrategy()); }
  }
  class RaceVehicle1 extends Vehicle1 {
      RaceVehicle1() { super(new SportsDriveStrategy()); }
  }
  class PassengerVehicle1 extends Vehicle1 {
      PassengerVehicle1() { super(new PassengerDriveStrategy()); }
  }
  ```

- **Usage Example**
  ```java
  public class WithStrategyDesignPattern {
      public static void main(String[] args) {
          Vehicle1 obj1 = new Goodsvehicle1();
          obj1.drive();
          Vehicle1 obj2 = new RaceVehicle1();
          obj2.drive();
          Vehicle1 obj3 = new PassengerVehicle1();
          obj3.drive();
      }
  }
  ```

### 🌟 Why Strategy Pattern?
- **Flexible:** Change an object’s behavior at runtime  
- **Reusable:** Keep behavior code separate and modular  
- **Extensible:** Add new behaviors without touching old code  
- **Cleaner OOP:** Follows “composition over inheritance”

### 🚗 Real-World Analogy
Choose your vehicle. Now, swap out how it drives—off-road, sports, or passenger—without touching vehicle internals. That’s Strategy Design Pattern in action!

Source:[https://github.com/jamalveve/LLD_HLD/tree/main/src/Strategy/design/pattern]
