package ru.yourbunny.yourbunny.exceptions;

import java.util.UUID;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(UUID uuid) {
        super(String.format("Profile with id %s not found", uuid));
    }
}
