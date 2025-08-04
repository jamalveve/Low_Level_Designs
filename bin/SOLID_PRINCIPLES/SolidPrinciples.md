# 🧱 SOLID Principles in Low-Level Design

**SOLID** is a set of five core principles that guide developers in designing flexible, maintainable, and scalable software systems. These principles are foundational in Object-Oriented Programming and were introduced and popularized by **Robert C. Martin** (Uncle Bob), based on early ideas by **Bertrand Meyer**.

---

## 📌 Overview

- Helps manage and prevent common software design pitfalls.
- Promotes **clean architecture** through decoupling and modular design.
- Encourages best practices for building **testable**, **extensible**, and **evolution-friendly** systems.

### 🔠 SOLID Acronym:

| Letter | Principle Name                    |
|--------|----------------------------------|
| S      | Single Responsibility Principle  |
| O      | Open/Closed Principle            |
| L      | Liskov Substitution Principle    |
| I      | Interface Segregation Principle  |
| D      | Dependency Inversion Principle   |

---

## 🔹 1. Single Responsibility Principle (SRP)

> “A class should have only one reason to change.”

### ✅ Key Ideas:
- A class/module should be responsible for **one part** of the functionality.
- All functionality in a class should relate to a **single purpose**.
- Promotes **clean**, **testable**, and **smaller** units of logic.

### 🎯 Motivation:
- **Maintainability** – Small focused classes are easier to update.
- **Testability** – Easier unit tests for well-scoped classes.
- **Flexibility & Extensibility** – Makes it easier to adapt as requirements evolve.
- **Parallel Development** – Multiple devs can work on different modules without conflicts.
- **Loose Coupling** – Clear module boundaries improve architecture resilience.

---

## 🔹 2. Open/Closed Principle (OCP)

> “Software entities should be open for extension, but closed for modification.” – Bertrand Meyer

### ✅ Key Ideas:
- Extend system behavior without modifying existing code.
- Leverages **polymorphism** and **abstraction**.
- Encourages **plug-and-play** extensibility.

### ⚠️ What If Not Followed:
- Changing existing code can break unrelated parts.
- Introduces bugs and requires full regression testing.
- Violates SRP by mixing old and new responsibilities.

### 🛠 Guidelines:
- Implement new behavior via **derived classes**.
- Use **interfaces or abstract classes** to define core behavior.

---

## 🔹 3. Liskov Substitution Principle (LSP)

> “If S is a subtype of P, then objects of type P may be replaced with objects of type S without altering the correctness of the program.”

### ✅ Key Ideas:
- Child classes must be **fully substitutable** for their parent.
- Subclasses should **extend**, not contradict, base class behavior.
- Enhances **inheritance safety** and **interface reliability**.

### 🛠 Best Practices:
- Avoid throwing **unexpected exceptions** in subclasses.
- Don’t **weaken** base class contracts.
- Avoid changing **return types** or behavior in an incompatible way.

---

## 🔹 4. Interface Segregation Principle (ISP)

> “No client should be forced to depend on methods it does not use.”

### ✅ Key Ideas:
- Split large, general-purpose interfaces into **smaller**, **purpose-specific** ones.
- Encourages **focused, reusable interfaces**.
- Helps prevent bloated implementations and improves **client-developer experience**.

### 💡 Real-Life Analogy:
**Xerox Printer Problem** – A single interface handled faxing, printing, and stapling, causing complex deployments and tight coupling.  
**Solution**: Create separate interfaces like `IPrint`, `IFax`, `IStaple` to ensure clients implement only what's needed.

---

## 🔹 5. Dependency Inversion Principle (DIP)

> “High-level modules should not depend on low-level modules. Both should depend on abstractions.”

### ✅ Key Ideas:
- Separate **high-level policy** from **low-level detail** via abstraction.
- Encourages **decoupled architecture**.
- Makes systems easier to refactor, test, and scale.

### 🧱 Example: Traditional 3-Layer Architecture

```text
Presentation Layer → Business Logic → Data Access Layer
(Button Click)     (Employee Save)     (Save to DB)
