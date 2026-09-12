package com.resume.payroll.model;

import com.resume.payroll.interfaces.Payable;

public abstract class Employee implements Payable {
    private final int id;
    private String name;
    private Department department;
    private double baseSalary;
    private double performanceBonus;
    private int performanceRating;

    private final Attendance attendance = new Attendance();
    private final LeaveRecord leaveRecord = new LeaveRecord();

    protected Employee(int id, String name, Department department, double baseSalary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.baseSalary = baseSalary;
    }

    public abstract String getEmployeeType();

    public int getId() { return id; }
    public String getName() { return name; }
    public Department getDepartment() { return department; }
    public double getBaseSalary() { return baseSalary; }
    public Attendance getAttendance() { return attendance; }
    public LeaveRecord getLeaveRecord() { return leaveRecord; }
    public int getPerformanceRating() { return performanceRating; }

    public void setName(String name) { this.name = name; }
    public void setDepartment(Department department) { this.department = department; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }

    public void setPerformanceRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        this.performanceRating = rating;
        this.performanceBonus = baseSalary * rating * 0.02;
    }

    public void promote(double increment) {
        if (increment <= 0) throw new IllegalArgumentException("Increment must be positive.");
        baseSalary += increment;
    }

    public String basicDetails() {
        return String.format("%d | %s | %s | %s | %.2f",
                id, name, getEmployeeType(), department.getName(), calculateSalary());
    }

    @Override
    public String toString() {
        return basicDetails() + " | Rating: " + performanceRating;
    }
}
