package ru.yourbunny.yourbunny.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.yourbunny.yourbunny.exceptions.ApplicationException;
import ru.yourbunny.yourbunny.exceptions.UserNotFoundException;

@ControllerAdvice
public class GetMethodAdvice {
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApplicationException> httpMessageReadableExceptionHandler(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(new ApplicationException(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApplicationException> userNotFoundExceptionHandler(UserNotFoundException e) {
        return new ResponseEntity<>(new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
