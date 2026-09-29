package com.library.model;

/*
 * ENUM #2 (with its own fields and constructor)
 * Every membership type has a different borrowing limit and a
 * different fine rate per day.
 */
public enum MembershipType {

    STUDENT(3, 1.50),
    TEACHER(5, 1.00),
    GUEST(1, 2.00);

    // enum fields are declared final because the rules never change at runtime
    private final int maxBooks;
    private final double finePerDay;

    MembershipType(int maxBooks, double finePerDay) {
        this.maxBooks = maxBooks;       // 'this' used to resolve name shadowing
        this.finePerDay = finePerDay;
    }

    public int getMaxBooks() {
        return maxBooks;
    }

    public double getFinePerDay() {
        return finePerDay;
    }
}
