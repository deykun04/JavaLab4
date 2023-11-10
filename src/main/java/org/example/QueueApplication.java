package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class QueueApplication {
   public static class AddressValue {
        String city;
        String street;
        int houseNumber;
        int apartmentNumber;

        public AddressValue(String city, String street, int houseNumber, int apartmentNumber) {
            this.city = city;
            this.street = street;
            this.houseNumber = houseNumber;
            this.apartmentNumber = apartmentNumber;
        }
    }

    public static class QueuePerson {
        String lastName;
        String firstName;
        String middleName;
        AddressValue address;
        int priority;

        public QueuePerson(String lastName, String firstName, String middleName, AddressValue address, int priority) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.middleName = middleName;
            this.address = address;
            this.priority = priority;
        }

        // Метод для оновлення пріоритету черговика
        public void updatePriorityAndMoveToEnd(int newPriority, Queue<QueuePerson> queue) {
            this.priority = newPriority;
            queue.remove(this); // Видаляємо поточну особу з черги
            queue.add(this);    // Додаємо її на кінець черги
        }

        @Override
        public String toString() {
            return "Name: " + lastName + " " + firstName + " " + middleName + "\n" +
                    "Address: " + address.city + ", " + address.street + ", " + address.houseNumber;
        }
    }
    public static void main(String[] args) {
        Queue<QueuePerson> queue = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        // Зчитування даних з клавіатури та додавання черговиків до черги
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter data for QueuePerson " + (i + 1));
            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Middle Name: ");
            String middleName = scanner.nextLine();
            System.out.print("City: ");
            String city = scanner.nextLine();
            System.out.print("Street: ");
            String street = scanner.nextLine();
            System.out.print("House Number: ");
            int houseNumber = Integer.parseInt(scanner.nextLine());
            System.out.print("Apartment Number (0 if none): ");
            int apartmentNumber = Integer.parseInt(scanner.nextLine());
            System.out.print("Priority: ");
            int priority = Integer.parseInt(scanner.nextLine());

            AddressValue address = new AddressValue(city, street, houseNumber, apartmentNumber);
            QueuePerson person = new QueuePerson(lastName, firstName, middleName, address, priority);
            queue.add(person);
        }
        for (QueuePerson person : queue) {
            System.out.println("Priority: " + person.priority);
            System.out.println(person);
            System.out.println("--------------------");
        }
        // Оновлення пріоритету черговика (приклад)
        System.out.print("Enter the index of the person to update priority: ");
        int indexToUpdate = Integer.parseInt(scanner.nextLine());

        if (indexToUpdate >= 0 && indexToUpdate < queue.size()) {
            System.out.print("Enter the new priority: ");
            int newPriority = Integer.parseInt(scanner.nextLine());
            QueuePerson personToUpdate = (QueuePerson) queue.toArray()[indexToUpdate];
            personToUpdate.updatePriorityAndMoveToEnd(newPriority,queue);
        } else {
            System.out.println("Invalid index.");
        }

        // Виведення всіх черговиків в порядку оновленого пріоритету
        for (QueuePerson person : queue) {
            System.out.println("Priority: " + person.priority);
            System.out.println(person);
            System.out.println("--------------------");
        }
    }
}
