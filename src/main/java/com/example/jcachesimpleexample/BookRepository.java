package com.example.jcachesimpleexample;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.springframework.stereotype.Component;

@Component
public class BookRepository {

    private static final String BOOK_NOT_FOUND = "No book found the isbn: $s";
    private final List<Book> books = new ArrayList<>();

    public BookRepository() {
        books.add(new Book("isbn1", "author1", "title1"));
        books.add(new Book("isbn2", "author2", "title2"));
        books.add(new Book("isbn3", "author3", "title3"));
    }

    public List<Book> findAll() {
        return books;
    }

    public Book add(Book book) {
        books.add(book);
        return book;
    }

    public Book findByIsbn(String isbn) {
        return books.stream()
                .filter(b -> b.isbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(BOOK_NOT_FOUND, isbn)));
    }

    public Book update(String isbn, Book book) {
        boolean success = false;
        final ListIterator<Book> iterator = books.listIterator();
        while (iterator.hasNext()) {
            final Book next = iterator.next();
            if (next.isbn().equals(book.isbn())) {
                // Replace element
                iterator.set(book);
                success = true;
                break;
            }
        }

        if (!success) {
            throw new IllegalArgumentException(String.format(BOOK_NOT_FOUND, isbn));
        }

        return book;
    }

    public void remove(String isbn) {
        if (!books.removeIf(b -> isbn.equals(b.isbn()))) {
            throw new IllegalArgumentException(String.format(BOOK_NOT_FOUND, isbn));
        }
    }

}