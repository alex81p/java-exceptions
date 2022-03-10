package com.example.university;

import com.example.exceptions.FacultyHasNoGroupsException;
import com.example.exceptions.GroupHasNoStudentsException;
import com.example.exceptions.StudentHasNoSubjectsException;

import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class Faculty implements IAverageGrade {
    private String name;
    private List<Group> groups;

    public Faculty() {
    }

    public Faculty(String name, List<Group> groups) {
        this.name = name;
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Group> getGroups() throws FacultyHasNoGroupsException {
        if (groups == null || groups.isEmpty()){
            throw new FacultyHasNoGroupsException("Faculty has no groups");
        }
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(name, faculty.name) && Objects.equals(groups, faculty.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, groups);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", groups=" + groups +
                '}';
    }

    @Override
    public OptionalDouble getAverageGrade() throws FacultyHasNoGroupsException {
        return getGroups().stream()
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
                })
                .mapToDouble(ExamResult::getGrade)
                .average();
    }

    @Override
    public OptionalDouble getAverageGradeBySubject(Subject subject) throws FacultyHasNoGroupsException, IllegalArgumentException {
        if (subject == null) {
            throw new IllegalArgumentException("Illegal argument");
        }
        return getGroups().stream()
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
                })
                .filter(examResult -> examResult.getSubject().equals(subject))
                .mapToDouble(ExamResult::getGrade)
                .average();
    }
}
