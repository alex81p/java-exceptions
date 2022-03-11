package com.example.university;

import com.example.exceptions.StudentHasNoSubjectsException;

import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

public class Student extends UniversityEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private List<ExamResult> examResults;

    public Student() {
    }

    public Student(Long id, String firstName, String lastName, List<ExamResult> examResults) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.examResults = examResults;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ExamResult> getExamResults() throws StudentHasNoSubjectsException {
        if (examResults == null || examResults.isEmpty()){
            throw new StudentHasNoSubjectsException("Student has no subjects");
        }
        return examResults;
    }

    public void setExamResults(List<ExamResult> examResults) {
        this.examResults = examResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(examResults, student.examResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, examResults);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", examResults=" + examResults +
                '}';
    }

    @Override
    public OptionalDouble getAverageGrade() throws StudentHasNoSubjectsException {
        return getExamResults().stream()
                .mapToDouble(ExamResult::getGrade)
                .average();
    }

    @Override
    public OptionalDouble getAverageGradeBySubject(Subject subject) throws StudentHasNoSubjectsException, IllegalArgumentException {
        if (subject == null) {
            throw new IllegalArgumentException("Illegal argument");
        }
        return getExamResults().stream()
                    .filter(examResult -> examResult.getSubject().equals(subject))
                    .mapToDouble(ExamResult::getGrade)
                    .average();
    }

}
