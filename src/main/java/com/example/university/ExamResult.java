package com.example.university;

import com.example.exceptions.InvalidGradeException;

import java.util.Objects;

public class ExamResult {
    private Subject subject;
    private int grade;

    public ExamResult() {
    }

    public ExamResult(Subject subject, int grade) throws InvalidGradeException {
        if (grade < 0 || grade > 10) {
            throw new InvalidGradeException("Invalid grade");
        }
        this.subject = subject;
        this.grade = grade;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) throws InvalidGradeException {
        if (grade < 0 || grade > 10) {
            throw new InvalidGradeException("Invalid grade");
        }
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamResult that = (ExamResult) o;
        return grade == that.grade && subject == that.subject;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, grade);
    }

    @Override
    public String toString() {
        return "ExamResult{" +
                "subject=" + subject +
                ", grade=" + grade +
                '}';
    }
}
