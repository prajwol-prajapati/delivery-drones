package com.musala.deliverydrones.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private List<ErrorDetail> errors;

    private String message;

    private String code;

    public List<ErrorDetail> getErrors() {
        return errors;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorDetail {
        private String field;

        private String message;
    }
}
