
## 🏭 Factory Design Pattern in Java

The **Factory Design Pattern** is a creational design pattern that provides an interface for creating objects in a superclass but allows subclasses to alter the type of objects that will be created. 
It encapsulates the object creation logic, hiding the instantiation details from the client.

### ⚡ What’s Happening?

The Factory Pattern delegates the responsibility of object instantiation to a separate class called the Factory. The client requests an object by specifying a type or criteria, and the factory decides which concrete class to instantiate and return. This promotes loose coupling between client code and concrete implementations.

### 🛠️ Components

#### 1. Product Interface

Defines the common interface for the objects created by the factory.

```java
interface BankAccount {
    void registerAccount();
}
```

Or in the notification example:

```java
interface Notification {
    void notifyUser();
}
```

#### 2. Concrete Products

Classes that implement the product interface with specific behaviors.

```java
class PersonalAccount implements BankAccount {
    public void registerAccount() {
        System.out.println("Creating a personal account");
    }
}

class BusinessAccount implements BankAccount {
    public void registerAccount() {
        System.out.println("Creating a business account");
    }
}

class CheckingAccount implements BankAccount {
    public void registerAccount() {
        System.out.println("Creating a checking account");
    }
}
```

Similarly, for notifications:

```java
class EmailNotification implements Notification {
    public void notifyUser() {
        System.out.println("Sending an Email Notification");
    }
}
```

#### 3. Factory Class

Contains a method that returns instances of concrete products based on input parameters or logic.

```java
class BankAccountFactory {
    public BankAccount createAccount(String accountType) {
        if (accountType == null || accountType.isEmpty())
            return null;

        switch (accountType.toUpperCase()) {
            case "P": return new PersonalAccount();
            case "B": return new BusinessAccount();
            case "C": return new CheckingAccount();
            default: throw new IllegalArgumentException("Unknown account type " + accountType);
        }
    }
}
```

Or for notifications:

```java
class NotificationFactory {
    public Notification createNotification(String type) {
        if (type == null || type.isEmpty())
            return null;

        switch (type.toLowerCase()) {
            case "sms": return new SMSNotification();
            case "email": return new EmailNotification();
            case "push": return new PushNotification();
            default: throw new IllegalArgumentException("Unknown notification type " + type);
        }
    }
}
```

#### 4. Client Code

Client does not instantiate concrete classes directly. It uses the factory to get product objects.

```java
BankAccountFactory factory = new BankAccountFactory();
BankAccount account = factory.createAccount("P");
account.registerAccount(); // Output: Creating a personal account
```

Similarly:

```java
NotificationFactory factory = new NotificationFactory();
Notification notification = factory.createNotification("email");
notification.notifyUser(); // Output: Sending an Email Notification
```

### 🌟 Why Factory Pattern?

- **Encapsulates Object Creation:** The client doesn't need to know the details of object creation.
- **Open/Closed Principle:** Easy to add new product types without modifying existing client code.
- **Promotes Loose Coupling:** The client depends on abstractions, not concrete implementations.
- **Centralizes Control:** Object creation logic is centralized and can be managed or changed easily.

### 🚗 Real-World Analogy

Ordering a product from a vending machine— you just press a button (specify type), and the machine (factory) delivers the correct item. You don’t need to know how or which components are involved inside.

**Source** :[https://github.com/jamalveve/LLD_HLD/tree/main/src/Factory/Design/Pattern]