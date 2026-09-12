package com.resume.payroll.model;

public class FullTimeEmployee extends Employee {
    private double allowance;

    public FullTimeEmployee(int id, String name, Department department,
                            double baseSalary, double allowance) {
        super(id, name, department, baseSalary);
        this.allowance = allowance;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + allowance + getPerformanceRating() * getBaseSalary() * 0.02;
    }

    @Override
    public String getEmployeeType() { return "Full-Time"; }

    public double getAllowance() { return allowance; }
}
