package ru.yourbunny.yourbunny.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import ru.yourbunny.yourbunny.exceptions.ApplicationErrorResponse;

@RestControllerAdvice
public class GlobalAdvice {
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ApplicationErrorResponse> httpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return new ResponseEntity<>(new ApplicationErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage()),
                HttpStatus.METHOD_NOT_ALLOWED);
    }
}
