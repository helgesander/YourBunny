package ru.yourbunny.yourbunny.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import ru.yourbunny.yourbunny.exceptions.ApplicationErrorResponse;
import ru.yourbunny.yourbunny.exceptions.UserNotFoundException;

@RestControllerAdvice
public class GetMethodAdvice {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApplicationErrorResponse> httpMessageReadableExceptionHandler(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(new ApplicationErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApplicationErrorResponse> userNotFoundExceptionHandler(UserNotFoundException e) {
        return new ResponseEntity<>(new ApplicationErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
