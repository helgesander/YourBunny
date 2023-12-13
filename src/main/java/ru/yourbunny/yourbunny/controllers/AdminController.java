package ru.yourbunny.yourbunny.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping()
    public String getAdminPanel() {
        // TODO: fix admin panel
        return "admin panel 0_0";
    }
    @PostMapping("user")
    public ResponseEntity<String> addUser() {
        //TODO: fix add user
        return new ResponseEntity<String>(HttpStatusCode.valueOf(200));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable UUID userId) {
        //TODO: fix update user
        return new ResponseEntity<String>(HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
        //TODO: fix delete user from admin panel
        return new ResponseEntity<String>(HttpStatusCode.valueOf(200));
    }
}
