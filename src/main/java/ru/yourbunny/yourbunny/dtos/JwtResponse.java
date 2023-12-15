package ru.yourbunny.yourbunny.dtos;

import lombok.Data;

@Data
public class JwtResponse {
    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
}
