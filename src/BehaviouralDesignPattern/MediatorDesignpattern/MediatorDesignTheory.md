## 🛩️ Mediator Design Pattern in Java

The **Mediator Design Pattern** is a behavioral design pattern that defines an object that encapsulates how a set of objects interact. This pattern promotes loose coupling by preventing objects from referring to each other explicitly, allowing their interaction to be varied independently.

### ⚡ What’s Happening?

Instead of objects referring and communicating with each other directly, they communicate via a *mediator* object. The mediator centralizes communication and control logic between objects, simplifying object interconnections and dependencies.

### 🛠️ Components

#### 1. Mediator Interface

Defines methods for communication between colleague objects.

```java
interface AirTrafficControl {
    void requestLanding(Airplane airplane);
    void requestTakeoff(Airplane airplane);
}
```

In chat example:

```java
interface ChatMediator1 {
    void sendMessage(String msg, User1 user);
    void addUser(User1 user);
}
```

#### 2. Concrete Mediator

Implements mediator interface and coordinates communication between colleagues.

```java
class AirportControlTower implements AirTrafficControl {
    public void requestLanding(Airplane airplane) {
        System.out.println("Control Tower grants landing permission to " + airplane.getName());
        airplane.land();
    }
    public void requestTakeoff(Airplane airplane) {
        System.out.println("Control Tower grants takeoff permission to " + airplane.getName());
        airplane.takeOff();
    }
}
```

Chat example:

```java
class ChatMediatorImpl implements ChatMediator1 {
    private List users = new ArrayList<>();

    public void addUser(User1 user) { users.add(user); }

    public void sendMessage(String msg, User1 sender) {
        for (User1 u : users) {
            if (u != sender) { u.receive(msg); }
        }
    }
}
```

#### 3. Colleague Interface / Abstract Class

Defines interface for communication through the mediator.

```java
interface Airplane {
    void land();
    void takeOff();
    String getName();
}
```

Chat example:

```java
abstract class User1 {
    protected ChatMediator1 mediator;
    protected String name;
    public User1(ChatMediator1 mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }
    public abstract void send(String msg);
    public abstract void receive(String msg);
}
```

#### 4. Concrete Colleagues

Implements colleague interface and communicates via the mediator.

```java
class FighterJet implements Airplane {
    private AirTrafficControl controlTower;
    private String name;

    public FighterJet(AirTrafficControl controlTower, String name) {
        this.controlTower = controlTower;
        this.name = name;
    }

    public String getName() { return name; }

    public void land() { System.out.println(name + " is landing."); }

    public void takeOff() { System.out.println(name + " is taking off."); }

    public void requestLanding() { controlTower.requestLanding(this); }
    public void requestTakeoff() { controlTower.requestTakeoff(this); }
}
```

Chat example:

```java
class ChatUser extends User1 {
    public ChatUser(ChatMediator1 mediator, String name) {
        super(mediator, name);
    }
    public void send(String msg) {
        System.out.println(this.name + ": Sending Message=" + msg);
        mediator.sendMessage(msg, this);
    }
    public void receive(String msg) {
        System.out.println(this.name + ": Received Message:" + msg);
    }
}
```

#### 5. Client

Sets up mediator and colleagues, then initiates communication.

```java
public class TrafficControlMediator {
    public static void main(String[] args) {
        AirTrafficControl controlTower = new AirportControlTower();

        FighterJet jet1 = new FighterJet(controlTower, "Jet 1");
        FighterJet jet2 = new FighterJet(controlTower, "Jet 2");

        jet1.requestLanding();
        jet2.requestTakeoff();
    }
}
```

Chat example:

```java
public class MultiUsersChatMediator {
    public static void main(String[] args) {
        ChatMediator1 mediator = new ChatMediatorImpl();

        User1 robert = new ChatUser(mediator, "Robert");
        User1 john = new ChatUser(mediator, "John");
        User1 alice = new ChatUser(mediator, "Alice");

        mediator.addUser(robert);
        mediator.addUser(john);
        mediator.addUser(alice);

        robert.send("Hello Everyone!");
        john.send("Hi Robert!");
    }
}
```

### 🌟 Why Mediator Pattern?

- Reduces complex dependencies between communicating objects.
- Centralizes control logic for easier maintenance and modification.
- Promotes loose coupling and reusability of components.
- Simplifies communications when many objects are involved.

### 🚗 Real-World Analogy

An airport control tower coordinates the landing and takeoff of different airplanes — pilots don’t coordinate with each other directly but communicate with the tower. Similarly, a chat room mediator forwards messages between users without them needing to connect directly.

This explanation provides a complete, example-driven understanding of the Mediator Design Pattern.