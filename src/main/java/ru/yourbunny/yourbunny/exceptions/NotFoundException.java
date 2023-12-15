package ru.yourbunny.yourbunny.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotFoundException extends RuntimeException {
    private final HttpStatus status = HttpStatus.NOT_FOUND;
    private String message;
    private Date timestamp;

    public NotFoundException(String message) {
        this.message = message;
        this.timestamp = new Date();
    }
}
