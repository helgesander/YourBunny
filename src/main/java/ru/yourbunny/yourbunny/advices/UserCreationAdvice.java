package ru.yourbunny.yourbunny.advices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import ru.yourbunny.yourbunny.exceptions.ApplicationErrorResponse;
import ru.yourbunny.yourbunny.exceptions.EmailAlreadyExistException;
import ru.yourbunny.yourbunny.exceptions.UserAlreadyExistException;

@Slf4j
@RestControllerAdvice
public class UserCreationAdvice {
    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApplicationErrorResponse> userAlreadyExistExceptionHandler(UserAlreadyExistException e) {
        return new ResponseEntity<>(new ApplicationErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApplicationErrorResponse> emailAlreadyExistExceptionHandler(EmailAlreadyExistException e) {
        return new ResponseEntity<>(new ApplicationErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApplicationErrorResponse> badCredentialsExceptionHandler(BadCredentialsException e) {
        return new ResponseEntity<>(new ApplicationErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }
}
