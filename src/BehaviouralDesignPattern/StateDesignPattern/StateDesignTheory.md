## 🎛️ State Design Pattern in Java

The **State Design Pattern** is a behavioral pattern that allows an object to alter its behavior when its internal state changes. The object will appear to change its class dynamically. This pattern helps to manage state-specific behaviors and transitions cleanly by encapsulating states into separate classes.

### ⚡ What’s Happening?

Instead of using lots of conditional statements to change behavior based on state, the State pattern lets you define separate state classes. The context object delegates requests to the current state object, which handles the behavior and can switch the context's state.

### 🛠️ Components

#### 1. State Interface

Declares the behaviors associated with the states.

```java
interface TrafficLightState {
    void showLight(TrafficLightContext context);
}
```

Or for vending machine:

```java
interface VendingMachineState {
    void selectProduct();
    void insertMoney();
    void dispenseProduct();
}
```

#### 2. Concrete State Classes

Implement the interface and define behavior for each state, including transitions to other states.

Traffic Light example:

```java
class RedState implements TrafficLightState {
    public void showLight(TrafficLightContext context) {
        System.out.println("Red light - Stop!");
        context.setState(new GreenState()); // Transition to Green
    }
}

class GreenState implements TrafficLightState {
    public void showLight(TrafficLightContext context) {
        System.out.println("Green light - Go!");
        context.setState(new YellowState()); // Transition to Yellow
    }
}

class YellowState implements TrafficLightState {
    public void showLight(TrafficLightContext context) {
        System.out.println("Yellow light - Slow Down!");
        context.setState(new RedState()); // Transition to Red
    }
}
```

Vending Machine example:

```java
class ReadyState implements VendingMachineState {
    private VendingMachineContext context;
    public ReadyState(VendingMachineContext context) { this.context = context; }
    public void selectProduct() {
        System.out.println("Product selected.");
        context.setState(context.getProductSelectedState());
    }
    public void insertMoney() { System.out.println("Select product before inserting money."); }
    public void dispenseProduct() { System.out.println("Select product first."); }
}
```

#### 3. Context Class

Maintains an instance of a concrete state subclass that defines the current state.

Traffic Light context:

```java
class TrafficLightContext {
    private TrafficLightState currentState;

    public TrafficLightContext() {
        currentState = new RedState(); // Initial state
    }

    public void setState(TrafficLightState state) {
        this.currentState = state;
    }

    public void showLight() {
        currentState.showLight(this);
    }
}
```

Vending Machine context:

```java
class VendingMachineContext {
    private VendingMachineState readyState;
    private VendingMachineState productSelectedState;
    private VendingMachineState moneyInsertedState;

    private VendingMachineState currentState;

    public VendingMachineContext() {
        readyState = new ReadyState(this);
        productSelectedState = new ProductSelectedState(this);
        moneyInsertedState = new MoneyInsertedState(this);
        currentState = readyState; // Initial state
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }
    
    public VendingMachineState getReadyState() { return readyState; }
    public VendingMachineState getProductSelectedState() { return productSelectedState; }
    public VendingMachineState getMoneyInsertedState() { return moneyInsertedState; }

    // Delegate actions to current state
    public void selectProduct() { currentState.selectProduct(); }
    public void insertMoney() { currentState.insertMoney(); }
    public void dispenseProduct() { currentState.dispenseProduct(); }
}
```

### 💡 How It Works (with Traffic Light example):

```java
public class TrafficLight {
    public static void main(String[] args) throws InterruptedException {
        TrafficLightContext trafficLight = new TrafficLightContext();

        for (int i = 0; i < 6; i++) {
            trafficLight.showLight();
            Thread.sleep(1000); // simulate time delay between state changes
        }
    }
}
```

Output sequence:
```
Red light - Stop!
Green light - Go!
Yellow light - Slow Down!
Red light - Stop!
Green light - Go!
Yellow light - Slow Down!
```

### 🌟 Why State Pattern?

- Removes complex conditional logic related to state changes.
- Encapsulates state-specific behaviors and transitions cleanly.
- Makes adding new states or changing state behaviors easy.
- Improves code maintainability and clarity by separating state concerns.

### 🚗 Real-World Analogy

A traffic light changes from Red to Green to Yellow in sequence, with each state fully controlling its behavior and when the next state occurs, much like the State pattern model.

**Source:** Inspired by the given [TrafficLight and VendingMachine State Pattern examples].