package com.resume.payroll.service;

import com.resume.payroll.interfaces.ReportGenerator;
import com.resume.payroll.model.Employee;

import java.util.List;

public class ReportService implements ReportGenerator {
    private final EmployeeService employeeService;

    public ReportService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void generateReport() {
        List<Employee> employees = employeeService.getEmployees();
        System.out.println("\n===== EMPLOYEE REPORT =====");
        if (employees.isEmpty()) {
            System.out.println("No employees available.");
        } else {
            employees.forEach(e -> System.out.println(e.basicDetails()));
        }
        System.out.println("============================\n");
    }
}
