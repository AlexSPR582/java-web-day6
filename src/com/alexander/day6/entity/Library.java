package com.alexander.day6.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {
    private static final int MAX_CAPACITY = 2000;
    private static Library library;

    private List<Book> books;

    public static Library getInstance() {
        if (library == null) {
            library = new Library();
        }
        return library;
    }

    private Library() {
        books = new ArrayList<>();
    }

    public boolean addBook(Book book) {
        if (books.contains(book) || books.size() + 1 > MAX_CAPACITY) {
            return false;
        }
        return books.add(book);
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }
}
