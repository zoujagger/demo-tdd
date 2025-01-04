package com.zouj.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Bibliotheque {

    private final List<String> books = new ArrayList<>();

    public List<String> books() {
        return Collections.unmodifiableList(books);
    }

    public void ajouteBook(String... bookNames) {
        Arrays.stream(bookNames).forEach(books::add);
    }

    public List<String> arrange() {
        // books.sort(Comparator.naturalOrder());
        return books.stream().sorted().collect(Collectors.toList());
    }

}
