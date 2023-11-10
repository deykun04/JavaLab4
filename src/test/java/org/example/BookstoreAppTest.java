package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BookstoreAppTest {

    @Test
    void testSortByPublisher() {
        HashMap<Integer, BookstoreApp.Book> catalog = new HashMap<>();
        catalog.put(1, new BookstoreApp.Book("Book1", "Author1", "Publisher1", 2020, 25.5f));
        catalog.put(2, new BookstoreApp.Book("Book2", "Author2", "Publisher2", 2019, 30.0f));
        catalog.put(3, new BookstoreApp.Book("Book3", "Author3", "Publisher1", 2021, 20.0f));

        ArrayList<BookstoreApp.Book> sortedByPublisher = BookstoreApp.Book.sortByPublisher(catalog);

        assertEquals("Publisher1", sortedByPublisher.get(0).getPublisher());
        assertEquals("Publisher1", sortedByPublisher.get(1).getPublisher());
        assertEquals("Publisher2", sortedByPublisher.get(2).getPublisher());
    }

    @Test
    void testSortByPrice() {
        HashMap<Integer, BookstoreApp.Book> catalog = new HashMap<>();
        catalog.put(1, new BookstoreApp.Book("Book1", "Author1", "Publisher1", 2020, 25.5f));
        catalog.put(2, new BookstoreApp.Book("Book2", "Author2", "Publisher2", 2019, 30.0f));
        catalog.put(3, new BookstoreApp.Book("Book3", "Author3", "Publisher1", 2021, 20.0f));

        ArrayList<BookstoreApp.Book> sortedByPrice = BookstoreApp.Book.sortByPrice(catalog);

        assertEquals(20.0f, sortedByPrice.get(0).getPrice(), 0.01);
        assertEquals(25.5f, sortedByPrice.get(1).getPrice(), 0.01);
        assertEquals(30.0f, sortedByPrice.get(2).getPrice(), 0.01);
    }
    @Test
    void testGetTitle() {
        BookstoreApp.Book book = new BookstoreApp.Book("Title", "Author", "Publisher", 2022, 29.99f);
        assertEquals("Title", book.getTitle());
    }

    @Test
    void testGetAuthor() {
        BookstoreApp.Book book = new BookstoreApp.Book("Title", "Author", "Publisher", 2022, 29.99f);
        assertEquals("Author", book.getAuthor());
    }

    @Test
    void testGetPublisher() {
        BookstoreApp.Book book = new BookstoreApp.Book("Title", "Author", "Publisher", 2022, 29.99f);
        assertEquals("Publisher", book.getPublisher());
    }

    @Test
    void testGetYear() {
        BookstoreApp.Book book = new BookstoreApp.Book("Title", "Author", "Publisher", 2022, 29.99f);
        assertEquals(2022, book.getYear());
    }

    @Test
    void testGetPrice() {
        BookstoreApp.Book book = new BookstoreApp.Book("Title", "Author", "Publisher", 2022, 29.99f);
        assertEquals(29.99f, book.getPrice(), 0.01);
    }

}