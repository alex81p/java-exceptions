package com.example.university;

import com.example.exceptions.FacultyHasNoGroupsException;
import com.example.exceptions.GroupHasNoStudentsException;
import com.example.exceptions.StudentHasNoSubjectsException;
import com.example.exceptions.UniversityHasNoFacultiesException;

import java.util.*;
import java.util.stream.Stream;

public class University extends UniversityEntity {
    private List<Faculty> faculties;

    public University() {
    }

    public University(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public List<Faculty> getFaculties() throws UniversityHasNoFacultiesException {
        if (faculties == null || faculties.isEmpty()){
            throw new UniversityHasNoFacultiesException("University has no faculties");
        }
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return Objects.equals(faculties, that.faculties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(faculties);
    }

    @Override
    public String toString() {
        return "University{" +
                "faculties=" + faculties +
                '}';
    }
    public Optional<Faculty> getFacultyByName (String name){
        try {
            return getFaculties().stream()
                    .filter(faculty -> faculty.getName().equals(name))
                    .findFirst();
        } catch (UniversityHasNoFacultiesException e) {
            return Optional.empty();
        }
    }

    public Optional<Group> getGroupById (Long id){
        try {
            return getFaculties().stream()
                    .flatMap(faculty -> {
                        try {
                            return faculty.getGroups().stream();
                        } catch (FacultyHasNoGroupsException e) {
                            return Stream.empty();
                        }
                    })
                    .filter(group -> group.getId().equals(id))
                    .findFirst();
        } catch (UniversityHasNoFacultiesException e) {
            return Optional.empty();
        }
    }

    public Optional<Student> getStudentById (Long id){
        try {
            return getFaculties().stream()
                    .flatMap(faculty -> {
                        try {
                            return faculty.getGroups().stream()
                                    .flatMap(group -> {
                                        try {
                                            return group.getStudents().stream();
                                        } catch (GroupHasNoStudentsException e) {
                                            return Stream.empty();
                                        }
                                    });
                        } catch (FacultyHasNoGroupsException e) {
                            return  Stream.empty();
                        }
                    })
                    .filter(student -> student.getId().equals(id))
                    .findFirst();
        } catch (UniversityHasNoFacultiesException e) {
            return Optional.empty();
        }
    }

    @Override
    public OptionalDouble getAverageGrade() throws UniversityHasNoFacultiesException {
        return getFaculties().stream().flatMap(faculty -> {
            try {
                return faculty.getGroups().stream()
                        .flatMap(group -> {
                            try {
                                return group.getStudents().stream()
                                        .flatMap(student -> {
                                            try {
                                                return student.getExamResults().stream();
                                            } catch (StudentHasNoSubjectsException e) {
                                                return Stream.empty();
                                            }
                                        });
                            } catch (GroupHasNoStudentsException e) {
                                return Stream.empty();
                            }
                        });
            } catch (FacultyHasNoGroupsException e){
                return Stream.empty();
            }
        })
                .mapToDouble(ExamResult::getGrade)
                .average();
    }

    @Override
    public OptionalDouble getAverageGradeBySubject(Subject subject) throws UniversityHasNoFacultiesException, IllegalArgumentException {
        if (subject == null) {
            throw new IllegalArgumentException("Illegal argument");
        }
        return getFaculties().stream().flatMap(faculty -> {
                    try {
                        return faculty.getGroups().stream()
                                .flatMap(group -> {
                                    try {
                                        return group.getStudents().stream()
                                                .flatMap(student -> {
                                                    try {
                                                        return student.getExamResults().stream();
                                                    } catch (StudentHasNoSubjectsException e) {
                                                        return Stream.empty();
                                                    }
                                                });
                                    } catch (GroupHasNoStudentsException e) {
                                        return Stream.empty();
                                    }
                                });
                    } catch (FacultyHasNoGroupsException e){
                        return Stream.empty();
                    }
                })
                .filter(examResult -> examResult.getSubject().equals(subject))
                .mapToDouble(ExamResult::getGrade)
                .average();
    }
}
