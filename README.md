# Employee & Payroll Management System

A console-based Java project demonstrating practical Object-Oriented Programming concepts.

## Features
- Add, update, remove and view employees
- Full-time, part-time and intern employee types
- Salary calculation using polymorphism
- Attendance and leave management
- Employee search
- Promotion support
- Department reports
- Custom exceptions
- File-based data persistence
- Java Collections

## OOP Concepts
- Encapsulation
- Inheritance
- Abstraction
- Polymorphism
- Interfaces
- Composition
- Method overloading and overriding

## How to Run

```bash
javac -d out src/com/resume/payroll/**/*.java
java -cp out com.resume.payroll.Main
```

If your shell does not expand `**`, compile all Java files with your IDE, or use:

```bash
find src -name "*.java" -print0 | xargs -0 javac -d out
java -cp out com.resume.payroll.Main
```

## Project Structure

```text
EmployeePayrollSystem/
├── src/com/resume/payroll/
│   ├── Main.java
│   ├── model/
│   ├── service/
│   ├── interfaces/
│   ├── exception/
│   └── util/
├── data/
└── README.md
```
