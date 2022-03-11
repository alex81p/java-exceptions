package com.example;

import com.example.exceptions.InvalidGradeException;
import com.example.university.*;

import java.util.Arrays;

public class UniversityCreator {

    public void createUniversity(University university) {
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
    }
}
