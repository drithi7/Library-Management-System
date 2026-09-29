package com.library.model;

/*
 * SUBCLASS 1 of Person.
 * It also IMPLEMENTS the Loanable interface, so a Member is the only
 * person who can actually borrow books.
 */
public class Member extends Person implements Loanable {

    private MembershipType type;
    private Book[] borrowedBooks;      // ARRAY OF OBJECTS
    private int booksHeld;
    private double totalFine;

    // FINAL CONSTANT - biggest shelf size we ever allot to one member
    public static final int MAX_LIMIT = 5;

    // STATIC counter only for members
    private static int totalMembers = 0;

    // ================= CONSTRUCTOR OVERLOADING =================

    // default
    public Member() {
        this(0, "New Member", "0000000000", MembershipType.GUEST);
    }

    // most common case - a student member
    public Member(int id, String name, String phone) {
        this(id, name, phone, MembershipType.STUDENT);
    }

    // full constructor
    public Member(int id, String name, String phone, MembershipType type) {
        super(id, name, phone);          // SUPER used to call parent constructor
        this.type = type;
        this.borrowedBooks = new Book[MAX_LIMIT];
        this.booksHeld = 0;
        this.totalFine = 0.0;
        totalMembers++;
    }

    public MembershipType getType() {
        return type;
    }

    public void setType(MembershipType type) {
        this.type = type;
    }

    public double getTotalFine() {
        return totalFine;
    }

    public void addFine(double amount) {
        this.totalFine = this.totalFine + amount;
    }

    public Book[] getBorrowedBooks() {
        return borrowedBooks;
    }

    public static int getTotalMembers() {
        return totalMembers;
    }

    // ================= ABSTRACT METHOD IMPLEMENTED =================
    @Override
    public String getRole() {
        return "MEMBER";
    }

    // ================= INTERFACE METHODS IMPLEMENTED =================

    @Override
    public boolean borrowBook(Book b) {
        // if-else + logical operators
        if (b == null || !b.isAvailable()) {
            return false;                       // JUMP STATEMENT: return
        }
        if (booksHeld >= type.getMaxBooks()) {
            return false;
        }
        borrowedBooks[booksHeld] = b;
        booksHeld++;
        b.setAvailable(false);
        return true;
    }

    @Override
    public boolean giveBackBook(Book b) {
        int position = -1;

        for (int i = 0; i < booksHeld; i++) {
            if (borrowedBooks[i] == null) {
                continue;                       // JUMP STATEMENT: continue
            }
            if (borrowedBooks[i].equals(b)) {   // uses our overridden equals()
                position = i;
                break;                          // JUMP STATEMENT: break
            }
        }

        if (position == -1) {
            return false;   // this member never took that book
        }

        // shift the remaining books one step left
        for (int i = position; i < booksHeld - 1; i++) {
            borrowedBooks[i] = borrowedBooks[i + 1];
        }
        borrowedBooks[booksHeld - 1] = null;
        booksHeld--;
        b.setAvailable(true);
        return true;
    }

    @Override
    public int getBooksHeld() {
        return booksHeld;
    }

    public boolean canBorrowMore() {
        return booksHeld < type.getMaxBooks();
    }

    // ================= METHOD OVERRIDING =================
    @Override
    public String toString() {
        // SUPER used to call the parent class version of the method
        return super.toString()
                + String.format(" | Type: %-7s | Books held: %d/%d | Fine: Rs.%.2f",
                        type, booksHeld, type.getMaxBooks(), totalFine);
    }
}
