package com.zouj.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("search")
public class SearchBibliothequeTest {

    private Bibliotheque biblio;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;
    private Book refactoring;

    @BeforeEach
    void init() {
        biblio = new Bibliotheque();

        effectiveJava = new Book("Effective Java", "Joshua Bloch", LocalDate.of(2008,
                Month.MAY, 8));
        codeComplete = new Book("Code Complete", "Steve McConnel", LocalDate.of(2004,
                Month.JUNE, 9));
        mythicalManMonth = new Book("The Mythical Man-Month", "Frederick Phillips Brooks",
                LocalDate.of(1975, Month.JANUARY, 1));
        cleanCode = new Book("Clean Code", "Robert C. Martin", LocalDate.of(2008,
                Month.AUGUST, 1));
        refactoring = new Book("Refactoring: Improving the Design of Existing Code", "Martin Fowler",
                LocalDate.of(2002, Month.MARCH, 9));

        biblio.ajouteBook(effectiveJava, codeComplete, mythicalManMonth, cleanCode, refactoring);
    }

    @Test
    @DisplayName(" should find books with title containing text")
    void shouldFindBooksWithTitleContainingText() {
        List<Book> books = biblio.findBooksByTitle("Clean");
        assertThat(books.size()).isEqualTo(1);
    }

    @Test
    @DisplayName(" should find books with title containing text and published after specified date.")
    void shouldFilterSearchedBooksBasedOnPublishedDate() {
        List<Book> books = biblio.findBooksByTitle("Code", b -> b.getPublishedOn().isBefore(LocalDate.of(2014, 12, 31)));
        assertThat(books.size()).isEqualTo(2);
    }

}
