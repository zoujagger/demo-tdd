package com.zouj.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static java.util.Collections.singletonList;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Feat1: As a user, I want to add multiple books to my bookshelf so that I can
 * read them later.
 * Feat2: As a user, I should be able to arrange my bookshelf based on certain
 * criteria
 * Feat3: As a user, I should be able to group books in my bookshelf based on
 * certain criteria
 */
class BibliothequeTest {

    private Bibliotheque biblio;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;

    @BeforeEach
    void init() throws Exception {
        biblio = new Bibliotheque();
        effectiveJava = new Book("Effective Java", "Joshua Bloch", LocalDate.of(2008, Month.MAY, 8));
        codeComplete = new Book("Code Complete", "Steve McConnel", LocalDate.of(2004, Month.JUNE, 9));
        mythicalManMonth = new Book("The Mythical Man-Month", "Frederick Phillips Brooks",
                LocalDate.of(1975, Month.JANUARY, 1));
        cleanCode = new Book("Clean Code", "Robert C. Martin", LocalDate.of(2008, Month.AUGUST, 1));
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
    // @Disabled("Needs to implement Comparator")
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

    @Test
    void bookshelfArrangedByUserProvidedCriteria2() {
        biblio.ajouteBook(effectiveJava, codeComplete, mythicalManMonth);
        Comparator<Book> reversed = Comparator.<Book>naturalOrder().reversed();
        List<Book> books = biblio.arrange(reversed);
        assertThat(books).isSortedAccordingTo(reversed);
    }

    // Feat3
    @Test
    @DisplayName("books inside bookshelf are grouped by publication year")
    void groupBooksInsideBookShelfByPublicationYear() {
        biblio.ajouteBook(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
        Map<Year, List<Book>> booksByPublicationYear = biblio.groupByPublicationYear();
        assertThat(booksByPublicationYear)
                .containsKey(Year.of(2008))
                .containsValues(Arrays.asList(effectiveJava, cleanCode));
        assertThat(booksByPublicationYear)
                .containsKey(Year.of(2004))
                .containsValues(singletonList(codeComplete));
    }

    @Test
    @DisplayName("books inside bookshelf are grouped according to user providedcriteria(group by author name)")
    void groupBooksByUserProvidedCriteria() {
        biblio.ajouteBook(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
        Map<String, List<Book>> booksByAuthor = biblio.groupBy(Book::getAuthor);

        assertThat(booksByAuthor)
                .containsKey("Joshua Bloch")
                .containsValues(singletonList(effectiveJava));
        assertThat(booksByAuthor)
                .containsKey("Steve McConnel")
                .containsValues(singletonList(codeComplete));
        assertThat(booksByAuthor)
                .containsKey("Frederick Phillips Brooks")
                .containsValues(singletonList(mythicalManMonth));
        assertThat(booksByAuthor)
                .containsKey("Robert C. Martin")
                .containsValues(singletonList(cleanCode));

    }

}
