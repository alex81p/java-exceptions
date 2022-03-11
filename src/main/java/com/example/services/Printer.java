package com.example.services;

import com.example.exceptions.FacultyHasNoGroupsException;
import com.example.exceptions.GroupHasNoStudentsException;
import com.example.exceptions.StudentHasNoSubjectsException;
import com.example.exceptions.UniversityHasNoFacultiesException;
import com.example.university.Subject;
import com.example.university.UniversityEntity;

public class Printer {
    public static <T extends UniversityEntity> void printAverageGrade(T universityEntity){
        try {
            universityEntity.getAverageGrade()
                    .ifPresentOrElse(System.out::println, ()-> System.out.println("-"));
        } catch (StudentHasNoSubjectsException | GroupHasNoStudentsException |
                FacultyHasNoGroupsException | UniversityHasNoFacultiesException e){
            System.out.println(e.getMessage());
        }
    }
    public static <T extends UniversityEntity> void printAverageGradeBySubject (T universityEntity, Subject subject) {
        try {
            universityEntity.getAverageGradeBySubject(subject)
                    .ifPresentOrElse(System.out::println, () -> System.out.println("-"));
        } catch (StudentHasNoSubjectsException | GroupHasNoStudentsException |
                FacultyHasNoGroupsException | UniversityHasNoFacultiesException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
