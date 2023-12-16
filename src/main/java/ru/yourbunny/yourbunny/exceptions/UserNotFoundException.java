package ru.yourbunny.yourbunny.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID msg) {
        super(String.format("User with id %s not found", msg));
    }
}
