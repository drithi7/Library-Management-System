# Library Management System — Java Mini Project

**REVA University | School of Computer Science and Engineering**
**Program:** B.Sc. (BSTCs)
**Semester:** V
**Subject:** OOP Java
**Domain:** Library Management System

**Submitted by:** M Drithi Reddy
**SRN:** R24SA023

---

## 1. About the Project

The **Library Management System** is a console-based Java application developed as part of the Java Programming course.

The main idea behind this project is to create a simple system that can help a librarian manage books and library members. The program allows users to add and search for books, register members, issue and return books, calculate late fines, sort books, and view basic library statistics.

The project is developed using **Core Java**, without using external libraries. I have also used the main Java concepts covered in the course, including classes and objects, inheritance, interfaces, abstract classes, method overloading and overriding, constructors, arrays, enums, loops, conditional statements, `static` and `final` members, and exception handling.

The project uses an inheritance hierarchy where `Person` is the parent class of `Member` and `Librarian`. The `Member` class also implements the `Loanable` interface because members can borrow and return books, whereas librarians cannot borrow books.

---

## 2. Project Structure

The project is divided into three packages so that the code is easier to understand and manage.

```text
src/
└── com/
    └── library/
        ├── model/
        │   ├── Person.java
        │   ├── Member.java
        │   ├── Librarian.java
        │   ├── Book.java
        │   ├── Loanable.java
        │   ├── Genre.java
        │   └── MembershipType.java
        │
        ├── service/
        │   ├── LibraryService.java
        │   └── FineCalculator.java
        │
        └── app/
            └── LibraryApp.java
```

### `model` package

This package contains the main data classes used by the library.

* **Person** – Abstract parent class for people in the library.
* **Member** – Represents a library member and manages borrowed books.
* **Librarian** – Represents a librarian.
* **Book** – Stores information about books.
* **Loanable** – Interface containing borrowing-related methods.
* **Genre** – Enum containing different book genres.
* **MembershipType** – Enum containing Student, Teacher and Guest membership types.

### `service` package

This package contains the main logic of the application.

* **LibraryService** – Handles books, members, searching, issuing, returning, sorting and statistics.
* **FineCalculator** – Calculates late-return fines.

### `app` package

* **LibraryApp** – Contains the `main()` method and the console menu through which the user interacts with the system.

---

## 3. How to Run the Project

The project can be run using any Java IDE such as **IntelliJ IDEA, Eclipse or VS Code**.

Make sure Java is installed on the system.

From the project folder, the program can also be compiled using:

```bash
javac -d bin src/com/library/model/*.java src/com/library/service/*.java src/com/library/app/*.java
```

Then run the application using:

```bash
java -cp bin com.library.app.LibraryApp
```

The project is designed to work with **JDK 8 or above**.

---

## 4. Main Features

The application provides the following features:

1. Display all books in the library.
2. Search for a book using its ID.
3. Search for books using the title or author's name.
4. Search for books based on genre.
5. Issue a book to a member.
6. Return a borrowed book.
7. Calculate a fine when a book is returned late.
8. Display only the currently available books.
9. Register a new library member.
10. Add a new book.
11. Display members and librarians.
12. Sort books alphabetically by title.
13. Display library statistics.

The program also checks basic conditions before issuing a book, such as whether the book is available and whether the member has reached their borrowing limit.

---

## 5. Java Concepts Used

One of the main purposes of this project is to demonstrate the Java concepts learned in class.

### Classes and Encapsulation

The project contains several classes such as `Person`, `Member`, `Librarian` and `Book`. Their data members are kept private and are accessed through getters and setters.

### Different Data Types

Different Java data types are used throughout the project, including:

* `int` for IDs and counts
* `String` for names and titles
* `double` for prices and fines
* `boolean` for book availability
* `char` for rack identification
* Enums for genre and membership type

### Constructors

Constructor overloading is used in classes such as `Person`, `Book` and `Member`. Both default and parameterized constructors are provided.

Constructor chaining using `this()` is also used to avoid repeating initialization code.

### Inheritance

The project uses the following inheritance structure:

```text
             Person
             /    \
            /      \
       Member    Librarian
```

Both `Member` and `Librarian` inherit common information from `Person`.

### Abstract Class

`Person` is an abstract class and contains the abstract method:

```java
getRole()
```

The subclasses provide their own implementation of this method.

### Interface

The `Loanable` interface defines operations related to borrowing and returning books.

The `Member` class implements this interface because members are the people who borrow books.

### Method Overloading

The `LibraryService` class contains overloaded `searchBook()` methods that allow books to be searched using:

* Book ID
* Keyword
* Genre

### Method Overriding

Methods such as `getRole()` and `toString()` are overridden in the subclasses.

This also demonstrates **runtime polymorphism and dynamic method binding**.

### `static` and `final`

The project uses static variables to keep track of values such as the total number of books, members and people.

`final` constants are also used for values that should not change, such as the library code and borrowing limits.

### Arrays

Arrays are used to store books and members. Each member also has an array for keeping track of the books they have borrowed.

### Enums

Two enums are used:

```text
Genre
- FICTION
- SCIENCE
- TECHNOLOGY
- HISTORY
- COMICS
```

and

```text
MembershipType
- STUDENT
- TEACHER
- GUEST
```

The membership type also stores information such as the maximum number of books a member can borrow and the fine charged per day.

### Control Statements and Loops

The project uses:

* `if-else`
* `switch`
* `for`
* `while`
* `do-while`
* enhanced `for` loop
* `break`
* `continue`
* `return`

These are used in different parts of the application for menu handling, searching, validation and processing library records.

### String Methods

Several methods from the Java `String` class are used, including:

* `trim()`
* `split()`
* `toLowerCase()`
* `toUpperCase()`
* `contains()`
* `equalsIgnoreCase()`
* `compareToIgnoreCase()`
* `length()`
* `charAt()`

---

## 6. Fine Calculation

The system calculates a fine when a member returns a book late.

Each membership type has a different fine rate. There are also **2 grace days** and a fixed processing charge of **Rs. 5**.

For example, if a Student member returns a book **5 days late**:

```text
Grace period       = 2 days
Total late days    = 5 days
Chargeable days    = 5 - 2 = 3 days

Fine = 3 × Rs.1.50 + Rs.5.00
     = Rs.9.50

Rounded fine = Rs.10
```

This part of the project also demonstrates operator precedence because multiplication is performed before addition.

---

## 7. Example of the Application

When the program starts, the user gets a menu similar to:

```text
========== LIBRARY MANAGEMENT SYSTEM ==========

1. Show all books
2. Search books
3. Issue a book
4. Return a book
5. Show available books
6. Register new member
7. Add new book
8. Display members and librarians
9. Sort books by title
10. Show statistics
0. Exit
```

For example, selecting **Issue a Book** asks for the member ID and book ID.

If the book is available and the member is within their borrowing limit:

```text
Book issued successfully.
```

If the book has already been issued:

```text
Unable to issue book. Check member, availability, or borrowing limit.
```

---

## 8. UML / Class Relationship

The main class relationship in the project is:

```text
                    <<abstract>>
                       Person
                          |
              +-----------+-----------+
              |                       |
           Member                 Librarian
              |
       implements
              |
          <<interface>>
            Loanable

Member
   |
   | borrows 0..5
   v
  Book
```

`LibraryService` manages the books and members, while `FineCalculator` handles the fine calculation.

---

## 9. Possible Improvements

The current version is intentionally kept simple and uses arrays so that the Java concepts required for the course are clearly demonstrated.

If the project were expanded further, I would add:

* File or database storage so that data is not lost when the program closes.
* A separate `Transaction` class to store issue dates and due dates.
* Automatic calculation of overdue days instead of asking the user to enter them.
* `ArrayList` or other collection classes instead of fixed-size arrays.
* A graphical user interface for easier interaction.
* Login functionality for librarians and members.

---

## 10. Conclusion

This project helped me understand how different Java concepts can be combined to build a small but functional application.

Instead of demonstrating each concept separately, the Library Management System uses them together in a practical example. The project covers object-oriented programming, inheritance, abstraction, interfaces, polymorphism, encapsulation, constructors, arrays, enums, control statements and basic exception handling.

Overall, the project provides a simple working model of how a library could manage its books, members and borrowing activities using Core Java.
