## 🏊 Object Pool Design Pattern in Java

The **Object Pool Design Pattern** is a creational pattern that manages a pool of reusable objects. It optimizes resource usage by reusing objects that are expensive to create instead of creating new ones every time an object is requested.

### ⚡ What’s Happening?

An object pool maintains a set of initialized objects ready to use. When a client requests an object, it is provided from the pool if available; otherwise, a new object may be created (up to a maximum limit). Once the client finishes using the object, it returns it back to the pool for future reuse. This reduces costly object creation and garbage collection.

### 🛠️ Components

#### 1. Pooled Object

The resource-intensive object managed by the pool.

```java
class DatabaseConnection {
    private static int counter = 0;
    private int id;

    public DatabaseConnection() {
        id = ++counter;
        System.out.println("Creating new connection with id: " + id);
    }

    public void connect() {
        System.out.println("Connection " + id + " connected.");
    }

    public void disconnect() {
        System.out.println("Connection " + id + " disconnected.");
    }

    public int getId() {
        return id;
    }
}
```

#### 2. Object Pool Manager

Maintains available and used objects, controls acquisition and release.

```java
class ConnectionPool {
    private List availableConnections;
    private List usedConnections = new ArrayList<>();
    private static final int MAX_CONNECTIONS = 3;

    public ConnectionPool(List pool) {
        this.availableConnections = pool;
    }

    public synchronized DatabaseConnection getConnection() {
        if (availableConnections.isEmpty()) {
            if (usedConnections.size()  initialPool = new ArrayList<>();
        initialPool.add(new DatabaseConnection());
        initialPool.add(new DatabaseConnection());

        ConnectionPool pool = new ConnectionPool(initialPool);

        DatabaseConnection c1 = pool.getConnection();
        c1.connect();

        DatabaseConnection c2 = pool.getConnection();
        c2.connect();

        System.out.println("Available connections after getting 2: " + pool.getAvailableConnectionsCount());

        pool.releaseConnection(c1);
        System.out.println("Available connections after releasing 1: " + pool.getAvailableConnectionsCount());

        DatabaseConnection c3 = pool.getConnection();
        c3.connect();

        System.out.println("Available connections at end: " + pool.getAvailableConnectionsCount());
    }
}
```

### Generic Object Pool Example

Shows how a pool can manage generic objects with simple acquire/release methods.

```java
class ObjectPool {
    private static final int POOL_SIZE = 2;
    private static List pool = new ArrayList<>(POOL_SIZE);

    static {
        for (int i = 0; i  0) {
            return pool.remove(0);
        } else {
            return new Object();
        }
    }

    public static synchronized void releaseObject(Object obj) {
        pool.add(obj);
    }
}
```

Usage:

```java
public class SimpleGenericObject {
    public static void main(String[] args) {
        Object obj1 = ObjectPool.getObject();
        Object obj2 = ObjectPool.getObject();
        System.out.println("Object 1: " + obj1);
        System.out.println("Object 2: " + obj2);

        ObjectPool.releaseObject(obj1);
        ObjectPool.releaseObject(obj2);

        Object obj3 = ObjectPool.getObject();
        Object obj4 = ObjectPool.getObject();
        System.out.println("Object 3: " + obj3);
        System.out.println("Object 4: " + obj4);
    }
}
```

### 🌟 Why Object Pool Pattern?

- Improves performance by reusing expensive-to-create objects.
- Manages limited resources efficiently.
- Controls resource usage and prevents resource exhaustion.
- Simplifies client code by encapsulating object lifecycle management.

### 🚗 Real-World Analogy

A rental car agency maintains a fleet of cars (objects). Customers rent cars if available and return them for others. New cars are purchased only if demand exceeds supply.

This explanation aligns with your requested style, clarifying how the Object Pool pattern manages resource reuse efficiently with clear code references.