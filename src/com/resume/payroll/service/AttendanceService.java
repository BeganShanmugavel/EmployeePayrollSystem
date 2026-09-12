package com.resume.payroll.service;

import com.resume.payroll.model.Employee;

public class AttendanceService {
    public void markPresent(Employee employee) {
        employee.getAttendance().markPresent();
    }

    public void markAbsent(Employee employee) {
        employee.getAttendance().markAbsent();
    }
}
