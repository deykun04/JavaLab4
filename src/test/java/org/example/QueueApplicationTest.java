package org.example;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class QueueApplicationTest {
    @Test
    void testChangePriorityByLastName() {
        LinkedList<QueueApplication.QueuePerson> queue = new LinkedList<>();
        QueueApplication.QueuePerson.addInitialPersons(queue);

        String lastNameToChange = "Іванов";
        int newPriority = 10;

        // Зміна пріоритету для існуючого черговика
        QueueApplication.QueuePerson.changePriorityByLastName(queue, lastNameToChange, newPriority);

        // Перевірка, чи змінилася пріоритет для вказаного прізвища
        boolean found = false;
        for (QueueApplication.QueuePerson person : queue) {
            if (person.lastName.equals(lastNameToChange) && person.priority == newPriority) {
                found = true;
                break;
            }
        }

        assertTrue(found, "Пріоритет не змінений або черговик не знайдено");
    }

    @Test
    void testChangePriorityByLastNameNotFound() {
        LinkedList<QueueApplication.QueuePerson> queue = new LinkedList<>();
        QueueApplication.QueuePerson.addInitialPersons(queue);

        String nonExistentLastName = "Незнайомець";
        int newPriority = 10;

        // Зміна пріоритету для черговика, якого немає в списку
        QueueApplication.QueuePerson.changePriorityByLastName(queue, nonExistentLastName, newPriority);

        // Перевірка, чи черговик із заданим прізвищем відсутній в списку
        boolean found = false;
        for (QueueApplication.QueuePerson person : queue) {
            if (person.lastName.equals(nonExistentLastName)) {
                found = true;
                break;
            }
        }

        assertFalse(found, "Черговика з невірним прізвищем не повинно бути в списку");
    }
    @Test
    void testAddPerson() {
        LinkedList<QueueApplication.QueuePerson> queue = new LinkedList<>();
        Scanner scanner = new Scanner("Новий\nОсоба\nІванович\nМісто\nВулиця\n1\n0\n10\n");

        int initialSize = queue.size();

        // Додавання нового черговика
        QueueApplication.QueuePerson.addPerson(queue, scanner);

        // Перевірка, чи черговик дійсно доданий
        assertEquals(initialSize + 1, queue.size(), "Не вдалося додати нового черговика");
    }

    @Test
    void testRemovePerson() {
        LinkedList<QueueApplication.QueuePerson> queue = new LinkedList<>();
        QueueApplication.QueuePerson.addInitialPersons(queue);

        String lastNameToRemove = "Іванов";

        int initialSize = queue.size();

        // Видалення існуючого черговика
        QueueApplication.QueuePerson.removePerson(queue, lastNameToRemove);

        // Перевірка, чи черговик дійсно видалений
        assertEquals(initialSize - 1, queue.size(), "Не вдалося видалити існуючого черговика");

        // Перевірка, чи черговика з заданим прізвищем вже немає в списку
        boolean found = false;
        for (QueueApplication.QueuePerson person : queue) {
            if (person.lastName.equals(lastNameToRemove)) {
                found = true;
                break;
            }
        }

        assertFalse(found, "Видалений черговик все ще присутній в списку");
    }

}