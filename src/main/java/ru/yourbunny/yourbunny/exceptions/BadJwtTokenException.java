package ru.yourbunny.yourbunny.exceptions;

public class BadJwtTokenException extends RuntimeException {
    public BadJwtTokenException() {
        super("Error parsing JWT token");
    }
}
