package com.library.app;

import java.util.Scanner;

// IMPORT STATEMENTS from my other two custom packages
import com.library.model.Book;
import com.library.model.Genre;
import com.library.model.Member;
import com.library.model.MembershipType;
import com.library.service.LibraryService;

/*
 * MAIN CLASS - this is the screen / menu part of the project.
 * Library Management System (console based)
 * Java Programming, B.Sc. BSTCs, Semester V, REVA University.
 */
public class LibraryApp {

    // one Scanner object for the whole program
    private static Scanner sc = new Scanner(System.in);

    // INSTANCE vs LOCAL vs STATIC:
    // 'service' below is a static field of this class, the variables
    // declared inside the methods are local variables, and the fields
    // inside Book/Member are instance variables.
    private static LibraryService service = new LibraryService();

    public static void main(String[] args) {

        int choice;   // local variable

        printWelcome();

        // DO-WHILE LOOP - the menu must be shown at least once
        do {
            showMenu();
            choice = readInt("Enter your choice : ");

            // SWITCH STATEMENT
            switch (choice) {
                case 1:
                    service.displayBooks();
                    break;
                case 2:
                    service.displayBooks(true);   // overloaded version
                    break;
                case 3:
                    searchMenu();
                    break;
                case 4:
                    addNewBook();
                    break;
                case 5:
                    registerMember();
                    break;
                case 6:
                    issueBookScreen();
                    break;
                case 7:
                    returnBookScreen();
                    break;
                case 8:
                    service.displayMembers();
                    break;
                case 9:
                    service.displayEveryone();
                    break;
                case 10:
                    service.sortBooksByTitle();
                    service.displayBooks();
                    break;
                case 11:
                    service.showStatistics();
                    break;
                case 0:
                    System.out.println("\nThank you for using the Library Management System. Bye!");
                    break;
                default:
                    System.out.println("That option is not in the menu, please try again.");
            }

        } while (choice != 0);

        sc.close();
    }

    private static void printWelcome() {
        System.out.println("=".repeat(60));
        System.out.println("      REVA UNIVERSITY CENTRAL LIBRARY  (" + Book.LIBRARY_CODE + ")");
        System.out.println("           Library Management System v1.0");
        System.out.println("=".repeat(60));
        System.out.println("Librarian on duty : " + service.getInCharge().getName()
                + " (" + service.getInCharge().getShift() + " shift)");
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("---------------- MAIN MENU ----------------");
        System.out.println(" 1. Show all books");
        System.out.println(" 2. Show only available books");
        System.out.println(" 3. Search a book");
        System.out.println(" 4. Add a new book");
        System.out.println(" 5. Register a new member");
        System.out.println(" 6. Issue a book");
        System.out.println(" 7. Return a book");
        System.out.println(" 8. Show all members");
        System.out.println(" 9. Show everyone (librarian + members)");
        System.out.println("10. Sort books by title");
        System.out.println("11. Library statistics");
        System.out.println(" 0. Exit");
        System.out.println("-------------------------------------------");
    }

    // ---------------- SEARCH ----------------
    private static void searchMenu() {
        System.out.println("\nSearch by : 1) Book ID   2) Title   3) Genre");
        int way = readInt("Your choice : ");

        // nested switch - calls the three OVERLOADED searchBook methods
        switch (way) {
            case 1: {
                int id = readInt("Enter book ID : ");
                Book b = service.searchBook(id);          // searchBook(int)
                showResult(b);
                break;
            }
            case 2: {
                String title = readLine("Enter title (full or part) : ");
                Book b = service.searchBook(title);       // searchBook(String)
                showResult(b);
                break;
            }
            case 3: {
                Genre g = askGenre();
                int count = service.searchBook(g);        // searchBook(Genre)
                System.out.println("Books found in " + g + " : " + count);
                break;
            }
            default:
                System.out.println("Wrong choice.");
        }
    }

    private static void showResult(Book b) {
        // IF-ELSE
        if (b == null) {
            System.out.println("Sorry, no such book in our library.");
        } else {
            System.out.println("Book found :");
            System.out.println(b);
            System.out.println("Author surname : " + b.getAuthorSurname()
                    + " | Security deposit : Rs." + b.getSecurityDeposit());
        }
    }

    // ---------------- ADD BOOK ----------------
    private static void addNewBook() {
        int id = readInt("Book ID      : ");

        if (service.searchBook(id) != null) {
            System.out.println("This ID is already used by another book.");
            return;                      // JUMP STATEMENT: return
        }

        String title = readLine("Title        : ");
        String author = readLine("Author       : ");
        Genre g = askGenre();
        double price = readDouble("Price (Rs.)  : ");
        String rackInput = readLine("Rack (A-D)   : ");

        // String method charAt + toUpperCase
        char rack = 'A';
        if (rackInput.trim().length() > 0) {
            rack = rackInput.trim().toUpperCase().charAt(0);
        }

        Book b = new Book(id, title, author, g, price, rack);
        service.addBook(b);
        System.out.println("Book added successfully.");
        System.out.println(b);
    }

    // ---------------- REGISTER MEMBER ----------------
    private static void registerMember() {
        int id = readInt("Member ID    : ");

        if (service.findMember(id) != null) {
            System.out.println("A member with this ID already exists.");
            return;
        }

        String name = readLine("Full name    : ");
        String phone = readLine("Phone number : ");

        // simple validation using String method length()
        if (phone.trim().length() != 10) {
            System.out.println("Phone number should be exactly 10 digits. Registration cancelled.");
            return;
        }

        System.out.println("Membership type : 1) STUDENT  2) TEACHER  3) GUEST");
        int t = readInt("Your choice  : ");

        MembershipType type;
        switch (t) {
            case 2:
                type = MembershipType.TEACHER;
                break;
            case 3:
                type = MembershipType.GUEST;
                break;
            default:
                type = MembershipType.STUDENT;
        }

        Member m = new Member(id, name, phone, type);
        service.addMember(m);
        System.out.println("Welcome " + m.getFirstName() + "! Your membership is active.");
        System.out.println(m.getIdCard());
        System.out.println(m);
    }

    // ---------------- ISSUE / RETURN ----------------
    private static void issueBookScreen() {
        int memberId = readInt("Member ID : ");
        int bookId = readInt("Book ID   : ");
        service.issueBook(memberId, bookId);
    }

    private static void returnBookScreen() {
        int memberId = readInt("Member ID        : ");
        int bookId = readInt("Book ID          : ");
        int daysLate = readInt("Days late (0 if on time) : ");
        service.returnBook(memberId, bookId, daysLate);
    }

    // ---------------- INPUT HELPERS ----------------

    private static String readLine(String prompt) {
        System.out.print(prompt);
        if (!sc.hasNextLine()) {
            return "";
        }
        return sc.nextLine().trim();      // String method trim()
    }

    private static int readInt(String prompt) {
        // WHILE(TRUE) loop with break and continue - keeps asking until
        // the user types a proper number, so the program never crashes.
        while (true) {
            String input = readLine(prompt);
            if (input.isEmpty()) {
                return 0;
            }
            try {
                int value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please type digits only.");
                continue;
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            String input = readLine(prompt);
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please type a proper amount, example 299.50");
            }
        }
    }

    private static Genre askGenre() {
        System.out.println("Genre : 1) FICTION 2) SCIENCE 3) TECHNOLOGY 4) HISTORY 5) COMICS");
        int g = readInt("Your choice : ");

        switch (g) {
            case 1:
                return Genre.FICTION;
            case 2:
                return Genre.SCIENCE;
            case 3:
                return Genre.TECHNOLOGY;
            case 4:
                return Genre.HISTORY;
            case 5:
                return Genre.COMICS;
            default:
                System.out.println("Not a valid genre, taking FICTION as default.");
                return Genre.FICTION;
        }
    }
}
