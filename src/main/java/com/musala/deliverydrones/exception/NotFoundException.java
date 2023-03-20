package com.musala.deliverydrones.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.musala.deliverydrones.MessageConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotFoundException extends RuntimeException {

    private HttpStatus status = HttpStatus.NOT_FOUND;
    private String message = MessageConstants.ErrorMessages.NOT_FOUND_ERROR;
    private List<Error.ErrorDetail> errors = new ArrayList<>();

    public NotFoundException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Error.ErrorDetail> getErrors() {
        return errors;
    }
}
