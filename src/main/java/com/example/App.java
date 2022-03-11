package com.example;

import com.example.services.Printer;
import com.example.university.*;

public class App {
    public static void main( String[] args ) {
        University university = new University();
        UniversityCreator universityCreator = new UniversityCreator();
        universityCreator.createUniversity(university);

        Long studentId = 1L;
        Long groupId = 101L;
        String facultyName = "Faculty of Economics";
        Subject subject = Subject.MATHEMATICS;

        System.out.println("Average academic performance of students:");
        System.out.println("1. individual:");
        university.getStudentById(studentId).ifPresentOrElse(student -> {
            System.out.print("\t- " + student.getFirstName() + " " + student.getLastName() + ": ");
            Printer.printAverageGrade(student);
            }, () -> System.out.println("\tThere is no student with this id."));

        System.out.println("2. by specific subject and group:");
        university.getGroupById(groupId).ifPresentOrElse(group -> {
            System.out.print("\t- " + subject + ", group #" + group.getId() + ": ");
            Printer.printAverageGradeBySubject(group,subject);
            }, () -> System.out.println("\tThere is no group with this id."));

        System.out.println("3. by specific subject and faculty:");
        university.getFacultyByName(facultyName).ifPresentOrElse(faculty -> {
            System.out.print("\t- " + subject + ", " + faculty.getName() + ": ");
            Printer.printAverageGradeBySubject(faculty,subject);
            }, () -> System.out.println("\tThere is no faculty with this name."));

        System.out.println("4. of all students, by specific subject:");
        System.out.print("\t- " + subject + ": ");
        Printer.printAverageGradeBySubject(university,subject);
    }
}
