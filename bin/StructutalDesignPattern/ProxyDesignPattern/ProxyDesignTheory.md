## 🛡️ Proxy Design Pattern in Java

The **Proxy Design Pattern** is a structural pattern that provides a surrogate or placeholder for another object to control access to it. It helps add functionality like lazy loading, caching, or access control without changing the original object’s code.

### ⚡ What’s Happening?

A proxy object implements the same interface as the real subject and controls access to it. The client interacts with the proxy, which delegates to the real subject but can add extra behavior (e.g., caching results, delaying expensive creation). This decouples the client from the complexity of direct access or object lifecycle management.

### 🛠️ Components

#### 1. Subject Interface

Defines methods that both the real subject and proxy implement.

```java
interface UserService {
    List getUsers(String country);
    int getAccessCount();
}
```

Or

```java
interface Image {
    void display();
}
```

#### 2. Real Subject

The original object that contains the core business logic.

```java
class UserServiceImpl implements UserService {
    private final Map> users = Map.of(
        "us", List.of("user1", "user2"),
        "en", List.of("user3", "user4", "user5"));
    private int count = 0;

    public List getUsers(String country) {
        count++;
        return users.get(country);
    }

    public int getAccessCount() {
        return count;
    }
}
```

```java
class RealImage implements Image {
    private String filename;
    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromDisk();
    }
    private void loadImageFromDisk() {
        System.out.println("Loading image: " + filename);
    }
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}
```

#### 3. Proxy

Implements the subject interface and adds extra behavior like caching or lazy loading before forwarding calls.

```java
class CachingUserServiceProxy implements UserService {
    private final UserService userService;
    private final ConcurrentMap> cache = new ConcurrentHashMap<>();
    private final Object writeLock = new Object();

    public CachingUserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    public List getUsers(String country) {
        if (!cache.containsKey(country)) {
            synchronized (writeLock) {
                if (!cache.containsKey(country)) {
                    List users = userService.getUsers(country);
                    cache.put(country, users);
                }
            }
        }
        return cache.get(country);
    }

    public int getAccessCount() {
        return userService.getAccessCount();
    }
}
```

```java
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename); // lazy load
        }
        realImage.display();
    }
}
```

#### 4. Client

Interacts with the proxy like the real subject but benefits from enhanced control.

```java
public class Cacheing {
    public static void main(String[] args) {
        UserService cachingProxy = new CachingUserServiceProxy(new UserServiceImpl());

        System.out.println("US Users (1st Fetch): " + cachingProxy.getUsers("us")); // real fetch
        System.out.println("US Users (2nd Fetch): " + cachingProxy.getUsers("us")); // cached
        System.out.println("EN Users (1st Fetch): " + cachingProxy.getUsers("en")); // real fetch
        System.out.println("EN Users (2nd Fetch): " + cachingProxy.getUsers("en")); // cached

        System.out.println("Access count: " + cachingProxy.getAccessCount());
    }
}
```

```java
public class LazyLoadingImage {
    public static void main(String[] args) {
        Image image = new ProxyImage("example.jpg");

        image.display(); // loads and displays image
        image.display(); // displays cached image, no load
    }
}
```

### 🌟 Why Proxy Pattern?

- Controls access to sensitive or resource-intensive objects.
- Supports lazy initialization and caching.
- Adds a level of abstraction, improving security and performance.
- Keeps client code simple by mimicking the real subject’s interface.

### 🚗 Real-World Analogy

Like a receptionist (proxy) managing appointments for a busy professional (real subject). The receptionist decides when and how to pass information, shielding the professional from unnecessary interruptions.

This explanation uses your examples to clearly show the Proxy pattern’s purpose, roles, and workflow in Java.