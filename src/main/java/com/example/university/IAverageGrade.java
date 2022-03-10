package com.example.university;

import java.util.OptionalDouble;

public interface IAverageGrade {
    OptionalDouble getAverageGrade() throws Exception;
    OptionalDouble getAverageGradeBySubject(Subject subject) throws Exception;
}
