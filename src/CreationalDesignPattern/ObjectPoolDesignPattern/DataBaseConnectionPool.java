package CreationalDesignPattern.ObjectPoolDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class DataBaseConnectionPool {
    public static void main(String[] args) {
        // Initialize pool with 2 connections
        List<DatabaseConnection> initialPool = new ArrayList<>();
        initialPool.add(new DatabaseConnection());
        initialPool.add(new DatabaseConnection());

        // Get singleton instance of connection pool
        ConnectionPool pool = ConnectionPool.getInstance(initialPool);

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

// Simulated DatabaseConnection class
class DatabaseConnection {
    private static int counter = 0;
    private int id;

    public DatabaseConnection() {
        this.id = ++counter;
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

// Singleton Connection Pool class
class ConnectionPool {
    private static ConnectionPool instance;

    private List<DatabaseConnection> availableConnections;
    private List<DatabaseConnection> usedConnections = new ArrayList<>();
    private static final int MAX_CONNECTIONS = 3;

    // Private constructor to prevent external instantiation
    private ConnectionPool(List<DatabaseConnection> pool) {
        this.availableConnections = pool;
    }

    // Thread-safe way to obtain singleton instance
    public static synchronized ConnectionPool getInstance(List<DatabaseConnection> pool) {
        if (instance == null) {
            instance = new ConnectionPool(pool);
        }
        return instance;
    }

    public synchronized DatabaseConnection getConnection() {
        if (availableConnections.isEmpty()) {
            if (usedConnections.size() < MAX_CONNECTIONS) {
                DatabaseConnection newConn = new DatabaseConnection();
                usedConnections.add(newConn);
                return newConn;
            }
            throw new RuntimeException("Max connections reached");
        }
        DatabaseConnection conn = availableConnections.remove(availableConnections.size() - 1);
        usedConnections.add(conn);
        return conn;
    }

    public synchronized void releaseConnection(DatabaseConnection connection) {
        usedConnections.remove(connection);
        availableConnections.add(connection);
    }

    public synchronized int getAvailableConnectionsCount() {
        return availableConnections.size();
    }
}
