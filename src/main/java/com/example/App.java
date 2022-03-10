package com.example;

import com.example.exceptions.*;
import com.example.university.*;

import java.util.Arrays;

public class App {
    public static void main( String[] args ) {
        University university = new University();
        try {
            university.setFaculties(Arrays.asList(
                    new Faculty("Faculty of Economics", Arrays.asList(
                            new Group(101L, Arrays.asList(
                                    new Student(1L, "Ivan", "Ivanov", Arrays.asList(
                                            new ExamResult(Subject.ECONOMICS,8),
                                            new ExamResult(Subject.MATHEMATICS, 10)
                                    )),
                                    new Student(2L,"Peter", "Petrov", Arrays.asList(
                                            new ExamResult(Subject.ECONOMICS, 9),
                                            new ExamResult(Subject.MATHEMATICS,9)
                                    ))
                            )),
                            new Group(201L, Arrays.asList(
                                    new Student(3L, "Pavel","Pavlov", Arrays.asList(
                                            new ExamResult(Subject.ECONOMICS, 8),
                                            new ExamResult(Subject.MATHEMATICS,5)
                                    ))
                            ))
                    )),
                    new Faculty("Faculty of Physics",Arrays.asList(
                            new Group(202L, Arrays.asList(
                                    new Student(4L, "Mikhail","Mikhailov", Arrays.asList(
                                            new ExamResult(Subject.PHYSICS, 10),
                                            new ExamResult(Subject.MATHEMATICS,8)
                                    ))
                            ))
                    ))));
        } catch (InvalidGradeException e){
            e.printStackTrace();
            System.exit(1);
        }

        Long studentId = 1L;
        Long groupId = 101L;
        String facultyName = "Faculty of Economics";
        Subject subject = Subject.MATHEMATICS;

        System.out.println("Average academic performance of students:");
        System.out.println("1. individual:");
        university.getStudentById(studentId).ifPresentOrElse(student -> {
            try {
                System.out.print("\t- " + student.getFirstName() + " " + student.getLastName() + ": ");
                System.out.println(student.getAverageGrade().getAsDouble());
            } catch (StudentHasNoSubjectsException e){
                System.out.println(e.getMessage());
            }
            }, () -> System.out.println("\tThere is no student with this id."));

        System.out.println("2. by specific subject and group:");
        university.getGroupById(groupId).ifPresentOrElse(group -> {
            try {
                System.out.print("\t- " + subject + ", group #" + group.getId() + ": ");
                group.getAverageGradeBySubject(subject).ifPresentOrElse(System.out::println, ()-> System.out.println("-"));
            } catch (GroupHasNoStudentsException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }, () -> System.out.println("\tThere is no group with this id."));

        System.out.println("3. by specific subject and faculty:");
        university.getFacultyByName(facultyName).ifPresentOrElse(faculty -> {
            try {
                System.out.print("\t- " + subject + ", " + faculty.getName() + ": ");
                faculty.getAverageGradeBySubject(subject).ifPresentOrElse(System.out::println, ()-> System.out.println("-"));
            } catch (FacultyHasNoGroupsException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }, () -> System.out.println("\tThere is no faculty with this name."));

        System.out.println("4. of all students, by specific subject:");
        try {
            System.out.print("\t- " + subject + ": ");
            university.getAverageGradeBySubject(subject).ifPresentOrElse(System.out::println, ()-> System.out.println("-"));
        } catch (UniversityHasNoFacultiesException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
