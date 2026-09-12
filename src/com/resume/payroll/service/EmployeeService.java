package com.resume.payroll.service;

import com.resume.payroll.exception.EmployeeNotFoundException;
import com.resume.payroll.exception.InvalidEmployeeException;
import com.resume.payroll.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) throws InvalidEmployeeException {
        if (employee == null || employee.getName() == null || employee.getName().isBlank()) {
            throw new InvalidEmployeeException("Employee name cannot be empty.");
        }
        if (employee.getBaseSalary() < 0) {
            throw new InvalidEmployeeException("Salary cannot be negative.");
        }
        if (findById(employee.getId()) != null) {
            throw new InvalidEmployeeException("Employee ID already exists.");
        }
        employees.add(employee);
    }

    public Employee findById(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Employee findByName(String name) throws EmployeeNotFoundException {
        return employees.stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found."));
    }

    public void removeEmployee(int id) throws EmployeeNotFoundException {
        Employee employee = findById(id);
        if (employee == null) throw new EmployeeNotFoundException("Employee not found.");
        employees.remove(employee);
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public List<Employee> getByDepartment(String departmentName) {
        return employees.stream()
                .filter(e -> e.getDepartment().getName().equalsIgnoreCase(departmentName))
                .collect(Collectors.toList());
    }
}
