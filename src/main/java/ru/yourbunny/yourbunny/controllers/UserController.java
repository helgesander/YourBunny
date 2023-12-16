package ru.yourbunny.yourbunny.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yourbunny.yourbunny.dtos.UserDto;
import ru.yourbunny.yourbunny.models.User;
import ru.yourbunny.yourbunny.repositories.UserRepository;
import ru.yourbunny.yourbunny.security.SiteUserDetails;
import ru.yourbunny.yourbunny.services.CustomUserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Tag(name = "API пользователей", description = "Взаимодейстивие с пользователями")
public class UserController {

    private CustomUserDetailsService userService;

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID user_id) {
        User user = userService.findById(user_id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
        if (userService.existsById(userId)) {
            userService.deleteById(userId);
            return ResponseEntity.ok("User deleted successfully");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> changeUserData(@RequestBody UserDto user, @PathVariable UUID userId) {
        //TODO: fix updating user information
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
