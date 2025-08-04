
## 🎨 Decorator Design Pattern in Java

The **Decorator Design Pattern** is a structural pattern that lets you dynamically add new responsibilities or features to objects without modifying their existing code. 
Instead of creating subclasses for every combination of features, you wrap the base object in decorator classes that each add a specific functionality.
This approach allows for flexible and scalable code.

### ⚡ What’s Happening?

The Decorator Pattern wraps an original object inside a decorator object that adds new behavior either before or after delegating to the original object. This lets you compose behaviors dynamically, without changing existing code or using inheritance explosion.

### 🛠️ Components

#### 1. Component Interface

Defines the common interface for both the original objects and their decorators.

```java
interface Car {
    void assemble();
}
```

Or in other examples, interfaces like `Coffee` or `Pizza` define common methods.

#### 2. Concrete Component

The default object which can be decorated with additional features.

```java
class BasicCar implements Car {
    public void assemble() {
        System.out.print("Basic Car.");
    }
}
```

SimpleCoffee and PlainPizza are other concrete components.

#### 3. Abstract Decorator

Implements the component interface and has a reference to a component object. It forwards calls to the wrapped object, allowing subclasses to add extra behavior.

```java
abstract class CarDecorator implements Car {
    protected Car decoratedCar;

    CarDecorator(Car c) {
        this.decoratedCar = c;
    }

    public void assemble() {
        decoratedCar.assemble();
    }
}
```

#### 4. Concrete Decorators

Extend the abstract decorator and override methods to add new behaviors.

```java
class SportsCar extends CarDecorator {
    SportsCar(Car c) { super(c); }
    public void assemble() {
        super.assemble();
        System.out.print(" Adding features of Sports Car.");
    }
}
```

Similarly:
- `WithMilk` adds milk to coffee,
- `CheeseDecorator` adds cheese to pizza,
- Each adds new behavior seamlessly.

### 💡 How It Works (with Car example):

```java
public class CarDecorShop {
    public static void main(String[] args) {
        Car sportsCar = new SportsCar(new BasicCar());
        sportsCar.assemble(); // Output: Basic Car. Adding features of Sports Car.

        Car luxuryCar = new LuxuryCar(new BasicCar());
        luxuryCar.assemble(); // Output: Basic Car. Adding features of Luxury Car.

        Car sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
        sportsLuxuryCar.assemble(); 
        // Output: Basic Car. Adding features of Luxury Car. Adding features of Sports Car.
    }
}
```

- Each decorator wraps the car and adds its own behavior on top of existing ones.
- You can add new decorators without modifying existing classes.
- You can combine multiple decorators to compose complex behaviors dynamically.

### 🌟 Why Decorator Pattern?

- **Open/Closed Principle:** Add new behaviors without modifying existing code.
- **Flexible:** Easily combine multiple behaviors at runtime.
- **Avoids Inheritance Explosion:** Instead of creating many subclasses for combinations, decorators compose behaviors.
- **Single Responsibility Principle:** Each decorator class has one responsibility.

### 🚗 Real-World Analogy:

Ordering a coffee at a café — start with simple coffee and add milk, sugar, chocolate, or other options. Each addition decorates the original coffee, adding new features or costs without changing the base coffee recipe.

**Source** :[https://github.com/jamalveve/LLD_HLD/tree/main/src/Decorator/Design/Pattern]