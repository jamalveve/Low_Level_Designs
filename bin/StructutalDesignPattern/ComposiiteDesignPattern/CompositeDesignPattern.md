## 🌲 Composite Design Pattern in Java

The **Composite Design Pattern** is a structural pattern that allows you to compose objects into tree structures to represent part-whole hierarchies. It lets clients treat individual objects and compositions of objects uniformly.

### ⚡ What’s Happening?

The pattern defines a common interface for both *simple* and *composite* objects. The composite object contains a collection of child components that can be either simple objects or other composites. Clients interact with all components through the common interface, allowing recursive composition.

### 🛠️ Components

#### 1. Component Interface

Defines common operations applicable to both simple and composite objects.

```java
interface Task {
    String getTitle();
    void setTitle(String title);
    void display();
}
```

#### 2. Leaf (Simple Object)

Represents the end objects of the composition; has no children.

```java
class SimpleTask implements Task {
    private String title;

    public SimpleTask(String title) { this.title = title; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public void display() {
        System.out.println("Simple Task: " + title);
    }
}
```

#### 3. Composite (Complex Object)

Contains and manages child components; forwards operations to children.

```java
class TaskList implements Task {
    private String title;
    private List tasks;

    public TaskList(String title) {
        this.title = title;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) { tasks.add(task); }
    public void removeTask(Task task) { tasks.remove(task); }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public void display() {
        System.out.println("Task List: " + title);
        for (Task task : tasks) {
            task.display();
        }
    }
}
```

#### 4. Client Usage

Builds complex tree structures of tasks and treats simple and composite tasks uniformly.

```java
public class TaskManagementSystem {
    public static void main(String[] args) {
        // Simple tasks
        Task simpleTask1 = new SimpleTask("Complete Coding");
        Task simpleTask2 = new SimpleTask("Write Documentation");

        // Composite task list
        TaskList projectTasks = new TaskList("Project Tasks");
        projectTasks.addTask(simpleTask1);
        projectTasks.addTask(simpleTask2);

        // Nested composite task list
        TaskList phase1Tasks = new TaskList("Phase 1 Tasks");
        phase1Tasks.addTask(new SimpleTask("Design"));
        phase1Tasks.addTask(new SimpleTask("Implementation"));

        // Add nested list to parent
        projectTasks.addTask(phase1Tasks);

        // Display all tasks, recursively
        projectTasks.display();
    }
}
```

Output:
```
Task List: Project Tasks
Simple Task: Complete Coding
Simple Task: Write Documentation
Task List: Phase 1 Tasks
Simple Task: Design
Simple Task: Implementation
```

### 🌟 Why Composite Pattern?

- Lets clients treat individual objects and compositions uniformly.
- Supports building tree-like structures with arbitrary depth.
- Simplifies client code by interacting through a common interface.
- Makes it easy to add new kinds of components.

### 🚗 Real-World Analogy

Think of folders and files on a computer — folders can contain files or other folders. Both files and folders share some common operations (like displaying names), but folders can manage collections of files and subfolders recursively.

This explanation reflects your previous pattern style, clarifying roles and relationships in the Composite pattern via your task management example.