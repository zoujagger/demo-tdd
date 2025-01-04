package com.zouj.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Bibliotheque {

    private final List<String> books = new ArrayList<>();

    public List<String> books() {
        return books;
    }

    public void ajouteBook(String... bookNames) {
        Arrays.stream(bookNames).forEach(books::add);
    }

}
