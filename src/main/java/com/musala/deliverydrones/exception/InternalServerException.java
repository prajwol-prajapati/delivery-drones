package com.musala.deliverydrones.exception;

import org.springframework.http.HttpStatus;

import com.musala.deliverydrones.MessageConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InternalServerException extends RuntimeException {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private String message = MessageConstants.ErrorMessages.INTERNAL_SERVER_ERROR;
}
