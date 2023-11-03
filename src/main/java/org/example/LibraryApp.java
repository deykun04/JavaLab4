package org.example;

import java.util.*;

public class LibraryApp {
    static class Abonent {
        private String lastName;
        private String firstName;
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
            return "Abonent{" +
                    "lastName='" + lastName + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        HashMap<String, Abonent> abonents = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть кількість абонентів:");
        int numberOfAbonents = scanner.nextInt();
        scanner.nextLine(); // Очистити буфер після введення числа
        // Додавання записів в HashMap з введеними даними
        for (int i = 1; i <= numberOfAbonents; i++) {
            System.out.println("Введіть дані для абонента #" + i);
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
            } catch (Exception e) {
                System.out.println("Помилка введення даних. Спробуйте ще раз.");
                i--; // Повторити введення для того самого абонента
                scanner.nextLine(); // Очистити буфер введення
            }
        }

        // Створення ArrayList для сортування за прізвищем
        ArrayList<Abonent> sortedAbonents = new ArrayList<>(abonents.values());
        sortedAbonents.sort(Comparator.comparing(Abonent::getLastName));

        // Виведення відсортованих абонентів
        System.out.println("Абоненти, відсортовані за прізвищем:");
        for (Abonent abonent : sortedAbonents) {
            System.out.println(abonent);
        }

        // Пошук та зміна абонента за номером
        System.out.print("Введіть номер абонента для пошуку та зміни: ");
        try {
            String searchNumber = scanner.nextLine();
            Abonent abonentToModify = abonents.get(searchNumber);
            if (abonentToModify != null) {
                System.out.println("Знайдений абонент з номером " + searchNumber + ": " + abonentToModify);

                // Модифікація абонента
                System.out.print("Введіть нову адресу: ");
                String newAddress = scanner.nextLine();
                abonentToModify.setAddress(newAddress);
                System.out.println("Абонент змінений: " + abonentToModify);
            } else {
                System.out.println("Абонент з номером " + searchNumber + " не знайдений.");
            }
        } catch (Exception e) {
            System.out.println("Помилка введення номера. Спробуйте ще раз.");
        }
    }
}
