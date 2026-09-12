package com.resume.payroll.util;

import com.resume.payroll.model.Employee;

import java.io.*;
import java.util.List;

public class FileManager {

    public void save(List<Employee> employees, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Employee e : employees) {
                writer.write(e.getId() + "," + e.getName() + "," +
                        e.getEmployeeType() + "," + e.getDepartment().getName() + "," +
                        e.calculateSalary());
                writer.newLine();
            }
        }
    }
}
