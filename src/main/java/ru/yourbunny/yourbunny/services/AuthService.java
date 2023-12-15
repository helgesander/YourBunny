package ru.yourbunny.yourbunny.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yourbunny.yourbunny.dtos.JwtRequest;
import ru.yourbunny.yourbunny.dtos.JwtResponse;
import ru.yourbunny.yourbunny.dtos.NewUserDto;
import ru.yourbunny.yourbunny.dtos.RegistrationDto;
import ru.yourbunny.yourbunny.exceptions.ApplicationException;
import ru.yourbunny.yourbunny.models.User;
import ru.yourbunny.yourbunny.utils.JwtTokenUtils;

@Service
@AllArgsConstructor
public class AuthService {
    private final CustomUserDetailsService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ApplicationException(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createUser(@RequestBody RegistrationDto registrationDto) {
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            return new ResponseEntity<>(new ApplicationException(HttpStatus.BAD_REQUEST.value(), "Passwords not match"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(registrationDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new ApplicationException(HttpStatus.BAD_REQUEST.value(), "User already exists szdfsadfsdfsfsdfsdfsdf"), HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        userService.createNewUser(registrationDto);
        String token = jwtTokenUtils.generateToken((UserDetails) user); // пофиксить
        return ResponseEntity.ok(new NewUserDto(user.getUserId(), user.getUsername(), user.getEmail(), token));
    }
}
