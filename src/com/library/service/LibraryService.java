package com.library.service;

// IMPORT STATEMENTS from another custom package
import com.library.model.Book;
import com.library.model.Genre;
import com.library.model.Librarian;
import com.library.model.Loanable;
import com.library.model.Member;
import com.library.model.MembershipType;
import com.library.model.Person;

/*
 * This class keeps all the library data (books, members, librarian)
 * and contains all the working logic. The menu class only calls these
 * methods, so the logic and the screen printing stay separate.
 */
public class LibraryService {

    private static final int MAX_BOOKS = 50;
    private static final int MAX_MEMBERS = 20;

    private Book[] books;          // ARRAY OF OBJECTS
    private Member[] members;      // ARRAY OF OBJECTS
    private int bookCount;
    private int memberCount;
    private Librarian inCharge;

    public LibraryService() {
        this.books = new Book[MAX_BOOKS];
        this.members = new Member[MAX_MEMBERS];
        this.bookCount = 0;
        this.memberCount = 0;
        this.inCharge = new Librarian(101, "Shobha Rao", "9845012345", "MORNING", 12);
        loadSampleData();
    }

    // some ready data so that the program is usable the moment it starts
    private void loadSampleData() {
        addBook(new Book(1, "Java The Complete Reference", "Herbert Schildt", Genre.TECHNOLOGY, 799.00, 'A'));
        addBook(new Book(2, "Let Us C", "Yashavant Kanetkar", Genre.TECHNOLOGY, 450.50, 'A'));
        addBook(new Book(3, "Wings of Fire", "A P J Abdul Kalam", Genre.HISTORY, 350.00, 'B'));
        addBook(new Book(4, "The Alchemist", "Paulo Coelho", Genre.FICTION, 299.00, 'C'));
        addBook(new Book(5, "A Brief History of Time", "Stephen Hawking", Genre.SCIENCE, 599.75, 'B'));
        addBook(new Book(6, "Tinkle Digest", "Anant Pai", Genre.COMICS, 120.00, 'D'));

        addMember(new Member(1001, "Drithi Reddy", "9900112233", MembershipType.STUDENT));
        addMember(new Member(1002, "Rahul Nair", "9812345678", MembershipType.STUDENT));
        addMember(new Member(1003, "Prof Anand Kumar", "9845567890", MembershipType.TEACHER));
    }

    public Librarian getInCharge() {
        return inCharge;
    }

    public int getBookCount() {
        return bookCount;
    }

    public int getMemberCount() {
        return memberCount;
    }

    // ================== ADDING ==================

    public boolean addBook(Book b) {
        if (bookCount >= MAX_BOOKS) {
            System.out.println("Sorry, the book register is full.");
            return false;
        }
        books[bookCount] = b;
        bookCount++;
        return true;
    }

    public boolean addMember(Member m) {
        if (memberCount >= MAX_MEMBERS) {
            System.out.println("Sorry, no more membership seats left.");
            return false;
        }
        members[memberCount] = m;
        memberCount++;
        return true;
    }

    // ================== METHOD OVERLOADING ==================
    // Three methods with the same name searchBook but different parameters.

    // 1. search by book id
    public Book searchBook(int id) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getBookId() == id) {
                return books[i];
            }
        }
        return null;
    }

    // 2. search by title (partial, case does not matter)
    public Book searchBook(String title) {
        String key = title.trim().toLowerCase();    // String methods trim + toLowerCase
        for (int i = 0; i < bookCount; i++) {
            String current = books[i].getTitle().toLowerCase();
            if (current.contains(key)) {            // String method contains
                return books[i];
            }
        }
        return null;
    }

    // 3. search by genre - prints all matches and returns how many were found
    public int searchBook(Genre g) {
        int found = 0;
        printTableHeader();
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getGenre() != g) {
                continue;               // skip this book and go to the next one
            }
            System.out.println(books[i]);
            found++;
        }
        if (found == 0) {
            System.out.println("  (no book found in this category)");
        }
        return found;
    }

    public Member findMember(int memberId) {
        int i = 0;
        while (i < memberCount) {                   // WHILE LOOP
            if (members[i].getId() == memberId) {
                return members[i];
            }
            i++;
        }
        return null;
    }

    // ================== DISPLAY (also method overloading) ==================

    public void displayBooks() {
        displayBooks(false);
    }

    public void displayBooks(boolean onlyAvailable) {
        printTableHeader();
        int shown = 0;
        for (int i = 0; i < bookCount; i++) {
            if (onlyAvailable && !books[i].isAvailable()) {
                continue;
            }
            System.out.println(books[i]);
            shown++;
        }
        System.out.println("-".repeat(95));
        System.out.printf("Total books shown : %d%n", shown);
    }

    private void printTableHeader() {
        System.out.println("-".repeat(95));
        System.out.printf("%-5s %-30s %-20s %-12s %-6s %-12s %s%n",
                "ID", "TITLE", "AUTHOR", "GENRE", "RACK", "PRICE", "STATUS");
        System.out.println("-".repeat(95));
    }

    public void displayMembers() {
        System.out.println("-".repeat(95));
        for (int i = 0; i < memberCount; i++) {
            System.out.println(members[i]);          // overridden toString of Member
        }
        System.out.println("-".repeat(95));
    }

    /*
     * DYNAMIC BINDING (run time polymorphism).
     * The array is of the PARENT type Person, but it holds Librarian and
     * Member objects. Java decides at run time whose getRole() and
     * toString() should run.
     */
    public void displayEveryone() {
        Person[] people = new Person[memberCount + 1];
        people[0] = inCharge;                  // parent reference -> child object
        for (int i = 0; i < memberCount; i++) {
            people[i + 1] = members[i];
        }

        System.out.println("-".repeat(95));
        for (Person p : people) {
            System.out.println(p.getIdCard());  // final method, same for all
            System.out.println("   " + p);      // different output for each child
        }
        System.out.println("-".repeat(95));
    }

    // ================== ISSUE AND RETURN ==================

    public boolean issueBook(int memberId, int bookId) {
        Member m = findMember(memberId);
        Book b = searchBook(bookId);

        if (m == null) {
            System.out.println("No member is registered with ID " + memberId);
            return false;
        }
        if (b == null) {
            System.out.println("No book is available with ID " + bookId);
            return false;
        }
        if (!b.isAvailable()) {
            System.out.println("Sorry, \"" + b.getTitle() + "\" is already issued to someone.");
            return false;
        }
        if (!m.canBorrowMore()) {
            System.out.printf("%s has already taken %d books, which is the limit for %s members.%n",
                    m.getName(), m.getBooksHeld(), m.getType());
            return false;
        }

        // INTERFACE REFERENCE holding an object of the implementing class
        Loanable borrower = m;
        boolean done = borrower.borrowBook(b);

        if (done) {
            System.out.printf("Issued \"%s\" to %s. Security deposit collected: Rs.%d%n",
                    b.getTitle(), m.getName(), b.getSecurityDeposit());
            System.out.printf("Books with %s now : %d (max %d, renewals allowed %d)%n",
                    m.getFirstName(), borrower.getBooksHeld(),
                    m.getType().getMaxBooks(), Loanable.MAX_RENEWALS);
        }
        return done;
    }

    public boolean returnBook(int memberId, int bookId, int daysLate) {
        Member m = findMember(memberId);
        Book b = searchBook(bookId);

        if (m == null || b == null) {
            System.out.println("Wrong member ID or book ID, please check once.");
            return false;
        }

        Loanable borrower = m;
        boolean done = borrower.giveBackBook(b);

        if (!done) {
            System.out.println(m.getName() + " has not borrowed this book.");
            return false;
        }

        double fine = FineCalculator.calculateFine(daysLate, m.getType());
        int payable = FineCalculator.roundToRupees(fine);

        System.out.printf("Returned \"%s\" from %s.%n", b.getTitle(), m.getName());
        if (payable == 0) {
            System.out.println("No fine. Returned within the grace period.");
        } else {
            m.addFine(payable);
            System.out.printf("Days late: %d | Grace days: %d | Fine payable: Rs.%d%n",
                    daysLate, FineCalculator.GRACE_DAYS, payable);
        }
        return true;
    }

    // ================== SORTING (String comparison) ==================

    public void sortBooksByTitle() {
        // simple bubble sort on the array of objects
        for (int i = 0; i < bookCount - 1; i++) {
            for (int j = 0; j < bookCount - 1 - i; j++) {
                // String method compareToIgnoreCase
                if (books[j].getTitle().compareToIgnoreCase(books[j + 1].getTitle()) > 0) {
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
        System.out.println("Books are now sorted alphabetically by title.");
    }

    // ================== STATISTICS ==================

    public void showStatistics() {
        int issued = 0;
        double totalValue = 0.0;

        for (int i = 0; i < bookCount; i++) {
            if (!books[i].isAvailable()) {
                issued++;
            }
            totalValue += books[i].getPrice();
        }

        // local variable (scope is only inside this method)
        double averagePrice = totalValue / bookCount;

        System.out.println("=".repeat(50));
        System.out.println("            LIBRARY STATISTICS");
        System.out.println("=".repeat(50));
        System.out.printf("Library code            : %s%n", Book.LIBRARY_CODE);
        // static methods called using the class name, no object needed
        System.out.printf("Book objects created    : %d%n", Book.getTotalBooks());
        System.out.printf("Members registered      : %d%n", Member.getTotalMembers());
        System.out.printf("Librarians              : %d%n", Librarian.getTotalLibrarians());
        System.out.printf("Total persons (parent)  : %d%n", Person.getPersonCount());
        System.out.printf("Books currently issued  : %d%n", issued);
        System.out.printf("Books on the shelf      : %d%n", bookCount - issued);
        System.out.printf("Total stock value       : Rs.%.2f%n", totalValue);
        System.out.printf("Average price of a book : Rs.%.2f%n", averagePrice);
        System.out.println("=".repeat(50));
    }
}
