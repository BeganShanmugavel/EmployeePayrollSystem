package com.resume.payroll;

import com.resume.payroll.exception.EmployeeNotFoundException;
import com.resume.payroll.exception.InvalidEmployeeException;
import com.resume.payroll.model.*;
import com.resume.payroll.service.*;
import com.resume.payroll.util.FileManager;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeService employeeService = new EmployeeService();
    private static final AttendanceService attendanceService = new AttendanceService();
    private static final LeaveService leaveService = new LeaveService();
    private static final PayrollService payrollService = new PayrollService();
    private static final ReportService reportService = new ReportService(employeeService);

    private static final Department IT = new Department(1, "IT");
    private static final Department HR = new Department(2, "HR");
    private static final Department SALES = new Department(3, "Sales");

    public static void main(String[] args) {
        seedData();

        while (true) {
            printMenu();
            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1 -> addEmployee();
                    case 2 -> reportService.generateReport();
                    case 3 -> searchEmployee();
                    case 4 -> attendance();
                    case 5 -> applyLeave();
                    case 6 -> generatePayroll();
                    case 7 -> promote();
                    case 8 -> removeEmployee();
                    case 9 -> saveData();
                    case 10 -> {
                        System.out.println("Thank you for using the system.");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== EMPLOYEE & PAYROLL SYSTEM =====");
        System.out.println("1. Add Employee");
        System.out.println("2. View Employees");
        System.out.println("3. Search Employee");
        System.out.println("4. Mark Attendance");
        System.out.println("5. Apply Leave");
        System.out.println("6. Generate Payroll");
        System.out.println("7. Promote Employee");
        System.out.println("8. Remove Employee");
        System.out.println("9. Save Data");
        System.out.println("10. Exit");
    }

    private static void seedData() {
        try {
            employeeService.addEmployee(new FullTimeEmployee(
                    101, "Arun", IT, 35000, 5000));
            employeeService.addEmployee(new PartTimeEmployee(
                    102, "Priya", HR, 12000, 80, 250));
            employeeService.addEmployee(new Intern(
                    103, "Kavin", SALES, 5000, 8000));
        } catch (InvalidEmployeeException ignored) {}
    }

    private static void addEmployee() throws InvalidEmployeeException {
        int id = readInt("Employee ID: ");
        String name = readText("Name: ");

        System.out.println("1. Full-Time  2. Part-Time  3. Intern");
        int type = readInt("Employee type: ");

        Department department = chooseDepartment();
        double salary = readDouble("Base salary: ");

        Employee employee;
        if (type == 1) {
            double allowance = readDouble("Allowance: ");
            employee = new FullTimeEmployee(id, name, department, salary, allowance);
        } else if (type == 2) {
            int hours = readInt("Hours worked: ");
            double rate = readDouble("Hourly rate: ");
            employee = new PartTimeEmployee(id, name, department, salary, hours, rate);
        } else if (type == 3) {
            double stipend = readDouble("Stipend: ");
            employee = new Intern(id, name, department, salary, stipend);
        } else {
            System.out.println("Invalid employee type.");
            return;
        }

        employeeService.addEmployee(employee);
        System.out.println("Employee added successfully.");
    }

    private static Department chooseDepartment() {
        System.out.println("1. IT  2. HR  3. Sales");
        return switch (readInt("Department: ")) {
            case 1 -> IT;
            case 2 -> HR;
            default -> SALES;
        };
    }

    private static void searchEmployee() throws EmployeeNotFoundException {
        System.out.println("1. Search by ID  2. Search by Name");
        int option = readInt("Choose: ");
        Employee employee;
        if (option == 1) {
            employee = employeeService.findById(readInt("ID: "));
            if (employee == null) throw new EmployeeNotFoundException("Employee not found.");
        } else {
            employee = employeeService.findByName(readText("Name: "));
        }
        System.out.println(employee);
    }

    private static Employee getEmployee() throws EmployeeNotFoundException {
        Employee employee = employeeService.findById(readInt("Employee ID: "));
        if (employee == null) throw new EmployeeNotFoundException("Employee not found.");
        return employee;
    }

    private static void attendance() throws EmployeeNotFoundException {
        Employee employee = getEmployee();
        System.out.println("1. Present  2. Absent");
        if (readInt("Status: ") == 1) attendanceService.markPresent(employee);
        else attendanceService.markAbsent(employee);
        System.out.println("Attendance updated.");
    }

    private static void applyLeave() throws EmployeeNotFoundException {
        Employee employee = getEmployee();
        int days = readInt("Leave days: ");
        leaveService.applyLeave(employee, days);
        System.out.println("Leave recorded.");
    }

    private static void generatePayroll() throws EmployeeNotFoundException {
        payrollService.printPayroll(getEmployee());
    }

    private static void promote() throws EmployeeNotFoundException {
        Employee employee = getEmployee();
        double increment = readDouble("Salary increment: ");
        employee.promote(increment);
        System.out.println("Employee promoted. New salary: " +
                employee.calculateSalary());
    }

    private static void removeEmployee() throws EmployeeNotFoundException {
        employeeService.removeEmployee(readInt("Employee ID: "));
        System.out.println("Employee removed.");
    }

    private static void saveData() throws IOException {
        new FileManager().save(employeeService.getEmployees(), "data/employees.txt");
        System.out.println("Data saved to data/employees.txt");
    }

    private static int readInt(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    private static double readDouble(String message) {
        System.out.print(message);
        return Double.parseDouble(scanner.nextLine().trim());
    }

    private static String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}
