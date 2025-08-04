package BehaviouralDesignPattern.CommandDesignPattern;

// Command interface
interface Command {
    void execute();
}

// Receiver interface
interface Device {
    void turnOn();

    void turnOff();
}

// Concrete receiver: TV
class TV implements Device {
    public void turnOn() {
        System.out.println("TV is now on");
    }

    public void turnOff() {
        System.out.println("TV is now off");
    }

    public void changeChannel() {
        System.out.println("Channel changed");
    }
}

// Concrete receiver: Stereo
class Stereo implements Device {
    public void turnOn() {
        System.out.println("Stereo is now on");
    }

    public void turnOff() {
        System.out.println("Stereo is now off");
    }

    public void adjustVolume() {
        System.out.println("Volume adjusted");
    }
}

// Concrete commands
class TurnOnCommand implements Command {
    private Device device;

    public TurnOnCommand(Device device) {
        this.device = device;
    }

    public void execute() {
        device.turnOn();
    }
}

class TurnOffCommand implements Command {
    private Device device;

    public TurnOffCommand(Device device) {
        this.device = device;
    }

    public void execute() {
        device.turnOff();
    }
}

class ChangeChannelCommand implements Command {
    private TV tv;

    public ChangeChannelCommand(TV tv) {
        this.tv = tv;
    }

    public void execute() {
        tv.changeChannel();
    }
}

class AdjustVolumeCommand implements Command {
    private Stereo stereo;

    public AdjustVolumeCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    public void execute() {
        stereo.adjustVolume();
    }
}

// Invoker
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Client usage
public class RemoteControlCommand {
    public static void main(String[] args) {
        TV tv = new TV();
        Stereo stereo = new Stereo();

        Command turnOnTV = new TurnOnCommand(tv);
        Command turnOffTV = new TurnOffCommand(tv);
        Command changeChannel = new ChangeChannelCommand(tv);
        Command adjustVolume = new AdjustVolumeCommand(stereo);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(turnOnTV);
        remote.pressButton(); // Output: TV is now on

        remote.setCommand(adjustVolume);
        remote.pressButton(); // Output: Volume adjusted

        remote.setCommand(changeChannel);
        remote.pressButton(); // Output: Channel changed

        remote.setCommand(turnOffTV);
        remote.pressButton(); // Output: TV is now off

        remote.setCommand(changeChannel);
        remote.pressButton(); // Output: Channel changed
    }
}
