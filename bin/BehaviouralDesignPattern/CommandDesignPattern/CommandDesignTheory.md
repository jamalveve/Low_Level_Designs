## 🎮 Command Design Pattern in Java

The **Command Design Pattern** is a behavioral design pattern that encapsulates a request as an object, thereby allowing for parameterization of clients with queues, requests, and operations. It decouples the object that invokes the operation from the one that knows how to perform it.

### ⚡ What’s Happening?

The pattern divides the responsibilities among:
- **Command objects** that represent actions,
- **Receiver objects** that perform the actual work,
- **Invoker objects** that initiate the commands without knowing their implementation,
- **Client** that configures commands and invokers.

Commands encapsulate a request as an object to support flexible and extendable command execution.

### 🛠️ Components

#### 1. Command Interface

Declares an execution method to be implemented by all concrete commands.

```java
interface FileCommand {
    void execute();
}
```

Or more generic:

```java
interface Command {
    void execute();
}
```

#### 2. Receiver Interface and Concrete Receivers

Receivers contain the business logic for the actions invoked by commands.

```java
interface FileSystemReceiver {
    void openFile();
    void writeFile();
    void closeFile();
}

class WindowsFileSystemReceiver implements FileSystemReceiver {
    public void openFile() { System.out.println("Opening file in Windows OS"); }
    public void writeFile() { System.out.println("Writing file in Windows OS"); }
    public void closeFile() { System.out.println("Closing file in Windows OS"); }
}
```

Or:

```java
interface Device {
    void turnOn();
    void turnOff();
}

class TV implements Device {
    public void turnOn() { System.out.println("TV is now on"); }
    public void turnOff() { System.out.println("TV is now off"); }
    public void changeChannel() { System.out.println("Channel changed"); }
}
```

#### 3. Concrete Commands

Implement the command interface and invoke corresponding actions on receivers.

```java
class OpenFileCommand implements FileCommand {
    private FileSystemReceiver fs;
    public OpenFileCommand(FileSystemReceiver fs) { this.fs = fs; }
    public void execute() { fs.openFile(); }
}

class WriteFileCommand implements FileCommand {
    private FileSystemReceiver fs;
    public WriteFileCommand(FileSystemReceiver fs) { this.fs = fs; }
    public void execute() { fs.writeFile(); }
}
```

Or:

```java
class TurnOnCommand implements Command {
    private Device device;
    public TurnOnCommand(Device device) { this.device = device; }
    public void execute() { device.turnOn(); }
}

class ChangeChannelCommand implements Command {
    private TV tv;
    public ChangeChannelCommand(TV tv) { this.tv = tv; }
    public void execute() { tv.changeChannel(); }
}
```

#### 4. Invoker

Holds a command and triggers execution, without knowing details about the command’s action.

```java
class FileInvoker {
    private FileCommand command;
    public void setCommand(FileCommand command) { this.command = command; }
    public void executeCommand() { command.execute(); }
}
```

Or:

```java
class RemoteControl {
    private Command command;
    public void setCommand(Command command) { this.command = command; }
    public void pressButton() { command.execute(); }
}
```

#### 5. Client

Creates commands, configures receivers, sets commands on invokers, and triggers execution.

```java
public class FileSystemCommmand {
    public static void main(String[] args) {
        FileSystemReceiver fs = new WindowsFileSystemReceiver();

        FileCommand open = new OpenFileCommand(fs);
        FileCommand write = new WriteFileCommand(fs);
        FileCommand close = new CloseFileCommand(fs);

        FileInvoker invoker = new FileInvoker();

        invoker.setCommand(open);
        invoker.executeCommand(); // Output: Opening file in Windows OS

        invoker.setCommand(write);
        invoker.executeCommand(); // Output: Writing file in Windows OS

        invoker.setCommand(close);
        invoker.executeCommand(); // Output: Closing file in Windows OS
    }
}
```

Or:

```java
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
    }
}
```

### 🌟 Why Command Pattern?

- Decouples sender and receiver.
- Supports queuing, logging, and undo/redo operations.
- Enables dynamic command execution and flexible extension.
- Encapsulates requests as objects.

### 🚗 Real-World Analogy

A TV remote (invoker) doesn’t need to know how the TV (receiver) works — it just issues commands like power on, change channel, etc., encapsulated as objects, making the system extensible and loosely coupled.

This explanation follows the structured style of your earlier design pattern descriptions with clear code examples and roles.