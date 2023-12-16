package ru.yourbunny.yourbunny.exceptions;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String email) {
        super(String.format("User with email '%s' does not exist", email));
    }
}
