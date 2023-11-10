package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class StudentManagementAppTest {
    @Test
    void testAddStudent() {
        ArrayList<StudentManagementApp.Student> students = new ArrayList<>();
        String input = "John Doe\nGroupD\n2001\n12\n25\n95,0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        StudentManagementApp.Student.addStudent(students, new Scanner(System.in));

        assertEquals(1, students.size());
        assertEquals("Name: John Doe, Group: GroupD, Birthdate: 2001-12-25, Average Grade: 95,00",
                students.get(0).toString());
    }

    @Test
    void testRemoveStudent() {
        ArrayList<StudentManagementApp.Student> students = new ArrayList<>();
        StudentManagementApp.Student.populateInitialStudents(students);

        String input = "0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        StudentManagementApp.Student.removeStudent(students, new Scanner(System.in));

        assertEquals(4, students.size());
        assertEquals("Name: Jane Smith, Group: GroupB, Birthdate: 1992-08-22, Average Grade: 82,00",
                students.get(0).toString());
    }

}