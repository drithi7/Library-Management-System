package com.library.model;

/*
 * INTERFACE
 * Anybody who is allowed to take a book from the library must be able
 * to do these three things. Right now only Member implements it, but
 * tomorrow if the library allows another college to borrow books,
 * that class can also implement Loanable without changing anything here.
 */
public interface Loanable {

    // interface variable -> automatically public static final
    int MAX_RENEWALS = 2;

    boolean borrowBook(Book b);

    boolean giveBackBook(Book b);

    int getBooksHeld();
}
