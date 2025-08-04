package BehaviouralDesignPattern.MediatorDesignpattern;

import java.util.ArrayList;
import java.util.List;

// Mediator interface
interface ChatMediator1 {
    void sendMessage(String msg, User1 user);

    void addUser(User1 user);
}

// Concrete Mediator
class ChatMediatorImpl implements ChatMediator1 {
    private List<User1> users = new ArrayList<>();

    @Override
    public void addUser(User1 user) {
        users.add(user);
    }

    @Override
    public void sendMessage(String msg, User1 sender) {
        for (User1 u : users) {
            // message not received by the sender
            if (u != sender) {
                u.receive(msg);
            }
        }
    }
}

// Colleague abstract class
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

// Concrete Colleague
class ChatUser extends User1 {
    public ChatUser(ChatMediator1 mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String msg) {
        System.out.println(this.name + ": Sending Message=" + msg);
        mediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.name + ": Received Message:" + msg);
    }
}

// Client
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
