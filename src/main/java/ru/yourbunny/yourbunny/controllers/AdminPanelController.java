package ru.yourbunny.yourbunny.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yourbunny.yourbunny.dtos.UserDto;
import ru.yourbunny.yourbunny.models.User;
import ru.yourbunny.yourbunny.services.CustomUserDetailsService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@Tag(name = "Панель администратора", description = "Управление, контроль, настройка, управление доступом")
public class AdminPanelController {

    @Autowired
    private CustomUserDetailsService userService;

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID user_id) {
        User user = userService.findById(user_id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
        if (userService.existsById(userId)) {
            userService.deleteById(userId);
            return ResponseEntity.ok("User deleted successfully");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/users/{userId}")
    public ResponseEntity<?> changeUserData(@RequestBody UserDto user, @PathVariable UUID userId) {

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
