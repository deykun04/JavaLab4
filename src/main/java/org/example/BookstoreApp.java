package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookstoreApp {

    public static class Book {
        private final String title;
        private final String author;
        private final String publisher;
        private final int year;
        private final float price;

        public Book(String title, String author, String publisher, int year, float price) {
            this.title = title;
            this.author = author;
            this.publisher = publisher;
            this.year = year;
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getPublisher() {
            return publisher;
        }

        public int getYear() {
            return year;
        }

        public float getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Title: " + title + ", Author: " + author + ", Publisher: " + publisher +
                    ", Year: " + year + ", Price: " + price;
        }
    }

    public static void main(String[] args) {
        HashMap<Integer, Book> catalog = new HashMap<>();

        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Enter details for book " + i + ":");
                System.out.print("Title: ");
                String title = scanner.nextLine();

                System.out.print("Author: ");
                String author = scanner.nextLine();

                System.out.print("Publisher: ");
                String publisher = scanner.nextLine();

                System.out.print("Year: ");
                int year = scanner.nextInt();

                System.out.print("Price: ");
                float price = scanner.nextFloat();
                scanner.nextLine(); // Consume the newline character after reading the float

                catalog.put(i, new Book(title, author, publisher, year, price));
            }

            // Виведення каталогу
            System.out.println("\nCatalog before sorting:");
            for (Book book : catalog.values()) {
                System.out.println(book);
            }

            // Сортування за видавництвом
            ArrayList<Book> sortedByPublisher = new ArrayList<>(catalog.values());
            sortedByPublisher.sort(Comparator.comparing(Book::getPublisher));
            System.out.println("\nCatalog after sorting by publisher:");
            for (Book book : sortedByPublisher) {
                System.out.println(book);
            }

            // Сортування за ціною
            ArrayList<Book> sortedByPrice = new ArrayList<>(catalog.values());
            sortedByPrice.sort(Comparator.comparing(Book::getPrice));
            System.out.println("\nCatalog after sorting by price:");
            for (Book book : sortedByPrice) {
                System.out.println(book);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
}
