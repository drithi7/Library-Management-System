package com.library.model;

/*
 * ABSTRACT BASE CLASS of the inheritance hierarchy.
 *
 *              Person (abstract)
 *              /              \
 *          Member          Librarian
 *
 * Person is abstract because a plain "person" has no meaning inside a
 * library - he/she has to be either a Member or a Librarian.
 */
public abstract class Person {

    // ---------- ENCAPSULATION: every field is private ----------
    private int id;
    private String name;
    private String phone;

    // ---------- STATIC field: one copy shared by ALL objects ----------
    private static int personCount = 0;

    // ---------- CONSTRUCTOR OVERLOADING ----------
    // default constructor -> calls the parameterised one using this()
    public Person() {
        this(0, "Unknown", "0000000000");
    }

    public Person(int id, String name, String phone) {
        // 'this' is needed here because parameter names are same as field
        // names (name shadowing)
        this.id = id;
        this.name = name;
        this.phone = phone;
        personCount++;
    }

    // ---------- getters and setters ----------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // STATIC METHOD - can be called without creating an object
    public static int getPersonCount() {
        return personCount;
    }

    // ---------- ABSTRACT METHOD ----------
    // Each child class must tell us what it is.
    public abstract String getRole();

    /*
     * FINAL METHOD.
     * Declared final because the library ID-card format is fixed by the
     * university and no subclass should be allowed to print it differently.
     */
    public final String getIdCard() {
        return String.format("|| %-9s ID:%-4d %-22s ||", getRole(), id, name);
    }

    // First word of the name, used for a friendly greeting.
    // Shows the String methods trim() and split().
    public String getFirstName() {
        String cleaned = name.trim();
        String[] words = cleaned.split(" ");
        return words[0];
    }

    // ---------- overriding toString() of the Object class ----------
    @Override
    public String toString() {
        return getRole() + " | ID: " + id + " | Name: " + name + " | Phone: " + phone;
    }
}
