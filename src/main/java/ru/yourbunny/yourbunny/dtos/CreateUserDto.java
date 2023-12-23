package ru.yourbunny.yourbunny.dtos;

import lombok.Data;

@Data
public class CreateUserDto {
    private String username;
    private String password;
    private String email;
    private String phone;
}
