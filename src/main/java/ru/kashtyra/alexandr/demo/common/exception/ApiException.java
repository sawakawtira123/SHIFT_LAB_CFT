package ru.kashtyra.alexandr.demo.common.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{

    private HttpStatus errorHttpStatus;

    private String messageCode;

    public ApiException(HttpStatus errorHttpStatus, String errorMessage) {
        super(errorMessage);
        this.errorHttpStatus = errorHttpStatus;
        this.messageCode = errorMessage;
    }

    public HttpStatus getErrorHttpStatus() {
        return errorHttpStatus;
    }

    public String getMessageCode() {
        return messageCode;
    }

}
