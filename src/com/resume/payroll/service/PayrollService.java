package com.resume.payroll.service;

import com.resume.payroll.model.Employee;

public class PayrollService {

    public void printPayroll(Employee employee) {
        System.out.println("\n===== PAYSLIP =====");
        System.out.println("Employee ID : " + employee.getId());
        System.out.println("Name        : " + employee.getName());
        System.out.println("Type        : " + employee.getEmployeeType());
        System.out.println("Department  : " + employee.getDepartment().getName());
        System.out.printf("Salary      : %.2f%n", employee.calculateSalary());
        System.out.println("Attendance  : " + employee.getAttendance());
        System.out.println("Leave       : " + employee.getLeaveRecord());
        System.out.println("===================\n");
    }
}
