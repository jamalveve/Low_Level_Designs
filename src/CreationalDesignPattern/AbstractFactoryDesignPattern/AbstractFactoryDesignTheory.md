

## 🏭 Abstract Factory Design Pattern in Java

The **Abstract Factory Design Pattern** is a creational design pattern that provides an interface for creating families of related or dependent objects without specifying their concrete classes. It promotes consistency among products of the same family and decouples client code from concrete implementations.

### ⚡ What’s Happening?

The Abstract Factory Pattern defines an interface (factory) with methods for creating various related products. Concrete factories implement this interface to create specific variants (families) of those products. The client uses the abstract factory interface to get product objects from the chosen concrete factory, enabling it to work with any product family transparently.

### 🛠️ Components

#### 1. Abstract Product Interfaces

Define interfaces for each kind of product to be created.

```java
interface Chair {
    void sitOn();
}
interface Sofa {
    void lieOn();
}
```

Or in GUI example:

```java
interface Button {
    void paint();
}
interface Checkbox {
    void paint();
}
```

#### 2. Concrete Products

Implement the product interfaces specific to a particular family or variant.

```java
class ModernChair implements Chair {
    public void sitOn() {
        System.out.println("Sitting on a modern chair");
    }
}
class VictorianChair implements Chair {
    public void sitOn() {
        System.out.println("Sitting on an antique Victorian chair");
    }
}
```

Similarly, concrete buttons and checkboxes for Windows and MacOS are implemented.

#### 3. Abstract Factory Interface

Declares creation methods for each product type.

```java
interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
}
```

Or GUIFactory in the graphical user interface example:

```java
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
```

#### 4. Concrete Factories

Implement the abstract factory interface to create concrete product objects belonging to a single family.

```java
class ModernFurnitureFactory implements FurnitureFactory {
    public Chair createChair() { return new ModernChair(); }
    public Sofa createSofa() { return new ModernSofa(); }
}

class VictorianFurnitureFactory implements FurnitureFactory {
    public Chair createChair() { return new VictorianChair(); }
    public Sofa createSofa() { return new VictorianSofa(); }
}
```

For GUI:

```java
class WindowsFactory implements GUIFactory {
    public Button createButton() { return new WindowsButton(); }
    public Checkbox createCheckbox() { return new WindowsCheckbox(); }
}
class MacOSFactory implements GUIFactory {
    public Button createButton() { return new MacOSButton(); }
    public Checkbox createCheckbox() { return new MacOSCheckbox(); }
}
```

#### 5. Client

Uses a factory object to get instances of products without knowing their concrete classes.

```java
public class FurnitureClient {
    private Chair chair;
    private Sofa sofa;

    public FurnitureClient(FurnitureFactory factory) {
        chair = factory.createChair();
        sofa = factory.createSofa();
    }

    public void useFurniture() {
        chair.sitOn();
        sofa.lieOn();
    }
}
```

Or in GUI example:

```java
public class GraphicApplication {
    private Button button;
    private Checkbox checkbox;

    public GraphicApplication(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}
```

### 🌟 Why Abstract Factory Pattern?

- **Family Consistency:** Ensures that products from the same family are used together.
- **Decouples Client from Concrete Classes:** Client code interacts only with abstract interfaces and factories.
- **Scalable and Extensible:** Easy to add new product families without changing existing client code.
- **Promotes Single Responsibility:** Factory classes focus on creating particular product families.

**Source** :[https://github.com/jamalveve/LLD_HLD/tree/main/src/AbstractFactory/Design/Pattern]
### 🚗 Real-World Analogy

Furniture stores offering complete matching sets (chairs, sofas), or operating systems providing native GUI components (buttons, checkboxes), ensure style and behavior consistency by grouping related products into families.

This explanation showcases how the Abstract Factory Design Pattern works structurally and conceptually, following your clear and example-driven style.
