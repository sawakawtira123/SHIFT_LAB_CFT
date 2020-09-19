package ru.kashtyra.alexandr.demo.common.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends DataAccessException {

    private final HttpStatus errorHttpStatus = HttpStatus.BAD_REQUEST;

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public HttpStatus getErrorHttpStatus() {
        return errorHttpStatus;
    }
}
