package com.example.exceptions;

public class FacultyHasNoGroupsException extends Exception{
    public FacultyHasNoGroupsException() {
    }

    public FacultyHasNoGroupsException(String message) {
        super(message);
    }

    public FacultyHasNoGroupsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FacultyHasNoGroupsException(Throwable cause) {
        super(cause);
    }
}
