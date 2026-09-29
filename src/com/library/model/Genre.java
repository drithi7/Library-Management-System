package com.library.model;

/*
 * ENUM #1 (simple)
 * Categories of books kept in our library.
 * I used an enum instead of a String because then a wrong category
 * like "Ficton" can never get stored by mistake.
 */
public enum Genre {
    FICTION,
    SCIENCE,
    TECHNOLOGY,
    HISTORY,
    COMICS
}
