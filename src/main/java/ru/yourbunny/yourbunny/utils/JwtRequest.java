package ru.yourbunny.yourbunny.utils;

import lombok.Data;

@Data
public class JwtRequest {
    private String login;
    private String password;
}
