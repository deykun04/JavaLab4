package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementApp {

    public static class BirthDate {
        private final int year;
        private final int month;
        private final int day;

        public BirthDate(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        @Override
        public String toString() {
            return String.format("%d-%02d-%02d", year, month, day);
        }
    }

    public static class Student {
        private final String fullName;
        private final String groupName;
        private final BirthDate birthDate;
        private final float averageGrade;

        public Student(String fullName, String groupName, BirthDate birthDate, float averageGrade) {
            this.fullName = fullName;
            this.groupName = groupName;
            this.birthDate = birthDate;
            this.averageGrade = averageGrade;
        }

        @Override
        public String toString() {
            return String.format("Name: %s, Group: %s, Birthdate: %s, Average Grade: %.2f",
                    fullName, groupName, birthDate, averageGrade);
        }
        static void viewStudents(ArrayList<Student> students) {
            System.out.println("\nStudents:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
        static void populateInitialStudents(ArrayList<Student> students) {
            students.add(new Student("John Doe", "GroupA", new BirthDate(1990, 5, 15), 75.5f));
            students.add(new Student("Jane Smith", "GroupB", new BirthDate(1992, 8, 22), 82.0f));
            students.add(new Student("Mark Johnson", "GroupA", new BirthDate(1989, 11, 10), 67.8f));
            students.add(new Student("Emily White", "GroupC", new BirthDate(1991, 3, 5), 91.2f));
            students.add(new Student("Alex Brown", "GroupB", new BirthDate(1993, 7, 18), 78.9f));
        }
        static void addStudent(ArrayList<Student> students, Scanner scanner) {
            System.out.println("\nEnter details for the new student:");
            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();

            System.out.print("Group Name: ");
            String groupName = scanner.nextLine();

            System.out.print("Birth Year: ");
            int birthYear = scanner.nextInt();

            System.out.print("Birth Month: ");
            int birthMonth = scanner.nextInt();

            System.out.print("Birth Day: ");
            int birthDay = scanner.nextInt();

            System.out.print("Average Grade: ");
            float averageGrade = scanner.nextFloat();
            scanner.nextLine(); // Consume the newline character after reading the float

            students.add(new Student(fullName, groupName, new BirthDate(birthYear, birthMonth, birthDay), averageGrade));
            System.out.println("Student added successfully!");
        }

        static void removeStudent(ArrayList<Student> students, Scanner scanner) {
            System.out.print("Enter the index of the student to remove: ");
            int index = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the int

            if (index >= 0 && index < students.size()) {
                students.remove(index);
                System.out.println("Student removed successfully!");
            } else {
                System.out.println("Invalid index. No student removed.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. View Students");
        System.out.println("2. Add Student");
        System.out.println("3. Remove Student");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Student.populateInitialStudents(students);

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the int

            switch (choice) {
                case 1 -> Student.viewStudents(students);
                case 2 -> Student.addStudent(students, scanner);
                case 3 -> Student.removeStudent(students, scanner);
                case 0 -> System.out.println("Exiting the program. Goodbye!");
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
