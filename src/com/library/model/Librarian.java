package com.library.model;

/*
 * SUBCLASS 2 of Person.
 * A librarian does not borrow books, so this class does NOT implement
 * Loanable. That is the whole point of keeping borrowing in an interface.
 */
public class Librarian extends Person {

    private String shift;      // MORNING or EVENING
    private int experience;    // in years

    private static int totalLibrarians = 0;

    public Librarian() {
        this(0, "Not Assigned", "0000000000", "MORNING", 0);
    }

    public Librarian(int id, String name, String phone, String shift) {
        this(id, name, phone, shift, 1);
    }

    public Librarian(int id, String name, String phone, String shift, int experience) {
        super(id, name, phone);        // SUPER constructor call
        this.shift = shift;
        this.experience = experience;
        totalLibrarians++;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public int getExperience() {
        return experience;
    }

    public static int getTotalLibrarians() {
        return totalLibrarians;
    }

    @Override
    public String getRole() {
        return "LIBRARIAN";
    }

    // METHOD OVERRIDING + calling the parent version with super
    @Override
    public String toString() {
        return super.toString() + " | Shift: " + shift + " | Experience: " + experience + " yrs";
    }
}
