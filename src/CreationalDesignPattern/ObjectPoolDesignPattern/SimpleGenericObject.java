package CreationalDesignPattern.ObjectPoolDesignPattern;

import java.util.ArrayList;
import java.util.List;

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

// Simple Object Pool class managing generic objects
class ObjectPool {
    private static final int POOL_SIZE = 2;
    private static List<Object> pool = new ArrayList<>(POOL_SIZE);

    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.add(new Object());
        }
    }

    // Get an object from the pool or create new if pool empty
    public static synchronized Object getObject() {
        if (pool.size() > 0) {
            return pool.remove(0);
        } else {
            return new Object();
        }
    }

    // Return an object back to the pool
    public static synchronized void releaseObject(Object obj) {
        pool.add(obj);
    }
}
