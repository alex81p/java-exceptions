package com.example.university;

import com.example.exceptions.FacultyHasNoGroupsException;
import com.example.exceptions.GroupHasNoStudentsException;
import com.example.exceptions.StudentHasNoSubjectsException;
import com.example.exceptions.UniversityHasNoFacultiesException;

import java.util.OptionalDouble;

public abstract class UniversityEntity {
    public abstract OptionalDouble getAverageGrade() throws StudentHasNoSubjectsException,
            GroupHasNoStudentsException, FacultyHasNoGroupsException, UniversityHasNoFacultiesException;
    public abstract OptionalDouble getAverageGradeBySubject(Subject subject) throws StudentHasNoSubjectsException,
            GroupHasNoStudentsException, FacultyHasNoGroupsException, UniversityHasNoFacultiesException, IllegalArgumentException;
}
