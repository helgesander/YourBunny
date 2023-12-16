package ru.yourbunny.yourbunny.advices;

import jakarta.validation.constraints.Email;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yourbunny.yourbunny.exceptions.EmailAlreadyExistException;
import ru.yourbunny.yourbunny.exceptions.UserAlreadyExistException;

@ControllerAdvice
public class UserCreationAdvice {
    @ResponseBody
    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String userAlreadyExistHandler(UserAlreadyExistException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmailAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String emailAlreadyExistHandler(EmailAlreadyExistException e) {
        return e.getMessage();
    }
}
