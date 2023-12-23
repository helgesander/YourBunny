package ru.yourbunny.yourbunny.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yourbunny.yourbunny.dtos.JwtRequest;
import ru.yourbunny.yourbunny.dtos.RegistrationDto;
import ru.yourbunny.yourbunny.exceptions.ApplicationErrorResponse;
import ru.yourbunny.yourbunny.services.AuthService;

@RestController
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

    @GetMapping("/activate/{code}")
    public ResponseEntity<?> activate(@PathVariable String code) {
        boolean isActivated = authService.activateUser(code);

        if (!isActivated) {
            return new ResponseEntity<>(new ApplicationErrorResponse(HttpStatus.BAD_REQUEST.value(), "Не корректный код активации"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Пользователь успешно активирован");
    }
}
