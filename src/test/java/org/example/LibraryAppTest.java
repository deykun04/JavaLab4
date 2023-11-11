package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class LibraryAppTest {

    @Test
    void addAbonentTest() {
        LibraryApp.Abonent abonent = new LibraryApp.Abonent("TestLastName", "TestFirstName", "TestAddress");
        assertEquals("TestLastName", abonent.getLastName());
        assertEquals("TestFirstName", abonent.getFirstName());
        assertEquals("TestAddress", abonent.getAddress());
    }

    @Test
    void createInitialAbonentsTest() {
       LibraryApp.Abonent abonent = LibraryApp.Abonent.createInitialAbonents().get("+380989846587");
        assertEquals("Maslov", abonent.getLastName());
        assertEquals("Oleksandr", abonent.getFirstName());
        assertEquals("Lermovtova 6", abonent.getAddress());
    }
    @Test
    void removeAbonentTest() {
        HashMap<String, LibraryApp.Abonent> abonents = LibraryApp.Abonent.createInitialAbonents();
        String phoneNumberToRemove = "+380989846587";

        // Захоплення виводу консолі для аналізу результату
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        LibraryApp.Abonent.removeAbonent(phoneNumberToRemove, abonents);

        // Повертаємо стандартний ввід та вивід
        System.setIn(System.in);
        System.setOut(System.out);

        assertNull(abonents.get(phoneNumberToRemove));

        // Обрізаємо символи нового рядка та пробіли з кінця
        assertEquals("Абонент з номером " + phoneNumberToRemove + " видалений.", outContent.toString().trim());
    }
    @Test
    void searchAbonentTest() {
        HashMap<String, LibraryApp.Abonent> abonents = LibraryApp.Abonent.createInitialAbonents();
        String searchNumber = "+380989846587";

        // Захоплення виводу консолі для аналізу результату
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        LibraryApp.Abonent.searchAbonent(searchNumber, abonents);

        // Повертаємо стандартний ввід та вивід
        System.setIn(System.in);
        System.setOut(System.out);

        LibraryApp.Abonent foundAbonent = abonents.get(searchNumber);
        assertNotNull(foundAbonent);
        assertEquals("Знайдений абонент з номером " + searchNumber + ": " + foundAbonent, outContent.toString().trim());
    }



}