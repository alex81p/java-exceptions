package com.example.exceptions;

public class UniversityHasNoFacultiesException extends Exception{
    public UniversityHasNoFacultiesException() {
    }

    public UniversityHasNoFacultiesException(String message) {
        super(message);
    }

    public UniversityHasNoFacultiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniversityHasNoFacultiesException(Throwable cause) {
        super(cause);
    }
}
