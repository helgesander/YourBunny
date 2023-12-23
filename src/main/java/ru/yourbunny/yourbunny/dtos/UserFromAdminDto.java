package ru.yourbunny.yourbunny.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class UserFromAdminDto {
    private UUID id;
    private String username;
    private String password;

    public UserFromAdminDto(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}