package com.zouj.tdd;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Bibliotheque {

    private final List<Book> books = new ArrayList<>();

    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }

    public void ajouteBook(Book... booksToAdd) {
        Arrays.stream(booksToAdd).forEach(books::add);
    }

    public List<Book> arrange() {
        // books.sort(Comparator.naturalOrder());
        // return books.stream().sorted().collect(Collectors.toList());
        return arrange(Comparator.naturalOrder());
    }

    public List<Book> arrange(Comparator<Book> criteria) {
        return books.stream().sorted(criteria).collect(Collectors.toList());
    }

    public Map<Year, List<Book>> groupByPublicationYear() {
        // return books
        // .stream()
        // .collect(Collectors.groupingBy(book ->
        // Year.of(book.getPublishedOn().getYear())));
        return groupBy(book -> Year.of(book.getPublishedOn().getYear()));
    }

    public <K> Map<K, List<Book>> groupBy(Function<Book, K> fx) {
        return books
                .stream()
                .collect(Collectors.groupingBy(fx));
    }

    public Progress progress() {
        int booksRead = Long.valueOf(books.stream().filter(Book::isRead).count()).intValue();
        int booksInProgress = Long.valueOf(books.stream().filter(Book::isProgress).count()).intValue();
        int booksToRead = books.size() - booksRead - booksInProgress;

        int percentageCompleted = booksRead * 100 / books.size();
        int percentageToRead = booksToRead * 100 / books.size();
        int percentageInProgress = booksInProgress * 100 / books.size();

        return new Progress(percentageCompleted, percentageToRead, percentageInProgress);
        // return new Progress(0, 100, 0);
    }

    public List<Book> findBooksByTitle(String title) {
        // return books.stream().filter(book ->
        // book.getTitle().contains(keyword)).collect(Collectors.toList());
        return findBooksByTitle(title, b -> true);
    }

    public List<Book> findBooksByTitle(String title, Predicate<Book> filter) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title))
                .filter(filter)
                .collect(Collectors.toList());
    }
}
