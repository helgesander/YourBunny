package ru.yourbunny.yourbunny.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yourbunny.yourbunny.dtos.ProfileDto;
import ru.yourbunny.yourbunny.dtos.UserDto;
import ru.yourbunny.yourbunny.models.Profile;
import ru.yourbunny.yourbunny.models.User;
import ru.yourbunny.yourbunny.repositories.RoleRepository;
import ru.yourbunny.yourbunny.services.CustomUserDetailsService;
import ru.yourbunny.yourbunny.services.ProfileService;
import ru.yourbunny.yourbunny.services.RoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@Tag(name = "Панель администратора", description = "Управление, контроль, настройка, управление доступом")
public class AdminPanelController {

    @Autowired
    private CustomUserDetailsService userService;
    @Autowired
    private ProfileService profileService;

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserDto> userDtos = new ArrayList();
        for (User usr : users) {
            userDtos.add(new UserDto(usr.getUserId(), usr.getUsername(), usr.getEmail(), usr.getPhone()));
        }
        return userDtos;
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID user_id) {
        User user = userService.findById(user_id);
        UserDto userDto = new UserDto(user.getUserId(), user.getUsername(), user.getEmail(), user.getPhone());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
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

    @GetMapping("/profiles")
    public List<ProfileDto> getAllProfiles() {
        List<Profile> profiles = profileService.findAll();
        List<ProfileDto> profileDtos = new ArrayList<>();
        for (Profile profile: profiles) {
            profileDtos.add(new ProfileDto(profile));
        }
        return profileDtos;
    }
}
