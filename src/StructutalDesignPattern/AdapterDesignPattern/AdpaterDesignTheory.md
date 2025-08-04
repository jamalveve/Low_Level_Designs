## 🔌 Adapter Design Pattern in Java

The **Adapter Design Pattern** is a structural pattern that allows incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by wrapping one class with an adapter class that provides the expected interface.

### ⚡ What’s Happening?

When existing classes have incompatible interfaces, an *adapter* class converts the interface of a class into another interface that a client expects. This enables the client to use existing classes without modifying them.

### 🛠️ Components

#### 1. Target Interface

The interface that clients expect and interact with.

```java
interface RowingBoat {
    void row();
}
```

Or:

```java
interface WeightMachineAdapter {
    double getWeightInKg();
}
```

Or:

```java
interface Printer {
    void print();
}
```

#### 2. Adaptee

The existing class with an incompatible interface.

```java
class FishingBoat {
    public void sail() {
        System.out.println("The fishing boat is sailing");
    }
}
```

```java
class WeightMachineImpl implements WeightMachine {
    public double getWeightInPounds() { return 150; }
}
```

```java
class LegacyPrinter {
    public void printDocument() {
        System.out.println("Legacy Printer is printing a document.");
    }
}
```

#### 3. Adapter

Implements the target interface and internally uses an instance of the adaptee, mapping calls accordingly.

```java
class FishingBoatAdapter implements RowingBoat {
    private final FishingBoat boat;

    public FishingBoatAdapter() {
        this.boat = new FishingBoat();
    }

    public void row() {
        boat.sail(); // adapts row() to sail()
    }
}
```

```java
class WeightMachineAdapterImpl implements WeightMachineAdapter {
    private final WeightMachine weightMachine;

    public WeightMachineAdapterImpl(WeightMachine weightMachine) {
        this.weightMachine = weightMachine;
    }

    public double getWeightInKg() {
        return weightMachine.getWeightInPounds() * 0.453592;
    }
}
```

```java
class PrinterAdapter implements Printer {
    private LegacyPrinter legacyPrinter;

    public PrinterAdapter(LegacyPrinter legacyPrinter) {
        this.legacyPrinter = legacyPrinter;
    }

    public void print() {
        legacyPrinter.printDocument();
    }
}
```

#### 4. Client

Interacts with the adapter through the target interface, unaware of the adaptee.

```java
public class FishingScenario {
    public static void main(String[] args) {
        RowingBoat boat = new FishingBoatAdapter();
        Captain captain = new Captain(boat);
        captain.row(); // Outputs: The fishing boat is sailing
    }
}
```

```java
public class WeighingMachine {
    public static void main(String[] args) {
        WeightMachine weightMachine = new WeightMachineImpl();
        WeightMachineAdapter adapter = new WeightMachineAdapterImpl(weightMachine);

        System.out.println("Weight in pounds: " + weightMachine.getWeightInPounds());
        System.out.println("Weight in kilograms (via adapter): " + adapter.getWeightInKg());
    }
}
```

```java
public class PrinterSystem {
    public static void main(String[] args) {
        LegacyPrinter legacyPrinter = new LegacyPrinter();
        Printer printerAdapter = new PrinterAdapter(legacyPrinter);

        printerAdapter.print(); // Output: Legacy Printer is printing a document.
    }
}
```

### 🌟 Why Adapter Pattern?

- Allows reuse of existing incompatible classes without modifying them.
- Decouples client from concrete implementation details.
- Promotes flexibility in using legacy or third-party components.
- Facilitates integration of unrelated interfaces.

### 🚗 Real-World Analogy

A power plug adapter lets your laptop (with a different plug type) connect to a foreign outlet. The adapter converts between incompatible interfaces seamlessly.

