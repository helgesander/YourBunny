package ru.yourbunny.yourbunny.dtos;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class NewUserDto {
    private UUID id;
    private String username;
    private String email;
    private String token;
}
