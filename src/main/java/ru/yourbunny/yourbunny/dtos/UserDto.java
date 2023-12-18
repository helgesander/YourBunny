package ru.yourbunny.yourbunny.dtos;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String phone;
}
