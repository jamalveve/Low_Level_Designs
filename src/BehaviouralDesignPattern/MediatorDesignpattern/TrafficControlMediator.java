package BehaviouralDesignPattern.MediatorDesignpattern;

// Mediator interface
interface AirTrafficControl {
    void requestLanding(Airplane airplane);

    void requestTakeoff(Airplane airplane);
}

// Concrete Mediator
class AirportControlTower implements AirTrafficControl {
    @Override
    public void requestLanding(Airplane airplane) {
        System.out.println("Control Tower grants landing permission to " + airplane.getName());
        airplane.land();
    }

    @Override
    public void requestTakeoff(Airplane airplane) {
        System.out.println("Control Tower grants takeoff permission to " + airplane.getName());
        airplane.takeOff();
    }
}

// Colleague interface
interface Airplane {
    void land();

    void takeOff();

    String getName();
}

// Concrete Colleague
class FighterJet implements Airplane {
    private AirTrafficControl controlTower;
    private String name;

    public FighterJet(AirTrafficControl controlTower, String name) {
        this.controlTower = controlTower;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void land() {
        System.out.println(name + " is landing.");
    }

    public void takeOff() {
        System.out.println(name + " is taking off.");
    }

    public void requestLanding() {
        controlTower.requestLanding(this);
    }

    public void requestTakeoff() {
        controlTower.requestTakeoff(this);
    }
}

// Client
public class TrafficControlMediator {
    public static void main(String[] args) {
        AirTrafficControl controlTower = new AirportControlTower();

        FighterJet jet1 = new FighterJet(controlTower, "Jet 1");
        FighterJet jet2 = new FighterJet(controlTower, "Jet 2");

        jet1.requestLanding();
        jet2.requestTakeoff();
    }
}
