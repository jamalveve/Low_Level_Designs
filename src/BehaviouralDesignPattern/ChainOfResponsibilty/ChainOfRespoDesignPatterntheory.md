## 🔗 Chain of Responsibility Design Pattern in Java

The **Chain of Responsibility (CoR)** pattern is a behavioral design pattern that allows a request to be passed along a chain of handlers. Each handler decides either to process the request or to pass it to the next handler in the chain. This enables decoupling between sender and receiver and allows multiple objects to handle a request dynamically.

### ⚡ What’s Happening?

In the Chain of Responsibility pattern, you set up a linked sequence (chain) of handler objects. When a client sends a request, it enters the chain at the first handler and flows down until a handler capable of processing it is found. If none can handle it, the request either ends unprocessed or is handled by a default handler.

### 🛠️ Components

#### 1. Request Object (Optional)

Encapsulates data related to the request.

```java
class Currency {
    private int amount;
    public Currency(int amount) { this.amount = amount; }
    public int getAmount() { return amount; }
}

class VendingRequest {
    private String item;
    private double payment;
    public VendingRequest(String item, double payment) { 
        this.item = item; this.payment = payment; 
    }
    public String getItem() { return item; }
    public double getPayment() { return payment; }
}

class Request {
    private Priority priority;
    public Request(Priority priority) { this.priority = priority; }
    public Priority getPriority() { return priority; }
}
```

#### 2. Handler Interface

Declares methods to set the next handler in the chain and to handle requests.

```java
interface DispenseChain {
    void setNextChain(DispenseChain nextChain);
    void dispense(Currency cur);
}

interface VendingHandler {
    void setNextHandler(VendingHandler nextHandler);
    void handleRequest(VendingRequest request);
}

interface SupportHandler {
    void setNextHandler(SupportHandler nextHandler);
    void handleRequest(Request request);
}
```

#### 3. Concrete Handlers

Implement the interface and override request handling logic. Each decides to process or delegate further.

Example: ATM Dollar 50 Dispenser Handler

```java
class Dollar50Dispenser implements DispenseChain {
    private DispenseChain nextChain;
    public void setNextChain(DispenseChain nextChain) { this.nextChain = nextChain; }
    public void dispense(Currency cur) {
        if (cur.getAmount() >= 50) {
            int count = cur.getAmount() / 50;
            int remainder = cur.getAmount() % 50;
            System.out.println("Dispensing " + count + " 50$ note(s)");
            if (remainder != 0) nextChain.dispense(new Currency(remainder));
        } else {
            nextChain.dispense(cur);
        }
    }
}
```

Example: Vending Machine Handlers (Validation -> Stock Check -> Payment -> Dispense)

Each handler checks a condition and forwards the request if it cannot fully process it.

```java
class ValidationHandler implements VendingHandler {
    private VendingHandler nextHandler;
    public void setNextHandler(VendingHandler nextHandler) { this.nextHandler = nextHandler; }
    public void handleRequest(VendingRequest request) {
        System.out.println("Validation passed for item: " + request.getItem());
        if (nextHandler != null) nextHandler.handleRequest(request);
    }
}
```

#### 4. Chain Setup and Client Request

Handlers are linked in a chain order. Client sends request to the first handler.

```java
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        Dollar50Dispenser c1 = new Dollar50Dispenser();
        DispenseChain c2 = new Dollar20Dispenser();
        DispenseChain c3 = new Dollar10Dispenser();

        c1.setNextChain(c2);
        c2.setNextChain(c3);

        c1.dispense(new Currency(130));

        ValidationHandler validation = new ValidationHandler();
        StockCheckHandler stockCheck = new StockCheckHandler();
        PaymentHandler payment = new PaymentHandler();
        DispenseHandler dispense = new DispenseHandler();

        validation.setNextHandler(stockCheck);
        stockCheck.setNextHandler(payment);
        payment.setNextHandler(dispense);

        validation.handleRequest(new VendingRequest("Soda", 2.00));
    }
}
```

```java
enum Priority { BASIC, INTERMEDIATE, CRITICAL }

class Level1SupportHandler implements SupportHandler {
    private SupportHandler nextHandler;
    public void setNextHandler(SupportHandler nextHandler) { this.nextHandler = nextHandler; }
    public void handleRequest(Request request) {
        if (request.getPriority() == Priority.BASIC) {
            System.out.println("Level 1 Support handled the request.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
```

### 🌟 Why Chain of Responsibility Pattern?

- Decouples sender and receiver: Client does not know which handler will process the request.
- Dynamic Handling: You can add or change handlers at runtime easily.
- Simplifies Responsibilities: Each handler focuses on its specific responsibility.
- Flexible Request Processing: Multiple handlers can process or ignore requests as appropriate.

### 🚗 Real-World Analogy

Think of customer support where requests flow through different support levels: simple queries are handled at Level 1, while complex issues escalate to higher levels transparently to the client.