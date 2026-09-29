package com.library.model;

/*
 * Book class - the main item of our library.
 * Shows encapsulation, varied data types, static members,
 * constructor overloading, equals() and toString() overriding.
 */
public class Book {

    // ---------- ENCAPSULATION + VARIED DATA TYPES ----------
    private int bookId;          // int
    private String title;        // String
    private String author;       // String
    private Genre genre;         // enum
    private double price;        // double
    private boolean available;   // boolean
    private char rack;           // char (rack letter in the shelf)

    // ---------- STATIC COUNTER ----------
    private static int totalBooks = 0;

    // ---------- FINAL CONSTANT ----------
    public static final String LIBRARY_CODE = "REVA-LIB";

    // ================= CONSTRUCTOR OVERLOADING =================

    // 1. default constructor - chains to the full one using this()
    public Book() {
        this(0, "Untitled", "Unknown", Genre.FICTION, 0.0, 'A');
    }

    // 2. short constructor - when price and genre are not known yet
    public Book(int bookId, String title, String author) {
        this(bookId, title, author, Genre.FICTION, 250.0, 'A');
    }

    // 3. full parameterised constructor
    public Book(int bookId, String title, String author, Genre genre, double price, char rack) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.rack = rack;
        this.available = true;   // a newly added book is always on the shelf
        totalBooks++;            // static field updated for every new object
    }

    // ================= GETTERS / SETTERS =================
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public char getRack() {
        return rack;
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    /*
     * TYPE CASTING example.
     * Security deposit is 10% of the book price, but the counter only
     * accepts whole rupees, so the double value is narrowed to an int.
     * Note: (int) simply cuts the decimal part, it does not round off.
     */
    public int getSecurityDeposit() {
        double tenPercent = price * 0.10;
        int deposit = (int) tenPercent;   // double -> int (narrowing / explicit cast)
        return deposit;
    }

    // String method demo: split() to pick the surname of the author
    public String getAuthorSurname() {
        String[] parts = author.trim().split(" ");
        return parts[parts.length - 1];
    }

    // ================= OVERRIDING Object CLASS METHODS =================

    /*
     * equals() is overridden so that two Book objects are treated as the
     * same book when the id and the title match, instead of comparing
     * their memory addresses.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        Book other = (Book) obj;                    // downcasting
        return this.bookId == other.bookId
                && this.title.equalsIgnoreCase(other.title);   // String compare
    }

    @Override
    public String toString() {
        // FORMATTED OUTPUT using String.format
        return String.format("%-5d %-30s %-20s %-12s %-6c Rs.%-9.2f %s",
                bookId, title, author, genre, rack, price,
                (available ? "Available" : "Issued"));
    }
}
