package com.helgesander.YourBunny.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @GetMapping("/registration")
    public String getReg() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser() {
        return null;
    }
}
