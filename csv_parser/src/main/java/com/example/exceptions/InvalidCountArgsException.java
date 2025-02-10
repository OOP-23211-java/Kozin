package com.example.exceptions;

public class InvalidCountArgsException extends RuntimeException {
    public InvalidCountArgsException(String message) {
        super(message);
    }
}
