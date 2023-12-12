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
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping()
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID user_id) {
        Optional<User> user = userRepository.findById(user_id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/user_id")
    public ResponseEntity<String> deleteUser(@PathVariable UUID user_id) {
        if (userRepository.existsById(user_id)) {
            userRepository.deleteById(user_id);
            return ResponseEntity.ok("User deleted successfully");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
