package com.zouj.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Feat1: As a user, I want to add multiple books to my bookshelf so that I can
 * read them later.
 * Feat2: As a user, I should be able to arrange my bookshelf based on certain
 * criteria
 */
class BibliothequeTest {

    private Bibliotheque biblio;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;

    @BeforeEach
    void init() throws Exception {
        biblio = new Bibliotheque();
        effectiveJava = new Book("Effective Java", "Joshua Bloch", LocalDate.of(2008, Month.MAY, 8));
        codeComplete = new Book("Code Complete", "Steve McConnel", LocalDate.of(2004, Month.JUNE, 9));
        mythicalManMonth = new Book("The Mythical Man-Month", "Frederick Phillips Brooks",
                LocalDate.of(1975, Month.JANUARY, 1));
    }

    // Feat1
    @Test
    void emptyBibliothequeWhenNoBookAdded() {
        List<Book> books = biblio.books();
        assertTrue(books.isEmpty(), () -> "Bibliotheque should be empty.");
    }

    @Test
    void BibliothequeContainsTwoBookWhenTwoBooksAdded() {
        biblio.ajouteBook(effectiveJava, codeComplete);
        // biblio.ajouteBook("Code Complete");

        List<Book> books = biblio.books();

        assertEquals(2, books.size(), () -> "Bibliotheque shold have two books");

    }

    @Test
    void booksReturnedFromBibliothequeIsImmutableForClient() {
        biblio.ajouteBook(effectiveJava, codeComplete);
        List<Book> books = biblio.books();
        try {
            books.add(mythicalManMonth);
            fail(() -> "Should not be able to add book to books");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException.");
        }
    }

    // Feat2
    @Test
    void shouldArrangeBibliothequeBasedOnBookTitle() {

        Bibliotheque shelf = new Bibliotheque();
        shelf.ajouteBook(effectiveJava, codeComplete, mythicalManMonth);
        List<Book> books = shelf.arrange();
        assertEquals(Arrays.asList(codeComplete, effectiveJava, mythicalManMonth), books,
                () -> "Books in a bookshelf should be arranged lexicographically by book title");

    }

    @Test
    void booksInBibliothequeAreInInsertionOrderAfterCallingArrange() {
        Bibliotheque shelf = new Bibliotheque();
        shelf.ajouteBook(effectiveJava, codeComplete, mythicalManMonth);
        shelf.arrange();
        List<Book> books = shelf.books();
        assertEquals(Arrays.asList(effectiveJava, codeComplete, mythicalManMonth), books,
                () -> "Books in bookshelf are in insertion order");
    }

    @Test
    void bookshelfArrangedByUserProvidedCriteria() {
        biblio.ajouteBook(effectiveJava, codeComplete, mythicalManMonth);
        List<Book> books = biblio.arrange(Comparator.<Book>naturalOrder().reversed());
        assertEquals(Arrays.asList(mythicalManMonth, effectiveJava, codeComplete), books,
                () -> "Books in a bookshelf are arranged in descending order of book title");
    }

}
