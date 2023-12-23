package ru.yourbunny.yourbunny.services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.yourbunny.yourbunny.dtos.RegistrationDto;
import ru.yourbunny.yourbunny.dtos.UserFromAdminDto;
import ru.yourbunny.yourbunny.exceptions.ApplicationErrorResponse;
import ru.yourbunny.yourbunny.models.User;

import javax.management.relation.RoleNotFoundException;
import java.util.Arrays;

@Service
@Slf4j
public class AdminPanelService {
    @Autowired private CustomUserDetailsService userService;
    @Autowired private RoleService roleService;

    public ResponseEntity<?> createUserByAdmin(RegistrationDto registrationDto, String role, HttpServletRequest request) {
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword()))
            return new ResponseEntity<>(new ApplicationErrorResponse(HttpStatus.BAD_REQUEST.value(), "Passwords not match"), HttpStatus.BAD_REQUEST);
        User user = null;
        user = userService.createNewUser(registrationDto);
        log.info("Create user by admin with username '{}' and role {}", request.getRemoteAddr(), role);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), userService.getAuthorities(Arrays.asList(roleService.findByName("ROLE_" + role))));
        return ResponseEntity.ok(new UserFromAdminDto(user.getUserId(), user.getUsername(), user.getPassword()));
    }
}
