package org.example;

import java.util.Comparator;
import java.util.LinkedList;
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
        public String lastName;
        public String firstName;
        public String middleName;
        public AddressValue address;
        public int priority;

        public QueuePerson(String lastName, String firstName, String middleName, AddressValue address, int priority) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.middleName = middleName;
            this.address = address;
            this.priority = priority;
        }

        public  static void changePriorityByLastName(LinkedList<QueuePerson> queue,String lastName, int newPriority ) {
            boolean found = false;
            for (QueuePerson person : queue) {
                if (person.lastName.equals(lastName)) {
                    person.priority = newPriority;
                    found = true;
                    break;
                }
            }

            if (found) {
                queue.sort(Comparator.comparingInt(p -> p.priority));
                System.out.println("Пріоритет для " + lastName + " успішно змінений.");
            } else {
                System.out.println("Черговика з прізвищем " + lastName + " не знайдено.");
            }
        }

        // Додаємо початкові записи
        public static void addInitialPersons(LinkedList<QueuePerson> queue) {
            queue.add(new QueuePerson("Іванов", "Іван", "Іванович", new AddressValue("Київ", "Вулиця 1", 10, 5), 3));
            queue.add(new QueuePerson("Петров", "Петро", "Петрович", new AddressValue("Львів", "Вулиця 2", 5, 0), 1));
            queue.add(new QueuePerson("Сидоров", "Олег", "Володимирович", new AddressValue("Харків", "Вулиця 3", 7, 2), 4));
            queue.add(new QueuePerson("Коваленко", "Марія", "Анатоліївна", new AddressValue("Одеса", "Вулиця 4", 12, 0), 2));
            queue.add(new QueuePerson("Бойко", "Тетяна", "Вікторівна", new AddressValue("Дніпро", "Вулиця 5", 8, 3), 5));
        }

        // Виводимо список черговиків
        public static void printQueue(LinkedList<QueuePerson> queue) {
            System.out.println("\nСписок черговиків:");
            for (QueuePerson person : queue) {
                System.out.println(person.lastName + " " + person.firstName + " " + person.middleName +
                        " - Пріоритет: " + person.priority + ", Адреса: " + getAddressString(person.address));
            }
        }

        // Додаємо нового черговика
        public static void addPerson(LinkedList<QueuePerson> queue,Scanner scanner) {
            System.out.println("\nДодавання нового черговика:");

            System.out.print("Прізвище: ");
            String lastName = scanner.nextLine();

            System.out.print("Ім'я: ");
            String firstName = scanner.nextLine();

            System.out.print("По батькові: ");
            String middleName = scanner.nextLine();

            System.out.print("Місто: ");
            String city = scanner.nextLine();

            System.out.print("Вулиця: ");
            String street = scanner.nextLine();

            System.out.print("Будинок: ");
            int houseNumber = scanner.nextInt();
            scanner.nextLine(); // Очищення буфера введення

            System.out.print("Квартира (0 якщо немає): ");
            int apartmentNumber = scanner.nextInt();
            scanner.nextLine(); // Очищення буфера введення

            System.out.print("Пріоритет: ");
            int priority = scanner.nextInt();
            scanner.nextLine(); // Очищення буфера введення

            AddressValue address = new AddressValue(city, street, houseNumber, apartmentNumber);
            QueuePerson newPerson = new QueuePerson(lastName, firstName, middleName, address, priority);

            queue.add(newPerson);
            queue.sort(Comparator.comparingInt(p -> p.priority));

            System.out.println("Черговик " + newPerson.lastName + " " + newPerson.firstName + " успішно доданий!");
        }

        // Видаляємо черговика
        public static void removePerson(LinkedList<QueuePerson> queue,String lastName) {
            boolean removed = queue.removeIf(person -> person.lastName.equals(lastName));

            if (removed) {
                queue.sort(Comparator.comparingInt(p -> p.priority));
                System.out.println("Черговик " + lastName + " успішно видалений.");
            } else {
                System.out.println("Черговика з прізвищем " + lastName + " не знайдено.");
            }
        }

        // Метод для отримання рядкового представлення адреси
        private static String getAddressString(AddressValue address) {
            if (address.apartmentNumber == 0) {
                return address.city + ", " + address.street + ", буд. " + address.houseNumber;
            } else {
                return address.city + ", " + address.street + ", буд. " + address.houseNumber + ", кв. " + address.apartmentNumber;
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<QueuePerson> queue = new LinkedList<>();
        // Додаємо початкові записи
        QueuePerson.addInitialPersons(queue);
        Scanner scanner = new Scanner(System.in);
        // Запускаємо головне меню
        while (true) {
            System.out.println("\n----- Головне меню -----");
            System.out.println("1. Вивести список черговиків");
            System.out.println("2. Додати нового черговика");
            System.out.println("3. Видалити черговика");
            System.out.println("4. Змінити пріоритет за прізвищем");
            System.out.println("5. Вихід");
            System.out.print("Виберіть опцію: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очищення буфера введення

            switch (choice) {
                case 1 -> QueuePerson.printQueue(queue);
                case 2 -> QueuePerson.addPerson(queue, scanner);
                case 3 -> {
                    System.out.println("\nВидалення черговика:");
                    System.out.print("Введіть прізвище черговика для видалення: ");
                    String lastName = scanner.nextLine();
                    QueuePerson.removePerson(queue, lastName);
                }
                case 4 -> {
                    System.out.println("\nЗміна пріоритету за прізвищем:");
                    System.out.print("Введіть прізвище черговика: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Введіть новий пріоритет: ");
                    int newPriority = scanner.nextInt();
                    scanner.nextLine(); // Очищення буфера введення
                    QueuePerson.changePriorityByLastName(queue, lastName, newPriority);
                }
                case 5 -> {
                    System.out.println("Дякую за використання програми. До побачення!");
                    System.exit(0);
                }
                default -> System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

}

