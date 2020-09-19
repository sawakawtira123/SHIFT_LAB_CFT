package ru.kashtyra.alexandr.demo.common.exception;

import org.springframework.dao.DataAccessException;

public class AlreadyExistException extends DataAccessException {
    public AlreadyExistException(String msg) {
        super(msg);
    }

    public AlreadyExistException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
