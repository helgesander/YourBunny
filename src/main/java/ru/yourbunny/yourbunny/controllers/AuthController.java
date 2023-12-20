package ru.yourbunny.yourbunny.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourbunny.yourbunny.dtos.JwtRequest;
import ru.yourbunny.yourbunny.dtos.RegistrationDto;
import ru.yourbunny.yourbunny.services.AuthService;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "API авторизации", description = "Получение токена JWT при входе и решистрации на ресурсе")
public class AuthController {
    @Autowired
    private final AuthService authService;

    @PostMapping("/get-token")
    public ResponseEntity<?> createsAuthToken(@RequestBody JwtRequest authRequest, HttpServletRequest request) {
        return authService.createAuthToken(authRequest, request);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegistrationDto user, HttpServletRequest request) {
        return authService.createUser(user, request);
    }
}
