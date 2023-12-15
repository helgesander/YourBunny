package ru.yourbunny.yourbunny.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourbunny.yourbunny.dtos.JwtRequest;
import ru.yourbunny.yourbunny.dtos.JwtResponse;
import ru.yourbunny.yourbunny.dtos.RegistrationDto;
import ru.yourbunny.yourbunny.exceptions.ApplicationException;
import ru.yourbunny.yourbunny.security.SiteUserDetails;
import ru.yourbunny.yourbunny.services.AuthService;
import ru.yourbunny.yourbunny.services.CustomUserDetailsService;
import ru.yourbunny.yourbunny.utils.JwtTokenUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/get-token")
    public ResponseEntity<?> createsAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody RegistrationDto user) {
        return authService.createUser(user);
    }
}
