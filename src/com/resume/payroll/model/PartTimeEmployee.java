package com.resume.payroll.model;

public class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(int id, String name, Department department,
                            double baseSalary, int hoursWorked, double hourlyRate) {
        super(id, name, department, baseSalary);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return (hoursWorked * hourlyRate) + getPerformanceRating() * getBaseSalary() * 0.01;
    }

    @Override
    public String getEmployeeType() { return "Part-Time"; }
}
