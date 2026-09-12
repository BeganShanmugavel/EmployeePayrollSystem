package com.resume.payroll.service;

import com.resume.payroll.model.Employee;

public class LeaveService {
    public void applyLeave(Employee employee, int days) {
        employee.getLeaveRecord().applyLeave(days);
    }
}
