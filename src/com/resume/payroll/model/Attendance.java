package com.resume.payroll.model;

public class Attendance {
    private int presentDays;
    private int absentDays;

    public void markPresent() { presentDays++; }
    public void markAbsent() { absentDays++; }

    public int getPresentDays() { return presentDays; }
    public int getAbsentDays() { return absentDays; }

    @Override
    public String toString() {
        return "Present: " + presentDays + ", Absent: " + absentDays;
    }
}
