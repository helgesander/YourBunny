package ru.yourbunny.yourbunny.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yourbunny.yourbunny.dtos.JwtRequest;
import ru.yourbunny.yourbunny.dtos.JwtResponse;
import ru.yourbunny.yourbunny.dtos.NewUserDto;
import ru.yourbunny.yourbunny.dtos.RegistrationDto;
import ru.yourbunny.yourbunny.exceptions.ApplicationErrorResponse;
import ru.yourbunny.yourbunny.models.User;
import ru.yourbunny.yourbunny.repositories.UserRepository;
import ru.yourbunny.yourbunny.utils.JwtTokenUtils;

import java.util.Arrays;

@Service
@Slf4j
@AllArgsConstructor
public class AuthService {
    private final CustomUserDetailsService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserRepository userRepo;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest, HttpServletRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        log.info("Login from {}", request.getRemoteAddr());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Transactional
    public ResponseEntity<?> createUser(@RequestBody RegistrationDto registrationDto, HttpServletRequest request) {
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword()))
            return new ResponseEntity<>(new ApplicationErrorResponse(HttpStatus.BAD_REQUEST.value(), "Passwords not match"), HttpStatus.BAD_REQUEST);
        User user = userService.createNewUser(registrationDto);
        log.info("Client from {}: Create user with username '{}'", request.getRemoteAddr(),registrationDto.getUsername());
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), userService.getAuthorities(Arrays.asList(roleService.findByName("ROLE_USER"))));
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new NewUserDto(user.getUserId(), user.getUsername(), user.getEmail(), token));
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);

        userRepo.save(user);

        return true;
    }
}
