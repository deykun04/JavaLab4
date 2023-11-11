package org.example;

import java.util.*;

public class LibraryApp {
    public static class Abonent {
        private final String lastName;
        private final String firstName;
        private String address;

        public Abonent(String lastName, String firstName, String address) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.address = address;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Abonent: " +
                    " lastName = " + getLastName() +
                    ", firstName = " + getFirstName() +
                    ", address = " + getAddress();
        }
        public static ArrayList<Abonent> sortAbonentsByLastName(HashMap<String, Abonent> abonents) {
            ArrayList<Abonent> sortedAbonents = new ArrayList<>(abonents.values());
            sortedAbonents.sort(Comparator.comparing(Abonent::getLastName));
            return sortedAbonents;
        }
        public static HashMap<String, Abonent> createInitialAbonents() {
            HashMap<String, Abonent> abonents = new HashMap<>();
            abonents.put("+380989846587", new Abonent("Maslov", "Oleksandr", "Lermovtova 6"));
            abonents.put("+380989566347", new Abonent("Lymarenko", "Artem", "Petropavliska 12"));
            abonents.put("+380661234567", new Abonent("Bikmaev", "Artem", "Parkova 1"));
            abonents.put("+380679876543", new Abonent("Mityaeva", "Alina", "Sonyachna 9"));
            abonents.put("+380682345678", new Abonent("Shevshenko", "Vlad", "Zarichna  35B"));
            return abonents;
        }

        public static void addAbonent(Scanner scanner, HashMap<String, Abonent> abonents) {
            System.out.println("Додавання нового абонента:");
            try {
                System.out.print("Семизначний номер телефону: ");
                String phoneNumber = scanner.nextLine();
                System.out.print("Прізвище: ");
                String lastName = scanner.nextLine();
                System.out.print("Ім'я: ");
                String firstName = scanner.nextLine();
                System.out.print("Адреса: ");
                String address = scanner.nextLine();

                abonents.put(phoneNumber, new Abonent(lastName, firstName, address));
                System.out.println("Абонент доданий успішно.");
            } catch (Exception e) {
                System.out.println("Помилка введення даних. Спробуйте ще раз.");
            }
        }

        public static void removeAbonent(String phoneNumber, HashMap<String, Abonent> abonents) {
            Abonent removedAbonent = abonents.remove(phoneNumber);
            if (removedAbonent != null) {
                System.out.println("Абонент з номером " + phoneNumber + " видалений.");
            } else {
                System.out.println("Абонент з номером " + phoneNumber + " не знайдений.");
            }
        }

        public static void searchAbonent(String searchNumber, HashMap<String, Abonent> abonents) {
            Abonent foundAbonent = abonents.get(searchNumber);
            if (foundAbonent != null) {
                System.out.println("Знайдений абонент з номером " + searchNumber + ": " + foundAbonent);
            } else {
                System.out.println("Абонент з номером " + searchNumber + " не знайдений.");
            }
        }

        public static void printSorted(ArrayList<Abonent> Abonents) {
            for (var temp : Abonents) {
                System.out.println(temp);
            }
        }
        public static void printAll(HashMap<String, Abonent> abonents) {
            for (Map.Entry<String, Abonent> entry : abonents.entrySet()) {
                String phoneNumber = entry.getKey();
                Abonent abonent = entry.getValue();
                System.out.println("Phone Number: " + phoneNumber+ " "+ abonent);
            }
        }

    }

    public static void main(String[] args) {
        HashMap<String, Abonent> abonents = Abonent.createInitialAbonents();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Додати нового абонента");
            System.out.println("2. Видалити абонента за номером телефону");
            System.out.println("3. Пошук абонента за номером телефону");
            System.out.println("4. Вивести відсортовані абоненти за прізвищем");
            System.out.println("5. Вивести інформацію про всіх абонентів");
            System.out.println("0. Вийти");

            System.out.print("Виберіть опцію: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистити буфер після введення числа

            switch (choice) {
                case 1 -> Abonent.addAbonent(scanner, abonents);
                case 2 -> {
                    System.out.print("Введіть номер абонента для видалення: ");
                    String phoneNumber = scanner.nextLine();
                    Abonent.removeAbonent(phoneNumber, abonents);
                }
                case 3 ->{
                    System.out.print("Введіть номер абонента для пошуку: ");
                    String searchNumber = scanner.nextLine();
                    Abonent.searchAbonent(searchNumber, abonents);
                }

                case 4 -> {
                    ArrayList<Abonent> sortedAbonents = Abonent.sortAbonentsByLastName(abonents);
                    Abonent.printSorted(sortedAbonents);
                }
                case 5 -> Abonent.printAll(abonents);
                case 0 -> {
                    System.out.println("Програма завершена.");
                    System.exit(0);
                }
                default -> System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }


}
