package ru.kashtyra.alexandr.demo.common.exception;

import org.springframework.http.HttpStatus;

public class ApiFormatException extends ApiException{

    public ApiFormatException(HttpStatus errorHttpStatus, String errorMessage) {
        super(errorHttpStatus, errorMessage);
    }

    @Override
    public HttpStatus getErrorHttpStatus() {
        return super.getErrorHttpStatus();
    }

    @Override
    public String getMessageCode() {
        return super.getMessageCode();
    }
}
