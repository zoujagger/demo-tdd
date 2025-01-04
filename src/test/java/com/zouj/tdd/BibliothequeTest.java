package com.zouj.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
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

    @BeforeEach
    void init() throws Exception {
        biblio = new Bibliotheque();
    }

    // Feat1
    @Test
    void emptyBibliothequeWhenNoBookAdded() {
        List<String> books = biblio.books();
        assertTrue(books.isEmpty(), () -> "Bibliotheque should be empty.");
    }

    @Test
    void BibliothequeContainsTwoBookWhenTwoBooksAdded() {
        biblio.ajouteBook("Effective Java", "Code Complete");
        // biblio.ajouteBook("Code Complete");

        List<String> books = biblio.books();

        assertEquals(2, books.size(), () -> "Bibliotheque shold have two books");

    }

    @Test
    void booksReturnedFromBibliothequeIsImmutableForClient() {
        biblio.ajouteBook("Effective Java", "Code Complete");
        List<String> books = biblio.books();
        try {
            books.add("The Mythical Man-Month");
            fail(() -> "Should not be able to add book to books");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException.");
        }
    }

    // Feat2
    @Test
    void shouldArrangeBibliothequeBasedOnBookTitle() {

        Bibliotheque shelf = new Bibliotheque();
        shelf.ajouteBook("Effective Java", "Code Complete", "The Mythical Man-Month");
        List<String> books = shelf.arrange();
        assertEquals(Arrays.asList("Code Complete", "Effective Java", "The Mythical Man-Month"), books,
                () -> "Books in a bookshelf should be arranged lexicographically by book title");

    }

    @Test
    void booksInBibliothequeAreInInsertionOrderAfterCallingArrange() {
        Bibliotheque shelf = new Bibliotheque();
        shelf.ajouteBook("Effective Java", "Code Complete", "The Mythical Man-Month");
        shelf.arrange();
        List<String> books = shelf.books();
        assertEquals(Arrays.asList("Effective Java", "Code Complete", "The Mythical Man-Month"), books,
                () -> "Books in bookshelf are in insertion order");
    }

}
