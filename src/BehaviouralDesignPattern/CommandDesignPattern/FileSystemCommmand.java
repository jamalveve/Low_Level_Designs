package BehaviouralDesignPattern.CommandDesignPattern;

// Command interface
interface FileCommand {
    void execute();
}

// Receiver interface
interface FileSystemReceiver {
    void openFile();

    void writeFile();

    void closeFile();
}

// Concrete receiver for Windows
class WindowsFileSystemReceiver implements FileSystemReceiver {
    public void openFile() {
        System.out.println("Opening file in Windows OS");
    }

    public void writeFile() {
        System.out.println("Writing file in Windows OS");
    }

    public void closeFile() {
        System.out.println("Closing file in Windows OS");
    }
}

// Concrete commands
class OpenFileCommand implements FileCommand {
    private FileSystemReceiver fs;

    public OpenFileCommand(FileSystemReceiver fs) {
        this.fs = fs;
    }

    public void execute() {
        fs.openFile();
    }
}

class WriteFileCommand implements FileCommand {
    private FileSystemReceiver fs;

    public WriteFileCommand(FileSystemReceiver fs) {
        this.fs = fs;
    }

    public void execute() {
        fs.writeFile();
    }
}

class CloseFileCommand implements FileCommand {
    private FileSystemReceiver fs;

    public CloseFileCommand(FileSystemReceiver fs) {
        this.fs = fs;
    }

    public void execute() {
        fs.closeFile();
    }
}

// Invoker
class FileInvoker {
    private FileCommand command;

    public void setCommand(FileCommand command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}

// Client
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
