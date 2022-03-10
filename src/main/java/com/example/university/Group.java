package com.example.university;

import com.example.exceptions.GroupHasNoStudentsException;
import com.example.exceptions.StudentHasNoSubjectsException;

import java.util.*;
import java.util.stream.Stream;

public class Group implements IAverageGrade {
    private Long id;
    private List<Student> students;

    public Group(Long id) {
        this.id = id;
    }

    public Group(Long id, List<Student> students) {
        this.id = id;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Student> getStudents() throws GroupHasNoStudentsException {
        if (students == null || students.isEmpty()){
            throw new GroupHasNoStudentsException("Group has no students");
        }
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(students, group.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, students);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", students=" + students +
                '}';
    }

    @Override
    public OptionalDouble getAverageGrade() throws GroupHasNoStudentsException {
        return getStudents().stream()
                .flatMap(student -> {
                    try {
                        return student.getExamResults().stream();
                    } catch (StudentHasNoSubjectsException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .mapToDouble(ExamResult::getGrade)
                .average();
    }

    @Override
    public OptionalDouble getAverageGradeBySubject(Subject subject) throws GroupHasNoStudentsException, IllegalArgumentException {
        if (subject == null) {
            throw new IllegalArgumentException("Illegal argument");
        }
        return getStudents().stream()
                .flatMap(student -> {
                    try {
                        return student.getExamResults().stream();
                    } catch (StudentHasNoSubjectsException e) {
                        e.printStackTrace();
                        return Stream.empty();
                    }
                })
                .filter(examResult -> examResult.getSubject().equals(subject))
                .mapToDouble(ExamResult::getGrade)
                .average();
    }
}
