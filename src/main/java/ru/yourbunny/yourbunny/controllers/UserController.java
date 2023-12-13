package ru.yourbunny.yourbunny.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yourbunny.yourbunny.models.User;
import ru.yourbunny.yourbunny.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID user_id) {
        Optional<User> user = userRepository.findById(user_id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return ResponseEntity.ok("User deleted successfully");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
