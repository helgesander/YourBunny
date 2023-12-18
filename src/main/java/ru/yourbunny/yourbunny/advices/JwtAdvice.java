package ru.yourbunny.yourbunny.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.yourbunny.yourbunny.exceptions.ApplicationException;
import ru.yourbunny.yourbunny.exceptions.BadJwtTokenException;

@ControllerAdvice
public class JwtAdvice {
    @ResponseBody
    @ExceptionHandler(BadJwtTokenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApplicationException> malformedJwtExceptionHandler(BadJwtTokenException e) {
        return new ResponseEntity<>(new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
