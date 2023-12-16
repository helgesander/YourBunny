package ru.yourbunny.yourbunny.advices;

import jakarta.validation.constraints.Email;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import ru.yourbunny.yourbunny.exceptions.ApplicationException;
import ru.yourbunny.yourbunny.exceptions.EmailAlreadyExistException;
import ru.yourbunny.yourbunny.exceptions.UserAlreadyExistException;

@ControllerAdvice
public class UserCreationAdvice {
    @ResponseBody
    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApplicationException> userAlreadyExistExceptionHandler(UserAlreadyExistException e) {
        return new ResponseEntity<>(new ApplicationException(HttpStatus.CONFLICT.value(), e.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ResponseBody
    @ExceptionHandler(EmailAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApplicationException> emailAlreadyExistExceptionHandler(EmailAlreadyExistException e) {
        return new ResponseEntity<>(new ApplicationException(HttpStatus.CONFLICT.value(), e.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApplicationException> badCredentialsExceptionHandler(BadCredentialsException e) {
        return new ResponseEntity<>(new ApplicationException(HttpStatus.UNAUTHORIZED.value(), e.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }
}
