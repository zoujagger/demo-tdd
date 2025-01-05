package com.zouj.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A bookshelf progress")
public class ProgressionBibliothequeTest {

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
        @DisplayName("is 0% completed and 100% to-read when no book is read yet")
        void progress100PercentUnread() {
                Progress progress = biblio.progress();
                assertThat(progress.completed()).isEqualTo(0);
                assertThat(progress.toRead()).isEqualTo(100);
        }

        @Test
        @DisplayName("is 40% completed and 60% to-read when 2 books are finished and 3 books not read yet")
        void progressWithCompletedAndToReadPercentages() {
                effectiveJava.startedReadingOn(LocalDate.of(2016, Month.JULY, 1));
                effectiveJava.finishedReadingOn(LocalDate.of(2016, Month.JULY, 31));
                cleanCode.startedReadingOn(LocalDate.of(2016, Month.AUGUST, 1));
                cleanCode.finishedReadingOn(LocalDate.of(2016, Month.AUGUST, 31));
                Progress progress = biblio.progress();
                assertThat(progress.completed()).isEqualTo(40);
                assertThat(progress.toRead()).isEqualTo(60);
        }

}
