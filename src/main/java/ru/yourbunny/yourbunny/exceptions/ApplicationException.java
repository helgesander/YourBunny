package ru.yourbunny.yourbunny.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApplicationException extends RuntimeException {
    private int status;
    private String message;
    private Date timestamp;

    public ApplicationException(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
