package ru.yourbunny.yourbunny.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String name) {
        super(String.format("Role with name '%s' not found", name));
    }
}
