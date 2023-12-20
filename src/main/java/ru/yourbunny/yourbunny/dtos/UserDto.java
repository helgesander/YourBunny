package ru.yourbunny.yourbunny.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Data
@AllArgsConstructor
@Getter
public class UserDto {
    UUID id;
    private String username;
//    private String password;
    private String email;
    private String phone;
}
