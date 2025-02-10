package com.example.exceptions;

public class InvalidInputFileException extends RuntimeException {
    public InvalidInputFileException(String message) {
        super(message);
    }
}

