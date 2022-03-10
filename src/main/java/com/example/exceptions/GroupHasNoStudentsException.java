package com.example.exceptions;

public class GroupHasNoStudentsException extends Exception{
    public GroupHasNoStudentsException() {
    }

    public GroupHasNoStudentsException(String message) {
        super(message);
    }

    public GroupHasNoStudentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupHasNoStudentsException(Throwable cause) {
        super(cause);
    }
}
