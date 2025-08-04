## 🔍 Iterator Design Pattern in Java

The **Iterator Design Pattern** is a behavioral design pattern that provides a way to access elements of a collection sequentially without exposing its underlying representation. It supports traversing a container’s elements and can be bidirectional or unidirectional.

### ⚡ What’s Happening?

Instead of exposing the collection’s internal structure (like arrays or lists), the pattern uses an iterator object that encapsulates traversal logic. Clients interact only with the iterator, making code simpler and collections replaceable without affecting client code.

### 🛠️ Components

#### 1. Iterator Interface

Defines methods for traversing elements, such as `hasNext()`, `next()`, and optionally `hasPrevious()` and `previous()` for bidirectional iterators.

```java
interface BidirectionalIterator {
    boolean hasNext();
    T next();
    boolean hasPrevious();
    T previous();
}
```

Or a simpler iterator interface:

```java
interface Iterator {
    boolean hasNext();
    Object next();
}
```

#### 2. Container Interface

Declares a method for obtaining an iterator.

```java
interface Container {
    Iterator getIterator();
}
```

#### 3. Concrete Container / Collection

Holds the data elements and implements the method to create an appropriate iterator.

```java
class CustomList {
    private T[] items;
    private int size;
    // constructor and add method omitted for brevity...

    public BidirectionalIterator getIterator() {
        return new CustomListIterator();
    }

    private class CustomListIterator implements BidirectionalIterator {
        private int currentIndex = 0;
        // hasNext, next, hasPrevious, previous implementation...
    }
}
```

Or a simple container:

```java
class NameRepository implements Container {
    public String[] names = { "Robert", "John", "Julie", "Lora" };
    public Iterator getIterator() {
        return new NameIterator();
    }
    private class NameIterator implements Iterator {
        int index = 0;
        public boolean hasNext() { return index  list = new CustomList<>(5);
        list.add("A");
        list.add("B");
        list.add("C");

        BidirectionalIterator iterator = list.getIterator();

        System.out.println("Forward iteration:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("Backward iteration:");
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }
}
```

Or simpler iterator usage:

```java
public class InternalIteratorDemo {
    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();

        Iterator iter = namesRepository.getIterator();
        while (iter.hasNext()) {
            String name = (String) iter.next();
            System.out.println("Name : " + name);
        }
    }
}
```

### 🌟 Why Iterator Pattern?

- Provides uniform traversal interface for different collection types.
- Hides internal representation, promoting encapsulation.
- Supports single-directional and bidirectional iteration.
- Makes the client code independent and reusable.

### 🚗 Real-World Analogy

Like flipping pages in a book sequentially without knowing the book’s binding or page structure — the iterator abstracts that complexity for you.

This explanation and examples match the detailed, example-driven style you requested, clarifying the Iterator pattern’s roles and usage in Java.