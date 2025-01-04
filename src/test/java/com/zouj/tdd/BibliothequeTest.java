package com.zouj.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Feat1: As a user, I want to add multiple books to my bookshelf so that I can
 * read them later.
 */
class BibliothequeTest {

    @Test
    void emptyBibliothequeWhenNoBookAdded() {
        Bibliotheque biblio = new Bibliotheque();
        List<String> books = biblio.books();
        assertTrue(books.isEmpty(), () -> "Bibliotheque should be empty.");
    }

    @Test
    void BibliothequeContainsTwoBookWhenTwoBooksAdded() {
        Bibliotheque biblio = new Bibliotheque();

        biblio.ajouteBook("Effective Java", "Code Complete");
        // biblio.ajouteBook("Code Complete");

        List<String> books = biblio.books();

        assertEquals(2, books.size(), () -> "Bibliotheque shold have two books");

    }

    @Test
    void booksReturnedFromBookShelfIsImmutableForClient() {
        Bibliotheque biblio = new Bibliotheque();
        biblio.ajouteBook("Effective Java", "Code Complete");
        List<String> books = biblio.books();
        try {
            books.add("The Mythical Man-Month");
            fail(() -> "Should not be able to add book to books");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException.");
        }
    }

}
