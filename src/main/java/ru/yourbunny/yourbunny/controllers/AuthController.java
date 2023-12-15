package ru.yourbunny.yourbunny.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourbunny.yourbunny.services.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final CustomUserDetailsService userService;

    @Autowired
    public AuthController(CustomUserDetailsService userService) {
        this.userService = userService;
    }
//    @PostMapping("/login")
//    public ResponseEntity<>
}
