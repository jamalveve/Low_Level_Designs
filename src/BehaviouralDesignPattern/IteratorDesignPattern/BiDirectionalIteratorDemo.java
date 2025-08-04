package BehaviouralDesignPattern.IteratorDesignPattern;

// Iterator Interface with bidirectional capabilities
interface BidirectionalIterator<T> {
    boolean hasNext();

    T next();

    boolean hasPrevious();

    T previous();
}

// Custom collection class
class CustomList<T> {
    private T[] items;
    private int size;

    @SuppressWarnings("unchecked")
    public CustomList(int capacity) {
        items = (T[]) new Object[capacity];
        size = 0;
    }

    public void add(T item) {
        if (size < items.length) {
            items[size++] = item;
        }
    }

    public BidirectionalIterator<T> getIterator() {
        return new CustomListIterator();
    }

    // Concrete iterator class
    private class CustomListIterator implements BidirectionalIterator<T> {
        private int currentIndex = 0;

        public boolean hasNext() {
            return currentIndex < size;
        }

        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return items[currentIndex++];
        }

        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            return items[--currentIndex];
        }
    }
}

// Client
public class BiDirectionalIteratorDemo {
    public static void main(String[] args) {
        CustomList<String> list = new CustomList<>(5);
        list.add("A");
        list.add("B");
        list.add("C");

        BidirectionalIterator<String> iterator = list.getIterator();

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
