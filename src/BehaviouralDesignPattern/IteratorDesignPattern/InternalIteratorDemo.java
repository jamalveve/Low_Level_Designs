package BehaviouralDesignPattern.IteratorDesignPattern;

// Iterator interface
interface Iterator {
    boolean hasNext();

    Object next();
}

// Container interface
interface Container {
    Iterator getIterator();
}

// Concrete container that holds names
class NameRepository implements Container {
    public String[] names = { "Robert", "John", "Julie", "Lora" };

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < names.length;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}

// Client
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
