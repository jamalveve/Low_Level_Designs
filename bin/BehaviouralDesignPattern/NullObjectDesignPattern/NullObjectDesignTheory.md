## 🛑 Null Object Design Pattern in Java

The **Null Object Design Pattern** is a behavioral design pattern that provides an object as a surrogate for the absence of an object of a given type. Instead of using `null` references and adding checks everywhere, a special Null Object handles the default or "do nothing" behavior gracefully.

### ⚡ What’s Happening?

Instead of returning `null` when an object is not found or unavailable, the pattern returns a **Null Object** that implements the same interface but provides default or harmless behavior. This prevents `NullPointerException`s and simplifies client code by eliminating explicit null checks.

### 🛠️ Components

#### 1. Abstract or Interface Type

Defines the contract that both real and null objects implement.

```java
abstract class AbstractCustomer {
    protected String name;
    public abstract String getName();
    public abstract boolean isNil();
}
```

or

```java
interface Student {
    String getName();
    int getGrade();
    boolean isNull();
}
```

#### 2. Real Object

Implements the normal behavior and holds real data.

```java
class RealCustomer extends AbstractCustomer {
    public RealCustomer(String name) { this.name = name; }
    public String getName() { return name; }
    public boolean isNil() { return false; }
}
```

```java
class RealStudent implements Student {
    private String name;
    private int grade;

    public RealStudent(String name, int grade) { this.name = name; this.grade = grade; }
    public String getName() { return name; }
    public int getGrade() { return grade; }
    public boolean isNull() { return false; }
}
```

#### 3. Null Object

Implements the interface but provides safe defaults indicating absence.

```java
class NullCustomer extends AbstractCustomer {
    public String getName() { return "Not Available in Customer Database"; }
    public boolean isNil() { return true; }
}
```

```java
class NullStudent implements Student {
    public String getName() { return "No Student Found"; }
    public int getGrade() { return -1; }
    public boolean isNull() { return true; }
}
```

#### 4. Factory or Retrieval Method

Decides whether to return a real object or the null object based on data presence.

```java
class CustomerFactory {
    private static final String[] names = { "John", "Jane", "Alice" };

    public static AbstractCustomer getCustomer(String name) {
        for (String n : names) {
            if (n.equalsIgnoreCase(name)) return new RealCustomer(name);
        }
        return new NullCustomer();
    }
}
```

```java
public class StudentManagementSystem {
    private static final Map studentDatabase = new HashMap<>();

    static {
        studentDatabase.put("kamal", 85);
        studentDatabase.put("sri", 90);
        studentDatabase.put("john", 78);
    }

    public static Student getStudent(String name) {
        if (studentDatabase.containsKey(name)) {
            return new RealStudent(name, studentDatabase.get(name));
        }
        return new NullStudent();
    }
}
```

### 💡 How It Works (with Customer example):

```java
public class CustomerData {
    public static void main(String[] args) {
        AbstractCustomer customer1 = CustomerFactory.getCustomer("Alice");
        AbstractCustomer customer2 = CustomerFactory.getCustomer("Bob");

        System.out.println(customer1.getName()); // Output: Alice
        System.out.println(customer2.getName()); // Output: Not Available in Customer Database
    }
}
```

- If a requested object exists, you get a real object.
- If it does not, you get a Null Object which safely handles calls.
- Client code doesn't need to do null checking or handle exceptions.

### 🌟 Why Null Object Pattern?

- **Eliminates Null Checks:** Avoids scattered `if (obj != null)` checks.
- **Prevents Null Pointer Exceptions:** Always returns a valid object.
- **Simplifies Client Code:** Makes code cleaner and easier to maintain.
- **Adheres to Polymorphism:** Null objects behave like real objects but with default behavior.

### 🚗 Real-World Analogy

Imagine a restaurant where every table has a waiter assigned. If no waiter is around, a “Null Waiter” silently attends without interrupting service—clients don't have to check if a waiter exists to place an order.

**Source:** Inspired by [Null Object Design Pattern examples](https://github.com/jamalveve/LLD_HLD/tree/main/src/NullObjectDesignPattern).