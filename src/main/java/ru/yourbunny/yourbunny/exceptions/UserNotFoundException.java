package ru.yourbunny.yourbunny.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {
        super(String.format("User with id %s not found", id));
    }
    public UserNotFoundException(String username) {super(String.format("User with username '%s' not found", username));}
}
