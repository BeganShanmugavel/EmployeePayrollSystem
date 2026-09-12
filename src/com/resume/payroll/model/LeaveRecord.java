package com.resume.payroll.model;

public class LeaveRecord {
    private int leaveDays;

    public void applyLeave(int days) {
        if (days > 0) leaveDays += days;
    }

    public int getLeaveDays() { return leaveDays; }

    @Override
    public String toString() {
        return "Leave days: " + leaveDays;
    }
}
