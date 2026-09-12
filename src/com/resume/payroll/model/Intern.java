package com.resume.payroll.model;

public class Intern extends Employee {
    private double stipend;

    public Intern(int id, String name, Department department,
                  double baseSalary, double stipend) {
        super(id, name, department, baseSalary);
        this.stipend = stipend;
    }

    @Override
    public double calculateSalary() {
        return stipend;
    }

    @Override
    public String getEmployeeType() { return "Intern"; }
}
